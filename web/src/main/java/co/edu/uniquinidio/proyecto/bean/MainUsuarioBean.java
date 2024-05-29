package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Libro;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.primefaces.event.RowEditEvent;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@ManagedBean(name = "mainUsuarioBean")
@ViewScoped

public class MainUsuarioBean implements Serializable {


    @Getter
    @Setter
    private String codigoBusqueda;
    @Autowired
    private UsuarioServicio usuarioServicio;

    private List<Usuario> usuarios;

    @PostConstruct
    public void inicializar() {
        cargarUsuarios();
    }

    private Usuario usuarioSeleccionado;

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void cargarUsuarios() {
        try {
            this.usuarios = usuarioServicio.listarUsuarios();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar usuarios", null));
        }
    }

    public void onRowEdit(RowEditEvent<Usuario> event) {
        try {
            Usuario usuarioEditado = event.getObject();

            // Lógica de edición - actualiza el usuario en la base de datos
            usuarioServicio.actualizarUsuario(usuarioEditado);

            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario editado correctamente", null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al editar usuario: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }


// Otros imports...

    public void eliminarUsuario(String codigo) {
        try {
            // Lógica para eliminar usuario
            usuarioServicio.eliminarUsuario(codigo);
            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado correctamente", null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar usuario: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }


    // Getter y setter para codigoBusqueda

    public void buscarUsuarioPorCodigo() {
        try {
            for (int i = -1; i <= 10; i++) {
                codigoBusqueda = "00" + i;

                System.out.println("Iniciando búsqueda de usuario por código: " + codigoBusqueda);

                if (codigoBusqueda != null && !codigoBusqueda.trim().isEmpty()) {
                    // Lógica para buscar usuarios por código
                    Usuario usuarioEncontrado = usuarioServicio.obtenerUsuario(codigoBusqueda);

                    if (usuarioEncontrado != null) {
                        // Mostrar solo el usuario encontrado en la tabla
                        this.usuarios = Collections.singletonList(usuarioEncontrado);
                        System.out.println("Usuario encontrado: " + usuarioEncontrado.getCodigo());
                    } else {
                        // Mostrar mensaje si no se encuentra el usuario
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario no encontrado", null);
                        FacesContext.getCurrentInstance().addMessage("messages", msg);
                        cargarUsuarios(); // Recargar la lista completa de usuarios
                        System.out.println("Usuario no encontrado. Recargando la lista completa de usuarios.");
                    }
                } else {
                    // Mostrar mensaje si el código de búsqueda está vacío
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese un código de búsqueda válido", null);
                    FacesContext.getCurrentInstance().addMessage("messages", msg);
                    cargarUsuarios(); // Recargar la lista completa de usuarios
                    System.out.println("Código de búsqueda vacío. Recargando la lista completa de usuarios.");
                }
            }

        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar usuario por código: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
            System.out.println("Error durante la búsqueda de usuario por código: " + e.getMessage());
        }
    }


    public String redirigirFormulario() {
        return "registrar_usuario.xhtml?faces-redirect=true";
    }

    /**
     * consulta Simple
     * lista todos los usuarios por la letra a
     * @param letra
     */
    public void generarReportePDF(String letra) {
        try {
            // Obtener la lista de usuarios por la letra específica
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorLetra(letra);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/usuarios_simple" + letra + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Usuarios cuyos nombres inician con la letra " + letra));

            PdfPTable table = new PdfPTable(4); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");

            int contador = 1;

            for (Usuario usuario : usuarios) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, usuario.getCodigo());
                agregarCelda(table, usuario.getNombre());
                agregarCelda(table, usuario.getApellido());

                table.completeRow(); // Completa la fila actual y comienza una nueva fila
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
     * consulta intermedia
     * lista todos los usuarios de la ciudad tal
     * @param ciudad
     */
    public void generarReporteIntermedioPDF(Ciudad ciudad) {
        try {

            // Obtener la lista de usuarios por la letra específica
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosPorCiudad(ciudad);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/usuarios_intermedio" + ciudad + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Usuarios de la ciudad " +ciudad));

            PdfPTable table = new PdfPTable(4); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");

            int contador = 1;

            for (Usuario usuario : usuarios) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, usuario.getCodigo());
                agregarCelda(table, usuario.getNombre());
                agregarCelda(table, usuario.getApellido());

                table.completeRow(); // Completa la fila actual y comienza una nueva fila
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
     * consulta intermedia
     * lista todos los usuarios de la ciudad tal
     * @param ciudad
     */
    public void generarReporteComplejoPDF(Ciudad ciudad) {
        try {

            // Obtener la lista de usuarios por la letra específica
            List<Usuario> usuarios = usuarioServicio.obtenerUsuariosConEdadSuperiorAlPromedioPorCiudad(ciudad);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/usuarios_edad_complejo" + ciudad + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de Usuarios de la ciudad " +ciudad));

            PdfPTable table = new PdfPTable(5); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");
            table.addCell("edad");

            int contador = 1;

            for (Usuario usuario : usuarios) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, usuario.getCodigo());
                agregarCelda(table, usuario.getNombre());
                agregarCelda(table, usuario.getApellido());
                agregarCelda(table, String.valueOf(usuario.getEdad()));

                table.completeRow(); // Completa la fila actual y comienza una nueva fila
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

    private void agregarCelda(PdfPTable table, String valor) {
        PdfPCell celda = new PdfPCell(new Phrase(valor));
        table.addCell(celda);
    }
}