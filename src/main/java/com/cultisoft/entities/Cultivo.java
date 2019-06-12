package com.cultisoft.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cultivo")
public class Cultivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private String clave;

	private String nombre;

	private String descripcion;

	@ManyToMany
	@JoinTable(
			name = "cultivo_variable",
			joinColumns = @JoinColumn(name = "id_cultivo"),
			inverseJoinColumns = @JoinColumn(name = "id_variable"))
	private List<Variable> variables;

	public Cultivo() {
	}
	
	public Cultivo(Long id, Usuario usuario, String clave, String nombre, String descripcion,
			List<Variable> variables) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.variables = variables;
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

	@Override
	public String toString() {
		return "Cultivo [id=" + id + ", usuario=" + usuario + ", clave=" + clave + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + "]";
	}
}
