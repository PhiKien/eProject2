/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drago
 */
@Entity
@Table(name = "chitiethoadon", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chitiethoadon.findAll", query = "SELECT c FROM Chitiethoadon c"),
    @NamedQuery(name = "Chitiethoadon.findByMaChiTietHD", query = "SELECT c FROM Chitiethoadon c WHERE c.maChiTietHD = :maChiTietHD"),
    @NamedQuery(name = "Chitiethoadon.findByMaHD", query = "SELECT c FROM Chitiethoadon c WHERE c.maHD = :maHD"),
    @NamedQuery(name = "Chitiethoadon.findByTenThuoc", query = "SELECT c FROM Chitiethoadon c WHERE c.tenThuoc = :tenThuoc"),
    @NamedQuery(name = "Chitiethoadon.findBySoLuong", query = "SELECT c FROM Chitiethoadon c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitiethoadon.findByTrieuChung", query = "SELECT c FROM Chitiethoadon c WHERE c.trieuChung = :trieuChung"),
    @NamedQuery(name = "Chitiethoadon.findByChuanDoan", query = "SELECT c FROM Chitiethoadon c WHERE c.chuanDoan = :chuanDoan"),
    @NamedQuery(name = "Chitiethoadon.findByTrangThai", query = "SELECT c FROM Chitiethoadon c WHERE c.trangThai = :trangThai")})
public class Chitiethoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaChiTietHD")
    private Integer maChiTietHD;
    @Column(name = "MaHD")
    private Integer maHD;
    @Column(name = "TenThuoc")
    private String tenThuoc;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "TrieuChung")
    private String trieuChung;
    @Column(name = "ChuanDoan")
    private String chuanDoan;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Chitiethoadon() {
    }

    public Chitiethoadon(Integer maChiTietHD) {
        this.maChiTietHD = maChiTietHD;
    }

    public Integer getMaChiTietHD() {
        return maChiTietHD;
    }

    public void setMaChiTietHD(Integer maChiTietHD) {
        this.maChiTietHD = maChiTietHD;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public Short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Short trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maChiTietHD != null ? maChiTietHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitiethoadon)) {
            return false;
        }
        Chitiethoadon other = (Chitiethoadon) object;
        if ((this.maChiTietHD == null && other.maChiTietHD != null) || (this.maChiTietHD != null && !this.maChiTietHD.equals(other.maChiTietHD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Chitiethoadon[ maChiTietHD=" + maChiTietHD + " ]";
    }
    
}
