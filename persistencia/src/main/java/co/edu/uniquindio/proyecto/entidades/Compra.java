package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
public class Compra implements Serializable {

    @Id
    @Column(length = 10)
    @EqualsAndHashCode.Include
    private String codigo;


    @Column(nullable = false,columnDefinition ="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate fechaCreacion;


    @Column(nullable = false)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Float valorTotal;

    @Enumerated(EnumType.STRING)
    private MedioDePago medioDePago;
    @ManyToOne
    private Usuario miUsuario;
    //entidad propietaria

    @OneToMany(mappedBy = "miCompra")
    private List<DetalleCompra> misDetalleCompras;


    public Compra(String codigo, LocalDate fechaCreacion, Float valorTotal, MedioDePago medioDePago, Usuario miUsuario) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.valorTotal = valorTotal;
        this.medioDePago = medioDePago;
        this.miUsuario = miUsuario;
    }
}




