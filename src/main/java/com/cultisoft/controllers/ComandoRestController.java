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
import com.cultisoft.entities.Cultivo;
import com.cultisoft.entities.Estado;
import com.cultisoft.entities.Sensor;
import com.cultisoft.responses.ResponseComando;
import com.cultisoft.responses.ResponseNovedades;
import com.cultisoft.service.ActuadorService;
import com.cultisoft.service.ComandoService;
import com.cultisoft.service.CultivoService;
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

	@Autowired(required = true)
	CultivoService cultivoService;

	@PostMapping(path = "/novedades")
	public ResponseNovedades getNovedades(@RequestBody NovedadesRequest nr) {
		ResponseNovedades response = new ResponseNovedades();
		try {
			this.guardarDatosSensores(nr.getSensores());

			Cultivo cultivo = cultivoService.buscar(nr.getId().toString());

			if (cultivo.getActuadores() != null) {
				for (Actuador actuador : cultivo.getActuadores()) {
					List<Comando> comandos = getComandos(actuador.getId().toString());

					// Itera los comandos para ver si alguno se tiene que ejecutar
					for (Comando com : comandos) {
						if (!puedeEjecutarseDesde(com)) {
							continue;
						}
						if (!com.isConfirmacion() && puedeEjecutarse(com)) {
							switch (com.getTipo()) {
							case Mensajes.ON:
								actuador.setEstado(true);
								break;
							case Mensajes.OFF:
								actuador.setEstado(false);
								break;
							}
							com.setConfirmacion(true);
							// Envio los comandos modificados (o no )
							actuadorService.actualizar(actuador);
						} else if (seTermina(com)) {
							actuador.setEstado(false);
							com.setConfirmacion(true);
							actuadorService.actualizar(actuador);
						}
						break;
					}
				}
				// Buscar comandos del usuario

				response.setActuadores(limpiarActuadoresParaSS(cultivo.getActuadores()));
			}

			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	private List<Actuador> limpiarActuadoresParaSS(List<Actuador> actuadores) {
		List<Actuador> nuevaLista = new ArrayList<Actuador>();
		for (Actuador actuador : actuadores) {
			Actuador nuevoActuador = new Actuador();
			nuevoActuador.setId(actuador.getId());
			nuevoActuador.setEstado(actuador.isEstado());
			nuevaLista.add(nuevoActuador);
		}
		return nuevaLista;
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
			Actuador actuador = actuadorService.buscar(command.getId_actuador().toString());
			command.setActuador(actuador);
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
			CallableStatement cs = conn.prepareCall("{call getComandosPorActuador(?)}");
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
			List<Actuador> acts = sen2.getCultivo().getActuadores();

			boolean enUmbral = true;
			if (sen2.getValorMaximo() != null && sen.getValor() >= sen2.getValorMaximo()) {
				enUmbral = false;
			}
			if (sen2.getValorMinimo() != null && sen.getValor() <= sen2.getValorMinimo()) {
				enUmbral = false;
			}

			if (sen2.getTipo() != null) {
				activarODesactivarUmbral(sen2, acts, enUmbral);
			}
			estadoService.guardar(new Estado(sen, new Date(), sen.getValor()));
		}
	}

	private void activarODesactivarUmbral(Sensor sen2, List<Actuador> acts, boolean enUmbral) {
		OUTER_LOOP: for (Actuador act : acts) {
			if (sen2.getTipo().equalsIgnoreCase(act.getTipo())) {
				List<Comando> comandos = getComandos(act.getId().toString());
				for (Comando comando : comandos) {
					if (!puedeEjecutarseDesde(comando)) {
						continue;
					}
					if (comando.getHasta() != null && dentroDeSuHorario(comando)) {
						continue OUTER_LOOP;
					}
					break;
				}
				if (act.isEstado() == enUmbral) {
					act.setEstado(!enUmbral);
					Comando cmd = new Comando();
					cmd.setActuador(act);
					cmd.setFechaHora(new Date());
					cmd.setTipo(enUmbral ? Mensajes.OFF : Mensajes.ON);
					comandoService.agregar(cmd);
				}
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
		return !this.tieneHorario(com) || this.dentroDeSuHorario(com);
	}

	private boolean puedeEjecutarseDesde(Comando com) {
		return com.getDesde() == null || new Date(com.getDesde().getTime()).before(new Date());
	}
	
	private boolean seTermina(Comando com) {
		return com.getHasta() != null && new Date(com.getHasta().getTime()).before(new Date());
	}

	private boolean dentroDeSuHorario(Comando com) {
		if (tieneHorario(com)) {
			return (new Date(com.getDesde().getTime()).before(new Date())
					&& new Date(com.getHasta().getTime()).after(new Date()));
		}
		return false;
	}

	private boolean tieneHorario(Comando com) {
		return com.getDesde() != null && com.getHasta() != null;
	}
}
