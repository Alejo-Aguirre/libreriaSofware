package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import org.springframework.ui.ModelExtensionsKt;

import java.util.List;

public interface ModeradorServicio {
    Moderador registrarModerador(Moderador m) throws Exception;

    Moderador actualizarModerador(Moderador m) throws Exception;

    void eliminarModerador(String codigo) throws Exception;

    public int obtenerUltimoNumero();

    List<Moderador> listarModeradores();

    Moderador buscarModeradorPorCodigo(String codigo) throws Exception;

    Moderador iniciarSesion(String username, String password) throws Exception;

    Moderador obtenerModerador(String codigo) throws Exception;


    List<Moderador>obtenerEmpleadosPorDepartamento(Departamento departamento);

    List<Moderador>obtenerEmpleadosPorLetraApellido(String letra);

    List<Moderador>obtenerEmpleadoConEdadSuperiorAlPromedioPorDepartamento(Departamento departamento);

}
