/* PAQUETE */
package com.zampa.app.modelos;

/* LIBRERIAS */
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
    
    @ManyToOne
    @JoinColumn(name = "id_dificultad")
    private Dificultad dificultad;
    
    @Size(max = 255, message = "No puede tener un nombre mayor a 255 caracteres.")
    private String imagen;
    
    @NotBlank(message = "No puede estar vacío.")
    @Size(min = 1, max = 50, message = "Debe contener de 1 a 50 caracteres.")
    private String nombre;
    
    @Lob
    @Size(max = 5000, message = "No puede tener más de 5000 caracteres.")
    private String descripcion;
}
