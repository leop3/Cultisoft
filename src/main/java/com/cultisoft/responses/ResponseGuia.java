package com.cultisoft.responses;

import java.util.List;

import com.cultisoft.entities.Guia;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGuia extends Response {

	@JsonInclude(Include.NON_NULL)
	List<Guia> guias;

	@JsonInclude(Include.NON_NULL)
	Guia guia;

	@Override
	public String toString() {
		return "ResponseGuia [guia=" + guia + "]";
	}

}
