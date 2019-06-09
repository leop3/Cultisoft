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
@Table(name = "guia")
public class Guia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private String nombre;

	private String descripcion;

	private String tipo;

	@ManyToMany
	@JoinTable(
			name = "guia_variable",
			joinColumns = @JoinColumn(name = "id_cultivo"),
			inverseJoinColumns = @JoinColumn(name = "id_variable"))
	List<Variable> variables;

	public Guia(Integer id, Usuario usuario, String nombre, String descripcion, String tipo, List<Variable> variables) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.variables = variables;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	@Override
	public String toString() {
		return "Guia [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", tipo=" + tipo + ", variables=" + variables + "]";
	}
}
