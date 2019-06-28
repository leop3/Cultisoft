package com.cultisoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.Request.LoginRequest;
import com.cultisoft.entities.Usuario;
import com.cultisoft.responses.ResponseUsuario;
import com.cultisoft.service.UsuarioService;
import com.cultisoft.utils.Mensajes;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioRestController {

	@Autowired(required = true)
	UsuarioService usuarioService;

	@PostMapping(path = "/login")
	public ResponseUsuario login(@RequestBody LoginRequest login) {
		ResponseUsuario response = new ResponseUsuario();
		try {
			Usuario us = usuarioService.findByUsuario(login.getUsuario());
			if (us != null) {
				if (us.getPassword().equals(login.getPassword())) {
					response.setMensaje(Mensajes.LOGIN);
					response.setUsuario(us);
				} else {
					response.setMensaje(Mensajes.INCORRECTO);
				}
			} else {
				response.setMensaje(Mensajes.INCORRECTO);
			}
		} catch (Exception e) {
			response.setMensaje(Mensajes.ERROR);
			response.setError(e + "");
		}
		return response;
	}
}
