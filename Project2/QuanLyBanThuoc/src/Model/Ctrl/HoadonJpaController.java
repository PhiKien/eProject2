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
import Model.Khachhang;
import Model.Nhanvien;
import Model.Chitiethoadon;
import Model.Ctrl.exceptions.IllegalOrphanException;
import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Hoadon;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
    
    public void delete(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hoadon hoadon;
            hoadon = em.getReference(Hoadon.class, id);
            short KHONG_HOAT_DONG = 0;
            hoadon.setTrangThai(KHONG_HOAT_DONG);
            em.getTransaction().commit();
        } catch (Exception ex){
            throw ex;
        }
    }

    public void create(Hoadon hoadon) {
        if (hoadon.getChitiethoadonCollection() == null) {
            hoadon.setChitiethoadonCollection(new ArrayList<Chitiethoadon>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Khachhang maKH = hoadon.getMaKH();
            if (maKH != null) {
                maKH = em.getReference(maKH.getClass(), maKH.getMaKH());
                hoadon.setMaKH(maKH);
            }
            Nhanvien maNV = hoadon.getMaNV();
            if (maNV != null) {
                maNV = em.getReference(maNV.getClass(), maNV.getMaNV());
                hoadon.setMaNV(maNV);
            }
            Collection<Chitiethoadon> attachedChitiethoadonCollection = new ArrayList<Chitiethoadon>();
            for (Chitiethoadon chitiethoadonCollectionChitiethoadonToAttach : hoadon.getChitiethoadonCollection()) {
                chitiethoadonCollectionChitiethoadonToAttach = em.getReference(chitiethoadonCollectionChitiethoadonToAttach.getClass(), chitiethoadonCollectionChitiethoadonToAttach.getChitiethoadonPK());
                attachedChitiethoadonCollection.add(chitiethoadonCollectionChitiethoadonToAttach);
            }
            hoadon.setChitiethoadonCollection(attachedChitiethoadonCollection);
            em.persist(hoadon);
            if (maKH != null) {
                maKH.getHoadonCollection().add(hoadon);
                maKH = em.merge(maKH);
            }
            if (maNV != null) {
                maNV.getHoadonCollection().add(hoadon);
                maNV = em.merge(maNV);
            }
            for (Chitiethoadon chitiethoadonCollectionChitiethoadon : hoadon.getChitiethoadonCollection()) {
                Hoadon oldHoadonOfChitiethoadonCollectionChitiethoadon = chitiethoadonCollectionChitiethoadon.getHoadon();
                chitiethoadonCollectionChitiethoadon.setHoadon(hoadon);
                chitiethoadonCollectionChitiethoadon = em.merge(chitiethoadonCollectionChitiethoadon);
                if (oldHoadonOfChitiethoadonCollectionChitiethoadon != null) {
                    oldHoadonOfChitiethoadonCollectionChitiethoadon.getChitiethoadonCollection().remove(chitiethoadonCollectionChitiethoadon);
                    oldHoadonOfChitiethoadonCollectionChitiethoadon = em.merge(oldHoadonOfChitiethoadonCollectionChitiethoadon);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Hoadon hoadon) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hoadon persistentHoadon = em.find(Hoadon.class, hoadon.getMaHD());
            Khachhang maKHOld = persistentHoadon.getMaKH();
            Khachhang maKHNew = hoadon.getMaKH();
            Nhanvien maNVOld = persistentHoadon.getMaNV();
            Nhanvien maNVNew = hoadon.getMaNV();
            Collection<Chitiethoadon> chitiethoadonCollectionOld = persistentHoadon.getChitiethoadonCollection();
            Collection<Chitiethoadon> chitiethoadonCollectionNew = hoadon.getChitiethoadonCollection();
            List<String> illegalOrphanMessages = null;
            for (Chitiethoadon chitiethoadonCollectionOldChitiethoadon : chitiethoadonCollectionOld) {
                if (!chitiethoadonCollectionNew.contains(chitiethoadonCollectionOldChitiethoadon)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Chitiethoadon " + chitiethoadonCollectionOldChitiethoadon + " since its hoadon field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (maKHNew != null) {
                maKHNew = em.getReference(maKHNew.getClass(), maKHNew.getMaKH());
                hoadon.setMaKH(maKHNew);
            }
            if (maNVNew != null) {
                maNVNew = em.getReference(maNVNew.getClass(), maNVNew.getMaNV());
                hoadon.setMaNV(maNVNew);
            }
            Collection<Chitiethoadon> attachedChitiethoadonCollectionNew = new ArrayList<Chitiethoadon>();
            for (Chitiethoadon chitiethoadonCollectionNewChitiethoadonToAttach : chitiethoadonCollectionNew) {
                chitiethoadonCollectionNewChitiethoadonToAttach = em.getReference(chitiethoadonCollectionNewChitiethoadonToAttach.getClass(), chitiethoadonCollectionNewChitiethoadonToAttach.getChitiethoadonPK());
                attachedChitiethoadonCollectionNew.add(chitiethoadonCollectionNewChitiethoadonToAttach);
            }
            chitiethoadonCollectionNew = attachedChitiethoadonCollectionNew;
            hoadon.setChitiethoadonCollection(chitiethoadonCollectionNew);
            hoadon = em.merge(hoadon);
            if (maKHOld != null && !maKHOld.equals(maKHNew)) {
                maKHOld.getHoadonCollection().remove(hoadon);
                maKHOld = em.merge(maKHOld);
            }
            if (maKHNew != null && !maKHNew.equals(maKHOld)) {
                maKHNew.getHoadonCollection().add(hoadon);
                maKHNew = em.merge(maKHNew);
            }
            if (maNVOld != null && !maNVOld.equals(maNVNew)) {
                maNVOld.getHoadonCollection().remove(hoadon);
                maNVOld = em.merge(maNVOld);
            }
            if (maNVNew != null && !maNVNew.equals(maNVOld)) {
                maNVNew.getHoadonCollection().add(hoadon);
                maNVNew = em.merge(maNVNew);
            }
            for (Chitiethoadon chitiethoadonCollectionNewChitiethoadon : chitiethoadonCollectionNew) {
                if (!chitiethoadonCollectionOld.contains(chitiethoadonCollectionNewChitiethoadon)) {
                    Hoadon oldHoadonOfChitiethoadonCollectionNewChitiethoadon = chitiethoadonCollectionNewChitiethoadon.getHoadon();
                    chitiethoadonCollectionNewChitiethoadon.setHoadon(hoadon);
                    chitiethoadonCollectionNewChitiethoadon = em.merge(chitiethoadonCollectionNewChitiethoadon);
                    if (oldHoadonOfChitiethoadonCollectionNewChitiethoadon != null && !oldHoadonOfChitiethoadonCollectionNewChitiethoadon.equals(hoadon)) {
                        oldHoadonOfChitiethoadonCollectionNewChitiethoadon.getChitiethoadonCollection().remove(chitiethoadonCollectionNewChitiethoadon);
                        oldHoadonOfChitiethoadonCollectionNewChitiethoadon = em.merge(oldHoadonOfChitiethoadonCollectionNewChitiethoadon);
                    }
                }
            }
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
    
    public void editHoaDon(Hoadon hoaDon){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Hoadon oldHoaDon = em.find(Hoadon.class, hoaDon.getMaHD());
            oldHoaDon.setMaKH(hoaDon.getMaKH());
            oldHoaDon.setMaNV(hoaDon.getMaNV());
            oldHoaDon.setNgayLapHD(hoaDon.getNgayLapHD());
            oldHoaDon.setTongTien(hoaDon.getTongTien());
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
            Hoadon hoadon;
            try {
                hoadon = em.getReference(Hoadon.class, id);
                hoadon.getMaHD();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The hoadon with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Chitiethoadon> chitiethoadonCollectionOrphanCheck = hoadon.getChitiethoadonCollection();
            for (Chitiethoadon chitiethoadonCollectionOrphanCheckChitiethoadon : chitiethoadonCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Hoadon (" + hoadon + ") cannot be destroyed since the Chitiethoadon " + chitiethoadonCollectionOrphanCheckChitiethoadon + " in its chitiethoadonCollection field has a non-nullable hoadon field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Khachhang maKH = hoadon.getMaKH();
            if (maKH != null) {
                maKH.getHoadonCollection().remove(hoadon);
                maKH = em.merge(maKH);
            }
            Nhanvien maNV = hoadon.getMaNV();
            if (maNV != null) {
                maNV.getHoadonCollection().remove(hoadon);
                maNV = em.merge(maNV);
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
