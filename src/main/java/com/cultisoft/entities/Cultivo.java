package com.cultisoft.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cultivo")
public class Cultivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	@JsonIgnore
	private Usuario usuario;

	private String clave;

	private String nombre;

	private String descripcion;

	@OneToMany(mappedBy = "cultivo")
	private List<Actuador> actuadores;

	@OneToMany(mappedBy = "cultivo")
	private List<Sensor> sensores;

	private boolean eliminado;

	public Cultivo() {
	}

	public Cultivo(Long id, Usuario usuario, String clave, String nombre, String descripcion) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Cultivo(Usuario usuario, String clave, String nombre, String descripcion) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Actuador> getActuadores() {
		return actuadores;
	}

	public void setActuadores(List<Actuador> actuadores) {
		this.actuadores = actuadores;
	}

	public List<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(List<Sensor> sensores) {
		this.sensores = sensores;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Cultivo [id=" + id + ", usuario=" + usuario + ", clave=" + clave + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
}
