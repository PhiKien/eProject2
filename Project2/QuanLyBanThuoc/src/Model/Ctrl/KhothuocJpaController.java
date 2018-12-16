/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Khothuoc;
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
public class KhothuocJpaController implements Serializable {

    public KhothuocJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Khothuoc khothuoc) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(khothuoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Khothuoc khothuoc) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            khothuoc = em.merge(khothuoc);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = khothuoc.getMaThuoc();
                if (findKhothuoc(id) == null) {
                    throw new NonexistentEntityException("The khothuoc with id " + id + " no longer exists.");
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
            Khothuoc khothuoc;
            try {
                khothuoc = em.getReference(Khothuoc.class, id);
                khothuoc.getMaThuoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The khothuoc with id " + id + " no longer exists.", enfe);
            }
            em.remove(khothuoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Khothuoc> findKhothuocEntities() {
        return findKhothuocEntities(true, -1, -1);
    }

    public List<Khothuoc> findKhothuocEntities(int maxResults, int firstResult) {
        return findKhothuocEntities(false, maxResults, firstResult);
    }

    private List<Khothuoc> findKhothuocEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Khothuoc.class));
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

    public Khothuoc findKhothuoc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Khothuoc.class, id);
        } finally {
            em.close();
        }
    }

    public int getKhothuocCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Khothuoc> rt = cq.from(Khothuoc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
