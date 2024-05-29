package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;

public interface DetalleCompraServicio {

    DetalleCompra registrarDetalleCompra(DetalleCompra dt)throws Exception;


}
