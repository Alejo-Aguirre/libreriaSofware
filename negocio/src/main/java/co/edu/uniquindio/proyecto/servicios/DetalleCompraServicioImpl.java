package co.edu.uniquindio.proyecto.servicios;


import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleCompraServicioImpl implements DetalleCompraServicio {

    private final DetalleCompraRepo detalleCompraRepo;

    public DetalleCompraServicioImpl(DetalleCompraRepo detalleCompraRepo){
        this.detalleCompraRepo=detalleCompraRepo;
    }
    @Override
    public DetalleCompra registrarDetalleCompra(DetalleCompra dt) throws Exception {

        Optional<DetalleCompra> buscado = detalleCompraRepo.findById(dt.getCodigo());
        if(buscado.isPresent()){
            throw  new Exception("el codigo del detalle ya existe");
        }
        return detalleCompraRepo.save(dt);
    }

    public List<DetalleCompra> listarDetalles(){
        return detalleCompraRepo.findAll();
    }
}
