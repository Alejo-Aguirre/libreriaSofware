package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.LibroRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServicioImpl implements LibroServicio {

    private final LibroRepo libroRepo;

    private final UsuarioRepo usuarioRepo;
    private final ComentarioRepo comentarioRepo;

    public LibroServicioImpl(LibroRepo libroRepo, ComentarioRepo comentarioRepo, UsuarioRepo usuarioRepo) {
        this.libroRepo = libroRepo;
        this.comentarioRepo=comentarioRepo;
        this.usuarioRepo=usuarioRepo;
    }

    @Override
    public Libro registrarLibro(Libro p) throws Exception {
        Optional<Libro> buscado= libroRepo.findById(p.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El codigo del libro ya existe");
        }

        return libroRepo.save(p);
    }





    @Override
    public void actualizarLibro(Libro p) throws Exception {

    }

    @Override
    public void eliminarLibro(String codigo) throws Exception {
        Optional<Libro> producto = libroRepo.findById(codigo);

        if (producto.isEmpty()){
            throw new Exception("No existe ningun producto con ese codigo");
        }

        libroRepo.deleteById(codigo);
    }

    @Override
    public Libro obtenerLibro(String codigo) throws Exception {
        return libroRepo.findById(codigo).orElseThrow(()-> new Exception("el codigo del producto no es valido") );
    }

    @Override
    public List<Libro> listarLibroPorCategoria(Categoria categoria) {
        return libroRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Libro> listarTodosLibros() throws Exception {
        return libroRepo.findAll();
    }

    @Override
    public void comentarProducto(String mensaje, Usuario usuario, Libro libro) throws Exception {
        LocalDate ld = LocalDate.now();
        Comentario comentario=new Comentario("32",mensaje,ld);
        comentario.setMiUsuario(usuario);
        comentario.setMiLibro(libro);
        try {
            comentarioRepo.save(comentario);
        }catch (Exception e ){
            e.getMessage();
        }





    }

    @Override
    public void guardarLibroEnFavoritos(Libro libro, Usuario usuario) throws Exception {

        if (libro ==null || usuario==null){
            throw new Exception("el producto o el usuario son nulos ");
        }

        libro.getUsuariosFavoritos().add(usuario);
        usuario.getLibrosFavoritos().add(libro);

        libroRepo.save(libro);
        usuarioRepo.save(usuario);

    }

    @Override
    public void eliminarLibrofavorito(Libro libro, Usuario usuario) throws Exception {
        if (libro ==null || usuario==null){
            throw new Exception("el producto o el usuario son nulos ");
        }

        libro.getUsuariosFavoritos().remove(usuario);
        usuario.getLibrosFavoritos().remove(libro);

        libroRepo.save(libro);
        usuarioRepo.save(usuario);

    }

    @Override
    public void comprarLibros(DetalleCompra detalleCompra, Libro libro) {

    }

    @Override
    public List<Libro> buscarLibroPorNombre(String nombre, String[] producto) {

        return libroRepo.buscarLibroNombre(nombre);
    }

    @Override
    public List<Libro> buscarLibro(String nombre, String[] producto) {
        return null;
    }

    @Override
    public List<Libro> listarLibros(String codigoUsuario) throws Exception {
        return null;
    }

    @Override
    public List<Libro> obtenerLibrosPorCategoria(String categoriaSeleccionada) {
        return libroRepo.obtenerLibrosPorCategoria(categoriaSeleccionada);
    }

    @Override
    public List<Libro> buscarLibrosCodigo(String codigo) {
        return libroRepo.buscarLibrosCodigo(codigo);
    }


    @Override
    public List<Libro> buscarLibrosporAutor(String autor) {
        return libroRepo.buscarLibroPorAutor(autor);
    }

    @Override
    public List<Libro> buscarLibrosPorPrecioSuperior(float precio) {
        return libroRepo.buscarLibrosPorPrecioSuperior(precio);
    }

    @Override
    public List<Object[]> BuscarLibroCategoriaAutorPorCategoriasConMasUnidades(Long unidades) {
        return libroRepo.BuscarLibroCategoriaAutorPorCategoriasConMasUnidades(unidades);
    }



    @Override
    public List<Libro>obtenerLibrosPorCategoriaOrdenadosPorPrecioDescendente(Categoria categoria){
        return  libroRepo.obtenerLibrosPorCategoriaOrdenadosPorPrecioDescendente(categoria);
    }




}
