/* PAQUETES */
package com.zampa.app.controladores;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.zampa.app.servicios.Servicio;


@ControllerAdvice
public class Global {
	private static final int DIFICULTADES = 3;
	
	@Autowired
	private Servicio servicio;
	
	@ModelAttribute
	private void dificultades(Model modelo) {
		modelo.addAttribute("dificultades", servicio.listadoDificultades(Pageable.ofSize(DIFICULTADES)));
	}
}
