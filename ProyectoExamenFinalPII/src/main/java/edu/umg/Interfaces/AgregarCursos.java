package edu.umg.Interfaces;

import edu.umg.DAO.CursosDAO;
import edu.umg.DTO.CursosDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AgregarCursos extends JFrame {
    private CursosDAO cursosDAO = new CursosDAO();
    private JPanel AgregarCursososs;
    private JTextField idField;
    private JTextField courseNameField;
    private JTextField professorField;
    private JButton addCourseButton;
    private JButton updateCourseButton;
    private JButton deleteCourseButton;
    private JTextArea courseListTextArea;
    private JButton buttonRegresarMenu;


    public AgregarCursos() {

        setContentPane(AgregarCursososs);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // Cargar la lista de cursos al abrir la ventana
        cargarListaCursos();


        // Configurar ActionListener para los botones de cursos
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCurso();
            }
        });

        updateCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCurso();
            }
        });

        deleteCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCurso();
            }
        });
        buttonRegresarMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaMenuOpciones();
            }
        });

    }
    public void abrirVentanaMenuOpciones() {
        // Crea y muestra la ventana de MenuOpciones
        MenuOpciones menuOpcionesForm = new MenuOpciones();
        menuOpcionesForm.setVisible(true);
        dispose();
    }



    private void cargarListaCursos() {
        List<CursosDTO> cursos = cursosDAO.obtenerTodosLosCursos();
        courseListTextArea.setText("");
        for (CursosDTO curso : cursos) {
            courseListTextArea.append("ID: " + curso.getIdCurso() + "\n");
            courseListTextArea.append("Nombre del Curso: " + curso.getNombreCurso() + "\n");
            courseListTextArea.append("Profesor: " + curso.getProfesor() + "\n");
            courseListTextArea.append("\n");
        }

    }

    private void agregarCurso() {
        int id = Integer.parseInt(idField.getText());
        String nombreCurso = courseNameField.getText();
        String profesor = professorField.getText();
        CursosDTO curso = new CursosDTO();
        curso.setIdCurso(id);
        curso.setNombreCurso(nombreCurso);
        curso.setProfesor(profesor);
        cursosDAO.agregarCurso(curso);
        JOptionPane.showMessageDialog(this, "Curso agregado correctamente.");
        limpiarCampos();
        cargarListaCursos(); // Actualizar la lista después de agregar un curso
    }

    private void actualizarCurso() {
        int idCurso = Integer.parseInt(idField.getText());
        String nombreCurso = courseNameField.getText();
        String profesor = professorField.getText();
        CursosDTO curso = cursosDAO.obtenerCurso(idCurso);
        if (curso != null) {
            curso.setNombreCurso(nombreCurso);
            curso.setProfesor(profesor);
            cursosDAO.actualizarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso actualizado correctamente.");
            limpiarCampos();
            cargarListaCursos(); // Actualizar la lista después de actualizar un curso
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el curso con ID " + idCurso);
        }
    }

    private void eliminarCurso() {
        int idCurso = Integer.parseInt(idField.getText());
        CursosDTO curso = cursosDAO.obtenerCurso(idCurso);
        if (curso != null) {
            cursosDAO.eliminarCurso(curso);
            JOptionPane.showMessageDialog(this, "Curso eliminado correctamente.");
            limpiarCampos();
            cargarListaCursos(); // Actualizar la lista después de eliminar un curso
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el curso con ID " + idCurso);
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        courseNameField.setText("");
        professorField.setText("");
    }
}
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("AgregarCursoss");
//        frame.setContentPane(new AgregarCursos().AgregarCursososs);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
//}

