package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;

import java.util.List;

public interface CategoriaServicio {

    Categoria registrarCategoria(Categoria categoria) throws Exception;
    void eliminarCategoria(String codigo) throws Exception;
    Categoria actualizarcategoria(Categoria categoria)throws Exception;

    Categoria obtenerCategoria(String codigo) throws Exception;
    Categoria obtenerCategoriaxn(String nombre) throws Exception;

    List<Categoria> listarCategorias();


}
