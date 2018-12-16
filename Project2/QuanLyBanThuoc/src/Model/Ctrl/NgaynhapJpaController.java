/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Ngaynhap;
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
public class NgaynhapJpaController implements Serializable {

    public NgaynhapJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ngaynhap ngaynhap) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ngaynhap);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ngaynhap ngaynhap) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ngaynhap = em.merge(ngaynhap);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ngaynhap.getMaNhap();
                if (findNgaynhap(id) == null) {
                    throw new NonexistentEntityException("The ngaynhap with id " + id + " no longer exists.");
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
            Ngaynhap ngaynhap;
            try {
                ngaynhap = em.getReference(Ngaynhap.class, id);
                ngaynhap.getMaNhap();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ngaynhap with id " + id + " no longer exists.", enfe);
            }
            em.remove(ngaynhap);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ngaynhap> findNgaynhapEntities() {
        return findNgaynhapEntities(true, -1, -1);
    }

    public List<Ngaynhap> findNgaynhapEntities(int maxResults, int firstResult) {
        return findNgaynhapEntities(false, maxResults, firstResult);
    }

    private List<Ngaynhap> findNgaynhapEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ngaynhap.class));
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

    public Ngaynhap findNgaynhap(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ngaynhap.class, id);
        } finally {
            em.close();
        }
    }

    public int getNgaynhapCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ngaynhap> rt = cq.from(Ngaynhap.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
