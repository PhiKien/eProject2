/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Nhanvien;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author drago
 */
public class NhanvienJpaController implements Serializable {

    public NhanvienJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nhanvien nhanvien) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(nhanvien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nhanvien nhanvien) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            nhanvien = em.merge(nhanvien);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nhanvien.getMaNV();
                if (findNhanvien(id) == null) {
                    throw new NonexistentEntityException("The nhanvien with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhanvien nhanvien;
            try {
                nhanvien = em.getReference(Nhanvien.class, id);
                nhanvien.getMaNV();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nhanvien with id " + id + " no longer exists.", enfe);
            }
            em.remove(nhanvien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nhanvien> findNhanvienEntities() {
        return findNhanvienEntities(true, -1, -1);
    }

    public List<Nhanvien> findNhanvienEntities(int maxResults, int firstResult) {
        return findNhanvienEntities(false, maxResults, firstResult);
    }

    private List<Nhanvien> findNhanvienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nhanvien.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Nhanvien findNhanvien(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nhanvien.class, id);
        } finally {
            em.close();
        }
    }

    public int getNhanvienCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nhanvien> rt = cq.from(Nhanvien.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
