package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepo extends JpaRepository<Compra,String> {
    //consulta que dado el codigo de una ciudad retorne la lista de los detalles de esa compra

    @Query("select d from Compra c INNER JOIN c.misDetalleCompras d where c.codigo = :codigo  ")
    List<DetalleCompra> detallesCompraPorCodigoDeCompra(String codigo);

    @Query("SELECT c.miUsuario.codigo, COUNT(c) FROM Compra c GROUP BY c.miUsuario.codigo ORDER BY COUNT(c) DESC")
    List<Object[]> listarUsuarioConMasCompras();


    @Query("select c from Compra c where c.codigo = :codigo")
    Compra obtenerCompraPorCodigo(String codigo);


    @Query("SELECT c.miUsuario FROM Compra c WHERE c.codigo = :codigo")
    Optional<Usuario> obtenerUsuarioCompra(@Param("codigo") String codigo);



    @Query("SELECT c FROM Compra c " +
            "JOIN FETCH c.misDetalleCompras dc " +
            "JOIN FETCH dc.miLibro " +
            "WHERE c.miUsuario.codigo = :codigoUsuario")
    List<Compra> obtenerComprasYLibrosPorCodigoUsuario(@Param("codigoUsuario") String codigoUsuario);


}
