package com.cultisoft.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Cultivo;
import com.cultisoft.entities.Usuario;

@Repository("CultivoJpaRepository")
public interface CultivoJpaRepository extends JpaRepository<Cultivo, Serializable> {

	public abstract Cultivo findByNombre(String nombre);
    public abstract Cultivo findById(Long id);
    public abstract List<Cultivo> findByUsuario(Usuario us);
}
