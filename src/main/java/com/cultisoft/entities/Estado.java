package com.cultisoft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_sensor")
	@JsonIgnore
	private Sensor sensor;

	private Date fechaHora;

	private Integer valor;

	public Estado() {

	}

	public Estado(Long id, Sensor sensor, Date fechaHora, Integer valor) {
		super();
		this.id = id;
		this.sensor = sensor;
		this.fechaHora = fechaHora;
		this.valor = valor;
	}

	public Estado(Sensor sensor, Date fechaHora, Integer valor) {
		super();
		this.sensor = sensor;
		this.fechaHora = fechaHora;
		this.valor = valor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", sensor=" + sensor + ", fechaHora=" + fechaHora + ", valor=" + valor + "]";
	}

}
