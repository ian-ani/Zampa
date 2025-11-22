package com.zampa.app.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zampa.app.modelos.Receta;
import com.zampa.app.servicios.Servicio;

@RestController
@RequestMapping("/api/v2/recetas")
public class RecetaRestController {
	@Autowired
	private Servicio servicio;
	
	@GetMapping
	public Page<Receta> listadoRecetas(Pageable pageable) {
		return servicio.listadoRecetas(pageable);
	}
}
