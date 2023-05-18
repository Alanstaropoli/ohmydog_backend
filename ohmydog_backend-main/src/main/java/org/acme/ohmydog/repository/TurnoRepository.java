package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Turno;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class TurnoRepository implements PanacheRepository<Turno> {

    public void register(LocalDate fecha, String motivo) {
        Turno turno = new Turno(fecha, motivo);
        persist(turno);
    }

    public List<Turno> listarTurnos() {
        return listAll();
    }

}
