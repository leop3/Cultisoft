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

	@Column(columnDefinition = "Bool not null default false")
	private boolean eliminado;

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

}
