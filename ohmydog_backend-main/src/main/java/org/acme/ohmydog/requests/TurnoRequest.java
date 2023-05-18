package org.acme.ohmydog.requests;

import java.time.LocalDate;

public class TurnoRequest {
//    private Long idPerro;
    private LocalDate fecha;
    private String motivo;

    public TurnoRequest(LocalDate fecha, String motivo) {
//      this.idPerro = idPerro;
        this.fecha = fecha;
        this.motivo = motivo;
    }

//   public Long getIdPerro() {
//       return this.idPerro;
//   }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public String getMotivo() {
        return this.motivo;
    }

//   public void setIdPerro(Long idPerro) {
//       this.idPerro = idPerro;
//   }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
}
