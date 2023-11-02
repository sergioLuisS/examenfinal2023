package edu.umg.DAO;

import edu.umg.DTO.InscripcionesDTO;
import edu.umg.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InscripcionesDAO {



    // Método para crear una nueva inscripción
    public static void guardarInscripcion(InscripcionesDTO inscripcion) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(inscripcion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }



    // Método para eliminar una inscripción
    public void eliminarInscripcion(InscripcionesDTO inscripcion) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(inscripcion);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static void eliminarInscripcionPorID(int idInscripcion) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            InscripcionesDTO inscripcion = session.get(InscripcionesDTO.class, idInscripcion);
            if (inscripcion != null) {
                session.delete(inscripcion);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static InscripcionesDTO obtenerInscripcionPorID(int idInscripcion) {
        Session session = Hibernate.getSessionFactory().openSession();
        InscripcionesDTO inscripcion = session.get(InscripcionesDTO.class, idInscripcion);
        session.close();
        return inscripcion;
    }

    public static void actualizarInscripcion(InscripcionesDTO inscripcion) {
        Session session = Hibernate.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(inscripcion);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<InscripcionesDTO> obtenerTodasLasInscripciones() {
        Session session = Hibernate.getSessionFactory().openSession();
        List<InscripcionesDTO> inscripciones = session.createQuery("FROM InscripcionesDTO", InscripcionesDTO.class).list();
        session.close();
        return inscripciones;
    }
}


