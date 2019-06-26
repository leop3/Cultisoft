package com.cultisoft.responses;

import java.util.ArrayList;
import java.util.List;

import com.cultisoft.entities.Cultivo;

public class ResponseCultivo extends Response {

	List<Cultivo> cultivos = new ArrayList<>();

	public List<Cultivo> getCultivos() {
		return cultivos;
	}

	public void setCultivos(List<Cultivo> cultivos) {
		this.cultivos = cultivos;
	}

	@Override
	public String toString() {
		return "ResponseCultivo [cultivos=" + cultivos + "]";
	}

}
