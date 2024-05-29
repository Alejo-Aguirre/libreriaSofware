package co.edu.uniquindio.proyecto.repositorios;


import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepo  extends JpaRepository<DetalleCompra,String> {

    @Query("select dt from DetalleCompra dt where dt.codigo = :codigo")
    DetalleCompra obtenerDetalleCompraPorCodigo(String codigo);


}
