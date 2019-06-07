package com.cultisoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cultivo")
public class Cultivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String descripcion;
	private String zonaHoraria;
	private String clave;

	public Cultivo() {
	}

	public Cultivo(Integer id, String nombre, String descripcion, String zonaHoraria, String clave) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.zonaHoraria = zonaHoraria;
		this.clave = clave;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getZonaHoraria() {
		return zonaHoraria;
	}

	public void setZonaHoraria(String zonaHoraria) {
		this.zonaHoraria = zonaHoraria;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return "Cultivo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", zonaHoraria="
				+ zonaHoraria + ", clave=" + clave + "]";
	}

}
