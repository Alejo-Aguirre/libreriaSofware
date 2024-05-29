package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@ViewScoped
public class MainEmpleadoBean implements Serializable {

    private String codigoBusqueda;
    @Autowired
    private ModeradorServicio empleadoServicio;

    private List<Moderador> empleados;

    @PostConstruct
    public void inicializar() {
        cargarEmpleados();
    }

    private Moderador empleadoSeleccionado;

    public Moderador getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(Moderador empleadoSeleccionado) {
        this.empleadoSeleccionado = empleadoSeleccionado;
    }

    public List<Moderador> getEmpleados() {
        return empleados;
    }

    public String getCodigoBusqueda() {
        return codigoBusqueda;
    }

    public void setCodigoBusqueda(String codigoBusqueda) {
        this.codigoBusqueda = codigoBusqueda;
    }

    public void cargarEmpleados() {
        try {
            this.empleados = empleadoServicio.listarModeradores();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar empleados", null));
        }
    }




    public void eliminarEmpleado(String codigo) {
        try {
            // Lógica para eliminar empleado
            empleadoServicio.eliminarModerador(codigo);
            // Mostrar mensaje de éxito
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado eliminado correctamente", null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar empleado: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
        }
    }

// Getter y setter para codigoBusqueda


    public void buscarEmpleadoPorCodigo() {

        try {
            //for (int i = -1; i <= 10; i++) {
                //codigoBusqueda = "00"+i;

                System.out.println("Iniciando búsqueda de empleado por código: "+getCodigoBusqueda()+codigoBusqueda);

                if (codigoBusqueda != null && !codigoBusqueda.trim().isEmpty()) {
                    // Lógica para buscar empleados por código
                    Moderador empleadoEncontrado = empleadoServicio.obtenerModerador(codigoBusqueda);

                    if (empleadoEncontrado != null) {
                        // Mostrar solo el empleado encontrado en la tabla
                        this.empleados = Collections.singletonList(empleadoEncontrado);
                        System.out.println("Empleado encontrado: " + empleadoEncontrado.getCodigo());
                    } else {
                        // Mostrar mensaje si no se encuentra el empleado
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empleado no encontrado", null);
                        FacesContext.getCurrentInstance().addMessage("messages", msg);
                        cargarEmpleados(); // Recargar la lista completa de empleados
                        System.out.println("Empleado no encontrado. Recargando la lista completa de empleados.");
                    }
                } else {
                    // Mostrar mensaje si el código de búsqueda está vacío
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese un código de búsqueda válido", null);
                    FacesContext.getCurrentInstance().addMessage("messages", msg);
                    cargarEmpleados(); // Recargar la lista completa de empleados
                    System.out.println("Código de búsqueda vacío. Recargando la lista completa de empleados.");
                }
           // }
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al buscar empleado por código: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
            System.out.println("Error durante la búsqueda de empleado por código: " + e.getMessage());
        }
    }

    public String actualizarEmpleado() {
        try {
            Moderador empleadoExistente = empleadoServicio.obtenerModerador(empleadoSeleccionado.getCodigo());

            if (empleadoExistente != null) {
                // Actualizar solo los campos que se han ingresado
                if (empleadoSeleccionado.getCedula() != null) {
                    empleadoExistente.setCedula(empleadoSeleccionado.getCedula());
                }
                if (empleadoSeleccionado.getNombre() != null) {
                    empleadoExistente.setNombre(empleadoSeleccionado.getNombre());
                }
                if (empleadoSeleccionado.getEmail() != null) {
                    empleadoExistente.setEmail(empleadoSeleccionado.getEmail());
                }
                if (empleadoSeleccionado.getPassword() != null) {
                    empleadoExistente.setPassword(empleadoSeleccionado.getPassword());
                }

                // Llamada al servicio para actualizar el empleado en la base de datos
                empleadoServicio.actualizarModerador(empleadoExistente);

                // Mostrar mensaje de éxito
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Empleado actualizado correctamente", null);
                FacesContext.getCurrentInstance().addMessage("messages", msg);

                // Limpiar el empleado seleccionado
                //empleadoSeleccionado = null;

                // Recargar la lista completa de empleados
                cargarEmpleados();

                // Cerrar el diálogo
                //RequestContext.getCurrentInstance().execute("PF('editDialog').hide();");
            } else {
                // Mostrar mensaje si el empleado no se encuentra
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Empleado no encontrado", null);
                FacesContext.getCurrentInstance().addMessage("messages", msg);
            }

            return null; // O la página a la que quieras redirigir después de actualizar
        } catch (Exception e) {
            // Mostrar mensaje de error
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar empleado: " + e.getMessage(), null);
            FacesContext.getCurrentInstance().addMessage("messages", msg);
            return null; // O la página de error
        }
    }


    public String redirigirFormulario() {
        return "crear_Empleado.xhtml?faces-redirect=true";
    }



    /**
     * consulta Simple
     * lista todos los empleados por la letra a en el apellido
     * @param letra
     */
    public void generarReportePDF(String letra) {
        try {
            // Obtener la lista de empleados por la letra específica
            List<Moderador> empleados = empleadoServicio.obtenerEmpleadosPorLetraApellido(letra);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/empleados_simple" + letra + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de empleados cuyos apellidos inician con la letra " + letra));

            PdfPTable table = new PdfPTable(4); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");

            int contador = 1;

            for (Moderador empleado : empleados) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, empleado.getCodigo());
                agregarCelda(table, empleado.getNombre());
                agregarCelda(table, empleado.getApellido());

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
     * lista todos los empleados del dpto tal
     * @param departamento
     */
    public void generarReporteIntermedioPDF(Departamento departamento) {
        try {

            // Obtener la lista de empleados por la letra específica
            List<Moderador> empleados = empleadoServicio.obtenerEmpleadosPorDepartamento(departamento);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/empleados_intermedio" + departamento + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de empleados del departamento " +departamento));

            PdfPTable table = new PdfPTable(4); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");

            int contador = 1;

            for (Moderador empleado : empleados) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, empleado.getCodigo());
                agregarCelda(table, empleado.getNombre());
                agregarCelda(table, empleado.getApellido());

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
     * @param departamento
     */
    public void generarReporteComplejoPDF(Departamento departamento) {
        try {

            // Obtener la lista de usuarios por la letra específica
            List<Moderador> empleados= empleadoServicio.obtenerEmpleadoConEdadSuperiorAlPromedioPorDepartamento(departamento);

            Document document = new Document();
            String filePath = "C:/Users/lenovo/Desktop/pdfs/EmpleadosComplejo_" + departamento + ".pdf";

            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            document.add(new Paragraph("Reporte de empleados del " + departamento + "que tienen una edad mayor al promedio de edades"));

            PdfPTable table = new PdfPTable(4); // 4 columnas (1 para el valor numérico y 3 para datos)
            table.setWidthPercentage(100);

            // Añadir encabezados de columna
            table.addCell("No.");
            table.addCell("Código");
            table.addCell("Nombre");
            table.addCell("Apellido");

            int contador = 1;

            for (Moderador empleado : empleados) {
                table.addCell(String.valueOf(contador));
                agregarCelda(table, empleado.getCodigo());
                agregarCelda(table, empleado.getNombre());
                agregarCelda(table, empleado.getApellido());
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
