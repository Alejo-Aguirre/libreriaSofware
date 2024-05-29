package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;



@Service
public class UsuarioServicioImpl implements UsuarioServicio{
    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;

    }
    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El código del usuario ya existe");
        }

        buscado = usuarioRepo.findByEmail(u.getEmail());
        if (buscado.isPresent()){
            throw new Exception("El email del usuario ya existe");
        }

        buscado = usuarioRepo.findByUsername(u.getUsername());
        if (buscado.isPresent()){
            throw new Exception("El username del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception {
        // Buscar el usuario por código
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());

        if (buscado.isPresent()) {
            // Verificar si ya existe un usuario con el mismo username
            Optional<Usuario> existente = usuarioRepo.findByUsername(u.getUsername());

            // Si existe un usuario con el mismo username y no es el mismo usuario que estamos actualizando
            if (existente.isPresent() && !existente.get().getCodigo().equals(u.getCodigo())) {
                throw new Exception("El username del usuario ya existe");
            }

            // Guardar la actualización del usuario
            return usuarioRepo.save(u);
        } else {
            // Si el usuario no existe, lanzar una excepción
            throw new Exception("El usuario no existe");
        }
    }



    @Override
    public void eliminarUsuario(String codigo) throws Exception {

        Optional<Usuario> buscado= usuarioRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("El codigo del usuario No existe");
        }

          usuarioRepo.deleteById(codigo);
    }

    private Optional<Usuario> buscarUsuarioPorEmail(String email){
        return  usuarioRepo.findByEmail(email);

    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public int obtenerUltimoNumero() {
        Integer ultimoNumero = usuarioRepo.obtenerUltimoNumero();

        // Devolvemos el último número + 1 o 0 si no hay usuarios en la base de datos
        return ultimoNumero != null ? ultimoNumero + 1 : 0;
    }


    @Override
    public Usuario iniciarSesion(String username, String password) throws Exception {
    return      usuarioRepo.findByUsernameAndPassword(username,password).orElseThrow(() -> new Exception ("los datos de autenticacion son incorrectos"));


    }

    @Override
    public Usuario obtenerUsuario(String codigo) {
        return usuarioRepo.obtenerUsuarioPorCodigo(codigo);
    }

    @Override
    public Usuario obtenerPropietarioProducto(String codigoProducto) {
        return null;
    }


    @Override
    public List<Usuario> obtenerUsuariosPorLetra(String letra) {
        return usuarioRepo.obtenerUsuariosPorLetra(letra);
    }

    @Override
    public List<Usuario> obtenerUsuariosPorCiudad(Ciudad ciudad) {
        return usuarioRepo.obtenerUsuariosPorCiudad(ciudad);
    }

    @Override
    public List<Usuario> obtenerUsuariosConEdadSuperiorAlPromedioPorCiudad(Ciudad ciudad) {
        return usuarioRepo.obtenerUsuariosConEdadSuperiorAlPromedioPorCiudad(ciudad);
    }




}
