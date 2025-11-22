/* PAQUETE */
package com.zampa.app.controladores;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/* LIBRERIAS INTERNAS */
import com.zampa.app.servicios.Servicio;

@Controller
public class IndexController {
	private static final int TAMANO_PAGINA = 5;
	
	@Autowired
	private Servicio servicio;
	
	@GetMapping
	public String index(Model modelo) {
		modelo.addAttribute("pagina", servicio.listadoRecetas(Pageable.ofSize(TAMANO_PAGINA)));
		
		return "index";
	}
	
	@GetMapping("receta")
	public String receta(Long id, Model modelo) {
		modelo.addAttribute("receta", servicio.detalleReceta(id));
		
		return "receta";
	}
}
