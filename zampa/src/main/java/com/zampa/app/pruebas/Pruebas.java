/* PAQUETE */
package com.zampa.app.pruebas;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Dificultad;
import com.zampa.app.modelos.Receta;
import com.zampa.app.repositorios.DificultadRepository;
import com.zampa.app.repositorios.RecetaRepository;
import com.zampa.app.servicios.Servicio;

@Component
public class Pruebas implements CommandLineRunner {
	private static final int TAMANO_PAGINA = 10;

	@Autowired
	private DificultadRepository dificultadRepository;

	@Autowired
	private RecetaRepository recetaRepository;

	@Autowired
	private Servicio servicio;

	@Override
	public void run(String... args) throws Exception {
		if (!dificultadRepository.findAll().iterator().hasNext()) {
			/* DIFICULTADES */
			Dificultad facil = dificultadRepository.save(Dificultad.builder().dificultad("Fácil").build());
			Dificultad normal = dificultadRepository.save(Dificultad.builder().dificultad("Normal").build());
			Dificultad dificil = dificultadRepository.save(Dificultad.builder().dificultad("Difícil").build());

			/* RECETAS */
			recetaRepository.save(Receta.builder().nombre("Bizcocho de limón").descripcion("Se cogen dos huevos...")
					.dificultad(facil).build());
			recetaRepository.save(
					Receta.builder().nombre("Paella").descripcion("Un vaso de arroz...").dificultad(dificil).build());
			recetaRepository.save(Receta.builder().nombre("Tortilla").descripcion("Se cogen dos huevos...")
					.dificultad(facil).build());
			recetaRepository
					.save(Receta.builder().nombre("Pollo").descripcion("KFC momento").dificultad(facil).build());
			recetaRepository.save(
					Receta.builder().nombre("Yogur de frambuesa").descripcion("Leche?").dificultad(normal).build());
			recetaRepository.save(Receta.builder().nombre("Sopa")
					.descripcion("Sopa de pollo patrocionada por Mercadona").dificultad(normal).build());

			System.out.println("Repositorio con findAll(): ");
			recetaRepository.findAll().forEach(System.out::println);

			var pageable = Pageable.ofSize(TAMANO_PAGINA);

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
}
