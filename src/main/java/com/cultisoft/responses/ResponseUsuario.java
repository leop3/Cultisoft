package com.cultisoft.responses;

import com.cultisoft.entities.Usuario;

public class ResponseUsuario extends Response {
	Usuario usuario;

	public ResponseUsuario() {
	}

	public ResponseUsuario(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
