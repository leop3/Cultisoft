package com.cultisoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "guia")
public class Guia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private String nombre;

	private String descripcion;

	private String tipo;

	private Double temperaturaMinima;

	private Double temperaturaMaxima;

	private Double humedadMinima;

	private Double humedadMaxima;

	private Double luzDesde;

	private Double luzHasta;

	public Guia(Long id, Usuario usuario, String nombre, String descripcion, String tipo, Double temperaturaMinima,
			Double temperaturaMaxima, Double humedadMinima, Double humedadMaxima, Double luzDesde, Double luzHasta) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.temperaturaMinima = temperaturaMinima;
		this.temperaturaMaxima = temperaturaMaxima;
		this.humedadMinima = humedadMinima;
		this.humedadMaxima = humedadMaxima;
		this.luzDesde = luzDesde;
		this.luzHasta = luzHasta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(Double temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Double getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(Double temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public Double getHumedadMinima() {
		return humedadMinima;
	}

	public void setHumedadMinima(Double humedadMinima) {
		this.humedadMinima = humedadMinima;
	}

	public Double getHumedadMaxima() {
		return humedadMaxima;
	}

	public void setHumedadMaxima(Double humedadMaxima) {
		this.humedadMaxima = humedadMaxima;
	}

	public Double getLuzDesde() {
		return luzDesde;
	}

	public void setLuzDesde(Double luzDesde) {
		this.luzDesde = luzDesde;
	}

	public Double getLuzHasta() {
		return luzHasta;
	}

	public void setLuzHasta(Double luzHasta) {
		this.luzHasta = luzHasta;
	}

}
