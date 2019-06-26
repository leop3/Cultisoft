package com.cultisoft.responses;

import java.util.List;

import com.cultisoft.entities.Actuador;

public class ResponseNovedades extends Response {
	private List<Actuador> comandos;

	public ResponseNovedades() {
	}

	public ResponseNovedades(List<Actuador> comandos) {
		super();
		this.comandos = comandos;
	}

	public List<Actuador> getComandos() {
		return comandos;
	}

	public void setComandos(List<Actuador> comandos) {
		this.comandos = comandos;
	}

}
