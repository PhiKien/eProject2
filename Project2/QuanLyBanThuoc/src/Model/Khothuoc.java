/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author drago
 */
@Entity
@Table(name = "khothuoc")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khothuoc.findAll", query = "SELECT k FROM Khothuoc k WHERE k.trangThai = 1"),
    @NamedQuery(name = "Khothuoc.findByMaThuoc", query = "SELECT k FROM Khothuoc k WHERE k.maThuoc = :maThuoc"),
    @NamedQuery(name = "Khothuoc.findByTenThuoc", query = "SELECT k FROM Khothuoc k WHERE k.tenThuoc = :tenThuoc"),
    @NamedQuery(name = "Khothuoc.findByDonVi", query = "SELECT k FROM Khothuoc k WHERE k.donVi = :donVi"),
    @NamedQuery(name = "Khothuoc.findByDonGia", query = "SELECT k FROM Khothuoc k WHERE k.donGia = :donGia"),
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
    @Basic(optional = false)
    @Column(name = "TenThuoc")
    private String tenThuoc;
    @Basic(optional = false)
    @Column(name = "DonVi")
    private String donVi;
    @Basic(optional = false)
    @Column(name = "DonGia")
    private int donGia;
    @Basic(optional = false)
    @Column(name = "NSX")
    @Temporal(TemporalType.DATE)
    private Date nsx;
    @Basic(optional = false)
    @Column(name = "HSD")
    @Temporal(TemporalType.DATE)
    private Date hsd;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @JoinColumn(name = "MaNhomThuoc", referencedColumnName = "MaNhomThuoc")
    @ManyToOne(optional = false)
    private Nhomthuoc maNhomThuoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "khothuoc")
    private Collection<Chitiethoadon> chitiethoadonCollection;

    public Khothuoc() {
    }

    public Khothuoc(Integer maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Khothuoc(Integer maThuoc, String tenThuoc, String donVi, int donGia, Date nsx, Date hsd, short trangThai) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.donVi = donVi;
        this.donGia = donGia;
        this.nsx = nsx;
        this.hsd = hsd;
        this.trangThai = trangThai;
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

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
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

    public short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(short trangThai) {
        this.trangThai = trangThai;
    }

    public Nhomthuoc getMaNhomThuoc() {
        return maNhomThuoc;
    }

    public void setMaNhomThuoc(Nhomthuoc maNhomThuoc) {
        this.maNhomThuoc = maNhomThuoc;
    }

    @XmlTransient
    public Collection<Chitiethoadon> getChitiethoadonCollection() {
        return chitiethoadonCollection;
    }

    public void setChitiethoadonCollection(Collection<Chitiethoadon> chitiethoadonCollection) {
        this.chitiethoadonCollection = chitiethoadonCollection;
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
        return "Khothuoc{" + "maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc + ", donVi=" + donVi + ", donGia=" + donGia + ", nsx=" + nsx + ", hsd=" + hsd + ", trangThai=" + trangThai + ", maNhomThuoc=" + maNhomThuoc + ", chitiethoadonCollection=" + chitiethoadonCollection + '}';
    }

    public void setMaNhomThuoc(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
