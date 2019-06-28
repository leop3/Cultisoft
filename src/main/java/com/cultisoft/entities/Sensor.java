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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sensor")
public class Sensor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipo;

	private String descripcion;

	private Integer valorMinimo;

	private Integer valorMaximo;

	private boolean eliminado;

	@Transient
	private Integer valor;

	@ManyToOne
	@JoinColumn(name = "id_cultivo")
	@JsonIgnore
	private Cultivo cultivo;

	@OneToMany(mappedBy = "sensor")
	private List<Estado> estados;

	public Sensor(Long id, String tipo, String descripcion, Integer valorMinimo, Integer valorMaximo, Cultivo cultivo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.cultivo = cultivo;
	}

	public Sensor(Long id, String tipo, String descripcion, Integer valorMinimo, Integer valorMaximo, Integer valor,
			Cultivo cultivo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.valorMinimo = valorMinimo;
		this.valorMaximo = valorMaximo;
		this.valor = valor;
		this.cultivo = cultivo;
	}

	public Sensor() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Integer getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(Integer valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public Integer getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(Integer valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public Cultivo getCultivo() {
		return cultivo;
	}

	public void setCultivo(Cultivo cultivo) {
		this.cultivo = cultivo;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

}
