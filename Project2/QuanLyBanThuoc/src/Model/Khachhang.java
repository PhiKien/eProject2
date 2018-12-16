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
@Table(name = "khachhang", catalog = "quanlybanthuocdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Khachhang.findAll", query = "SELECT k FROM Khachhang k"),
    @NamedQuery(name = "Khachhang.findByMaKH", query = "SELECT k FROM Khachhang k WHERE k.maKH = :maKH"),
    @NamedQuery(name = "Khachhang.findByMaHD", query = "SELECT k FROM Khachhang k WHERE k.maHD = :maHD"),
    @NamedQuery(name = "Khachhang.findByHoTen", query = "SELECT k FROM Khachhang k WHERE k.hoTen = :hoTen"),
    @NamedQuery(name = "Khachhang.findByNgaySinh", query = "SELECT k FROM Khachhang k WHERE k.ngaySinh = :ngaySinh"),
    @NamedQuery(name = "Khachhang.findByGioiTinh", query = "SELECT k FROM Khachhang k WHERE k.gioiTinh = :gioiTinh"),
    @NamedQuery(name = "Khachhang.findByDiaChi", query = "SELECT k FROM Khachhang k WHERE k.diaChi = :diaChi"),
    @NamedQuery(name = "Khachhang.findBySoDienThoai", query = "SELECT k FROM Khachhang k WHERE k.soDienThoai = :soDienThoai"),
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
    @Column(name = "MaHD")
    private Integer maHD;
    @Column(name = "HoTen")
    private String hoTen;
    @Column(name = "NgaySinh")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngaySinh;
    @Column(name = "GioiTinh")
    private Boolean gioiTinh;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "SoDienThoai")
    private String soDienThoai;
    @Column(name = "TrieuChung")
    private String trieuChung;
    @Column(name = "ChuanDoan")
    private String chuanDoan;
    @Column(name = "TrangThai")
    private Short trangThai;

    public Khachhang() {
    }

    public Khachhang(Integer maKH) {
        this.maKH = maKH;
    }

    public Integer getMaKH() {
        return maKH;
    }

    public void setMaKH(Integer maKH) {
        this.maKH = maKH;
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

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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
