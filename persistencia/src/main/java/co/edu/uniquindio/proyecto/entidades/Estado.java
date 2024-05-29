package co.edu.uniquindio.proyecto.entidades;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class Estado implements Serializable {

    @Id
    private String codigo;


    @Column(nullable = false,length = 100)
    private String nombre;

    @OneToMany(mappedBy = "miEstado")
    private List<LibroModerador> misProductosModerador;


}
