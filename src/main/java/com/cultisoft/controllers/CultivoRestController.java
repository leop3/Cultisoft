package com.cultisoft.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cultisoft.entities.Cultivo;
import com.cultisoft.service.CultivoService;

@RestController
@RequestMapping("/cultivo")
public class CultivoRestController {

	@Autowired(required = true)
	CultivoService cultivoService;
	
    @PostMapping(value="/getCultivos", headers="Accept=application/json")
    public ModelMap mostrarTodosLosCultivos() {
        List<Cultivo> lista=cultivoService.mostrarTodo();
        ModelMap model = new ModelMap();
        model.put("contenido", lista);
        return model;

    }
}
