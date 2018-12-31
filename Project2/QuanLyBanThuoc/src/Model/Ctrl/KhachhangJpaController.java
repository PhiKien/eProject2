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
import Model.Khachhang;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
        if (khachhang.getHoadonCollection() == null) {
            khachhang.setHoadonCollection(new ArrayList<Hoadon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Hoadon> attachedHoadonCollection = new ArrayList<Hoadon>();
            for (Hoadon hoadonCollectionHoadonToAttach : khachhang.getHoadonCollection()) {
                hoadonCollectionHoadonToAttach = em.getReference(hoadonCollectionHoadonToAttach.getClass(), hoadonCollectionHoadonToAttach.getMaHD());
                attachedHoadonCollection.add(hoadonCollectionHoadonToAttach);
            }
            khachhang.setHoadonCollection(attachedHoadonCollection);
            em.persist(khachhang);
            for (Hoadon hoadonCollectionHoadon : khachhang.getHoadonCollection()) {
                Khachhang oldMaKHOfHoadonCollectionHoadon = hoadonCollectionHoadon.getMaKH();
                hoadonCollectionHoadon.setMaKH(khachhang);
                hoadonCollectionHoadon = em.merge(hoadonCollectionHoadon);
                if (oldMaKHOfHoadonCollectionHoadon != null) {
                    oldMaKHOfHoadonCollectionHoadon.getHoadonCollection().remove(hoadonCollectionHoadon);
                    oldMaKHOfHoadonCollectionHoadon = em.merge(oldMaKHOfHoadonCollectionHoadon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Khachhang khachhang) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Khachhang persistentKhachhang = em.find(Khachhang.class, khachhang.getMaKH());
            Collection<Hoadon> hoadonCollectionOld = persistentKhachhang.getHoadonCollection();
            Collection<Hoadon> hoadonCollectionNew = khachhang.getHoadonCollection();
            List<String> illegalOrphanMessages = null;
            for (Hoadon hoadonCollectionOldHoadon : hoadonCollectionOld) {
                if (!hoadonCollectionNew.contains(hoadonCollectionOldHoadon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Hoadon " + hoadonCollectionOldHoadon + " since its maKH field is not nullable.");
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
            khachhang.setHoadonCollection(hoadonCollectionNew);
            khachhang = em.merge(khachhang);
            for (Hoadon hoadonCollectionNewHoadon : hoadonCollectionNew) {
                if (!hoadonCollectionOld.contains(hoadonCollectionNewHoadon)) {
                    Khachhang oldMaKHOfHoadonCollectionNewHoadon = hoadonCollectionNewHoadon.getMaKH();
                    hoadonCollectionNewHoadon.setMaKH(khachhang);
                    hoadonCollectionNewHoadon = em.merge(hoadonCollectionNewHoadon);
                    if (oldMaKHOfHoadonCollectionNewHoadon != null && !oldMaKHOfHoadonCollectionNewHoadon.equals(khachhang)) {
                        oldMaKHOfHoadonCollectionNewHoadon.getHoadonCollection().remove(hoadonCollectionNewHoadon);
                        oldMaKHOfHoadonCollectionNewHoadon = em.merge(oldMaKHOfHoadonCollectionNewHoadon);
                    }
                }
            }
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
    
    public void editKhachHang(Khachhang khachHang){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Khachhang oldKhachHang = em.find(Khachhang.class, khachHang.getMaKH());
            oldKhachHang.setHoTenKH(khachHang.getHoTenKH());
            oldKhachHang.setDiaChi(khachHang.getDiaChi());
            oldKhachHang.setGioiTinh(khachHang.getGioiTinh());
            oldKhachHang.setChuanDoan(khachHang.getChuanDoan());
            oldKhachHang.setTrieuChung(khachHang.getTrieuChung());
            oldKhachHang.setNgaySinh(khachHang.getNgaySinh());
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
            Khachhang khachhang;
            try {
                khachhang = em.getReference(Khachhang.class, id);
                khachhang.getMaKH();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The khachhang with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Hoadon> hoadonCollectionOrphanCheck = khachhang.getHoadonCollection();
            for (Hoadon hoadonCollectionOrphanCheckHoadon : hoadonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Khachhang (" + khachhang + ") cannot be destroyed since the Hoadon " + hoadonCollectionOrphanCheckHoadon + " in its hoadonCollection field has a non-nullable maKH field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
