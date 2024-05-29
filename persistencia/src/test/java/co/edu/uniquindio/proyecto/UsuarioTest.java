package co.edu.uniquindio.proyecto;


import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.System.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest {

    @Autowired// nos permite inicializar las variables que representen componentes de SpringBoot
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;


    //----------------------------------------CRUD------------------------------------------------------
    @Test
    public void resgistraUsuario(){
        Usuario usuario=  new Usuario();
        usuario.setCodigo("904");
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@gmail.com");
        usuario.setPassword("Heropro.12");
        usuario.setDireccion("cll12#20-89");

         usuarioRepo.save(usuario);

         usuarioRepo.deleteById("904");

         Usuario usuarioBuscado = usuarioRepo.findById("904").orElse(null);

         Assertions.assertNull(usuarioBuscado);


    }

    @Test
    public void actualizarTest(){

        //si el usuario ya existe se actualza el registro
        //se guarda el ususario
        Usuario usuario=  new Usuario();
        usuario.setCodigo("904");
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@gmail.com");
        usuario.setPassword("Heropro.12");
        usuario.setDireccion("cll12#20-89");

        Usuario usuarioGuardado =usuarioRepo.save(usuario);

        // se modifica el correo
        usuarioGuardado.setEmail("pedrog@gmail.com");

        //se vuelve a guardar pero con un email diferente
        usuarioRepo.save(usuarioGuardado);
        Usuario usuarioBuscado = usuarioRepo.findById("904").orElse(null);
        out.println(usuarioGuardado);

       // Assertions.assertEquals("pedrog@gmail.com",usuarioBuscado.getEmail());


    }
    @Test
    public void eliminarTest(){
        Usuario usuario=  new Usuario();
        usuario.setCodigo("904");
        usuario.setNombre("Pedro");
        usuario.setEmail("pedro@gmail.com");
        usuario.setPassword("Heropro.12");
        usuario.setDireccion("cll12#20-89");

        usuarioRepo.save(usuario);
        Usuario usuarioBuscado= usuarioRepo.findById("904").orElse(null);
        usuarioRepo.delete(usuarioBuscado);

         usuarioBuscado= usuarioRepo.findById("904").orElse(null);
         Assertions.assertNull(usuarioBuscado);

    }



    //-----------------------------------consultas------------------------------------------------------
    //caundo se coloca la etiqueta sql hace que se indique que el data set de esa funcion sea el que este en el path asignado en la etuiqueta
    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuario() {
        List<Usuario> usuarios = usuarioRepo.findAll();//busca todos los usuarios, findall me permite traer los datos del data set
        usuarios.forEach(u -> out.println(u)); //funcion landa
     //listar actualizar eliminar y guardar
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPorNombre(){
        List<Usuario> misUsuarios=usuarioRepo.findAllByNombreContains("pedro");
        misUsuarios.forEach(u -> out.println(u));

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuariosPorNumeroTelefono(){

        //Optional<Usuario> usuario = usuarioRepo.findAllByTelefono("23");
        //sirve para que los errore no inpidan el manejo del codigo
        //System.out.println(usuario);

    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void paginarLista(){

        Pageable paginador = PageRequest.of(0,2);
    Page<Usuario> list = usuarioRepo.findAllBy(paginador);
        out.println(list.stream().collect(Collectors.toList()));

    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void ordenarLista(){


        List<Usuario> list = usuarioRepo.findAll(Sort.by("nombre"));
        out.println(list.stream().collect(Collectors.toList()));

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarCategorias(){

    List<Categoria> categorias = categoriaRepo.findAll();
    categorias.forEach(c -> out.println(c));

    }




    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerComentariosDeUsuaroi(){


        List<Comentario> comentarios= usuarioRepo.obtenerComentarioDeUsuarioPorCodigo("908");
        Assertions.assertEquals(3,comentarios.size());

    }



    @Test
    @Sql("classpath:usuarios.sql")
    public void buscarUsuarioPorCorreo(){
        Optional<Usuario> usuario = usuarioRepo.findByEmail("lian@gmail.com");
        Assertions.assertNotNull(usuario);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void buscarUsuarioPorUsername(){
        Optional<Usuario> usuario = usuarioRepo.findByUsername("unimario");
        System.out.println(usuario);
        Assertions.assertNotNull(usuario);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void inicioSesionTest(){
        Optional<Usuario>  usuario= usuarioRepo.findByUsernameAndPassword("unimario","Heropro.12");
        Assertions.assertNotNull(usuario);

    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void obtenerUsuTest(){
        Usuario  usuario= usuarioRepo.obtenerUsuarioPorCodigo("904");
        Assertions.assertNotNull(usuario);

    }























}
