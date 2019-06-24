package com.cultisoft.responses;

import com.cultisoft.entities.Comando;

public class ResponseNovedades {
	private Comando comando;
	private String novedades;

	public ResponseNovedades() {
	}

	public ResponseNovedades(Comando comando, String novedades) {
		super();
		this.comando = comando;
		this.novedades = novedades;
	}

	public boolean getNovedades() {
		return comando == null;
	}

	public Comando getComando() {
		return comando;
	}

	public void setComando(Comando comando) {
		this.comando = comando;
	}

	public void setNovedades(String novedades) {
		this.novedades = novedades;
	}

	@Override
	public String toString() {
		return "ResponseNovedades [comando=" + comando + ", novedades=" + novedades + "]";
	}

}
