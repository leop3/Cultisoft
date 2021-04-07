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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	@Column(columnDefinition = "Bool not null default false")
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

}
