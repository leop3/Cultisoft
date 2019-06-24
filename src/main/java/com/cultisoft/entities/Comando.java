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

@Entity
@Table(name = "comando")
public class Comando {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_actuador")
	private Actuador actuador;

	private String tipo;

	private Date fechaHora;

	private Date desde;

	private Date hasta;

	private boolean confirmacion;

	@Transient
	private Long id_actuador;

	public Comando() {
		super();
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isConfirmacion() {
		return this.confirmacion;
	}

	public void setConfirmacion(boolean confirmacion) {
		this.confirmacion = confirmacion;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Date getDesde() {
		return desde;
	}

	public void setDesde(Date desde) {
		this.desde = desde;
	}

	public Date getHasta() {
		return hasta;
	}

	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}

}
