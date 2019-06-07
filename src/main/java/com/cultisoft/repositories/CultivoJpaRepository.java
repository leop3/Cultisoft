package com.cultisoft.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Cultivo;

@Repository("CultivoJpaRepository")
public interface CultivoJpaRepository extends JpaRepository<Cultivo, Serializable> {

	public abstract Cultivo findByNombre(String nombre);
    public abstract Cultivo findById(Long id);
}
