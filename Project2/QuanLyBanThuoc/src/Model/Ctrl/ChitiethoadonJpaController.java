/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Chitiethoadon;
import Model.ChitiethoadonPK;
import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Ctrl.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Hoadon;
import Model.Khothuoc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public void create(Chitiethoadon chitiethoadon) throws PreexistingEntityException, Exception {
        if (chitiethoadon.getChitiethoadonPK() == null) {
            chitiethoadon.setChitiethoadonPK(new ChitiethoadonPK());
        }
        chitiethoadon.getChitiethoadonPK().setMaThuoc(chitiethoadon.getKhothuoc().getMaThuoc());
        chitiethoadon.getChitiethoadonPK().setMaHD(chitiethoadon.getHoadon().getMaHD());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hoadon hoadon = chitiethoadon.getHoadon();
            if (hoadon != null) {
                hoadon = em.getReference(hoadon.getClass(), hoadon.getMaHD());
                chitiethoadon.setHoadon(hoadon);
            }
            Khothuoc khothuoc = chitiethoadon.getKhothuoc();
            if (khothuoc != null) {
                khothuoc = em.getReference(khothuoc.getClass(), khothuoc.getMaThuoc());
                chitiethoadon.setKhothuoc(khothuoc);
            }
            em.persist(chitiethoadon);
            if (hoadon != null) {
                hoadon.getChitiethoadonCollection().add(chitiethoadon);
                hoadon = em.merge(hoadon);
            }
            if (khothuoc != null) {
                khothuoc.getChitiethoadonCollection().add(chitiethoadon);
                khothuoc = em.merge(khothuoc);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findChitiethoadon(chitiethoadon.getChitiethoadonPK()) != null) {
                throw new PreexistingEntityException("Chitiethoadon " + chitiethoadon + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chitiethoadon chitiethoadon) throws NonexistentEntityException, Exception {
        chitiethoadon.getChitiethoadonPK().setMaThuoc(chitiethoadon.getKhothuoc().getMaThuoc());
        chitiethoadon.getChitiethoadonPK().setMaHD(chitiethoadon.getHoadon().getMaHD());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chitiethoadon persistentChitiethoadon = em.find(Chitiethoadon.class, chitiethoadon.getChitiethoadonPK());
            Hoadon hoadonOld = persistentChitiethoadon.getHoadon();
            Hoadon hoadonNew = chitiethoadon.getHoadon();
            Khothuoc khothuocOld = persistentChitiethoadon.getKhothuoc();
            Khothuoc khothuocNew = chitiethoadon.getKhothuoc();
            if (hoadonNew != null) {
                hoadonNew = em.getReference(hoadonNew.getClass(), hoadonNew.getMaHD());
                chitiethoadon.setHoadon(hoadonNew);
            }
            if (khothuocNew != null) {
                khothuocNew = em.getReference(khothuocNew.getClass(), khothuocNew.getMaThuoc());
                chitiethoadon.setKhothuoc(khothuocNew);
            }
            chitiethoadon = em.merge(chitiethoadon);
            if (hoadonOld != null && !hoadonOld.equals(hoadonNew)) {
                hoadonOld.getChitiethoadonCollection().remove(chitiethoadon);
                hoadonOld = em.merge(hoadonOld);
            }
            if (hoadonNew != null && !hoadonNew.equals(hoadonOld)) {
                hoadonNew.getChitiethoadonCollection().add(chitiethoadon);
                hoadonNew = em.merge(hoadonNew);
            }
            if (khothuocOld != null && !khothuocOld.equals(khothuocNew)) {
                khothuocOld.getChitiethoadonCollection().remove(chitiethoadon);
                khothuocOld = em.merge(khothuocOld);
            }
            if (khothuocNew != null && !khothuocNew.equals(khothuocOld)) {
                khothuocNew.getChitiethoadonCollection().add(chitiethoadon);
                khothuocNew = em.merge(khothuocNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ChitiethoadonPK id = chitiethoadon.getChitiethoadonPK();
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

    public void destroy(ChitiethoadonPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Chitiethoadon chitiethoadon;
            try {
                chitiethoadon = em.getReference(Chitiethoadon.class, id);
                chitiethoadon.getChitiethoadonPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chitiethoadon with id " + id + " no longer exists.", enfe);
            }
            Hoadon hoadon = chitiethoadon.getHoadon();
            if (hoadon != null) {
                hoadon.getChitiethoadonCollection().remove(chitiethoadon);
                hoadon = em.merge(hoadon);
            }
            Khothuoc khothuoc = chitiethoadon.getKhothuoc();
            if (khothuoc != null) {
                khothuoc.getChitiethoadonCollection().remove(chitiethoadon);
                khothuoc = em.merge(khothuoc);
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

    public Chitiethoadon findChitiethoadon(ChitiethoadonPK id) {
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
