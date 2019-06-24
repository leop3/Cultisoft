package com.cultisoft.responses;

public class ResponseComando {

	public String estado;

	public ResponseComando() {
	}

	public ResponseComando(String estado) {
		super();
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ResponseComando [estado=" + estado + "]";
	}

}
