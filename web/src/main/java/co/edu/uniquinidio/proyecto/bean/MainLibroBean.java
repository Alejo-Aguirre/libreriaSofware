package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.LibroServicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.itextpdf.text.pdf.PdfWriter;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.FileOutputStream;
import java.io.IOException;


@Component
@ViewScoped
public class MainLibroBean implements Serializable {


    private String codigo;

// ... Otros métodos y propiedades ...

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    @Autowired
    private LibroServicio libroServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;


    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }


    @PostConstruct
    public void inicializar() {
        cargarLibros();
    }
    // Método para cargar los libros
    public void cargarLibros() {
        try {
            // Asegúrate de que libroServicio.listarTodosLibros() devuelve datos
            this.libros = libroServicio.listarTodosLibros();
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la excepción para obtener más detalles en la consola
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar libros", null));
        }
    }

    public String redirigirFormulario() {
        // Lógica para redirigir al formulario de creación

        return "crear_libro?faces-redirect=true";
    }


    public void actualizarLibro(Libro libro) {
        // Lógica para actualizar el libro seleccionado
    }

    public void eliminarLibro(String codigo) {
        try {
            // Lógica para eliminar usuario
            libroServicio.eliminarLibro(codigo);
            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","libro eliminado correctamente");
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta","Error al eliminar libro: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }

    public void buscarLibros(String codigo) {
        try {
            // Lógica para buscar libros por código
            List<Libro> librosEncontrados = libroServicio.buscarLibrosCodigo(codigo);

            // Asignar los resultados a la propiedad en el bean
            setLibros(librosEncontrados);

            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Libro(s) buscado(s) correctamente", null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar libro: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }


    /**
     * consulta simple
     * lista los libros por el nombre de autor
     * @param autor
     * @return
     */

    //la base
    public void generarReportePDF(String autor) {
        try {
            // Obtener la lista de libros por el autor específico
            List<Libro> libros = libroServicio.buscarLibrosporAutor(autor);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/libros_simple" + autor + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Libros de " + autor));

            PdfPTable table = new PdfPTable(5); // 5 columnas (1 para el valor numérico y 4 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Autor");
            table.addCell("Nombre");
            table.addCell("Precio");

            int contador = 1;

            for (Libro libro : libros) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, libro.getCodigo());
                agregarCelda(table, libro.getAutor());
                agregarCelda(table, libro.getNombre());
                agregarCelda(table, String.valueOf(libro.getPrecio()));

                table.completeRow(); // Completa la fila actual y comienza una nueva fila
                contador++;
            }

            document.add(table);
            document.close();

            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta","Informe PDF generado correctamente");
            FacesContext.getCurrentInstance().addMessage("messages", msg);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();

            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta","Error al generar el informe PDF: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }


    /**
     * Reporte intermedio
     * este reporte permite listar libros por categoria ( se hace join con la tabla) y los ordenados por precio desc
     *
     */
    public void generarReporteIntermedio() {
        try {
            // Obtener la categoría por su nombre
            Categoria categoria = categoriaServicio.obtenerCategoriaxn("Misterio");

            // Verificar si la categoría es nula
            if (categoria == null) {
                // Mostrar mensaje de error si la categoría no se encuentra
                FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Categoría no encontrada: Misterio");
                FacesContext.getCurrentInstance().addMessage("messages", errorMsg);
                return;
            }

            // Obtener la lista de libros por la categoría específica, ordenados por precio descendente
            List<Libro> libros = libroServicio.obtenerLibrosPorCategoriaOrdenadosPorPrecioDescendente(categoria);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/libros_" + categoria.getNombre() + "_intermedio.pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte Intermedio de Libros de la categoría " + categoria.getNombre()));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Autor");
            table.addCell("Nombre");
            table.addCell("Precio");

            int contador = 1;

            for (Libro libro : libros) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, libro.getCodigo());
                agregarCelda(table, libro.getAutor());
                agregarCelda(table, libro.getNombre());
                agregarCelda(table, String.valueOf(libro.getPrecio()));

                table.completeRow();
                contador++;
            }

            document.add(table);
            document.close();

            // Mostrar mensaje de éxito
            FacesMessage successMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Informe Intermedio PDF generado correctamente");
            FacesContext.getCurrentInstance().addMessage("messages", successMsg);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();

            // Mostrar mensaje de error
            FacesMessage errorMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", "Error al generar el informe intermedio PDF: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("messages", errorMsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * reporte complejo
     * consulta compleja con subconsulta
     * Consulta para obtener libros con su categoría y autor,
     * pero solo para aquellos cuya categoría tiene más de cierta cantidad de unidades en total:
     * @param unidades
     */
    public void generarReporteComplejo(String unidades) {
        try {

            Long unidades2 = Long.parseLong(unidades);
            // Obtener la lista de libros con más de cierta cantidad de unidades
            List<Object[]> librosConCategoriaAutor = libroServicio.BuscarLibroCategoriaAutorPorCategoriasConMasUnidades(unidades2);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/libros_unidades_superiores_complejo" + unidades + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Libros con Más de " + unidades + " Unidades, Ordenados por Nombre"));

            PdfPTable table = new PdfPTable(5); // 5 columnas
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Autor");
            table.addCell("Nombre");
            table.addCell("Precio");

            int contador = 1;

            for (Object[] libroConCategoriaAutor : librosConCategoriaAutor) {
                Libro libro = (Libro) libroConCategoriaAutor[0];

                table.addCell(String.valueOf(contador));
                agregarCelda(table, libro.getCodigo());
                agregarCelda(table, libro.getAutor());
                agregarCelda(table, libro.getNombre());
                agregarCelda(table, String.valueOf(libro.getPrecio()));

                table.completeRow();
                contador++;
            }

            document.add(table);
            document.close();

            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Informe PDF generado correctamente", null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();

            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al generar el informe PDF: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }
















    /**
     * lista los libros segun el precio y los ordena asc por nombre
     */
    public void generarReporteIntermedio2(float precioSuperior) {
        try {
            // Obtener la lista de libros con precio superior al valor proporcionado
            List<Libro> libros = libroServicio.buscarLibrosPorPrecioSuperior(precioSuperior);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/libros_precio_superior_" + precioSuperior + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Libros con Precio Superior a " + precioSuperior + ", Ordenados por Nombre"));

            PdfPTable table = new PdfPTable(5); // 5 columnas
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Autor");
            table.addCell("Nombre");
            table.addCell("Precio");

            int contador = 1;

            for (Libro libro : libros) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, libro.getCodigo());
                agregarCelda(table, libro.getAutor());
                agregarCelda(table, libro.getNombre());
                agregarCelda(table, String.valueOf(libro.getPrecio()));

                table.completeRow();
                contador++;
            }

            document.add(table);
            document.close();

            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta", "Informe PDF generado correctamente");
            FacesContext.getCurrentInstance().addMessage("messages", msg);

        } catch (DocumentException | IOException e) {
            e.printStackTrace();

            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta","Error al generar el informe PDF: " + e.getMessage());
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }


    private void agregarCelda(PdfPTable table, String valor) {
        PdfPCell celda = new PdfPCell(new Phrase(valor));
        table.addCell(celda);
    }






















}
