package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = NegocioApplication.class)

public class CategoriaServicioTest {
    @Autowired
    CategoriaServicio categoriaServicio;


    /**
    @Test
    public void registrarcategoria () {
        Categoria cat1= new Categoria("301","Terror");
        Categoria cat2= new Categoria("302","Misterio");
        Categoria cat3= new Categoria("303","juvenil");
        Categoria cat4= new Categoria("304","suspenso");

        try {
            categoriaServicio.registrarCategoria(cat1);
            categoriaServicio.registrarCategoria(cat2);
            categoriaServicio.registrarCategoria(cat3);
            categoriaServicio.registrarCategoria(cat4);

            // Verificar que las categorías se hayan registrado correctamente
            Categoria categoriaRegistrada1 = categoriaServicio.obtenerCategoria(cat1.getCodigo());
            Categoria categoriaRegistrada2 = categoriaServicio.obtenerCategoria(cat2.getCodigo());
            Categoria categoriaRegistrada3 = categoriaServicio.obtenerCategoria(cat3.getCodigo());
            Categoria categoriaRegistrada4 = categoriaServicio.obtenerCategoria(cat4.getCodigo());

            assertEquals(cat1, categoriaRegistrada1);
            assertEquals(cat2, categoriaRegistrada2);
            assertEquals(cat3, categoriaRegistrada3);
            assertEquals(cat4, categoriaRegistrada4);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void eliminarCategoriaTest() throws Exception {
        // Crear una categoría con algunos datos de ejemplo
        Categoria categoria = new Categoria("301", "VEHICULOS");

        // Registrar la categoría
        categoriaServicio.registrarCategoria(categoria);

        // Eliminar la categoría
        categoriaServicio.eliminarCategoria(categoria.getCodigo());

        // Intentar obtener la categoría eliminada
        Categoria categoriaEliminada = null;
        try {
            categoriaEliminada = categoriaServicio.obtenerCategoria(categoria.getCodigo());
        } catch (Exception e) {
            // Capturar la excepción si se lanza al intentar obtener la categoría eliminada
        }

        // Verificar que la categoría eliminada no se pueda encontrar
        Assertions.assertNull(categoriaEliminada);
    }


    @Test
    public void buscarCategoriaPorCodigoTest(){
        //registrarcategoria();
        try {
            Categoria categoria =categoriaServicio.obtenerCategoria("301");
            Assertions.assertNotNull(categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listarCategoriasTest() throws Exception {
        try {
            // Registrar algunas categorías
            Categoria categoria1 = new Categoria("101", "TECNOLOGIA");
            Categoria categoria2 = new Categoria("102", "HOGAR");
            categoriaServicio.registrarCategoria(categoria1);
            categoriaServicio.registrarCategoria(categoria2);

            // Listar las categorías
            List<Categoria> categorias = categoriaServicio.listarCategorias();

            // Imprimir las categorías
            categorias.forEach(c -> System.out.println(c.getCodigo() + " - " + c.getNombre()));
        } catch (Exception e) {
            // Manejar cualquier excepción lanzada durante la prueba
            Assertions.fail("La prueba ha fallado: " + e.getMessage());
        }
    }
    */
}
