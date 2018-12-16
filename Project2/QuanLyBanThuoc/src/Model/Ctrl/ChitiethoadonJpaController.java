/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Chitiethoadon;
import Model.Ctrl.exceptions.NonexistentEntityException;
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
public class ChitiethoadonJpaController implements Serializable {

    public ChitiethoadonJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Chitiethoadon chitiethoadon) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(chitiethoadon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chitiethoadon chitiethoadon) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            chitiethoadon = em.merge(chitiethoadon);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = chitiethoadon.getMaChiTietHD();
                if (findChitiethoadon(id) == null) {
                    throw new NonexistentEntityException("The chitiethoadon with id " + id + " no longer exists.");
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
            Chitiethoadon chitiethoadon;
            try {
                chitiethoadon = em.getReference(Chitiethoadon.class, id);
                chitiethoadon.getMaChiTietHD();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chitiethoadon with id " + id + " no longer exists.", enfe);
            }
            em.remove(chitiethoadon);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chitiethoadon> findChitiethoadonEntities() {
        return findChitiethoadonEntities(true, -1, -1);
    }

    public List<Chitiethoadon> findChitiethoadonEntities(int maxResults, int firstResult) {
        return findChitiethoadonEntities(false, maxResults, firstResult);
    }

    private List<Chitiethoadon> findChitiethoadonEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chitiethoadon.class));
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

    public Chitiethoadon findChitiethoadon(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chitiethoadon.class, id);
        } finally {
            em.close();
        }
    }

    public int getChitiethoadonCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chitiethoadon> rt = cq.from(Chitiethoadon.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
