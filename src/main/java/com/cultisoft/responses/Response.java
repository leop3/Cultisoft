package com.cultisoft.responses;

public class Response {

	private String mensaje = "";

	private String error = "";

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Response [mensaje=" + mensaje + "]";
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
