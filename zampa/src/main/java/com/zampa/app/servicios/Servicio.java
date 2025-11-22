/* PAQUETE */
package com.zampa.app.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/* LIBRERIAS */
import com.zampa.app.modelos.Receta;

public interface Servicio {
	Page<Receta> listadoRecetas(Pageable pageable);
}
