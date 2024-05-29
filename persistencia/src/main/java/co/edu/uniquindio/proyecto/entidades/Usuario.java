package co.edu.uniquindio.proyecto.entidades;

import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable {

    @Column(nullable = false,length = 150)
    private String direccion;

    @Column(length = 20)
    private String telefono;


     //entidad propietaria entre usuario producto
    @ManyToMany
    @ToString.Exclude
     private List<Libro> librosFavoritos;

     @ToString.Exclude
     @OneToMany(mappedBy = "miUsuario")
     private List<Comentario> misComentarios;


     @ToString.Exclude
     @OneToMany(mappedBy = "miUsuario")
     private List<Compra> misCompras;


    public Usuario(String codigo, @Length(max = 150) String cedula, @Length(max = 150) String nombre, @Length(max = 150) String apellido, int edad, @Email String email, Ciudad miCiudad, Departamento miDepartamento, String username, String password, String direccion, String telefono) {
        super(codigo, cedula, nombre, apellido, edad, email, miCiudad, miDepartamento, username, password);
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
