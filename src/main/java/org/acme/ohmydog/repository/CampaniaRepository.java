package org.acme.ohmydog.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.ohmydog.entities.Campania;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CampaniaRepository implements PanacheRepository<Campania> {
    /**
     * Busca una campania por su id
     *
     * @param id
     * @return campania
     */
    public Campania buscarCampaniaPorId(Long id) {
        return find("id", id).firstResult();
    }

    public boolean register(String nombre, String motivo, long cvu, long telefono, String email, LocalDate fechaInicio,
            LocalDate fechaFin) {
        Campania campania = new Campania(nombre, motivo, cvu, telefono, email, fechaInicio, fechaFin);
        persist(campania);
        return true;
    }

    public List<Campania> listarCampanias() {
        // Filtra las campañas que están borradas lógicamente y cuya fecha de fin es
        // posterior o igual a la fecha actual
        List<Campania> campanias = listAll();
        List<Campania> campaniasFiltradas = new ArrayList<>();

        LocalDate fechaActual = LocalDate.now();

        for (Campania campania : campanias) {
            LocalDate fechaFin = campania.getFechaFin();
            if (!campania.isBorrado()
                    && (fechaFin == null || fechaFin.isEqual(fechaActual) || fechaFin.isAfter(fechaActual))) {
                campaniasFiltradas.add(campania);
            }
        }

        return campaniasFiltradas;
    }

    public List<Campania> listarCampaniasBorradas() {
        // Filtra las campanias que estan borradas logicamente y cuya fecha de fin es
        // posterior o igual a la fecha actual

        List<Campania> campanias = listAll();
        List<Campania> campaniasBorradas = new ArrayList<>();

        LocalDate fechaActual = LocalDate.now();
        for (Campania campania : campanias) {
            LocalDate fechaFin = campania.getFechaFin();
            if (campania.isBorrado() || fechaFin.isBefore(fechaActual)) {
                campaniasBorradas.add(campania);
            }
        }

        return campaniasBorradas;
    }

}
