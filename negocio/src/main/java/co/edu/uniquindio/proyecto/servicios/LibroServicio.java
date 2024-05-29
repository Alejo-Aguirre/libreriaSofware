package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;

public interface LibroServicio {

    Libro registrarLibro(Libro p) throws Exception;

    void actualizarLibro(Libro p) throws Exception;


    void eliminarLibro(String codigo) throws Exception;


    Libro obtenerLibro(String codigo) throws Exception;

    List<Libro> listarLibroPorCategoria(Categoria categoria);
    List<Libro> listarTodosLibros()throws Exception;

    void comentarProducto(String mensaje, Usuario usuario, Libro libro) throws Exception;

    void guardarLibroEnFavoritos(Libro libro, Usuario usuario) throws Exception;
    void eliminarLibrofavorito(Libro libro, Usuario usuario)throws Exception;
    void comprarLibros(DetalleCompra detalleCompra, Libro libro);

    List<Libro> buscarLibroPorNombre(String nombre, String [] producto);

    List<Libro> buscarLibro(String nombre, String[] producto);

    List<Libro> listarLibros(String codigoUsuario)throws Exception;


    List<Libro> obtenerLibrosPorCategoria(String categoriaSeleccionada);


    List <Libro> buscarLibrosCodigo(String codigo);

    List<Libro> buscarLibrosporAutor(String autor);


    List<Libro> buscarLibrosPorPrecioSuperior(float precio);

    List<Object[]> BuscarLibroCategoriaAutorPorCategoriasConMasUnidades(Long unidades);

    List<Libro>obtenerLibrosPorCategoriaOrdenadosPorPrecioDescendente(Categoria categoria);


    ;
}
