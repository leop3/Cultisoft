package com.cultisoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.Request.CultivoRequest;
import com.cultisoft.entities.Cultivo;
import com.cultisoft.responses.Response;
import com.cultisoft.responses.ResponseCultivo;
import com.cultisoft.service.CultivoService;
import com.cultisoft.service.UsuarioService;

@RestController
@RequestMapping("/cultivo")
public class CultivoRestController {

	@Autowired(required = true)
	CultivoService cultivoService;

	@Autowired(required = true)
	UsuarioService usuarioService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseCultivo getById(@PathVariable("id") String id) {
		ResponseCultivo response = new ResponseCultivo();
		try {
			Cultivo culti = cultivoService.buscar(id);
			response.getCultivos().add(culti);
			response.setMensaje("OK");
		} catch (Exception e) {
			response.setMensaje("Error");
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
			cultivoService.agregar(cultivo);
			response.setMensaje("Insertado");
		} catch (Exception e) {
			response.setMensaje("Error");
			response.setError(e + "");
		}
		return response;
	}

	@PostMapping(path = "/getCultivos")
	public ResponseCultivo getCultivos() {
		ResponseCultivo response = new ResponseCultivo();
		try {
			response.setCultivos(cultivoService.mostrarTodo());
			response.setMensaje("OK");
		} catch (Exception e) {
			response.setMensaje("Error");
			response.setError(e + "");
		}
		return response;
	}
}
