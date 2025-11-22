/* PAQUETE */
package com.zampa.app.servicios;

/* LIBRERIAS */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.java.Log;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Receta;
import com.zampa.app.repositorios.RecetaRepository;

@Log
@Service
public class ServiceImpl implements Servicio {
	@Autowired
	private RecetaRepository recetaRepository;

	@Override
	public Page<Receta> listadoRecetas(Pageable pageable) {
		log.info(String.format("Se han pedido productos de la página %d con tamaño %d", pageable.getPageNumber(), pageable.getPageSize()));
		return recetaRepository.findAll(pageable);
	}

	@Override
	public Receta detalleReceta(Long id) {
		return recetaRepository.findById(id).orElse(null);
	}

}
