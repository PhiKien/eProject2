/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Khachhang;
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
public class KhachhangJpaController implements Serializable {

    public KhachhangJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Khachhang khachhang) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(khachhang);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Khachhang khachhang) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            khachhang = em.merge(khachhang);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = khachhang.getMaKH();
                if (findKhachhang(id) == null) {
                    throw new NonexistentEntityException("The khachhang with id " + id + " no longer exists.");
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
            Khachhang khachhang;
            try {
                khachhang = em.getReference(Khachhang.class, id);
                khachhang.getMaKH();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The khachhang with id " + id + " no longer exists.", enfe);
            }
            em.remove(khachhang);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Khachhang> findKhachhangEntities() {
        return findKhachhangEntities(true, -1, -1);
    }

    public List<Khachhang> findKhachhangEntities(int maxResults, int firstResult) {
        return findKhachhangEntities(false, maxResults, firstResult);
    }

    private List<Khachhang> findKhachhangEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Khachhang.class));
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

    public Khachhang findKhachhang(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Khachhang.class, id);
        } finally {
            em.close();
        }
    }

    public int getKhachhangCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Khachhang> rt = cq.from(Khachhang.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
