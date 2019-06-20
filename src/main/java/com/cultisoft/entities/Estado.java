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
@Table(name = "estado")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_sensor")
	private Sensor sensor;

	private Date fechaHora;

	private Date desde;

	private Date hasta;

	private Integer valor;

	public Estado(Long id, Sensor sensor, Date fechaHora, Date desde, Date hasta, Integer valor) {
		super();
		this.id = id;
		this.sensor = sensor;
		this.fechaHora = fechaHora;
		this.desde = desde;
		this.hasta = hasta;
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

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", sensor=" + sensor + ", fechaHora=" + fechaHora + ", desde=" + desde + ", hasta="
				+ hasta + ", valor=" + valor + "]";
	}

}
