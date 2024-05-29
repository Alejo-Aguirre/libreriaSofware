package co.edu.uniquinidio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import co.edu.uniquindio.proyecto.servicios.ModeradorServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;


@Component
@ViewScoped
public class EmpleadoBean  implements Serializable {

    @Getter
    @Setter
    private Moderador empleado;
    @Autowired
    private ModeradorServicio empleadoServicio;

// ciudades,ciudad seleccionada y pal depa lo mismo y en el resgitro agregar tambien
    @Getter @Setter
    private Departamento departamentoSeleccionado;

    private List<Departamento> departamentos;

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public List<Departamento> getDepartamentos() {
        return Arrays.asList(Departamento.values());
    }


    @Getter @Setter
    private Ciudad ciudadSeleccionada;

    private List<Ciudad> ciudades;


    public void setCiudades(List<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }

    public List<Ciudad> getCiudades() {
        return Arrays.asList(Ciudad.values());
    }



    //cuando se instancia la clase automaticamente se llama a la siguiente funcion
    @PostConstruct
    public void inicializar(){
        empleado=new Moderador();
    }

    public String generarNuevoCodigo() {
        // Obtener el último código existente o establecerlo en "000000" si no hay usuarios
        String ultimoCodigo = String.valueOf(empleadoServicio.obtenerUltimoNumero());
        int nuevoNumero = 1;

        if (ultimoCodigo != null && !ultimoCodigo.isEmpty()) {
            // Obtener el número actual y aumentarlo en 1
            int numeroActual = Integer.parseInt(ultimoCodigo);
            nuevoNumero = numeroActual ;
        }

        // Formatear el nuevo número con ceros a la izquierda
        DecimalFormat formato = new DecimalFormat("000");
        String nuevoCodigo = formato.format(nuevoNumero);

        return nuevoCodigo;
    }

    public void registrarEmpleado(){
        try {
            empleado.setCodigo(generarNuevoCodigo());

            Ciudad ciudadSeleccionada = getCiudadSeleccionada();
            // Configurar la editorial seleccionada en el libro
            empleado.setMiCiudad(ciudadSeleccionada);


            Departamento departamentoSeleccionado= getDepartamentoSeleccionado();
            empleado.setMiDepartamento(departamentoSeleccionado);
            empleadoServicio.registrarModerador(empleado);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Alerta","Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null,msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Alerta",e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null,msg);
        }
    }
}