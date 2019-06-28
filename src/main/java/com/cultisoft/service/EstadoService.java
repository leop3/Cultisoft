package com.cultisoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Estado;
import com.cultisoft.repositories.EstadoJpaRepository;

@Service
public class EstadoService {

	@Autowired(required = true)
	@Qualifier(value = "EstadoJpaRepository")
	EstadoJpaRepository estadoJpaRepository;

	public void guardar(Estado state) {
		estadoJpaRepository.save(state);
	}
}
