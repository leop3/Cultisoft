package com.cultisoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.entities.Guia;
import com.cultisoft.entities.Usuario;
import com.cultisoft.responses.ResponseGuia;
import com.cultisoft.service.GuiaService;
import com.cultisoft.service.UsuarioService;
import com.cultisoft.utils.Mensajes;

@RestController
@RequestMapping("/comando")
@CrossOrigin
public class GuiaRestController {

	@Autowired(required = true)
	UsuarioService usuarioService;

	@Autowired(required = true)
	GuiaService guiaService;

	@PostMapping(path = "/getCultivos")
	public ResponseGuia getGuias(@RequestBody String idUsuario) {
		ResponseGuia response = new ResponseGuia();
		try {
			Usuario usuario = usuarioService.buscar(idUsuario);
//			List<Guia> guias= sacarEliminados(guiaService.getCultivosDeUsuario(usuario));
//			response.setCultivos(cultivos);
			response.setMensaje(Mensajes.OK);
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}
}
