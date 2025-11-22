/* PAQUETE */
package com.zampa.app.servicios;

/* LIBRERIAS */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Receta;

public interface Servicio {
	Page<Receta> listadoRecetas(Pageable pageable);

	Receta detalleReceta(Long id);
}
