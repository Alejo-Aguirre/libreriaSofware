package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CategoriaRepo extends JpaRepository<Categoria,String> {

   // ---------------------------------Consultas propias-------------------------------------
    //se pueden contruir consultas propias mediante el lenguaje JPQL como la siguiente
    @Query("select c from Categoria c where c.nombre = :nombre")
    List<Categoria> obtenerCategoriasPorNombre(String nombre);

    @Query("select c from Categoria c  where c.codigo = :codigo")
    List<Categoria> obtenerCategoriasPorCodigo(String codigo);


    @Query("select c from Categoria c where c.codigo = :codigo")
    Categoria obtenerCategoriaPorCodigo(String codigo);



 //---------------------------------Consultas inferidas por SpringBoot----------------------------------------

    //con inferencia de datos gracias a el framework de spring que es JPArepository
    Optional<Categoria> findAllByNombreContains(String nombre);
    Page<Categoria> findAllBy(Pageable paginador);
    Optional<Categoria> findByCodigo(String codigo);
    //con inferencia de datos gracias a el framework de spring que es JPArepository



}
