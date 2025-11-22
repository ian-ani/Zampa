/* PAQUETE */
package com.zampa.app.controladores;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Dificultad;
import com.zampa.app.modelos.Receta;
import com.zampa.app.servicios.Servicio;

@Controller
public class IndexController {
	private static final int TAMANO_PAGINA = 10;
	
	@Autowired
	private Servicio servicio;
	
	@GetMapping
	public String index(Model modelo, @RequestParam(name = "id", required = false) Long idDificultad, 
			@RequestParam(name = "pagina", required = false, defaultValue = "0") Integer numPagina) {
		
		Page<Receta> pagina;
		Pageable pageable = Pageable.ofSize(TAMANO_PAGINA).withPage(numPagina);
		
		
		Dificultad dificultad;
		
		if (idDificultad == null) {
			pagina = servicio.listadoRecetas(pageable);			
			dificultad = Dificultad.builder().dificultad("Todas").build();
		} else {
			pagina = servicio.listadoRecetas(pageable, idDificultad);
			dificultad = servicio.detalleDificultad(idDificultad);
		}

		modelo.addAttribute("dificultad", dificultad);
		modelo.addAttribute("pagina", pagina);
		
		return "index";
	}
	
	@GetMapping("receta")
	public String receta(Long id, Model modelo) {
		modelo.addAttribute("receta", servicio.detalleReceta(id));
		
		return "receta";
	}
	
	@GetMapping("anadir")
	public String anadir() {
		return "anadir";
	}
}
