package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Editorial;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiciolmpl implements CategoriaServicio {

    private final CategoriaRepo categoriaRepo;

    public CategoriaServiciolmpl(CategoriaRepo categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria registrarCategoria(Categoria categoria) throws Exception {
        Optional<Categoria> catBuscada = categoriaRepo.findByCodigo(categoria.getCodigo());

        if (catBuscada.isPresent()){
            throw  new Exception("el codigo de la categoria ya existe");
        }

        return  categoriaRepo.save(categoria);
    }

    @Override
    public void eliminarCategoria(String codigo) throws Exception {

        Optional<Categoria> buscado= categoriaRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("El codigo de la categoria No existe");
        }

        categoriaRepo.deleteById(codigo);

    }


    @Override
    public Categoria actualizarcategoria(Categoria categoria) throws Exception {
        return null;
    }

    @Override
    public Categoria obtenerCategoria(String codigo) throws Exception {
        Optional<Categoria> categoriaBuscada = categoriaRepo.findByCodigo(codigo);
        if (categoriaBuscada.isEmpty()){
            throw  new Exception("el codigo de la categoria no existe");
        }
        return categoriaBuscada.get();
    }
    @Override
    public Categoria obtenerCategoriaxn(String nombre) throws Exception {
        Optional<Categoria> categoriaBuscada = categoriaRepo.findAllByNombreContains(nombre);
        if (categoriaBuscada.isEmpty()){
            throw  new Exception("el codigo de la categoria no existe");
        }
        return categoriaBuscada.get();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepo.findAll();
    }





}
