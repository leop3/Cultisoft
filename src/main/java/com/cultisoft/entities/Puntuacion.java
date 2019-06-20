package com.cultisoft.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "puntuacion")
public class Puntuacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_guia")
	private Guia guia;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;

	private Integer puntuacion;

	public Puntuacion(Long id, Guia guia, Usuario usuario, Integer puntuacion) {
		super();
		this.id = id;
		this.guia = guia;
		this.usuario = usuario;
		this.puntuacion = puntuacion;
	}

	public Puntuacion() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Guia getGuia() {
		return guia;
	}

	public void setGuia(Guia guia) {
		this.guia = guia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	@Override
	public String toString() {
		return "Puntuacion [id=" + id + ", guia=" + guia + ", usuario=" + usuario + ", puntuacion=" + puntuacion + "]";
	}
}
