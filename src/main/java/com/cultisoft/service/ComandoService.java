package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Comando;
import com.cultisoft.repositories.ComandoJpaRepository;

@Service
public class ComandoService {

	@Autowired(required = true)
	@Qualifier(value = "ComandoJpaRepository")
	ComandoJpaRepository comandoJpaRepository;

	public List<Comando> mostrarTodo() {
		return comandoJpaRepository.findAll();
	}

	public Comando agregar(Comando comando) {
		return comandoJpaRepository.save(comando);
	}

	public Comando actualizar(Comando comando) {
		return comandoJpaRepository.save(comando);
	}

	public void borrar(Comando comando) {
		comandoJpaRepository.delete(comando);
	}

	public Comando buscar(String id) {
		return comandoJpaRepository.findById(Long.parseLong(id));
	}
}
