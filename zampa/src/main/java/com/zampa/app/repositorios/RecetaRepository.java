/* PAQUETE */
package com.zampa.app.repositorios;

/* LIBRERIAS */
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Receta;

@RepositoryRestResource(collectionResourceRel = "recetas", path = "recetas")
public interface RecetaRepository extends CrudRepository<Receta, Long>, PagingAndSortingRepository<Receta, Long>{
	Page<Receta> findByDificultadId(Pageable pageable, Long idDificultad);
}
