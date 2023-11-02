package edu.umg.Interfaces;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuOpciones extends JFrame {
    private JPanel MenuOpciones;
    private JButton buttonAgregarEstudiantes;
    private JButton buttonAgregarCursos;
    private JButton buttonInscripciones;
    private JButton buttonCerrarSecion;

    public MenuOpciones() {
        setContentPane(MenuOpciones);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);


        // Registra ActionListener para los botones
        buttonAgregarEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAgregarEstudiantes();
            }
        });

        buttonAgregarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaAgregarCursos();
            }
        });

        buttonInscripciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaInscripciones();
            }
        });
        buttonCerrarSecion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaCerrarSecion();
            }
        });

    }



    private void abrirVentanaCerrarSecion() {
        // Crea y muestra la ventana de Cerrar Secion
       MenuOpciones cerrarSecionForm = new MenuOpciones();
        cerrarSecionForm.setVisible(true);
        dispose();
    }
    private void abrirVentanaAgregarEstudiantes() {
        // Crea y muestra la ventana de Agregar Estudiantes
        AgregarEstudiantes agregarEstudiantesForm = new AgregarEstudiantes();
        agregarEstudiantesForm.setVisible(true);
        dispose();
    }

    private void abrirVentanaAgregarCursos() {
        // Crea y muestra la ventana de Agregar Cursos
        AgregarCursos agregarCursosForm = new AgregarCursos();
        agregarCursosForm.setVisible(true);
        dispose();
    }

    private void abrirVentanaInscripciones() {
        // Crea y muestra la ventana de Inscripciones
        Inscripciones inscripcionesForm = new Inscripciones();
        inscripcionesForm.setVisible(true);
        dispose();
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("MenuOpciones");
//        frame.setContentPane(new MenuOpciones().MenuOpciones);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }
}



