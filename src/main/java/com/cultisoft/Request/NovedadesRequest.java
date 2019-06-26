package com.cultisoft.Request;

import java.util.List;

import com.cultisoft.entities.Sensor;

public class NovedadesRequest {

	private List<Sensor> sensores;
	private String id;

	public NovedadesRequest() {
	}

	public NovedadesRequest(List<Sensor> sensores, String id) {
		super();
		this.sensores = sensores;
		this.id = id;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "NovedadesRequest [sensores=" + sensores + ", id=" + id + "]";
	}

}
