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
@Table(name = "ngaynhap", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ngaynhap.findAll", query = "SELECT n FROM Ngaynhap n"),
    @NamedQuery(name = "Ngaynhap.findByMaNhap", query = "SELECT n FROM Ngaynhap n WHERE n.maNhap = :maNhap"),
    @NamedQuery(name = "Ngaynhap.findByMaThuoc", query = "SELECT n FROM Ngaynhap n WHERE n.maThuoc = :maThuoc"),
    @NamedQuery(name = "Ngaynhap.findBySoLuong", query = "SELECT n FROM Ngaynhap n WHERE n.soLuong = :soLuong"),
    @NamedQuery(name = "Ngaynhap.findByDonGia", query = "SELECT n FROM Ngaynhap n WHERE n.donGia = :donGia"),
    @NamedQuery(name = "Ngaynhap.findByTrangThai", query = "SELECT n FROM Ngaynhap n WHERE n.trangThai = :trangThai")})
public class Ngaynhap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNhap")
    private Integer maNhap;
    @Column(name = "MaThuoc")
    private Integer maThuoc;
    @Column(name = "SoLuong")
    private Integer soLuong;
    @Column(name = "DonGia")
    private Integer donGia;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Ngaynhap() {
    }

    public Ngaynhap(Integer maNhap) {
        this.maNhap = maNhap;
    }

    public Integer getMaNhap() {
        return maNhap;
    }

    public void setMaNhap(Integer maNhap) {
        this.maNhap = maNhap;
    }

    public Integer getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Integer getDonGia() {
        return donGia;
    }

    public void setDonGia(Integer donGia) {
        this.donGia = donGia;
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
        hash += (maNhap != null ? maNhap.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ngaynhap)) {
            return false;
        }
        Ngaynhap other = (Ngaynhap) object;
        if ((this.maNhap == null && other.maNhap != null) || (this.maNhap != null && !this.maNhap.equals(other.maNhap))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Ngaynhap[ maNhap=" + maNhap + " ]";
    }
    
}
