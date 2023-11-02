package edu.umg.Interfaces;

import com.toedter.calendar.JDateChooser;
import edu.umg.DAO.InscripcionesDAO;
import edu.umg.DTO.CursosDTO;
import edu.umg.DTO.EstudiantesDTO;
import edu.umg.DTO.InscripcionesDTO;
import edu.umg.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Inscripciones extends JFrame {
    private JComboBox<EstudiantesDTO> studentComboBox;
    private JComboBox<CursosDTO> courseComboBox;
    private JButton enrollButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton loadButton;
    private JButton RegresarMenu;
    private JDateChooser dateChooser;  // Campo de fecha de inscripción
    private JTextField idTextField;  // Campo de ID
    private JTextArea inscripcionesTextArea;  // Área de texto para mostrar inscripciones

    public Inscripciones() {
        // Configura la ventana principal
        setTitle("Gestión de Inscripciones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);  // Aumentamos el tamaño de la ventana
        setLayout(new BorderLayout());


        // Panel para la parte superior (formularios)
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2));

        // Componentes de la interfaz gráfica para inscripciones
        JLabel studentLabel = new JLabel("Estudiante:");
        studentComboBox = new JComboBox<>();
        JLabel courseLabel = new JLabel("Curso:");
        courseComboBox = new JComboBox<>();
        dateChooser = new JDateChooser();  // Campo de fecha de inscripción
        idTextField = new JTextField(10);  // Campo de ID
        enrollButton = new JButton("Inscribir");
        deleteButton = new JButton("Eliminar");
        updateButton = new JButton("Actualizar");
        RegresarMenu = new JButton("Regresar Menu");
        loadButton = new JButton("Cargar Inscripciones");


        // Obtén la lista de estudiantes y cursos desde tu base de datos
        List<EstudiantesDTO> estudiantes = obtenerEstudiantesDesdeBD();
        List<CursosDTO> cursos = obtenerCursosDesdeBD();

        // Llena los JComboBox con estudiantes y cursos
        for (EstudiantesDTO estudiante : estudiantes) {
            studentComboBox.addItem(estudiante);
        }
        for (CursosDTO curso : cursos) {
            courseComboBox.addItem(curso);
        }

        // Agregar componentes al panel de formularios
        formPanel.add(studentLabel);
        formPanel.add(studentComboBox);
        formPanel.add(courseLabel);
        formPanel.add(courseComboBox);
        formPanel.add(new JLabel("ID:"));  // Etiqueta para el ID
        formPanel.add(idTextField);  // Campo de ID
        formPanel.add(new JLabel("Fecha de Inscripción:"));  // Etiqueta para la fecha
        formPanel.add(dateChooser);  // Campo de fecha de inscripción
        formPanel.add(enrollButton);
        formPanel.add(deleteButton);
        formPanel.add(updateButton);
        formPanel.add(loadButton);
        formPanel.add(RegresarMenu);

        // Agregar panel de formularios al norte de la ventana
        add(formPanel, BorderLayout.NORTH);

        // Panel para la parte inferior (área de texto)
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BorderLayout());

        inscripcionesTextArea = new JTextArea(10, 40);
        inscripcionesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(inscripcionesTextArea);
        TitledBorder border = BorderFactory.createTitledBorder("Inscripciones");
        scrollPane.setBorder(border);

        textPanel.add(scrollPane);

        // Agregar panel de área de texto al centro de la ventana
        add(textPanel, BorderLayout.CENTER);

        // Configurar ActionListeners para los botones
        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inscribirEstudianteEnCurso();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarInscripcion();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarInscripcion();
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarInscripciones();
            }
        });
        RegresarMenu.addActionListener(new ActionListener() {
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
    // Métodos para inscribir, eliminar, actualizar y cargar inscripciones
    private void inscribirEstudianteEnCurso() {
        EstudiantesDTO estudiante = (EstudiantesDTO) studentComboBox.getSelectedItem();
        CursosDTO curso = (CursosDTO) courseComboBox.getSelectedItem();
        String idText = idTextField.getText(); // Obtener el valor del campo de ID

        if (idText.isEmpty() || estudiante == null || curso == null) {
            JOptionPane.showMessageDialog(this, "Asegúrate de seleccionar un estudiante, un curso y proporcionar un ID.");
        } else {
            // Obtener la fecha de inscripción del dateChooser
            Date selectedDate = dateChooser.getDate();
            Timestamp fechaInscripcion = new Timestamp(selectedDate.getTime());

            // Crear una nueva inscripción utilizando InscripcionesDTO
            InscripcionesDTO inscripcion = new InscripcionesDTO();
            inscripcion.setIdEstudiante(estudiante.getId());
            inscripcion.setIdCurso(curso.getId());
            inscripcion.setFechaInscripcion(fechaInscripcion);

            // Asignar el ID ingresado
            inscripcion.setIdInscripcion(Integer.parseInt(idText)); // Asume que el ID es un entero

            // Utilizar el DAO para guardar la inscripción en la base de datos
            InscripcionesDAO.guardarInscripcion(inscripcion);

            JOptionPane.showMessageDialog(this, "Estudiante inscrito en el curso correctamente.");
            limpiarCampos();
        }
    }

    private void eliminarInscripcion() {
        // Obtener el ID de la inscripción a eliminar desde el campo idTextField
        String idText = idTextField.getText();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido para eliminar la inscripción.");
        } else {
            try {
                int idInscripcion = Integer.parseInt(idText);

                // Llama al método de tu DAO para eliminar la inscripción
                InscripcionesDAO.eliminarInscripcionPorID(idInscripcion);

                JOptionPane.showMessageDialog(this, "Inscripción eliminada correctamente.");
                limpiarCampos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
            }
        }
    }

    private void actualizarInscripcion() {
        // Obtener el ID de la inscripción a actualizar desde el campo idTextField
        String idText = idTextField.getText();

        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID válido para actualizar la inscripción.");
        } else {
            try {
                int idInscripcion = Integer.parseInt(idText);

                // Llama al método de tu DAO para obtener la inscripción por ID
                InscripcionesDTO inscripcion = InscripcionesDAO.obtenerInscripcionPorID(idInscripcion);

                if (inscripcion != null) {
                    // Realiza aquí la lógica para actualizar la inscripción
                    // Puedes modificar los campos que desees de 'inscripcion'
                    // Luego, llama al método de tu DAO para actualizarla
                    InscripcionesDAO.actualizarInscripcion(inscripcion);

                    JOptionPane.showMessageDialog(this, "Inscripción actualizada correctamente.");
                    limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró ninguna inscripción con ese ID.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
            }
        }
    }

    private void cargarInscripciones() {
        // Llama al método de tu DAO para obtener la lista de inscripciones desde la base de datos
        List<InscripcionesDTO> inscripciones = InscripcionesDAO.obtenerTodasLasInscripciones();

        // Limpia el área de texto
        inscripcionesTextArea.setText("");

        // Agrega las inscripciones al área de texto
        for (InscripcionesDTO inscripcion : inscripciones) {
            inscripcionesTextArea.append("ID: " + inscripcion.getIdInscripcion() + "\n");
            inscripcionesTextArea.append("Estudiante: " + inscripcion.getIdEstudiante() + "\n");
            inscripcionesTextArea.append("Curso: " + inscripcion.getIdCurso() + "\n");
            inscripcionesTextArea.append("Fecha de Inscripción: " + inscripcion.getFechaInscripcion() + "\n");
            inscripcionesTextArea.append("\n");
        }
    }


    private List<EstudiantesDTO> obtenerEstudiantesDesdeBD() {
        List<EstudiantesDTO> estudiantes = null;

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // Utiliza una consulta HQL para obtener la lista de estudiantes desde la base de datos
            Query<EstudiantesDTO> query = session.createQuery("FROM EstudiantesDTO", EstudiantesDTO.class);
            estudiantes = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return estudiantes;
    }

    private List<CursosDTO> obtenerCursosDesdeBD() {
        List<CursosDTO> cursos = null;

        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // Utiliza una consulta HQL para obtener la lista de cursos desde la base de datos
            Query<CursosDTO> query = session.createQuery("FROM CursosDTO", CursosDTO.class);
            cursos = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cursos;
    }

    private void limpiarCampos() {
        studentComboBox.setSelectedIndex(0);
        courseComboBox.setSelectedIndex(0);
        dateChooser.setCalendar(null);  // Restablece el campo de fecha
        idTextField.setText("");  // Limpiar el campo de ID
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Inscripciones app = new Inscripciones();
//                app.setVisible(true);
//            }
//        });
//    }
}




