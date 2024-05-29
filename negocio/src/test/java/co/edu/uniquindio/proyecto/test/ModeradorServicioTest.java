package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;


@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class ModeradorServicioTest {

    //inicializa el usuarioServicio
    @Autowired
    private ModeradorServicio moderadorServicio;

    /**
    @Test
    public void registrarModeradorTest(){
        Moderador m = new Moderador("905","22","Juan Pérez","juan@gmail.com","juan","juan123","123", Ciudad.Armenia, Departamento.Caldas);
        try {
            Moderador registrado = moderadorServicio.registrarModerador(m);
            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }


    @Test
    public void actualizarModeradorTest() throws Exception {
        // Crear un moderador existente en la base de datos
        Moderador m =  new Moderador("905","22","Juan Pérez","juan@gmail.com","juan","juan123","123", Ciudad.BARRANQUILLA, Departamento.ANTIOQUIA);
        Moderador registrado = moderadorServicio.registrarModerador(m);

        // Modificar los datos del moderador
        registrado.setNombre("Juan Modificado");
        registrado.setEmail("juan-modificado@gmail.com");

        try {
            Moderador actualizado = moderadorServicio.actualizarModerador(registrado); // Llamada al método de actualización de moderador
            Assertions.assertNotNull(actualizado);
            Assertions.assertEquals("Juan Modificado", actualizado.getNombre()); // Verificar que los datos se hayan actualizado correctamente
            Assertions.assertEquals("juan-modificado@gmail.com", actualizado.getEmail());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void eliminarModeradorTest(){
        Moderador m = new Moderador("905","22","Juan Pérez","juan@gmail.com","juan","juan123","123", Ciudad.BARRANQUILLA, Departamento.ANTIOQUIA);
        try {
            moderadorServicio.registrarModerador(m);
            moderadorServicio.eliminarModerador("904");
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listarModeradoresTest(){
        Moderador m =  new Moderador("905","22","Juan Pérez","juan@gmail.com","juan","juan123","123", Ciudad.BARRANQUILLA, Departamento.ANTIOQUIA);
        try {
            moderadorServicio.registrarModerador(m);
            List<Moderador> moderadores = moderadorServicio.listarModeradores();
            moderadores.forEach(md -> System.out.println(md));
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void inicioSesionTest(){
        Moderador m = new Moderador("905","22","Juan Pérez","juan@gmail.com","juan","juan123","123", Ciudad.BARRANQUILLA, Departamento.ANTIOQUIA);
        try {
            moderadorServicio.registrarModerador(m);
            moderadorServicio.iniciarSesion("juan@gmail.com","juan1234");
        } catch (Exception e) {
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void obtenerModeradorTest(){
        registrarModeradorTest();
        try {
            Moderador m = moderadorServicio.obtenerModerador("905");
            Assertions.assertNotNull(m);
        } catch (Exception e) {
            Assertions.assertTrue(false);
        }
    }

**/

}
