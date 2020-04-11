package com.cultisoft.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "actuador")
public class Actuador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cultivo")
	@JsonIgnore
	private Cultivo cultivo;

	private String descripcion;

	private String tipo;

	private boolean estado;

	@Transient
	private Long idCultivo;

	@Column(columnDefinition = "Bool not null default false")
	private boolean eliminado;
	
	@OneToMany(mappedBy = "actuador")
	private List<Comando> comandos;

	public Actuador() {
		super();
	}

	public Actuador(Long id, Cultivo cultivo, String descripcion, String tipo, boolean estado) {
		super();
		this.id = id;
		this.cultivo = cultivo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Actuador(Long id, Long idCultivo, String descripcion, String tipo, boolean estado) {
		super();
		this.id = id;
		this.idCultivo = idCultivo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Long getIdCultivo() {
		return idCultivo;
	}

	public void setIdCultivo(Long idCultivo) {
		this.idCultivo = idCultivo;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	public List<Comando> getComandos() {
		return comandos;
	}

	public void setComandos(List<Comando> comandos) {
		this.comandos = comandos;
	}
	
	@Override
	public String toString() {
		return "Actuador [id=" + id + ", cultivo=" + cultivo + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", estado=" + estado + "]";
	}

}
