package com.cultisoft.controllers;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.Request.NovedadesRequest;
import com.cultisoft.entities.Actuador;
import com.cultisoft.entities.Comando;
import com.cultisoft.entities.Estado;
import com.cultisoft.entities.Sensor;
import com.cultisoft.responses.ResponseComando;
import com.cultisoft.responses.ResponseNovedades;
import com.cultisoft.service.ActuadorService;
import com.cultisoft.service.ComandoService;
import com.cultisoft.service.EstadoService;
import com.cultisoft.service.SensorService;
import com.cultisoft.utils.Mensajes;

@RestController
@RequestMapping("/comando")
@CrossOrigin
public class ComandoRestController {

	@Autowired(required = true)
	ComandoService comandoService;

	@Autowired(required = true)
	ActuadorService actuadorService;

	@Autowired(required = true)
	SensorService sensorService;

	@Autowired(required = true)
	EstadoService estadoService;

	@PostMapping(path = "/novedades")
	public ResponseNovedades getNovedades(@RequestBody NovedadesRequest nr) {
		ResponseNovedades response = new ResponseNovedades();
		try {
			this.guardarDatosSensores(nr.getSensores());

			// Buscar comandos del usuario
			List<Comando> comandos = this.getComandos(nr.getId());

			// Itera los comandos para ver si alguno se tiene que ejecutar
			for (Comando com : comandos) {
				if (this.puedeEjecutarse(com)) {
					Actuador act = actuadorService.buscar(com.getId_actuador().toString());
					switch (com.getTipo()) {
					case Mensajes.ON: {
						act.setEstado(true);
						break;
					}
					case Mensajes.OFF: {
						act.setEstado(false);
						break;
					}
					default:
						break;
					}
					// Si no tiene desde y hasta se finaliza a penas se ejecuta
					if (!this.tieneHorario(com))
						com.setConfirmacion(true);
					;
					actuadorService.actualizar(act);
				}

			}
			// Envio los comandos modificados (o no )
			response.setActuadores(this.getActuadores(nr.getId()));
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	/**
	 * Método para recibir los comandos enviados por los usuarios
	 * 
	 * @param command
	 * @return
	 */
	@PostMapping(path = "/enviarComando")
	public ResponseComando recibirComando(@RequestBody Comando command) {
		ResponseComando response = new ResponseComando();
		try {
			comandoService.agregar(command);
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	// Metodos Auxiliares

	/**
	 * Realiza La conexion a la base
	 */
	public Connection conectar() throws Exception {
		String databaseURL = "jdbc:mariadb://localhost:3306/cultisoft";
		Class.forName("org.mariadb.jdbc.Driver");
		return DriverManager.getConnection(databaseURL, "root", "root");
	}

	/**
	 * Obtiene los comandos del usuario
	 */
	public List<Comando> getComandos(String id) {
		List<Comando> commands = new ArrayList<>();
		try {
			Connection conn = this.conectar();
			CallableStatement cs = conn.prepareCall("{call getComandos(?)}");
			cs.setLong(1, Long.parseLong(id));
			cs.execute();
			final ResultSet rs = cs.getResultSet();
			while (rs.next()) {
				commands.add(new Comando(rs.getLong("id"), rs.getString("tipo"), rs.getDate("fechaHora"),
						rs.getDate("desde"), rs.getDate("hasta"), rs.getBoolean("confirmacion"),
						rs.getLong("idActuador")));
			}
			System.out.println(commands);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commands;
	}

	/**
	 * Obtiene los actuadores del usuario
	 */
	public List<Actuador> getActuadores(String id) {
		List<Actuador> acts = new ArrayList<>();
		try {
			Connection conn = this.conectar();
			CallableStatement cs = conn.prepareCall("{call getActuadores(?)}");
			cs.setLong(1, Long.parseLong(id));
			cs.execute();
			final ResultSet rs = cs.getResultSet();
			while (rs.next()) {
				acts.add(new Actuador(rs.getLong("id"), rs.getLong("id_cultivo"), rs.getString("descripcion"),
						rs.getString("tipo"), rs.getBoolean("estado")));

			}
			System.out.println(acts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acts;
	}

	/**
	 * Guarda los datos de los sensores para guardar un historico
	 * 
	 * @param sensores
	 */
	private void guardarDatosSensores(List<Sensor> sensores) {
		for (Sensor sen : sensores) {
			Sensor sen2 = sensorService.buscar(sen.getId().toString());
			List<Actuador> acts = sen.getCultivo().getActuadores();
			if (sen.getValor() >= sen2.getValorMaximo() || sen.getValor() <= sen2.getValorMinimo()) {
				activarODesactivarUmbral(sen2, acts, true);
			} else {
				activarODesactivarUmbral(sen2, acts, false);
			}
			estadoService.guardar(new Estado(sen, new Date(), sen.getValor()));
		}
	}

	private void activarODesactivarUmbral(Sensor sen2, List<Actuador> acts, boolean estado) {
		for (Actuador act : acts) {
			if (act.getTipo() == sen2.getTipo()) {
				act.setEstado(estado);
				Comando cmd = new Comando();
				cmd.setActuador(act);
				cmd.setFechaHora(new Date());
				cmd.setTipo(Mensajes.ON);
				comandoService.agregar(new Comando());
			}
		}
	}

	/**
	 * Comprueba si se puede ejecutar
	 * 
	 * @param com
	 * @return
	 */
	private boolean puedeEjecutarse(Comando com) {
		return (this.dentroDeSuHorario(com)) || (!this.tieneHorario(com));
	}

	private boolean dentroDeSuHorario(Comando com) {
		if (tieneHorario(com)) {
			return com.getDesde().before(new Date()) && com.getHasta().after(new Date());
		}
		return false;
	}

	private boolean tieneHorario(Comando com) {
		return com.getDesde() != null && com.getHasta() != null;
	}
}
