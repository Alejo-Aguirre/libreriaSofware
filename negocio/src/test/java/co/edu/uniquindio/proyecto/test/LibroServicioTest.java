package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class LibroServicioTest {

    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

/**

    @Test
    public void registrarProducto(){
        LocalDate ld = LocalDate.now();
        Libro l = new Libro("2","misery","stephen king","libro de",400000,ld,5);
        try {
            libroServicio.registrarLibro(l);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void comentarProductoTest(){

        Usuario  u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");

        LocalDate ld = LocalDate.now();
        Libro libro = new Libro("2","misery","stephen king","libro de",400000,ld,5);


        try {
            usuarioServicio.registrarUsuario(u);
            libroServicio.registrarLibro(libro);
            libroServicio.comentarProducto("precio minimo",u , libro);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void guardarProductoEnFavoritosTest(){

        Usuario  u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");

        LocalDate ld = LocalDate.now();
        Libro l = new Libro("2","misery","stephen king","libro de",400000,ld,5);
        try {
            usuarioServicio.registrarUsuario(u);
            libroServicio.registrarLibro(l);

            libroServicio.guardarLibroEnFavoritos(l,u);
            //productoServicio.eliminarProductofavorito(p,u);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void buscarProductosPorNombreTest(){

        List<Libro> libros = libroServicio.buscarLibroPorNombre("lenovo",null);
        libros.forEach(p -> System.out.println(p));
    }


    @Test
    public void obtenerProductoTest(){

        try {
            LocalDate ld = LocalDate.now();
            Libro libro = new Libro("2","misery","stephen king","libro de",400000,ld,5);
            // Producto publicado =productoServicio.publicarProducto(producto);

            // Assertions.assertNotNull(publicado);
        } catch (Exception e) {
            // throw new RuntimeException(e);
            Assertions.assertTrue(false, e.getMessage());
        }


    }
**/
}
