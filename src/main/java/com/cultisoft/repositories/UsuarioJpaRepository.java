package com.cultisoft.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cultisoft.entities.Usuario;

@Repository("UsuarioJpaRepository")
public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {

	public abstract Usuario findByNombre(String nombre);

	public abstract Optional<Usuario> findById(Long id);
}
