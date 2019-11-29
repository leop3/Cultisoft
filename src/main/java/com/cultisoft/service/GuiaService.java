package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Guia;
import com.cultisoft.repositories.GuiaJpaRepository;

@Service
public class GuiaService {

	@Autowired(required = true)
	@Qualifier(value = "guiaJpaRepository")
	GuiaJpaRepository guiaJpaRepository;

	public List<Guia> mostrarTodo() {
		return guiaJpaRepository.findAll();
	}

	public Guia agregar(Guia Guia) {
		return guiaJpaRepository.save(Guia);
	}

	public Guia actualizar(Guia Guia) {
		return guiaJpaRepository.save(Guia);
	}

	public void borrar(Guia Guia) {
		guiaJpaRepository.delete(Guia);
	}
}
