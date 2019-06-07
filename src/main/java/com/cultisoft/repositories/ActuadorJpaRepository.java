package com.cultisoft.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Actuador;

@Repository("ActuadorJpaRepository")
public interface ActuadorJpaRepository extends JpaRepository<Actuador, Serializable> {

	public abstract Actuador findByDescripcion(String nombre);
    public abstract Actuador findById(Long id);
}
