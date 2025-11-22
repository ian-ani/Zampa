/* PAQUETE */
package com.zampa.app.repositorios;

/* LIBRERIAS */
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/* LIBRERIAS INTERNAS */
import com.zampa.app.modelos.Dificultad;

@RepositoryRestResource(collectionResourceRel = "dificultades", path = "dificultades")
public interface DificultadRepository extends CrudRepository<Dificultad, Long>, PagingAndSortingRepository<Dificultad, Long>{
    
}
