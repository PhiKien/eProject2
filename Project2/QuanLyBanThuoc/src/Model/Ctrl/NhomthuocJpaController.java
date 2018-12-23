/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Ctrl;

import Model.Ctrl.exceptions.IllegalOrphanException;
import Model.Ctrl.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Model.Khothuoc;
import Model.Nhomthuoc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author drago
 */
public class NhomthuocJpaController implements Serializable {

    public NhomthuocJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Nhomthuoc nhomthuoc) {
        if (nhomthuoc.getKhothuocCollection() == null) {
            nhomthuoc.setKhothuocCollection(new ArrayList<Khothuoc>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Khothuoc> attachedKhothuocCollection = new ArrayList<Khothuoc>();
            for (Khothuoc khothuocCollectionKhothuocToAttach : nhomthuoc.getKhothuocCollection()) {
                khothuocCollectionKhothuocToAttach = em.getReference(khothuocCollectionKhothuocToAttach.getClass(), khothuocCollectionKhothuocToAttach.getMaThuoc());
                attachedKhothuocCollection.add(khothuocCollectionKhothuocToAttach);
            }
            nhomthuoc.setKhothuocCollection(attachedKhothuocCollection);
            em.persist(nhomthuoc);
            for (Khothuoc khothuocCollectionKhothuoc : nhomthuoc.getKhothuocCollection()) {
                Nhomthuoc oldMaNhomThuocOfKhothuocCollectionKhothuoc = khothuocCollectionKhothuoc.getMaNhomThuoc();
                khothuocCollectionKhothuoc.setMaNhomThuoc(nhomthuoc);
                khothuocCollectionKhothuoc = em.merge(khothuocCollectionKhothuoc);
                if (oldMaNhomThuocOfKhothuocCollectionKhothuoc != null) {
                    oldMaNhomThuocOfKhothuocCollectionKhothuoc.getKhothuocCollection().remove(khothuocCollectionKhothuoc);
                    oldMaNhomThuocOfKhothuocCollectionKhothuoc = em.merge(oldMaNhomThuocOfKhothuocCollectionKhothuoc);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nhomthuoc nhomthuoc) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhomthuoc persistentNhomthuoc = em.find(Nhomthuoc.class, nhomthuoc.getMaNhomThuoc());
            Collection<Khothuoc> khothuocCollectionOld = persistentNhomthuoc.getKhothuocCollection();
            Collection<Khothuoc> khothuocCollectionNew = nhomthuoc.getKhothuocCollection();
            List<String> illegalOrphanMessages = null;
            for (Khothuoc khothuocCollectionOldKhothuoc : khothuocCollectionOld) {
                if (!khothuocCollectionNew.contains(khothuocCollectionOldKhothuoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Khothuoc " + khothuocCollectionOldKhothuoc + " since its maNhomThuoc field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Khothuoc> attachedKhothuocCollectionNew = new ArrayList<Khothuoc>();
            for (Khothuoc khothuocCollectionNewKhothuocToAttach : khothuocCollectionNew) {
                khothuocCollectionNewKhothuocToAttach = em.getReference(khothuocCollectionNewKhothuocToAttach.getClass(), khothuocCollectionNewKhothuocToAttach.getMaThuoc());
                attachedKhothuocCollectionNew.add(khothuocCollectionNewKhothuocToAttach);
            }
            khothuocCollectionNew = attachedKhothuocCollectionNew;
            nhomthuoc.setKhothuocCollection(khothuocCollectionNew);
            nhomthuoc = em.merge(nhomthuoc);
            for (Khothuoc khothuocCollectionNewKhothuoc : khothuocCollectionNew) {
                if (!khothuocCollectionOld.contains(khothuocCollectionNewKhothuoc)) {
                    Nhomthuoc oldMaNhomThuocOfKhothuocCollectionNewKhothuoc = khothuocCollectionNewKhothuoc.getMaNhomThuoc();
                    khothuocCollectionNewKhothuoc.setMaNhomThuoc(nhomthuoc);
                    khothuocCollectionNewKhothuoc = em.merge(khothuocCollectionNewKhothuoc);
                    if (oldMaNhomThuocOfKhothuocCollectionNewKhothuoc != null && !oldMaNhomThuocOfKhothuocCollectionNewKhothuoc.equals(nhomthuoc)) {
                        oldMaNhomThuocOfKhothuocCollectionNewKhothuoc.getKhothuocCollection().remove(khothuocCollectionNewKhothuoc);
                        oldMaNhomThuocOfKhothuocCollectionNewKhothuoc = em.merge(oldMaNhomThuocOfKhothuocCollectionNewKhothuoc);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = nhomthuoc.getMaNhomThuoc();
                if (findNhomthuoc(id) == null) {
                    throw new NonexistentEntityException("The nhomthuoc with id " + id + " no longer exists.");
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
            Nhomthuoc nhomthuoc;
            try {
                nhomthuoc = em.getReference(Nhomthuoc.class, id);
                nhomthuoc.getMaNhomThuoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The nhomthuoc with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Khothuoc> khothuocCollectionOrphanCheck = nhomthuoc.getKhothuocCollection();
            for (Khothuoc khothuocCollectionOrphanCheckKhothuoc : khothuocCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Nhomthuoc (" + nhomthuoc + ") cannot be destroyed since the Khothuoc " + khothuocCollectionOrphanCheckKhothuoc + " in its khothuocCollection field has a non-nullable maNhomThuoc field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(nhomthuoc);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Nhomthuoc> findNhomthuocEntities() {
        return findNhomthuocEntities(true, -1, -1);
    }

    public List<Nhomthuoc> findNhomthuocEntities(int maxResults, int firstResult) {
        return findNhomthuocEntities(false, maxResults, firstResult);
    }

    private List<Nhomthuoc> findNhomthuocEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Nhomthuoc.class));
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

    public Nhomthuoc findNhomthuoc(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Nhomthuoc.class, id);
        } finally {
            em.close();
        }
    }

    public int getNhomthuocCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Nhomthuoc> rt = cq.from(Nhomthuoc.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
