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
import Model.Hoadon;
import Model.Nhanvien;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (nhanvien.getHoadonCollection() == null) {
            nhanvien.setHoadonCollection(new ArrayList<Hoadon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Hoadon> attachedHoadonCollection = new ArrayList<Hoadon>();
            for (Hoadon hoadonCollectionHoadonToAttach : nhanvien.getHoadonCollection()) {
                hoadonCollectionHoadonToAttach = em.getReference(hoadonCollectionHoadonToAttach.getClass(), hoadonCollectionHoadonToAttach.getMaHD());
                attachedHoadonCollection.add(hoadonCollectionHoadonToAttach);
            }
            nhanvien.setHoadonCollection(attachedHoadonCollection);
            em.persist(nhanvien);
            for (Hoadon hoadonCollectionHoadon : nhanvien.getHoadonCollection()) {
                Nhanvien oldMaNVOfHoadonCollectionHoadon = hoadonCollectionHoadon.getMaNV();
                hoadonCollectionHoadon.setMaNV(nhanvien);
                hoadonCollectionHoadon = em.merge(hoadonCollectionHoadon);
                if (oldMaNVOfHoadonCollectionHoadon != null) {
                    oldMaNVOfHoadonCollectionHoadon.getHoadonCollection().remove(hoadonCollectionHoadon);
                    oldMaNVOfHoadonCollectionHoadon = em.merge(oldMaNVOfHoadonCollectionHoadon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Nhanvien nhanvien) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhanvien persistentNhanvien = em.find(Nhanvien.class, nhanvien.getMaNV());
            Collection<Hoadon> hoadonCollectionOld = persistentNhanvien.getHoadonCollection();
            Collection<Hoadon> hoadonCollectionNew = nhanvien.getHoadonCollection();
            List<String> illegalOrphanMessages = null;
            for (Hoadon hoadonCollectionOldHoadon : hoadonCollectionOld) {
                if (!hoadonCollectionNew.contains(hoadonCollectionOldHoadon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Hoadon " + hoadonCollectionOldHoadon + " since its maNV field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Hoadon> attachedHoadonCollectionNew = new ArrayList<Hoadon>();
            for (Hoadon hoadonCollectionNewHoadonToAttach : hoadonCollectionNew) {
                hoadonCollectionNewHoadonToAttach = em.getReference(hoadonCollectionNewHoadonToAttach.getClass(), hoadonCollectionNewHoadonToAttach.getMaHD());
                attachedHoadonCollectionNew.add(hoadonCollectionNewHoadonToAttach);
            }
            hoadonCollectionNew = attachedHoadonCollectionNew;
            nhanvien.setHoadonCollection(hoadonCollectionNew);
            nhanvien = em.merge(nhanvien);
            for (Hoadon hoadonCollectionNewHoadon : hoadonCollectionNew) {
                if (!hoadonCollectionOld.contains(hoadonCollectionNewHoadon)) {
                    Nhanvien oldMaNVOfHoadonCollectionNewHoadon = hoadonCollectionNewHoadon.getMaNV();
                    hoadonCollectionNewHoadon.setMaNV(nhanvien);
                    hoadonCollectionNewHoadon = em.merge(hoadonCollectionNewHoadon);
                    if (oldMaNVOfHoadonCollectionNewHoadon != null && !oldMaNVOfHoadonCollectionNewHoadon.equals(nhanvien)) {
                        oldMaNVOfHoadonCollectionNewHoadon.getHoadonCollection().remove(hoadonCollectionNewHoadon);
                        oldMaNVOfHoadonCollectionNewHoadon = em.merge(oldMaNVOfHoadonCollectionNewHoadon);
                    }
                }
            }
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
    
    public void editNhanVien(Nhanvien nhanVien){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Nhanvien oldNhanvien = em.find(Nhanvien.class, nhanVien.getMaNV());
            oldNhanvien.setHoTenNV(nhanVien.getHoTenNV());
            oldNhanvien.setDiaChi(nhanVien.getDiaChi());
            oldNhanvien.setGioiTinh(nhanVien.getGioiTinh());
            oldNhanvien.setUsernane(nhanVien.getUsernane());
            oldNhanvien.setPassword(nhanVien.getPassword());
            oldNhanvien.setNgaySinh(nhanVien.getNgaySinh());
            em.getTransaction().commit();
        } catch(Exception ex){
            throw ex;
        } finally{
            if(em!= null){
                em.close();
            }
        }

    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Collection<Hoadon> hoadonCollectionOrphanCheck = nhanvien.getHoadonCollection();
            for (Hoadon hoadonCollectionOrphanCheckHoadon : hoadonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Nhanvien (" + nhanvien + ") cannot be destroyed since the Hoadon " + hoadonCollectionOrphanCheckHoadon + " in its hoadonCollection field has a non-nullable maNV field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
