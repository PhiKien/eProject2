/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author drago
 */
public class Model_NhanVien_KhachHang {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
//    EntityManager em = emf.createEntityManager();
//
//    TypedQuery<Khachhang> createNamedQueryKH = em.createNamedQuery("Khachhang.findAll", Khachhang.class);
//    List<Khachhang> resultListKH = createNamedQueryKH.getResultList();
//
//    TypedQuery<Nhanvien> createNamedQueryNV = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
//    List<Nhanvien> resultListNV = createNamedQueryNV.getResultList();
//
//    TypedQuery<Hoadon> createNamedQueryHD = em.createNamedQuery("Hoadon.findAll", Hoadon.class);
//    List<Hoadon> resultListHD = createNamedQueryHD.getResultList();
//    
//    
//    
//
//    
//    public String getTenNVByMaNV(int maNV) {
//        String tenNV = null;
//
//        Nhanvien getNV = resultListNV.get(maNV);
//
//        tenNV = getNV.getHoTenNV();
//
//        return tenNV;
//    }
//
//    public String getTenKHByMaKH(int maKH) {
//        String tenNV = null;
//
//        Khachhang getKH = resultListKH.get(maKH);
//
//        tenNV = getKH.getHoTenKH();
//
//        return tenNV;
//    }
    
    private int maHD;
    private String tenNV;
    private String tenKH;
    private Date ngayLapHD;
    private int tongTien;

    public Model_NhanVien_KhachHang() {
    }

    public Model_NhanVien_KhachHang(int maHD, String tenNV, String tenKH, Date ngayLapHD, int tongTien) {
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "Model_NhanVien_KhachHang{" + "maHD=" + maHD + ", tenNV=" + tenNV + ", tenKH=" + tenKH + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + '}';
    }
    
   
    
}
