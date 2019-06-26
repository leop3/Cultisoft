package com.cultisoft.Request;

import javax.persistence.Transient;

import com.cultisoft.entities.Usuario;
import com.cultisoft.service.UsuarioService;

public class UsuarioRequest extends Usuario {

	@Transient
	String id_usuario;

	public Usuario getUser(UsuarioService service) {
		return service.buscar(id_usuario);
	}
}
