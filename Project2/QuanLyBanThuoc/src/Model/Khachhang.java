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
@Table(name = "khachhang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khachhang.findAll", query = "SELECT k FROM Khachhang k WHERE k.trangThai = 1"),
    @NamedQuery(name = "Khachhang.findByMaKH", query = "SELECT k FROM Khachhang k WHERE k.maKH = :maKH"),
    @NamedQuery(name = "Khachhang.findByHoTenKH", query = "SELECT k FROM Khachhang k WHERE k.hoTenKH = :hoTenKH"),
    @NamedQuery(name = "Khachhang.findByNgaySinh", query = "SELECT k FROM Khachhang k WHERE k.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "Khachhang.findByGioiTinh", query = "SELECT k FROM Khachhang k WHERE k.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Khachhang.findByDiaChi", query = "SELECT k FROM Khachhang k WHERE k.diaChi = :diaChi"),
    @NamedQuery(name = "Khachhang.findByTrieuChung", query = "SELECT k FROM Khachhang k WHERE k.trieuChung = :trieuChung"),
    @NamedQuery(name = "Khachhang.findByChuanDoan", query = "SELECT k FROM Khachhang k WHERE k.chuanDoan = :chuanDoan"),
    @NamedQuery(name = "Khachhang.findByTrangThai", query = "SELECT k FROM Khachhang k WHERE k.trangThai = :trangThai")})
public class Khachhang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaKH")
    private Integer maKH;
    @Basic(optional = false)
    @Column(name = "HoTenKH")
    private String hoTenKH;
    @Basic(optional = false)
    @Column(name = "NgaySinh")
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;
    @Basic(optional = false)
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Basic(optional = false)
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "TrieuChung")
    private String trieuChung;
    @Column(name = "ChuanDoan")
    private String chuanDoan;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maKH")
    private Collection<Hoadon> hoadonCollection;

    public Khachhang() {
    }

    public Khachhang(Integer maKH) {
        this.maKH = maKH;
    }

    public Khachhang(Integer maKH, String hoTenKH, Date ngaySinh, String gioiTinh, String diaChi, short trangThai) {
        this.maKH = maKH;
        this.hoTenKH = hoTenKH;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
    }

    public String getHoTenKH() {
        return hoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        this.hoTenKH = hoTenKH;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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

    public short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(short trangThai) {
        this.trangThai = trangThai;
    }

    @XmlTransient
    public Collection<Hoadon> getHoadonCollection() {
        return hoadonCollection;
    }

    public void setHoadonCollection(Collection<Hoadon> hoadonCollection) {
        this.hoadonCollection = hoadonCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maKH != null ? maKH.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Khachhang)) {
            return false;
        }
        Khachhang other = (Khachhang) object;
        if ((this.maKH == null && other.maKH != null) || (this.maKH != null && !this.maKH.equals(other.maKH))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Khachhang[ maKH=" + maKH + " ]";
    }
    
}
