/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Hoadon;
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
public class HoadonJpaController implements Serializable {

    public HoadonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Hoadon hoadon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(hoadon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hoadon hoadon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            hoadon = em.merge(hoadon);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = hoadon.getMaHD();
                if (findHoadon(id) == null) {
                    throw new NonexistentEntityException("The hoadon with id " + id + " no longer exists.");
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
            Hoadon hoadon;
            try {
                hoadon = em.getReference(Hoadon.class, id);
                hoadon.getMaHD();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hoadon with id " + id + " no longer exists.", enfe);
            }
            em.remove(hoadon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Hoadon> findHoadonEntities() {
        return findHoadonEntities(true, -1, -1);
    }

    public List<Hoadon> findHoadonEntities(int maxResults, int firstResult) {
        return findHoadonEntities(false, maxResults, firstResult);
    }

    private List<Hoadon> findHoadonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Hoadon.class));
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

    public Hoadon findHoadon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Hoadon.class, id);
        } finally {
            em.close();
        }
    }

    public int getHoadonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Hoadon> rt = cq.from(Hoadon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
