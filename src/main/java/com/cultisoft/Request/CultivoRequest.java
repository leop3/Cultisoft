package com.cultisoft.Request;

import com.cultisoft.entities.Cultivo;
import com.cultisoft.entities.Usuario;
import com.cultisoft.service.UsuarioService;

public class CultivoRequest extends Cultivo {

//	@Transient
	private String id_usuario;

	public CultivoRequest() {
	}

	public CultivoRequest(String id_usuario) {
		super();
		this.id_usuario = id_usuario;
	}

	public Usuario getUser(UsuarioService service) {
		return service.buscar(id_usuario);
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

}
