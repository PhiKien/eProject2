/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author drago
 */
@Entity
@Table(name = "nhomthuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nhomthuoc.findAll", query = "SELECT n FROM Nhomthuoc n"),
    @NamedQuery(name = "Nhomthuoc.findByMaNhomThuoc", query = "SELECT n FROM Nhomthuoc n WHERE n.maNhomThuoc = :maNhomThuoc"),
    @NamedQuery(name = "Nhomthuoc.findByTenNhomThuoc", query = "SELECT n FROM Nhomthuoc n WHERE n.tenNhomThuoc = :tenNhomThuoc"),
    @NamedQuery(name = "Nhomthuoc.findByGhiChu", query = "SELECT n FROM Nhomthuoc n WHERE n.ghiChu = :ghiChu"),
    @NamedQuery(name = "Nhomthuoc.findByTrangThai", query = "SELECT n FROM Nhomthuoc n WHERE n.trangThai = :trangThai")})
public class Nhomthuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNhomThuoc")
    private Integer maNhomThuoc;
    @Basic(optional = false)
    @Column(name = "TenNhomThuoc")
    private String tenNhomThuoc;
    @Basic(optional = false)
    @Column(name = "GhiChu")
    private String ghiChu;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNhomThuoc")
    private Collection<Khothuoc> khothuocCollection;

    public Nhomthuoc() {
    }

    public Nhomthuoc(Integer maNhomThuoc) {
        this.maNhomThuoc = maNhomThuoc;
    }

    public Nhomthuoc(Integer maNhomThuoc, String tenNhomThuoc, String ghiChu, short trangThai) {
        this.maNhomThuoc = maNhomThuoc;
        this.tenNhomThuoc = tenNhomThuoc;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public Integer getMaNhomThuoc() {
        return maNhomThuoc;
    }

    public void setMaNhomThuoc(Integer maNhomThuoc) {
        this.maNhomThuoc = maNhomThuoc;
    }

    public String getTenNhomThuoc() {
        return tenNhomThuoc;
    }

    public void setTenNhomThuoc(String tenNhomThuoc) {
        this.tenNhomThuoc = tenNhomThuoc;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(short trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Collection<Khothuoc> getKhothuocCollection() {
        return khothuocCollection;
    }

    public void setKhothuocCollection(Collection<Khothuoc> khothuocCollection) {
        this.khothuocCollection = khothuocCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maNhomThuoc != null ? maNhomThuoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhomthuoc)) {
            return false;
        }
        Nhomthuoc other = (Nhomthuoc) object;
        if ((this.maNhomThuoc == null && other.maNhomThuoc != null) || (this.maNhomThuoc != null && !this.maNhomThuoc.equals(other.maNhomThuoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Nhomthuoc[ maNhomThuoc=" + maNhomThuoc + " ]";
    }
    
}
