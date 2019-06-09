package com.cultisoft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "variable")
public class Variable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String tipo;

	private Date desde;

	private Date hasta;

	private String umbral;

	@ManyToOne
	@JoinColumn(name = "id_sensor")
	private Sensor sensor;

	@ManyToOne
	@JoinColumn(name = "id_actuador")
	private Actuador actuador;

	public Variable(Integer id, String tipo, Date desde, Date hasta, String umbral, Sensor sensor, Actuador actuador) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.desde = desde;
		this.hasta = hasta;
		this.umbral = umbral;
		this.sensor = sensor;
		this.actuador = actuador;
	}

	public Variable() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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

	public String getUmbral() {
		return umbral;
	}

	public void setUmbral(String umbral) {
		this.umbral = umbral;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Actuador getActuador() {
		return actuador;
	}

	public void setActuador(Actuador actuador) {
		this.actuador = actuador;
	}

	@Override
	public String toString() {
		return "Variable [id=" + id + ", tipo=" + tipo + ", desde=" + desde + ", hasta=" + hasta + ", umbral=" + umbral
				+ ", sensor=" + sensor + ", actuador=" + actuador + "]";
	}

}
