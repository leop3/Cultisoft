package com.cultisoft.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.Request.CultivoRequest;
import com.cultisoft.entities.Actuador;
import com.cultisoft.entities.Cultivo;
import com.cultisoft.entities.Sensor;
import com.cultisoft.entities.Usuario;
import com.cultisoft.responses.Response;
import com.cultisoft.responses.ResponseCultivo;
import com.cultisoft.service.ActuadorService;
import com.cultisoft.service.CultivoService;
import com.cultisoft.service.SensorService;
import com.cultisoft.service.UsuarioService;
import com.cultisoft.utils.Mensajes;

@RestController
@RequestMapping("/cultivo")
@CrossOrigin
public class CultivoRestController {

	@Autowired(required = true)
	CultivoService cultivoService;

	@Autowired(required = true)
	UsuarioService usuarioService;

	@Autowired(required = true)
	SensorService sensorService;

	@Autowired(required = true)
	ActuadorService actuadorService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseCultivo getById(@PathVariable("id") String id) {
		ResponseCultivo response = new ResponseCultivo();
		try {
			Cultivo culti = cultivoService.buscar(id);
			if (culti == null) {
				response.setMensaje(Mensajes.VACIO);
			} else if (culti.isEliminado()) {
				response.setMensaje(Mensajes.ELIMINADO);
			} else {
				response.getCultivos().add(culti);
				response.setMensaje(Mensajes.OK);
			}
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	@PostMapping(path = "/getCultivo")
	public ResponseCultivo getCultivo(@RequestBody String cultivoId) {
		ResponseCultivo response = new ResponseCultivo();
		try {
			Cultivo culti = cultivoService.buscar(cultivoId);
			if (culti.isEliminado()) {
				response.setMensaje(Mensajes.ELIMINADO);
			} else {
				response.getCultivos().add(culti);
				List<Cultivo> cultivos = sacarEliminados(response.getCultivos());
				response.setCultivos(cultivos);
				response.setMensaje(Mensajes.OK);
			}
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	@PostMapping(path = "/insertCultivo")
	public Response insertarCultivo(@RequestBody CultivoRequest cr) {
		Response response = new Response();
		try {
			Cultivo cultivo = new Cultivo(cr.getUser(usuarioService), cr.getClave(), cr.getNombre(),
					cr.getDescripcion());
			Cultivo nuevoCultivo = cultivoService.agregar(cultivo);
			guardarSensoresYActuadores(nuevoCultivo, cr.getSensores(), cr.getActuadores());
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	@PostMapping(path = "/getCultivos")
	public ResponseCultivo getCultivos(@RequestBody String idUsuario) {
		ResponseCultivo response = new ResponseCultivo();
		try {
			Usuario usuario = usuarioService.buscar(idUsuario);
			List<Cultivo> cultivos = sacarEliminados(cultivoService.getCultivosDeUsuario(usuario));
			response.setCultivos(cultivos);
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	private List<Cultivo> sacarEliminados(List<Cultivo> cults) {
		List<Cultivo> cultivosSinRepetidos = new ArrayList<>();
		for (Cultivo cult : cults) {
			if (!cult.isEliminado()) {
				cultivosSinRepetidos.add(cult);
				cult.setSensores(cult.getSensores().stream().filter(sensor -> !sensor.isEliminado())
						.collect(Collectors.toList()));
				cult.setActuadores(cult.getActuadores().stream().filter(actuador -> !actuador.isEliminado())
						.collect(Collectors.toList()));
			}
		}
		return cultivosSinRepetidos;
	}

	@PostMapping(path = "/modificarCultivo")
	public Response modificarCultivo(@RequestBody Cultivo cultivo) {
		Response response = new Response();
		try {
			Cultivo original = cultivoService.buscar(cultivo.getId().toString());
			original.setNombre(cultivo.getNombre());
			original.setDescripcion(cultivo.getDescripcion());
			original.setEliminado(cultivo.isEliminado());
			cultivoService.actualizar(original);
			guardarSensoresYActuadores(original, cultivo.getSensores(), cultivo.getActuadores());
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	private void guardarSensoresYActuadores(Cultivo cultivo, List<Sensor> sensores, List<Actuador> actuadores) {
		if (sensores != null) {
			for (Sensor sen : sensores) {
				if (sen.getId() == null) {
					sen.setCultivo(cultivo);
					sensorService.agregar(sen);
				} else {
					Sensor original = sensorService.buscar(sen.getId().toString());
					original.setDescripcion(sen.getDescripcion());
					original.setEliminado(sen.isEliminado());
					original.setTipo(sen.getTipo());
					original.setValorMaximo(sen.getValorMaximo());
					original.setValorMinimo(sen.getValorMinimo());
					sensorService.actualizar(original);
				}
			}
		}
		if (actuadores != null) {
			for (Actuador act : actuadores) {
				if (act.getId() == null) {
					act.setCultivo(cultivo);
					actuadorService.agregar(act);
				} else {
					Actuador original = actuadorService.buscar(act.getId().toString());
					original.setDescripcion(act.getDescripcion());
					original.setTipo(act.getTipo());
					original.setEliminado(act.isEliminado());
					actuadorService.actualizar(original);
				}
			}
		}
	}
}
