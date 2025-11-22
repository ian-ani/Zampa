/* PAQUETE */
package com.zampa.app.pruebas;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.zampa.app.modelos.Receta;
import com.zampa.app.repositorios.RecetaRepository;
import com.zampa.app.servicios.Servicio;

@Component
public class Pruebas implements CommandLineRunner {
	@Autowired
	private RecetaRepository recetaRepository;
	
	@Autowired
	private Servicio servicio;
	
	@Override
	public void run(String... args) throws Exception {
		recetaRepository.save(Receta.builder().nombre("Bizcocho de limón").build());	
		recetaRepository.save(Receta.builder().nombre("Paella").build());
		recetaRepository.save(Receta.builder().nombre("Tortilla").build());
		
		System.out.println("Repositorio con findAll(): ");
		recetaRepository.findAll().forEach(System.out::println);
		
		var pageable = Pageable.ofSize(2);
		
		Page<Receta> pagina;
		
		do {
			pagina = servicio.listadoRecetas(pageable);
			
			if (pagina.hasContent()) {
				System.out.println("Página: ");
				pagina.forEach(System.out::println);
				
				pageable = pagina.nextOrLastPageable();
			}
		} while (pagina.hasNext());
	}

}
