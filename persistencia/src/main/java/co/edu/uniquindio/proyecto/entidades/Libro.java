package co.edu.uniquindio.proyecto.entidades;


import co.edu.uniquindio.proyecto.entidades.enums.Editorial;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Libro implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(nullable = false,length = 100)
    private String nombre;

    @NotBlank(message = "El nombre del autor es obligatorio")
    @Column(nullable = false,length = 100)
    private String autor;

    @NotBlank
    @Column(nullable = false)
    private String descripcion;

    @Positive
    @Column(nullable = false)
    private float precio;

    @Column(nullable = false)
    private LocalDate fechaCreacion;

    @PositiveOrZero
    @Column(nullable = false)
    private Integer unidades;


    @ElementCollection
    private List<String> imagenes;

    @ToString.Exclude
    @ManyToOne
    private Categoria miCategoria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Editorial miEditorial;




    //datos que se necesitan para crear un producto

    public Libro(String codigo, String nombre, String autor, String descripcion, float precio, LocalDate fechaCreacion, Integer unidades) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.autor = autor;
        this.descripcion = descripcion;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
        this.unidades = unidades;
    }

    public String getImagenPrincipal(){
        if(imagenes != null && !imagenes.isEmpty()){
            return imagenes.get(0);
        }
        return  "default.png";
    }

    //entidad Propietaria es el producto porque para hacer un producto necesitamos una categoria

    @ToString.Exclude
    @OneToMany(mappedBy = "miLibro")
    private List<Comentario> miComentario;

    @ToString.Exclude
    @OneToMany(mappedBy = "miLibro")
    private List<DetalleCompra> misDetalleCompras;


    @ToString.Exclude
    @OneToMany(mappedBy = "miLibro")
    private List<DetalleDeLibro> misDetalleLibros;

    @ToString.Exclude
    @OneToMany(mappedBy = "miLibro")
    private List<LibroModerador> misLibrosModerador;


    //estos son los usuarios que tienen el producto en favoritos
    //entidad inversa entre usuario y producto
    @ToString.Exclude
    @ManyToMany(mappedBy = "librosFavoritos")
    private List<Usuario> usuariosFavoritos;

//se debe crear la relacion con el moderador

}
