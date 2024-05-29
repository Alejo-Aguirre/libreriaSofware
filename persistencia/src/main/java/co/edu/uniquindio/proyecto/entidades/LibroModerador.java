package co.edu.uniquindio.proyecto.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class LibroModerador implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String motivo;

    @Column(nullable = false)
    private LocalDate fecha;


    @ManyToOne
    private Moderador miModerador;


    @ManyToOne
    private Libro miLibro;

    @ManyToOne
    private Estado miEstado;

}
