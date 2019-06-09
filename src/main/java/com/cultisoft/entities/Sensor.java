package com.cultisoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sensor")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tipo;
	
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "id_cultivo")
	private Cultivo cultivo;

	public Sensor(Integer id, String tipo, String descripcion) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	public Sensor() {
		super();
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
