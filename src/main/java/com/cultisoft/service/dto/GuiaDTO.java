package com.cultisoft.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class GuiaDTO {

	private Long idUsuario;

	private String nombre;

	private String descripcion;

	private String tipo;

	private Double temperaturaMinima;

	private Double temperaturaMaxima;

	private Double humedadMinima;

	private Double humedadMaxima;

	private Double luzDesde;

	private Double luzHasta;

}
