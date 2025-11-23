/* PAQUETE */
package com.zampa.app.controladores;

/* LIBRERIAS */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Dificultad;
import com.zampa.app.modelos.Receta;
import com.zampa.app.repositorios.DificultadRepository;
import com.zampa.app.servicios.Servicio;

@Controller
public class IndexController {
	private static final int TAMANO_PAGINA = 10;
	
	@Autowired
	private Servicio servicio;
	
	@Autowired
	private DificultadRepository dificultadRepository;
	
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
	public String anadir(Model model, Receta receta) {
		model.addAttribute("receta", receta);
		
		List<Dificultad> dificultades = (List<Dificultad>) dificultadRepository.findAll(); 
		model.addAttribute("dificultades", dificultades);
		
		return "anadir";
	}
	
	@PostMapping("anadir")
	public String anadir(@Valid Receta receta, BindingResult bindingResult, @RequestParam("imagenForm") MultipartFile imagenForm) throws IOException {
		// Ruta de las imagenes guardadas y si no existe la carpeta, la crea
		Path rutaSubidas = Paths.get("uploads");
		Files.createDirectories(rutaSubidas);

		System.out.println(receta);
		
		if (bindingResult.hasErrors()) {
			System.err.println(bindingResult);
			
			return "anadir";
		}
		
		if (!imagenForm.isEmpty()) {
			// Validacion de tamaño (aprox. 1MB)
			if (imagenForm.getSize() > 1000000) {
				bindingResult.rejectValue("imagenForm", "error.imagenForm", "La imagen es mayor a 1MB.");
				
				return "anadir";
			}
			
			String nombreImagen = imagenForm.getOriginalFilename();
			
			// Guardar fisicamente
			Path rutaCompleta = rutaSubidas.resolve(nombreImagen);
			Files.copy(imagenForm.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Archivo guardado en: " + rutaCompleta.toAbsolutePath());
			
			// Guardar en bd
			receta.setImagen("/uploads/" + nombreImagen);
		}
		
		servicio.anadirReceta(receta);
		
		return "redirect:/";
	}
}
