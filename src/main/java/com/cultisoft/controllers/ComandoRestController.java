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
			Cultivo cultivo = cultivoService.buscar(nr.getId().toString());

			guardarDatosSensores(cultivo, nr.getSensores());

			if (cultivo.getActuadores() != null) {
				for (Actuador actuador : cultivo.getActuadores()) {
					List<Comando> comandos = getComandos(actuador.getId().toString());

					// Itera los comandos para ver si alguno se tiene que ejecutar
					Comando com = ultimoComando(comandos);
					if (com != null) {
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
							com.setActuador(actuador);

							System.out.println("comando");
							System.out.println(com.getId());
							System.out.println(actuador.getDescripcion());
							System.out.println(com.getTipo());

							// Envio los comandos modificados (o no )
							actuadorService.actualizar(actuador);
							comandoService.actualizar(com);
						}
					} else if (actuador.isEstado()) {
						actuador.setEstado(false);
						actuadorService.actualizar(actuador);
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

	private Comando ultimoComandoEjecutable(List<Comando> comandos) {
		Comando ultimo = ultimoComandoTemporal(comandos);

		// Itera los comandos para ver si alguno se tiene que ejecutar
		for (Comando comando : comandos) {
			if (puedeEjecutarse(comando)) {
				if (ultimo == null || comando.getDesde() != null) {
					return comando;
				} else if (comando.getFechaHora() != null
						&& new Date(comando.getFechaHora().getTime()).after(new Date(ultimo.getHasta().getTime()))) {
					return comando;
				}
			}
		}
		return null;
	}

	private Comando ultimoComando(List<Comando> comandos) {
		// Itera los comandos para ver si alguno se tiene que ejecutar
		for (Comando comando : comandos) {
			if (comando.getDesde() != null) {
				if (puedeEjecutarse(comando)) {
					return comando;
				} else {
					return ultimoComandoEjecutable(comandos);
				}
			}
		}
		return ultimoComandoEjecutable(comandos);
	}

	private Comando ultimoComandoTemporal(List<Comando> comandos) {
		// Itera los comandos para ver si alguno se tiene que ejecutar
		for (Comando comando : comandos) {
			if (comando.getDesde() != null) {
				if (puedeEjecutarseDesde(comando)) {
					return comando;
				} else {
					return null;
				}
			}
		}
		return null;
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
	private void guardarDatosSensores(Cultivo cultivo, List<Sensor> sensores) {

		for (Sensor sensor : cultivo.getSensores()) {
			if (sensor.isEliminado()) {
				continue;
			}
			for (Sensor sensorEstado : sensores) {
				if (sensor.getId() == sensorEstado.getId()) {
					boolean enUmbral = true;
					if (sensor.getValorMaximo() != null && sensorEstado.getValor() > sensor.getValorMaximo()) {
						enUmbral = false;
					}
					if (sensor.getValorMinimo() != null && sensorEstado.getValor() < sensor.getValorMinimo()) {
						enUmbral = false;
					}

					if (sensor.getTipo() != null) {
						activarODesactivarUmbral(sensor, cultivo.getActuadores(), enUmbral);
					}
					estadoService.guardar(new Estado(sensor, new Date(), sensorEstado.getValor()));
					break;
				}
			}
		}
	}

	private void activarODesactivarUmbral(Sensor sensor, List<Actuador> acts, boolean enUmbral) {
		for (Actuador act : acts) {
			if (!act.isEliminado() && sensor.getTipo().equalsIgnoreCase(act.getTipo())) {
				List<Comando> comandos = getComandos(act.getId().toString());

				Comando comando = ultimoComando(comandos);
				if (comando != null && comando.getHasta() != null) {
					continue;
				}

				if (act.isEstado() == enUmbral) {
					System.out.println(sensor.getDescripcion());
					System.out.println(act.getDescripcion());
					System.out.println(enUmbral ? Mensajes.OFF : Mensajes.ON);

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
