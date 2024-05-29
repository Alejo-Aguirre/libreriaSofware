package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
public class InicioBean implements Serializable {

    @Autowired
    private LibroServicio libroServicio;

    @Getter
    @Setter
    private List<Libro> libros;


    @PostConstruct
    public void inicializar()
    {
        try {
            this.libros = libroServicio.listarTodosLibros();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
