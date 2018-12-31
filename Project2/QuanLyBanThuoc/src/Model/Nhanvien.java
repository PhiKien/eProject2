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
@Table(name = "nhanvien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nhanvien.findAll", query = "SELECT n FROM Nhanvien n WHERE n.trangThai = 1"),
    @NamedQuery(name = "Nhanvien.findByMaNV", query = "SELECT n FROM Nhanvien n WHERE n.maNV = :maNV"),
    @NamedQuery(name = "Nhanvien.findByHoTenNV", query = "SELECT n FROM Nhanvien n WHERE n.hoTenNV like :hoTenNV"),
    @NamedQuery(name = "Nhanvien.findByNgaySinh", query = "SELECT n FROM Nhanvien n WHERE n.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "Nhanvien.findByGioiTinh", query = "SELECT n FROM Nhanvien n WHERE n.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Nhanvien.findByDiaChi", query = "SELECT n FROM Nhanvien n WHERE n.diaChi = :diaChi"),
    @NamedQuery(name = "Nhanvien.findByUsernane", query = "SELECT n FROM Nhanvien n WHERE n.usernane = :usernane"),
    @NamedQuery(name = "Nhanvien.findByPassword", query = "SELECT n FROM Nhanvien n WHERE n.password = :password"),
    @NamedQuery(name = "Nhanvien.findByTrangThai", query = "SELECT n FROM Nhanvien n WHERE n.trangThai = :trangThai")})
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNV")
    private Integer maNV;
    @Basic(optional = false)
    @Column(name = "HoTenNV")
    private String hoTenNV;
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
    @Basic(optional = false)
    @Column(name = "Usernane")
    private String usernane;
    @Basic(optional = false)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maNV")
    private Collection<Hoadon> hoadonCollection;

    public Nhanvien() {
    }

    public Nhanvien(Integer maNV) {
        this.maNV = maNV;
    }

    public Nhanvien(Integer maNV, String hoTenNV, Date ngaySinh, String gioiTinh, String diaChi, String usernane, String password, short trangThai) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.usernane = usernane;
        this.password = password;
        this.trangThai = trangThai;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
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

    public String getUsernane() {
        return usernane;
    }

    public void setUsernane(String usernane) {
        this.usernane = usernane;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        hash += (maNV != null ? maNV.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nhanvien)) {
            return false;
        }
        Nhanvien other = (Nhanvien) object;
        if ((this.maNV == null && other.maNV != null) || (this.maNV != null && !this.maNV.equals(other.maNV))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Nhanvien[ maNV= " + maNV + " - " + "hoTenNV= " + hoTenNV + " - "  + "ngaySinh= " + ngaySinh + " - "  + "gioiTinh= " + gioiTinh + " - "  + "diaChi= "+ diaChi + " - "  + "usernane= " + usernane + " - "  + "password= " + password + " - "  + "trangThai= " + trangThai +" ]";
    }
    
}
