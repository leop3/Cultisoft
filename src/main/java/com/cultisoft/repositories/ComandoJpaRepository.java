package com.cultisoft.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Comando;

@Repository("ComandoJpaRepository")
public interface ComandoJpaRepository extends JpaRepository<Comando, Serializable> {

	public abstract Comando findByTipo(String nombre);

	public abstract Comando findById(Long id);
}
