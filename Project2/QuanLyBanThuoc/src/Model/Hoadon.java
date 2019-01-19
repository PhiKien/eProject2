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
@Table(name = "hoadon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hoadon.findAll", query = "SELECT h FROM Hoadon h WHERE h.trangThai = 1"),
    @NamedQuery(name = "Hoadon.findByMaHD", query = "SELECT h FROM Hoadon h WHERE h.maHD = :maHD"),
    @NamedQuery(name = "Hoadon.findByNgayLapHD", query = "SELECT h FROM Hoadon h WHERE h.ngayLapHD = :ngayLapHD"),
    @NamedQuery(name = "Hoadon.findByTongTien", query = "SELECT h FROM Hoadon h WHERE h.tongTien = :tongTien"),
    @NamedQuery(name = "Hoadon.findByTrangThai", query = "SELECT h FROM Hoadon h WHERE h.trangThai = :trangThai")})
public class Hoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MaHD")
    private Integer maHD;
    @Basic(optional = false)
    @Column(name = "NgayLapHD")
    @Temporal(TemporalType.DATE)
    private Date ngayLapHD;
    @Basic(optional = false)
    @Column(name = "TongTien")
    private int tongTien;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @JoinColumn(name = "MaKH", referencedColumnName = "MaKH")
    @ManyToOne(optional = false)
    private Khachhang maKH;
    @JoinColumn(name = "MaNV", referencedColumnName = "MaNV")
    @ManyToOne(optional = false)
    private Nhanvien maNV;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hoadon")
    private Collection<Chitiethoadon> chitiethoadonCollection;

    public Hoadon() {
    }

    public Hoadon(Integer maHD) {
        this.maHD = maHD;
    }

    public Hoadon(Integer maHD, Date ngayLapHD, int tongTien, short trangThai) {
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public Integer getMaHD() {
        return maHD;
    }

    public void setMaHD(Integer maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(short trangThai) {
        this.trangThai = trangThai;
    }

    public Khachhang getMaKH() {
        return maKH;
    }

    public void setMaKH(Khachhang maKH) {
        this.maKH = maKH;
    }

    public Nhanvien getMaNV() {
        return maNV;
    }

    public void setMaNV(Nhanvien maNV) {
        this.maNV = maNV;
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
        return "Hoadon{" + "maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", tongTien=" + tongTien + ", trangThai=" + trangThai + ", maKH=" + maKH + ", maNV=" + maNV + ", chitiethoadonCollection=" + chitiethoadonCollection + '}';
    }
   
}
