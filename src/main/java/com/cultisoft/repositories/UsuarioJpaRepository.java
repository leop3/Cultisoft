package com.cultisoft.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Usuario;

@Repository("UsuarioJpaRepository")
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Serializable> {

	public abstract Usuario findByNombre(String nombre);

	public abstract Usuario findById(Long id);
}
