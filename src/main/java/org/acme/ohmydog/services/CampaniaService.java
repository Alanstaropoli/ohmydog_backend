package org.acme.ohmydog.services;

import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;

import org.acme.ohmydog.repository.CampaniaRepository;
import org.acme.ohmydog.requests.CampaniaRequest;
import org.acme.ohmydog.entities.Campania;
import java.time.LocalDate;

@ApplicationScoped
public class CampaniaService {
    @Inject
    CampaniaRepository campaniaRepository;

    @Transactional
    public boolean register(CampaniaRequest campaniaRequest) {
        return campaniaRepository.register(campaniaRequest.getNombre(), campaniaRequest.getMotivo(),
                campaniaRequest.getCvu(),
                campaniaRequest.getTelefono(), campaniaRequest.getEmail(), campaniaRequest.getFechaInicio(),
                campaniaRequest.getFechaFin());
    }

    @Transactional
    public List<Campania> listarCampanias() {
        return campaniaRepository.listarCampanias();
    }

    @Transactional
    public List<Campania> listarCampaniasBorradas() {
        return campaniaRepository.listarCampaniasBorradas();
    }

    @Transactional
    public boolean modificarCampania(long id, String nombre, String motivo, long telefono, long cvu, String email,
            LocalDate fechaInicio, LocalDate fechaFin) {
        Campania campania = campaniaRepository.buscarCampaniaPorId(id);
        if (campania == null) {
            return false;
        }
        campania.setNombre(nombre);
        campania.setMotivo(motivo);
        campania.setTelefono(telefono);
        campania.setEmail(email);
        campania.setCvu(cvu);
        campania.setFechaInicio(fechaInicio);
        campania.setFechaFin(fechaFin);
        campaniaRepository.persist(campania);
        return true;
    }

    @Transactional
    public boolean eliminarCampania(Long id) {
        Campania campania = campaniaRepository.buscarCampaniaPorId(id);
        if (campania == null) {
            return false;
        }
        campania.setBorrado(true);
        campaniaRepository.persist(campania);
        return true;
    }

    @Transactional
    public boolean recuperarCampania(Long id) {
        Campania campania = campaniaRepository.buscarCampaniaPorId(id);
        if (campania == null) {
            return false;
        }
        if (campania.isBorrado()) {
            campania.setBorrado(false);
        }
        if (campania.getFechaFin() != null) {
            LocalDate fechaFin = campania.getFechaFin();
            LocalDate fechaActual = LocalDate.now();
            if (fechaFin.isBefore(fechaActual)) {
                campania.setFechaFin(null);
            }
        }
        campaniaRepository.persist(campania);
        return true;
    }

    @Transactional
    public Campania buscarCampaniaPorId(Long id) {
        Campania campania = campaniaRepository.buscarCampaniaPorId(id);
        if (campania == null) {
            return null;
        }
        return campania;
    }
}
