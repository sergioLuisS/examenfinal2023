package edu.umg.DTO;

import java.sql.Timestamp;
import java.util.Objects;

public class InscripcionesDTO {
    private int idInscripcion;
    private Integer idEstudiante;
    private Integer idCurso;
    private Timestamp fechaInscripcion;

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }


    public Integer getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Integer idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }

    public Timestamp getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Timestamp fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    @Override
    public String toString() {
        return "InscripcionesDTO{" +
                "idInscripcion=" + idInscripcion +
                ", idEstudiante=" + idEstudiante +
                ", idCurso=" + idCurso +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionesDTO that = (InscripcionesDTO) o;
        return idInscripcion == that.idInscripcion && Objects.equals(idEstudiante, that.idEstudiante) && Objects.equals(idCurso, that.idCurso) && Objects.equals(fechaInscripcion, that.fechaInscripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInscripcion, idEstudiante, idCurso, fechaInscripcion);
    }
}
