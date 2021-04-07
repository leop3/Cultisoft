package com.cultisoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.responses.ResponseGuia;
import com.cultisoft.service.GuiaService;
import com.cultisoft.service.UsuarioService;
import com.cultisoft.service.dto.GuiaDTO;

@RestController
@RequestMapping("/guia")
@CrossOrigin
public class GuiaRestController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	GuiaService guiaService;

	@GetMapping
	public ResponseEntity<ResponseGuia> getGuias() {

		ResponseGuia response = new ResponseGuia();

		response.setGuias(guiaService.mostrarTodo());

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseGuia> getGuiaById(@PathVariable Long id) {
		ResponseGuia response = new ResponseGuia();

		response.setGuia(guiaService.buscarPorId(id));

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<ResponseGuia> newGuia(@RequestBody GuiaDTO nuevaGuia) {
		ResponseGuia response = new ResponseGuia();

		guiaService.agregar(nuevaGuia);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseGuia> updateGuiaById(@PathVariable Long id, @RequestBody GuiaDTO guiaDTO) {
		ResponseGuia response = new ResponseGuia();

		guiaService.actualizar(id, guiaDTO);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseGuia> deleteGuiaById(@PathVariable Long id) {
		ResponseGuia response = new ResponseGuia();

		guiaService.borrar(id);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
