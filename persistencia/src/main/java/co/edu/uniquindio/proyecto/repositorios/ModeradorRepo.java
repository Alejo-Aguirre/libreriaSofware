package co.edu.uniquindio.proyecto.repositorios;

import ch.qos.logback.core.util.Loader;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.entidades.enums.Ciudad;
import co.edu.uniquindio.proyecto.entidades.enums.Departamento;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ModeradorRepo extends JpaRepository<Moderador,String> {
    List<Moderador> findAllByNombreContains(String nombre);
    Optional<Moderador> findByEmail(String email);
    Optional<Moderador> findByNombre(String nombre);
    Optional<Moderador> findByEmailAndCodigo(String email, String codigo);
    Optional<Moderador> findByNombreAndPassword(String username, String password);
    Page<Moderador> findAllBy(Pageable pageable);
    @Query("select m from Moderador m where m.codigo = :codigo")
    Moderador obtenerModeradorPorCodigo(String codigo);

    Optional<Moderador> findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT MAX(codigo) FROM Moderador", nativeQuery = true)
    Integer obtenerUltimoNumero();



    /**
     * consulta simple
     * sirve para listar todos los EMPLEADOS por Primera letra APELLLIDO
     * @param letra
     * @return
     */
    @Query("SELECT m FROM Moderador m WHERE LOWER(SUBSTRING(LOWER(m.apellido), 1, 1)) = LOWER(:letra)")
    List<Moderador> obtenerEmpleadosPorLetraApellido(@Param("letra") String letra);

    /**
     * consulta intermedia lista todos los empleados por dpto y ordena por nombre
     * @param departamento
     * @return
     */
    @Query("SELECT m " +
            "FROM Moderador m "+
            "WHERE m.miDepartamento = :departamento ORDER BY m.nombre")
    List<Moderador> obtenerEmpleadosPorDepartamento(@Param("departamento") Departamento departamento);

    // esta misma en moderador departamento
    /**
     * consulta compleja
     * En esta consulta, se seleccionan empleados cuya edad es superior al promedio de la edad de los empleados en la misma ciudad.
     * @param departamento
     * @return
     */

    @Query("SELECT m FROM Moderador m WHERE m.miDepartamento = :departamento AND m.edad > (SELECT AVG(m2.edad) FROM Moderador m2 WHERE m2.miDepartamento = :departamento)")
    List<Moderador> obtenerEmpleadoConEdadSuperiorAlPromedioPorDepartamento(@Param("departamento") Departamento departamento);



}
