package co.edu.uniquinidio.proyecto.bean;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class CategoriaBean implements Serializable {

    private List<Categoria> categorias;

    @Getter @Setter
    private Categoria categoria;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @PostConstruct
    public void init() {
        categorias = getCategorias();
    }

    public List<Categoria> getCategorias() {
        return categoriaServicio.listarCategorias(); // Obtener la lista de categorías desde tu servicio o base de datos
    }

}
