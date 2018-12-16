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
@Table(name = "khothuoc", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khothuoc.findAll", query = "SELECT k FROM Khothuoc k"),
    @NamedQuery(name = "Khothuoc.findByMaThuoc", query = "SELECT k FROM Khothuoc k WHERE k.maThuoc = :maThuoc"),
    @NamedQuery(name = "Khothuoc.findByTenThuoc", query = "SELECT k FROM Khothuoc k WHERE k.tenThuoc = :tenThuoc"),
    @NamedQuery(name = "Khothuoc.findBySoLuong", query = "SELECT k FROM Khothuoc k WHERE k.soLuong = :soLuong"),
    @NamedQuery(name = "Khothuoc.findByDonGia", query = "SELECT k FROM Khothuoc k WHERE k.donGia = :donGia"),
    @NamedQuery(name = "Khothuoc.findByDonVi", query = "SELECT k FROM Khothuoc k WHERE k.donVi = :donVi"),
    @NamedQuery(name = "Khothuoc.findByNsx", query = "SELECT k FROM Khothuoc k WHERE k.nsx = :nsx"),
    @NamedQuery(name = "Khothuoc.findByHsd", query = "SELECT k FROM Khothuoc k WHERE k.hsd = :hsd"),
    @NamedQuery(name = "Khothuoc.findByTrangThai", query = "SELECT k FROM Khothuoc k WHERE k.trangThai = :trangThai")})
public class Khothuoc implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaThuoc")
    private Integer maThuoc;
    @Column(name = "TenThuoc")
    private String tenThuoc;
    @Column(name = "SoLuong")
    private Integer soLuong;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "DonGia")
    private Float donGia;
    @Column(name = "DonVi")
    private Integer donVi;
    @Column(name = "NSX")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nsx;
    @Column(name = "HSD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hsd;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Khothuoc() {
    }

    public Khothuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Integer getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
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

    public Float getDonGia() {
        return donGia;
    }

    public void setDonGia(Float donGia) {
        this.donGia = donGia;
    }

    public Integer getDonVi() {
        return donVi;
    }

    public void setDonVi(Integer donVi) {
        this.donVi = donVi;
    }

    public Date getNsx() {
        return nsx;
    }

    public void setNsx(Date nsx) {
        this.nsx = nsx;
    }

    public Date getHsd() {
        return hsd;
    }

    public void setHsd(Date hsd) {
        this.hsd = hsd;
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
        hash += (maThuoc != null ? maThuoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khothuoc)) {
            return false;
        }
        Khothuoc other = (Khothuoc) object;
        if ((this.maThuoc == null && other.maThuoc != null) || (this.maThuoc != null && !this.maThuoc.equals(other.maThuoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Khothuoc[ maThuoc=" + maThuoc + " ]";
    }
    
}
