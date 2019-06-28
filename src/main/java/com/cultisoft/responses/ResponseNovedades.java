package com.cultisoft.responses;

import java.util.List;

import com.cultisoft.entities.Actuador;

public class ResponseNovedades extends Response {
	private List<Actuador> actuadores;

	public ResponseNovedades() {
	}

	public ResponseNovedades(List<Actuador> comandos) {
		super();
		this.actuadores = comandos;
	}

	public List<Actuador> getActuadores() {
		return actuadores;
	}

	public void setActuadores(List<Actuador> comandos) {
		this.actuadores = comandos;
	}

}
