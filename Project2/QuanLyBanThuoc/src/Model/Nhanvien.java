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
@Table(name = "nhanvien", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nhanvien.findAll", query = "SELECT n FROM Nhanvien n"),
    @NamedQuery(name = "Nhanvien.findByMaNV", query = "SELECT n FROM Nhanvien n WHERE n.maNV = :maNV"),
    @NamedQuery(name = "Nhanvien.findByMaHD", query = "SELECT n FROM Nhanvien n WHERE n.maHD = :maHD"),
    @NamedQuery(name = "Nhanvien.findByHoTen", query = "SELECT n FROM Nhanvien n WHERE n.hoTen = :hoTen"),
    @NamedQuery(name = "Nhanvien.findByGioiTinh", query = "SELECT n FROM Nhanvien n WHERE n.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Nhanvien.findByDiaChi", query = "SELECT n FROM Nhanvien n WHERE n.diaChi = :diaChi"),
    @NamedQuery(name = "Nhanvien.findBySoDienThoai", query = "SELECT n FROM Nhanvien n WHERE n.soDienThoai = :soDienThoai"),
    @NamedQuery(name = "Nhanvien.findByUsername", query = "SELECT n FROM Nhanvien n WHERE n.username = :username"),
    @NamedQuery(name = "Nhanvien.findByPassword", query = "SELECT n FROM Nhanvien n WHERE n.password = :password"),
    @NamedQuery(name = "Nhanvien.findByTrangThai", query = "SELECT n FROM Nhanvien n WHERE n.trangThai = :trangThai")})
public class Nhanvien implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaNV")
    private Integer maNV;
    @Column(name = "MaHD")
    private Integer maHD;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @Column(name = "Username")
    private String username;
    @Column(name = "Password")
    private String password;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Nhanvien() {
    }

    public Nhanvien(Integer maNV) {
        this.maNV = maNV;
    }

    public Integer getMaNV() {
        return maNV;
    }

    public void setMaNV(Integer maNV) {
        this.maNV = maNV;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Model.Nhanvien[ maNV=" + maNV + " ]";
    }
    
}
