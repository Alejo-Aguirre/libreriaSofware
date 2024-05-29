package co.edu.uniquindio.proyecto.entidades;


import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.entidades.enums.Editorial;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@ToString
@AllArgsConstructor
public class Persona implements Serializable  {
    @Id
    @EqualsAndHashCode.Include
    @Column(length = 10)
    private String codigo;

    @Column(nullable = false,length = 100)
    @Length(max = 150)
    private String cedula;
    @Column(nullable = false,length = 100)
    @Length(max = 150)
    private String nombre;

    @Column(nullable = false,length = 100)
    @Length(max = 150)
    private String apellido;

    @Column(nullable = false)
    private int edad;  // Nuevo campo para la edad
    @Column(nullable = false,length = 150,unique = true)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Ciudad miCiudad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Departamento miDepartamento;

    @Column(nullable = false,length = 20,unique = true)
    private String username;

    @Column(nullable = false,length = 20)
    private String password;


}
