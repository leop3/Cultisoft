package com.cultisoft.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.entities.Cultivo;
import com.cultisoft.service.CultivoService;

@RestController
@RequestMapping("/cultivo")
public class CultivoRestController {

	@Autowired(required = true)
	CultivoService cultivoService;

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cultivo getById(@PathVariable("id") String id) {
		Cultivo culti = cultivoService.buscar(id);
		return culti;
	}

	@PostMapping(path = "/insertCultivo")
	public void insertarCultivo(@RequestBody Cultivo culti) {
		cultivoService.agregar(culti);
	}
	
	@PostMapping(path = "/getCultivos")
	public List<Cultivo> getCultivos(){
		return cultivoService.mostrarTodo();
	}
}
