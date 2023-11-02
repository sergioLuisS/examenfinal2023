package edu.umg.Hibernate;

import edu.umg.DTO.CursosDTO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Hibernate {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Crea la fábrica de sesiones a partir de la configuración de hibernate.cfg.xml
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(CursosDTO.class); // Agrega la clase mapeada
            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Error al crear la fábrica de sesiones: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
