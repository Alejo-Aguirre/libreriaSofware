package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.MedioDePago;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.CompraServicio;
import co.edu.uniquindio.proyecto.servicios.DetalleCompraServicio;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Component
@ViewScoped
public class CompraBean {

    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @Autowired
    private LibroServicio libroServicio;

    @Getter @Setter
    private Libro libroSeleccionado;
    @Getter @Setter
    private int[] cantidadLibros;

    @Getter @Setter
    private Compra compra;

    @Getter @Setter
    private List<Libro> librosDisponibles; // Lista de libros disponibles para la selección

    @Getter @Setter
    private List<Libro> librosSeleccionados; // Lista de libros seleccionados por el usuario



    private int contadorDetalles = 1;
    @Getter @Setter
    private MedioDePago medioSeleccionado;

    private List<MedioDePago> mediosPago;


    public List<MedioDePago> getMediosPago() {
        return Arrays.asList(MedioDePago.values());
    }


    public void setMediosPago(List<MedioDePago> mediosPago) {
        this.mediosPago = mediosPago;
    }

    public void seleccionarLibro(Libro libro) {
        libroSeleccionado = libro;
    }
    @PostConstruct
    public void init() throws Exception {
        librosDisponibles = getLibrosDisponibles();
        librosSeleccionados = new ArrayList<>();
        cantidadLibros = new int[librosDisponibles.size()];
        compra = new Compra();

    }


        // Método para obtener la lista de libros disponibles (puedes implementar esto según tus necesidades)
        public List<Libro> getLibrosDisponibles() throws Exception {
            // Lógica para obtener los libros disponibles
            return libroServicio.listarTodosLibros();
        }


        // Método para crear una compra con los libros seleccionados
        public void crearCompraConLibros() {
            try {
                // Crear la compra


                //compra.setCodigo(codigo);
                compra.setMiUsuario(usuarioServicio.obtenerUsuario("001"));
                MedioDePago medioSeleccionado= getMedioSeleccionado();
                compra.setMedioDePago(medioSeleccionado);

                compra.setFechaCreacion(LocalDate.now());
                compra.setValorTotal(calcularValorTotal()); // Método para calcular el valor total de la compra
                compraServicio.registrarCompra(compra);

                // Crear el detalle de la compra para cada libro seleccionado
                for (Libro libro : librosSeleccionados) {
                    DetalleCompra detalleCompra = new DetalleCompra();
                    detalleCompra.setCodigo(compra.getCodigo()+"-"+ contadorDetalles); // Utilizando un contador
                    contadorDetalles++; // Incrementar el contador
                    detalleCompra.setMiCompra(compra);
                    detalleCompra.setUnidades(3); // Misma cantidad para todos los libros en esta compra
                    detalleCompra.setPrecioProducto(libro.getPrecio()); // Mismo precio para todos los libros en esta compra
                    detalleCompra.setMiLibro(libro);
                    detalleCompraServicio.registrarDetalleCompra(detalleCompra);
                }

                // Mostrar mensaje de éxito
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra creada exitosamente", null);
                FacesContext.getCurrentInstance().addMessage(null, msg);

            } catch (Exception e) {
                e.printStackTrace();

                // Mostrar mensaje de error
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la compra: " + e.getMessage(), null);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }

        // Método para calcular el valor total de la compra
        private float calcularValorTotal() {
            float valorTotal = 0;
            for (Libro libro : librosSeleccionados) {
                // Puedes ajustar esta lógica según la estructura de tu Libro y cómo obtienes el precio
                valorTotal += libro.getPrecio(); // Suponiendo que el Libro tiene un método getPrecio()
            }
            return valorTotal;
        }

}
