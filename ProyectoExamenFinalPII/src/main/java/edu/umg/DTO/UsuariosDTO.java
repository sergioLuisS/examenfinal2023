package edu.umg.DTO;

import java.util.Objects;

public class UsuariosDTO {
    private int id;
    private String nombreUsuario;
    private String contrasena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuariosDTO that = (UsuariosDTO) o;
        return id == that.id && Objects.equals(nombreUsuario, that.nombreUsuario) && Objects.equals(contrasena, that.contrasena);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreUsuario, contrasena);
    }
}
