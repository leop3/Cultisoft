package com.cultisoft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "comando")
public class Comando {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_actuador")
	@JsonIgnore
	private Actuador actuador;

	private String tipo;

	private Date fechaHora;

	private Date desde;

	private Date hasta;

	private boolean confirmacion;

	@Transient
	private Long id_actuador;

	public Comando(Long id, String tipo, Date fechaHora, Date desde, Date hasta, boolean confirmacion,
			Long id_actuador) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fechaHora = fechaHora;
		this.desde = desde;
		this.hasta = hasta;
		this.confirmacion = confirmacion;
		this.id_actuador = id_actuador;
	}

	public Comando(Long id, String tipo, Date fechaHora, Date desde, Date hasta, boolean confirmacion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.fechaHora = fechaHora;
		this.desde = desde;
		this.hasta = hasta;
		this.confirmacion = confirmacion;
	}

}
