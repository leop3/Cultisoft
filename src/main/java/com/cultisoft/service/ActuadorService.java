package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Actuador;
import com.cultisoft.repositories.ActuadorJpaRepository;

@Service
public class ActuadorService {

	@Autowired(required = true)
	@Qualifier(value = "ActuadorJpaRepository")
	ActuadorJpaRepository actuadorJpaRepository;

	public List<Actuador> mostrarTodo() {
		return actuadorJpaRepository.findAll();
	}

	public Actuador agregar(Actuador actuador) {
		return actuadorJpaRepository.save(actuador);
	}

	public Actuador actualizar(Actuador actuador) {
		return actuadorJpaRepository.save(actuador);
	}

	public void borrar(Actuador actuador) {
		actuadorJpaRepository.delete(actuador);
	}

	public Actuador buscar(String id) {
		return actuadorJpaRepository.findById(Long.parseLong(id));
	}
}
