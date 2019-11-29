package com.cultisoft.responses;

import java.util.ArrayList;
import java.util.List;

import com.cultisoft.entities.Guia;

public class ResponseGuia extends Response {

	List<Guia> guia = new ArrayList<>();

	public List<Guia> getGuia() {
		return guia;
	}

	public void setGuia(List<Guia> guia) {
		this.guia = guia;
	}

	@Override
	public String toString() {
		return "ResponseGuia [guia=" + guia + "]";
	}

}
