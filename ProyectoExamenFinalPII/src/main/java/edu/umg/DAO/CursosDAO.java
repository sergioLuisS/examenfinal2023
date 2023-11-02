package edu.umg.DAO;

import edu.umg.DTO.CursosDTO;
import edu.umg.Hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CursosDAO {
    //Metodo para obtener un curso por su ID
    public CursosDTO obtenerCurso(int idCurso) {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            return session.get(CursosDTO.class, idCurso);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void agregarCurso(CursosDTO curso) {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    //Metodo para Actualizar un Curso
    public void actualizarCurso(CursosDTO curso)
     {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    //Metodo para Eliminar un Curso
    public void eliminarCurso(CursosDTO curso)
    {
        Transaction transaction = null;
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(curso);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
    }
    }



    public List<CursosDTO> obtenerTodosLosCursos() {
        try (Session session = Hibernate.getSessionFactory().openSession()) {
            // Utiliza HQL (Hibernate Query Language) para obtener todos los cursos
            String hql = "FROM CursosDTO";
            Query<CursosDTO> query = session.createQuery(hql, CursosDTO.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
