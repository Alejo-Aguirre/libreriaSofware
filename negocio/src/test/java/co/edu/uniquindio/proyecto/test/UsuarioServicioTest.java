package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//se define la clase que se va a probar
//este sirve para que las pruebas que ajecutemos aqui no afecten a la base de datos real
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {
    //inicializa el usuarioServicio
    @Autowired
    private UsuarioServicio usuarioServicio;

    /**
    @Test
    public void registrarusuarioTest(){
        Usuario u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");
        try {
            Usuario registrado = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(registrado);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void actualizarUsuarioTest() throws Exception {
        // Crear un usuario existente en la base de datos
        Usuario u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");
        Usuario registrado = usuarioServicio.registrarUsuario(u);

        // Modificar los datos del usuario
        registrado.setNombre("Mario Modificado");
        registrado.setTelefono("9876543210");

        try {
            Usuario actualizado = usuarioServicio.actualizarUsuario(registrado); // Llamada al método de actualización de usuario
            Assertions.assertNotNull(actualizado);
            Assertions.assertEquals("Mario Modificado", actualizado.getNombre()); // Verificar que los datos se hayan actualizado correctamente
            Assertions.assertEquals("9876543210", actualizado.getTelefono());
        } catch (Exception e) {
            Assertions.fail("Se lanzó una excepción al actualizar el usuario: " + e.getMessage());
        }
    }


    @Test
    public void eliminarUsuarioTest() throws Exception {
        // Crear un objeto Usuario con algunos datos de ejemplo
        Usuario usuario = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");

        // Registrar el usuario
        usuarioServicio.registrarUsuario(usuario);

        // Eliminar el usuario
        usuarioServicio.eliminarUsuario(usuario.getCodigo());

        // Intentar obtener el usuario eliminado
        Usuario usuarioEliminado = null;
        try {
            usuarioEliminado = usuarioServicio.obtenerUsuario(usuario.getCodigo());
        } catch (Exception e) {
            // Capturar la excepción si se lanza al intentar obtener el usuario eliminado
        }

        // Verificar que el usuario eliminado no se pueda encontrar
        Assertions.assertNull(usuarioEliminado);
    }


    @Test
    public void listarUsuariosTest(){
        Usuario u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");
        try {
            usuarioServicio.registrarUsuario(u);
            List<Usuario> usuarios =usuarioServicio.listarUsuarios();
            usuarios.forEach(us -> System.out.println(us));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            //sirve para que no salga la excepcion y simplemente la prueba se muestre como no aprobada
            Assertions.assertTrue(false);
        }
    }


    @Test
    public void inicioSesionTest(){
        Usuario u = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");
        try {
            usuarioServicio.registrarUsuario(u);
            usuarioServicio.iniciarSesion("unimario","Heropro.12");


        } catch (Exception e) {
            //throw new RuntimeException(e);
            //sirve para que no salga la excepcion y simplemente la prueba se muestre como no aprobada
            Assertions.assertTrue(false, e.getMessage());
        }
    }

    @Test
    public void obtenerUsuarioTest(){
        Usuario usuario = new Usuario("909","22", "Mario","aguirre", "mario@gmail.com","alejo","123", Ciudad.CALI, Departamento.VALLE_DEL_CAUCA,"clll 6","322554");
        try {
            usuarioServicio.registrarUsuario(usuario);
            Usuario u = usuarioServicio.obtenerUsuario("909");
            Assertions.assertNotNull(u);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Assertions.assertTrue(false);
        }
    }

    */

}
