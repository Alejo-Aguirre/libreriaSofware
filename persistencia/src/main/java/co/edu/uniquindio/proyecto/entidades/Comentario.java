package co.edu.uniquindio.proyecto.entidades;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Comentario implements Serializable {
    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;


    @Column(nullable = false,columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCreacion;
    @Column(length = 150,nullable = false)
    private String mensaje;


    @ToString.Exclude
    @ManyToOne
    private Libro miLibro;
    //entidad propietaria

    @ToString.Exclude
    @ManyToOne
    private Usuario miUsuario;


    public Comentario(String codigo, String mensaje, LocalDate fechaCreacion) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
    }


}
