package edu.umg.Interfaces;

import edu.umg.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login  extends JFrame{
    private JPanel Login;
    private JButton buttonIngresar;
    private JPasswordField passwordField1;
    private JTextField textFieldUsuario;

    public Login() {
        buttonIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = textFieldUsuario.getText();
                String contrasena = new String(passwordField1.getPassword());

                // Lógica de autenticación con Hibernate
                if (autenticarUsuario(nombreUsuario, contrasena)) {

                    dispose();
                    MenuOpciones menuOpciones = new MenuOpciones();
                    menuOpciones.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        });
    }

    // Método para autenticar al usuario
    private boolean autenticarUsuario(String nombreUsuario, String contrasena) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            Query<Long> query = session.createQuery(
                    "SELECT COUNT(u.id) FROM UsuariosDTO u " +
                            "WHERE u.nombreUsuario = :nombreUsuario AND u.contrasena = :contrasena",
                    Long.class
            );
            query.setParameter("nombreUsuario", nombreUsuario);
            query.setParameter("contrasena", contrasena);
            Long count = query.uniqueResult();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().Login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
