package com.cultisoft.controllers;

import java.util.ArrayList;

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

//	@PostMapping(path = "/getCultivos", headers = "Accept=application/json")
//	public Cultivo getCultivo() {
//		return new Cultivo(1, null, "clave", "nombre", "descripcion", new ArrayList());
//
//	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Cultivo getById(@PathVariable("id") String id) {
		Cultivo culti = cultivoService.buscar(id);
		return culti;//new Cultivo(1, null, clave, "nombre", "descripcion", new ArrayList());

	}

	@PostMapping(path = "/insertCultivo")
	public void insertarCultivo(@RequestBody Cultivo culti) {
		cultivoService.agregar(culti);
	}
}
