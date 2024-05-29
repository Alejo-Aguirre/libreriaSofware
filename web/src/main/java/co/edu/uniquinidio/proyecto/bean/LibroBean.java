package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.enums.Editorial;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@ViewScoped
public class LibroBean implements Serializable {

    @Getter @Setter
    private Libro libro;
    private List<Libro> librosPorCategoria;

    @Getter @Setter
    private String codigoCategoriaSeleccionada;

    @Getter @Setter
    private Editorial editorialSeleccionada;
    private List<Editorial> editoriales;


    public List<Editorial> getEditoriales() {
        return Arrays.asList(Editorial.values());
    }

    public void setEditorialSeleccionada(Editorial editorialSeleccionada) {
        this.editorialSeleccionada = editorialSeleccionada;
    }

    @Autowired
    private LibroServicio libroServicio;

    private ArrayList<String> imagenes ;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Value("${uploads.url}")
    private  String urlUploads;

    @PostConstruct
    public void inicializar(){
        libro = new Libro();
        this.imagenes = new ArrayList<>();
    }

    public List<Categoria> getCategorias() {
        return categoriaServicio.listarCategorias(); // Obtener la lista de categorías desde tu servicio o base de datos
    }


    public void crearLibro(){
        try {

            if(!imagenes.isEmpty()){
                LocalDate ldn = LocalDate.now();
                // Obtener la categoría seleccionada por su código
                Categoria categoriaSeleccionada = categoriaServicio.obtenerCategoria(codigoCategoriaSeleccionada);
                libro.setMiCategoria(categoriaSeleccionada);

                // Obtener la editorial seleccionada (asumiendo que es un Enum)
                Editorial editorialSeleccionada = getEditorialSeleccionada();

                // Configurar la editorial seleccionada en el libro
                libro.setMiEditorial(editorialSeleccionada);

                libro.setImagenes(imagenes);
                libro.setFechaCreacion(ldn);
                libroServicio.registrarLibro(libro);
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","La creacion del producto fue exitosa");
                FacesContext.getCurrentInstance().addMessage(null,msg);
            }else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Alerta","Es necesario subir una imagen");
                FacesContext.getCurrentInstance().addMessage(null,msg);

            }
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }


    }

    public void subirImagenes(FileUploadEvent event){
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if(nombreImagen != null){
            imagenes.add(nombreImagen);

        }
    }

    private String subirImagen(UploadedFile imagen) {

        try {
            File archivo = new File(urlUploads+"/"+imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(),outputStream);
            return imagen.getFileName();
        }catch (Exception e){
            e.printStackTrace();

        }return null;

    }

    public void obtenerProductosPorCategoria() {
        // Obtener la categoría seleccionada desde el parámetro de la URL
        String categoriaSeleccionada = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("categoria");

        // Obtener los productos de la categoría seleccionada desde tu servicio o base de datos
        librosPorCategoria = libroServicio.obtenerLibrosPorCategoria(categoriaSeleccionada);
    }


}
