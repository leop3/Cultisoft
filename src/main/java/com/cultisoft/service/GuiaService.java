package com.cultisoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.cultisoft.entities.Guia;
import com.cultisoft.repositories.GuiaJpaRepository;
import com.cultisoft.service.dto.GuiaDTO;

@Service
public class GuiaService {

	@Autowired(required = true)
	@Qualifier(value = "guiaJpaRepository")
	GuiaJpaRepository guiaJpaRepository;

	@Autowired
	ConversionService conversionService;

	public List<Guia> mostrarTodo() {
		return guiaJpaRepository.findAll();
	}

	public Guia agregar(GuiaDTO guiaDTO) {
		Guia guia = conversionService.convert(guiaDTO, Guia.class);
		return guiaJpaRepository.save(guia);
	}

	public Guia actualizar(Long id, GuiaDTO guiaDTO) {
		Guia guia = conversionService.convert(guiaDTO, Guia.class);
		guia.setId(id);
		return guiaJpaRepository.save(guia);
	}

	public void borrar(Long id) {
		Guia guia = guiaJpaRepository.findById(id).orElse(null);
		guiaJpaRepository.delete(guia);
	}

	public Guia buscarPorId(Long id) {
		return guiaJpaRepository.findById(id).orElse(null);
	}
}
