package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface CompraServicio {

    Compra registrarCompra(Compra c) throws Exception;

    Compra actualizarCompra(Compra c) throws Exception;

    void eliminarCompra(String codigo) throws Exception;

    List<Compra> listarCompras();

    Compra buscarCompraPorCodigo(String codigo) throws Exception;

    Compra obtenerCompra(String codigo) throws Exception;

    List<Compra>obtenerComprasYLibrosPorCodigoUsuario(String codigo);
}