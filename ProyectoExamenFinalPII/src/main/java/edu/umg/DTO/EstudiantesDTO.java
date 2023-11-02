package edu.umg.DTO;

public class EstudiantesDTO {
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private String email;



    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // Otros atributos y métodos de la clase
    public Integer getId() {
        // Devuelve el ID del estudiante
        return idEstudiante;
    }

    @Override
    public String toString() {
        return "  " +
                "   Codigo:" + idEstudiante +
                "   Nombre:" + nombre + '\'' +
                "    Apellidos:" + apellido + '\'' +
                "    Email" + email + '\'' +
                ' ';
    }
}
