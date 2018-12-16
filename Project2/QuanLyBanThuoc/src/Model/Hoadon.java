/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drago
 */
@Entity
@Table(name = "hoadon", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoadon.findAll", query = "SELECT h FROM Hoadon h"),
    @NamedQuery(name = "Hoadon.findByMaHD", query = "SELECT h FROM Hoadon h WHERE h.maHD = :maHD"),
    @NamedQuery(name = "Hoadon.findByMaThuoc", query = "SELECT h FROM Hoadon h WHERE h.maThuoc = :maThuoc"),
    @NamedQuery(name = "Hoadon.findByMaKH", query = "SELECT h FROM Hoadon h WHERE h.maKH = :maKH"),
    @NamedQuery(name = "Hoadon.findByNgayBan", query = "SELECT h FROM Hoadon h WHERE h.ngayBan = :ngayBan"),
    @NamedQuery(name = "Hoadon.findByTrangThai", query = "SELECT h FROM Hoadon h WHERE h.trangThai = :trangThai")})
public class Hoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHD")
    private Integer maHD;
    @Column(name = "MaThuoc")
    private Integer maThuoc;
    @Column(name = "MaKH")
    private Integer maKH;
    @Column(name = "NgayBan")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayBan;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Hoadon() {
    }

    public Hoadon(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Integer getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public Date getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(Date ngayBan) {
        this.ngayBan = ngayBan;
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
        hash += (maHD != null ? maHD.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hoadon)) {
            return false;
        }
        Hoadon other = (Hoadon) object;
        if ((this.maHD == null && other.maHD != null) || (this.maHD != null && !this.maHD.equals(other.maHD))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Hoadon[ maHD=" + maHD + " ]";
    }
    
}
