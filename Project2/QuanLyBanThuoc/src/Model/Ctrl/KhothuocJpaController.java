/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Nhomthuoc;
import Model.Chitiethoadon;
import Model.Ctrl.exceptions.IllegalOrphanException;
import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Khothuoc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (khothuoc.getChitiethoadonCollection() == null) {
            khothuoc.setChitiethoadonCollection(new ArrayList<Chitiethoadon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhomthuoc maNhomThuoc = khothuoc.getMaNhomThuoc();
            if (maNhomThuoc != null) {
                maNhomThuoc = em.getReference(maNhomThuoc.getClass(), maNhomThuoc.getMaNhomThuoc());
                khothuoc.setMaNhomThuoc(maNhomThuoc);
            }
            Collection<Chitiethoadon> attachedChitiethoadonCollection = new ArrayList<Chitiethoadon>();
            for (Chitiethoadon chitiethoadonCollectionChitiethoadonToAttach : khothuoc.getChitiethoadonCollection()) {
                chitiethoadonCollectionChitiethoadonToAttach = em.getReference(chitiethoadonCollectionChitiethoadonToAttach.getClass(), chitiethoadonCollectionChitiethoadonToAttach.getChitiethoadonPK());
                attachedChitiethoadonCollection.add(chitiethoadonCollectionChitiethoadonToAttach);
            }
            khothuoc.setChitiethoadonCollection(attachedChitiethoadonCollection);
            em.persist(khothuoc);
            if (maNhomThuoc != null) {
                maNhomThuoc.getKhothuocCollection().add(khothuoc);
                maNhomThuoc = em.merge(maNhomThuoc);
            }
            for (Chitiethoadon chitiethoadonCollectionChitiethoadon : khothuoc.getChitiethoadonCollection()) {
                Khothuoc oldKhothuocOfChitiethoadonCollectionChitiethoadon = chitiethoadonCollectionChitiethoadon.getKhothuoc();
                chitiethoadonCollectionChitiethoadon.setKhothuoc(khothuoc);
                chitiethoadonCollectionChitiethoadon = em.merge(chitiethoadonCollectionChitiethoadon);
                if (oldKhothuocOfChitiethoadonCollectionChitiethoadon != null) {
                    oldKhothuocOfChitiethoadonCollectionChitiethoadon.getChitiethoadonCollection().remove(chitiethoadonCollectionChitiethoadon);
                    oldKhothuocOfChitiethoadonCollectionChitiethoadon = em.merge(oldKhothuocOfChitiethoadonCollectionChitiethoadon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Khothuoc khothuoc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Khothuoc persistentKhothuoc = em.find(Khothuoc.class, khothuoc.getMaThuoc());
            Nhomthuoc maNhomThuocOld = persistentKhothuoc.getMaNhomThuoc();
            Nhomthuoc maNhomThuocNew = khothuoc.getMaNhomThuoc();
            Collection<Chitiethoadon> chitiethoadonCollectionOld = persistentKhothuoc.getChitiethoadonCollection();
            Collection<Chitiethoadon> chitiethoadonCollectionNew = khothuoc.getChitiethoadonCollection();
            List<String> illegalOrphanMessages = null;
            for (Chitiethoadon chitiethoadonCollectionOldChitiethoadon : chitiethoadonCollectionOld) {
                if (!chitiethoadonCollectionNew.contains(chitiethoadonCollectionOldChitiethoadon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chitiethoadon " + chitiethoadonCollectionOldChitiethoadon + " since its khothuoc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (maNhomThuocNew != null) {
                maNhomThuocNew = em.getReference(maNhomThuocNew.getClass(), maNhomThuocNew.getMaNhomThuoc());
                khothuoc.setMaNhomThuoc(maNhomThuocNew);
            }
            Collection<Chitiethoadon> attachedChitiethoadonCollectionNew = new ArrayList<Chitiethoadon>();
            for (Chitiethoadon chitiethoadonCollectionNewChitiethoadonToAttach : chitiethoadonCollectionNew) {
                chitiethoadonCollectionNewChitiethoadonToAttach = em.getReference(chitiethoadonCollectionNewChitiethoadonToAttach.getClass(), chitiethoadonCollectionNewChitiethoadonToAttach.getChitiethoadonPK());
                attachedChitiethoadonCollectionNew.add(chitiethoadonCollectionNewChitiethoadonToAttach);
            }
            chitiethoadonCollectionNew = attachedChitiethoadonCollectionNew;
            khothuoc.setChitiethoadonCollection(chitiethoadonCollectionNew);
            khothuoc = em.merge(khothuoc);
            if (maNhomThuocOld != null && !maNhomThuocOld.equals(maNhomThuocNew)) {
                maNhomThuocOld.getKhothuocCollection().remove(khothuoc);
                maNhomThuocOld = em.merge(maNhomThuocOld);
            }
            if (maNhomThuocNew != null && !maNhomThuocNew.equals(maNhomThuocOld)) {
                maNhomThuocNew.getKhothuocCollection().add(khothuoc);
                maNhomThuocNew = em.merge(maNhomThuocNew);
            }
            for (Chitiethoadon chitiethoadonCollectionNewChitiethoadon : chitiethoadonCollectionNew) {
                if (!chitiethoadonCollectionOld.contains(chitiethoadonCollectionNewChitiethoadon)) {
                    Khothuoc oldKhothuocOfChitiethoadonCollectionNewChitiethoadon = chitiethoadonCollectionNewChitiethoadon.getKhothuoc();
                    chitiethoadonCollectionNewChitiethoadon.setKhothuoc(khothuoc);
                    chitiethoadonCollectionNewChitiethoadon = em.merge(chitiethoadonCollectionNewChitiethoadon);
                    if (oldKhothuocOfChitiethoadonCollectionNewChitiethoadon != null && !oldKhothuocOfChitiethoadonCollectionNewChitiethoadon.equals(khothuoc)) {
                        oldKhothuocOfChitiethoadonCollectionNewChitiethoadon.getChitiethoadonCollection().remove(chitiethoadonCollectionNewChitiethoadon);
                        oldKhothuocOfChitiethoadonCollectionNewChitiethoadon = em.merge(oldKhothuocOfChitiethoadonCollectionNewChitiethoadon);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Chitiethoadon> chitiethoadonCollectionOrphanCheck = khothuoc.getChitiethoadonCollection();
            for (Chitiethoadon chitiethoadonCollectionOrphanCheckChitiethoadon : chitiethoadonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Khothuoc (" + khothuoc + ") cannot be destroyed since the Chitiethoadon " + chitiethoadonCollectionOrphanCheckChitiethoadon + " in its chitiethoadonCollection field has a non-nullable khothuoc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Nhomthuoc maNhomThuoc = khothuoc.getMaNhomThuoc();
            if (maNhomThuoc != null) {
                maNhomThuoc.getKhothuocCollection().remove(khothuoc);
                maNhomThuoc = em.merge(maNhomThuoc);
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
