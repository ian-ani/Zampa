/* PAQUETE */
package com.zampa.app.modelos;

/* LIBRERIAS */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // El join
    //private Long fk_dificultad;
    
    // A lo mejor luego tiene que ser mas grande
    @Size(max = 255)
    private String imagen;
    
    @NotBlank
    @Size(max = 50)
    private String nombre;
    
    @Lob
    @Size(max = 5000)
    private String descripcion;
}
