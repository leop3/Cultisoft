package com.cultisoft.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "actuador")
public class Actuador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_cultivo")
	private Cultivo cultivo;

	private String descripcion;

	private String tipo;

	private boolean estado;

	private Date activacionDesde;

	private Date activacionHasta;

	public Actuador() {
		super();
	}

	public Actuador(Long id, Cultivo cultivo, String descripcion, String tipo, boolean estado, Date activacionDesde,
			Date activacionHasta) {
		super();
		this.id = id;
		this.cultivo = cultivo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.estado = estado;
		this.activacionDesde = activacionDesde;
		this.activacionHasta = activacionHasta;
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

	public Date getActivacionDesde() {
		return activacionDesde;
	}

	public void setActivacionDesde(Date activacionDesde) {
		this.activacionDesde = activacionDesde;
	}

	public Date getActivacionHasta() {
		return activacionHasta;
	}

	public void setActivacionHasta(Date activacionHasta) {
		this.activacionHasta = activacionHasta;
	}

	@Override
	public String toString() {
		return "Actuador [id=" + id + ", cultivo=" + cultivo + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", estado=" + estado + ", activacionDesde=" + activacionDesde + ", activacionHasta=" + activacionHasta
				+ "]";
	}

}
