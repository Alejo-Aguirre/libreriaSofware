package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServicioImpl implements CompraServicio{

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public Compra registrarCompra(Compra c) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(c.getCodigo());
        if (buscado.isPresent()){
            throw new Exception("El código de la compra ya existe");
        }

        return compraRepo.save(c);
    }



    @Override
    public Compra actualizarCompra(Compra c) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(c.getCodigo());
        if (buscado.isEmpty()){
            throw new Exception("La compra con código " + c.getCodigo() + " no existe");
        }

        return compraRepo.save(c);
    }

    @Override
    public void eliminarCompra(String codigo) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(codigo);
        if (buscado.isEmpty()){
            throw new Exception("La compra con código " + codigo + " no existe");
        }

        compraRepo.deleteById(codigo);
    }

    @Override
    public List<Compra> listarCompras() {
        return compraRepo.findAll();
    }

    @Override
    public Compra buscarCompraPorCodigo(String codigo) throws Exception {
        Optional<Compra> buscado = compraRepo.findById(codigo);
        if (buscado.isEmpty()) {
            throw new Exception("La compra con código " + codigo + " no existe");
        }

        return buscado.get();
    }

    @Override
    public Compra obtenerCompra(String codigo) throws Exception {
        Optional<Compra> compraExistente = compraRepo.findById(codigo);
        if (!compraExistente.isPresent()) {
            throw new Exception("La compra con el código " + codigo + " no existe");
        }
        return compraExistente.get();
    }

    @Override
    public List<Compra>obtenerComprasYLibrosPorCodigoUsuario(String codigo){
        return  compraRepo.obtenerComprasYLibrosPorCodigoUsuario(codigo);
    }
}

