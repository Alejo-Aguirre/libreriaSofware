package co.edu.uniquindio.proyecto;


import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest {

    @Autowired
    private CompraRepo compraRepo;

    @Autowired// nos permite inicializar las variables que representen componentes de SpringBoot
    private UsuarioRepo usuarioRepo;

    /**

    /**
     * el usuario se obtiene y se asocia desde usuarios.sql
     */

    /**
    @Test
    @Sql("classpath:usuarios.sql") // Archivo SQL con datos de usuarios en la base de datos
    public void crearCompra() {
        // Crear una nueva instancia de la entidad Compra
        Compra compra = new Compra();

        // Asignar valores predeterminados a los campos de la entidad
        compra.setCodigo("123");
        compra.setValorTotal(100000F);
        compra.setMedioDePago(MedioDePago.EFECTIVO_EN_PUNTO_DE_PAGO);

        // Obtener un usuario existente de la base de datos
        Usuario usuario = usuarioRepo.obtenerUsuarioPorCodigo("906");
        assertNotNull(usuario);
        compra.setMiUsuario(usuario);

        compra.setFechaCreacion(LocalDate.now());

        // Guardar la compra en la base de datos
        Compra compraGuardada = compraRepo.save(compra);
        assertNotNull(compraGuardada);
    }

    @Test
    @Sql("classpath:usuarios.sql") // Archivo SQL con datos de usuarios en la base de datos
    public void actualizarCompra() {
        // Crear una nueva instancia de la entidad Compra
        Compra compra = new Compra();

        // Asignar valores predeterminados a los campos de la entidad
        compra.setCodigo("123");
        compra.setValorTotal(100000F);
        compra.setMedioDePago(MedioDePago.EFECTIVO_EN_PUNTO_DE_PAGO);

        // Obtener un usuario existente de la base de datos
        Usuario usuario = usuarioRepo.obtenerUsuarioPorCodigo("906");
        assertNotNull(usuario);
        compra.setMiUsuario(usuario);

        compra.setFechaCreacion(LocalDate.now());

        // Guardar la compra en la base de datos
        Compra compraGuardada = compraRepo.save(compra);
        assertNotNull(compraGuardada);

        // Actualizar la compra recién creada
        compraGuardada.setValorTotal(150000F); // Actualizar el valor total de la compra
        Compra compraActualizada = compraRepo.save(compraGuardada);
        assertNotNull(compraActualizada);
    }

    @Test
    @Sql("classpath:usuarios.sql") // Archivo SQL con datos de usuarios en la base de datos
    public void eliminarCompra() {
        // Crear una nueva instancia de la entidad Compra
        Compra compra = new Compra();

        // Asignar valores predeterminados a los campos de la entidad
        compra.setCodigo("123");
        compra.setValorTotal(100000F);
        compra.setMedioDePago(MedioDePago.EFECTIVO_EN_PUNTO_DE_PAGO);

        // Obtener un usuario existente de la base de datos
        Usuario usuario = usuarioRepo.obtenerUsuarioPorCodigo("906");
        assertNotNull(usuario);
        compra.setMiUsuario(usuario);

        compra.setFechaCreacion(LocalDate.now());

        // Guardar la compra en la base de datos
        Compra compraGuardada = compraRepo.save(compra);
        assertNotNull(compraGuardada);

        // Eliminar la compra recién creada
        compraRepo.delete(compraGuardada);

        // Verificar que la compra haya sido eliminada
        Compra compraObtenida = compraRepo.obtenerCompraPorCodigo("123");
        assertNull(compraObtenida);
    }
    //-------------------------------CONSULTAS-----------------------------------------------------------
    @Test
    public void listarCompras() {
        List<Compra> compras = compraRepo.findAll(); // Obtener todas las compras

        // Iterar sobre las compras y mostrar información relevante
        for (Compra compra : compras) {
            System.out.println("ID de Compra: " + compra.getCodigo());
            System.out.println("Fecha de Compra: " + compra.getFechaCreacion());
            System.out.println("Total de Compra: " + compra.getValorTotal());
            // Puedes mostrar más información relacionada con la compra según tus necesidades
        }
    }

    @Test
    @Sql("classpath:usuarios.sql")
    public void listarComprasConDetalle() {
        List<Compra> compras = compraRepo.findAll(); // Obtener todas las compras

        // Iterar sobre las compras y mostrar su detalle
        for (Compra compra : compras) {
            System.out.println("Compra con ID: " + compra.getCodigo() + ", Fecha: " + compra.getFechaCreacion() + ", Total: " + compra.getValorTotal());

            // Obtener el detalle de la compra
            List<DetalleCompra> detalles = compra.getMisDetalleCompras();
            for (DetalleCompra detalle : detalles) {
                System.out.println("Detalle - Producto: " + detalle.getMiLibro().getNombre() + ", Cantidad: " + detalle.getUnidades()+ ", Precio: " + detalle.getPrecioProducto());
            }
        }
    }


    @Test
    @Sql("classpath:usuarios.sql")
    public void listarUsuarioConMasCompras() {
        List<Object[]> resultado = compraRepo.listarUsuarioConMasCompras();
        for (Object[] row : resultado) {
            String usuarioId = row[0].toString(); // Convertir a String
            Long cantidadCompras = (Long) row[1]; // Obtener como Long
            System.out.println("Usuario con el ID: " + usuarioId + " - Cantidad de Compras: " + cantidadCompras);
        }
    }
    */
}
