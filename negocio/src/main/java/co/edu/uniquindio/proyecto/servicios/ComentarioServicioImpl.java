package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Moderador;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComentarioServicioImpl implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;

    public ComentarioServicioImpl(ComentarioRepo comentarioRepo) {
        this.comentarioRepo = comentarioRepo;
    }

    @Override
    public Comentario resgistrarComentario(Comentario c) throws Exception {
        Optional<Moderador> buscado = comentarioRepo.findByCodigo(c.getCodigo());
        if (buscado.isPresent()) {
            throw new Exception("El id electrónico ya está registrado");
        }

        return comentarioRepo.save(c);
    }
}
