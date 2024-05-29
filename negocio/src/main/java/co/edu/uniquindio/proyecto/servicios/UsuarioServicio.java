package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UsuarioServicio {
    Usuario registrarUsuario(Usuario u) throws Exception;
    Usuario actualizarUsuario(Usuario u) throws Exception;

    void eliminarUsuario(String codigo) throws Exception;
    List<Usuario> listarUsuarios();

    public int obtenerUltimoNumero();

    Usuario iniciarSesion(String username,String password) throws Exception;

    Usuario obtenerUsuario(String codigo) throws Exception;

    Usuario obtenerPropietarioProducto(String codigoProducto);

    List<Usuario> obtenerUsuariosPorLetra(String letra);

    List<Usuario> obtenerUsuariosPorCiudad(Ciudad ciudad);

    List<Usuario> obtenerUsuariosConEdadSuperiorAlPromedioPorCiudad(Ciudad ciudad);
}
