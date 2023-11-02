package edu.umg.DAO;

import edu.umg.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UsuariosDAO {

    // Método para autenticar a un usuario
    public boolean autenticarUsuario(String nombreUsuario, String contrasena) {
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


    }}