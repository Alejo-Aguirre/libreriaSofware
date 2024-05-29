package co.edu.uniquindio.proyecto;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest {

    @Autowired
    private CategoriaRepo categoriaRepo;

    //------
    // --------------------------------Crud---------------------------------------------------------------
    /**
    @Test
    public void crearCategoria() {
        Categoria categoria = new Categoria();
        categoria.setCodigo("306");
        categoria.setNombre("Tecnologia");

        categoriaRepo.save(categoria);

        Categoria categoriaGuardada = categoriaRepo.findById("306").orElse(null);
        Assertions.assertNotNull(categoriaGuardada);
        Assertions.assertEquals("Tecnologia", categoriaGuardada.getNombre());
    }

    @Test
    public void actualizarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setCodigo("306");
        categoria.setNombre("Tecnologia");

        categoriaRepo.save(categoria);

        Categoria categoriaActualizada = categoriaRepo.findById("306").orElse(null);
        Assertions.assertNotNull(categoriaActualizada);

        categoriaActualizada.setNombre("Tecnologia Actualizados");
        categoriaRepo.save(categoriaActualizada);

        Categoria categoriaObtenida = categoriaRepo.findById("306").orElse(null);
        Assertions.assertNotNull(categoriaObtenida);
        Assertions.assertEquals("Tecnologia Actualizados", categoriaObtenida.getNombre());
    }

    @Test
    public void eliminarCategoria() {
        Categoria categoria = new Categoria();
        categoria.setCodigo("306");
        categoria.setNombre("Tecnologia");

        categoriaRepo.save(categoria);

        Categoria categoriaEliminada = categoriaRepo.findById("306").orElse(null);
        Assertions.assertNotNull(categoriaEliminada);

        categoriaRepo.deleteById("306");

        Categoria categoriaObtenida = categoriaRepo.findById("306").orElse(null);
        Assertions.assertNull(categoriaObtenida);
    }

    //------------------------------------ CONSULTAS-------------------------------------------------------------

    /**
    @Test
    @Sl("classpath:usuarios.sql")
    public void listarCategorias(){
        List<Categoria> categorias = categoriaRepo.findAll();
        categorias.forEach(c -> System.out.println(c));

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerCategoriasOrdenadas(){

        String campoOrdenacion = "nombre"; // Campo por el cual ordenar las categorías
        List<Categoria> categorias = categoriaRepo.findAll(Sort.by(campoOrdenacion));
        // Verificar si las categorías están ordenadas por el campo especificado
        for (int i = 0; i < categorias.size() - 1; i++) {
            String valorActual = categorias.get(i).getNombre();
            String valorSiguiente = categorias.get(i + 1).getNombre();
            Assertions.assertTrue(valorActual.compareToIgnoreCase(valorSiguiente) <= 0);
            System.out.println(categorias);
        }

    }


 **/


}
