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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
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

}
