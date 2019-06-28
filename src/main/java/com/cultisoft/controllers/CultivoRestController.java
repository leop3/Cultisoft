package com.cultisoft.controllers;

import java.util.List;

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
			response.getCultivos().add(culti);
			response.setMensaje(Mensajes.OK);
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
			guardarSensoresYActuadores(cultivo);
			cultivoService.agregar(cultivo);
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
			List<Cultivo> cultivos = cultivoService.getCultivosDeUsuario(usuario);
			response.setCultivos(cultivos);
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	@PostMapping(path = "/modificarCultivo")
	public Response modificarCultivo(@RequestBody Cultivo cultivo) {
		Response response = new Response();
		try {
			guardarSensoresYActuadores(cultivo);
			cultivoService.actualizar(cultivo);
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}

	private void guardarSensoresYActuadores(Cultivo cultivo) {
		for (Sensor sen : cultivo.getSensores()) {
			if (sen.getId() == null) {
				sensorService.agregar(sen);
			} else {
				sensorService.actualizar(sen);
			}
		}
		for (Actuador act : cultivo.getActuadores()) {
			if (act.getId() == null) {
				actuadorService.agregar(act);
			} else {
				actuadorService.actualizar(act);
			}
		}
	}
}
