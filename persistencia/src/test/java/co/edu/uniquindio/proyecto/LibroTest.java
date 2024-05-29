package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.LibroRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LibroTest {
    @Autowired
    private LibroRepo libroRepo;

    /**

    //------------------------------CONSULTAS----------------------------------------------------------
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarLibros(){


        List<Libro> misLibros = libroRepo.findAll();
        System.out.println(misLibros);
    }



    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosYComentarios(){


        List<Object[]> respuesta= libroRepo.listarUsuariosYLibros();

        for (Object[] objeto:respuesta){
            System.out.println(objeto[0]+"-----"+objeto[1]);
        }

    }



    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosValidos(){
        System.out.println(new Date()+"----"+"Date");
        System.out.println(LocalDateTime.now()+"----"+"LocalDateTime");

        //List<ProductoValido> respuesta= productoRepo.listarProductosValidos(LocalDateTime.now());
       // respuesta.forEach(u -> System.out.println(u));
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosConMasComentarios() {
        List<Object[]> resultado = libroRepo.listarLibrosConMasComentarios();
        for (Object[] row : resultado) {
            String productoId = (String) row[0];
            String nombreProducto = (String) row[1];
            Long cantidadComentarios = (Long) row[2];
            System.out.println("Producto con el codigo: " + productoId + " - Nombre: " + nombreProducto + " - Cantidad de Comentarios: " + cantidadComentarios);
        }
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarProductosMasAgregadosFavoritos() {
        List<Object[]> resultado = libroRepo.listarLibrosMasAgregadosFavoritos();
        Assertions.assertNotNull(resultado);
        Assertions.assertFalse(resultado.isEmpty());
        for (Object[] row : resultado) {
            String productoId = (String) row[0];
            String nombreProducto = (String) row[1];
            Long cantidadFavoritos = (Long) row[2];
            System.out.println("Producto con el código: " + productoId + " - Nombre: " + nombreProducto + " - Cantidad de Favoritos: " + cantidadFavoritos);
        }
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void guardarProductoEnFavoritosTest() {

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerUsuariosFavoritosPorCodigoTest(){
        List<Usuario> usuarios= libroRepo.obtenerUsuariosFavoritosPorCodigo("1");
        Assertions.assertEquals(1,usuarios.size());

    }

*/


}