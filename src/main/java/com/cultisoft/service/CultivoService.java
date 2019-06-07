package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Cultivo;
import com.cultisoft.repositories.CultivoJpaRepository;

@Service
public class CultivoService {

	@Autowired(required = true)
	@Qualifier(value = "CultivoJpaRepository")
	CultivoJpaRepository cultivoJpaRepository;

	public List<Cultivo> mostrarTodo() {
		return cultivoJpaRepository.findAll();
	}

	public Cultivo agregar(Cultivo cultivo) {
		return cultivoJpaRepository.save(cultivo);
	}

	public Cultivo actualizar(Cultivo cultivo) {
		return cultivoJpaRepository.save(cultivo);
	}

	public void borrar(Cultivo cultivo) {
		cultivoJpaRepository.delete(cultivo);
	}

	public Cultivo buscar(String id) {
		return cultivoJpaRepository.findById(Long.parseLong(id));
	}
}
