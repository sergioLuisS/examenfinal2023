package edu.umg.Interfaces;

import edu.umg.DAO.EstudiantesDAO;
import edu.umg.DTO.EstudiantesDTO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class AgregarEstudiantes extends JFrame {
    private EstudiantesDAO estudiantesDAO = new EstudiantesDAO();
    private JTextField idField;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JButton updateButton;
    private JButton addButton;
    private JButton deleteButton;
    private JTextArea textArea1;
    private JScrollPane scrollPane;
    private JPanel AgregarEstudiantes;
    private JButton buttonRegresarMenu;

    public AgregarEstudiantes() {
           setContentPane(AgregarEstudiantes);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        // Cargar la lista de estudiantes al abrir la ventana
        cargarListaEstudiantes();

        // Configurar ActionListener para los botones
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarEstudiante();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEstudiante();
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

    private void cargarListaEstudiantes() {
        List<EstudiantesDTO> estudiantes = estudiantesDAO.obtenerTodosLosEstudiantes();
        textArea1.setText(""); // Limpia el contenido anterior del JTextArea
        for (EstudiantesDTO estudiante : estudiantes) {
            textArea1.append("ID: " + estudiante.getIdEstudiante() + "\n");
            textArea1.append("Nombre: " + estudiante.getNombre() + "\n");
            textArea1.append("Apellido: " + estudiante.getApellido() + "\n");
            textArea1.append("Email: " + estudiante.getEmail() + "\n");
            textArea1.append("\n");
        }
    }

    private void agregarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();
        EstudiantesDTO estudiante = new EstudiantesDTO();
        estudiante.setIdEstudiante(id); // Establece el ID del estudiante.
        estudiante.setNombre(nombre);
        estudiante.setApellido(apellido);
        estudiante.setEmail(email);
        estudiantesDAO.guardarEstudiante(estudiante);
        JOptionPane.showMessageDialog(this, "Estudiante agregado correctamente.");
        limpiarCampos();
        cargarListaEstudiantes();

    }

    private void actualizarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        String nombre = nameField.getText();
        String apellido = lastNameField.getText();
        String email = emailField.getText();
        EstudiantesDTO estudiante = estudiantesDAO.obtenerEstudiante(id);
        if (estudiante != null) {
            estudiante.setNombre(nombre);
            estudiante.setApellido(apellido);
            estudiante.setEmail(email);
            estudiantesDAO.actualizarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante actualizado correctamente.");
            limpiarCampos();
            cargarListaEstudiantes();

        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el estudiante con ID " + id);
        }
    }

    private void eliminarEstudiante() {
        int id = Integer.parseInt(idField.getText());
        EstudiantesDTO estudiante = estudiantesDAO.obtenerEstudiante(id);
        if (estudiante != null) {
            estudiantesDAO.eliminarEstudiante(estudiante);
            JOptionPane.showMessageDialog(this, "Estudiante eliminado correctamente.");
            limpiarCampos();
            cargarListaEstudiantes();

        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el estudiante con ID " + id);
        }
    }

    private void limpiarCampos() {
        idField.setText("");
        nameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
    }
    public void AgregarEstudiantes() {
        JFrame frame = new JFrame("AgregarEstudiantes");
        frame.setContentPane(new AgregarEstudiantes().AgregarEstudiantes);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


//    public static void main(String[] args) {
//        JFrame frame = new JFrame("AgregarEstudiantes");
//        frame.setContentPane(new AgregarEstudiantes().AgregarEstudiantes);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
}




