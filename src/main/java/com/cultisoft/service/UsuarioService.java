package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Usuario;
import com.cultisoft.repositories.UsuarioJpaRepository;

@Service
public class UsuarioService {

	@Autowired(required = true)
	@Qualifier(value = "UsuarioJpaRepository")
	UsuarioJpaRepository usuarioJpaRepository;

	public List<Usuario> mostrarTodo() {
		return usuarioJpaRepository.findAll();
	}

	public Usuario agregar(Usuario sensor) {
		return usuarioJpaRepository.save(sensor);
	}

	public Usuario actualizar(Usuario usuario) {
		return usuarioJpaRepository.save(usuario);
	}

	public void borrar(Usuario usuario) {
		usuarioJpaRepository.delete(usuario);
	}

	public Usuario buscar(String id) {
		return usuarioJpaRepository.findById(Long.parseLong(id));
	}
}
