package org.acme.ohmydog.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.ohmydog.entities.Turno;
import org.acme.ohmydog.repository.TurnoRepository;
import org.acme.ohmydog.requests.TurnoRequest;

import java.util.List;

@ApplicationScoped
public class TurnoService {

    @Inject
    TurnoRepository turnoRepository;

//    @Inject
//    PerroRepository perroRepository;

    @Transactional
    public boolean register(TurnoRequest turnoRequest) {
//        if (perroRepository.buscarPerroPorId(turnoRequest.getIdPerro()) == null) {
//            return false;
//        }
        turnoRepository.register(turnoRequest.getFecha(), turnoRequest.getMotivo());
        return true;
    }

    @Transactional
    public List<Turno> listarTurnos() {
        return turnoRepository.listarTurnos();
    }

}
