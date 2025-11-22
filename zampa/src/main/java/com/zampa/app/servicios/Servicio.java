/* PAQUETE */
package com.zampa.app.servicios;

/* LIBRERIAS */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Dificultad;
import com.zampa.app.modelos.Receta;

public interface Servicio {
	Page<Receta> listadoRecetas(Pageable pageable);
	Page<Receta> listadoRecetas(Pageable pageable, Long idDificultad);
	Receta detalleReceta(Long id);
	
	Page<Dificultad> listadoDificultades(Pageable pageable);
	Dificultad detalleDificultad(Long idDificultad);
}
