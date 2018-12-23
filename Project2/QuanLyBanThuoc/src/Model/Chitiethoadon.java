/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author drago
 */
@Entity
@Table(name = "chitiethoadon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chitiethoadon.findAll", query = "SELECT c FROM Chitiethoadon c"),
    @NamedQuery(name = "Chitiethoadon.findByMaHD", query = "SELECT c FROM Chitiethoadon c WHERE c.chitiethoadonPK.maHD = :maHD"),
    @NamedQuery(name = "Chitiethoadon.findByMaThuoc", query = "SELECT c FROM Chitiethoadon c WHERE c.chitiethoadonPK.maThuoc = :maThuoc"),
    @NamedQuery(name = "Chitiethoadon.findBySoLuong", query = "SELECT c FROM Chitiethoadon c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "Chitiethoadon.findByTrangThai", query = "SELECT c FROM Chitiethoadon c WHERE c.trangThai = :trangThai")})
public class Chitiethoadon implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ChitiethoadonPK chitiethoadonPK;
    @Basic(optional = false)
    @Column(name = "SoLuong")
    private short soLuong;
    @Basic(optional = false)
    @Column(name = "TrangThai")
    private short trangThai;
    @JoinColumn(name = "MaHD", referencedColumnName = "MaHD", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Hoadon hoadon;
    @JoinColumn(name = "MaThuoc", referencedColumnName = "MaThuoc", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Khothuoc khothuoc;

    public Chitiethoadon() {
    }

    public Chitiethoadon(ChitiethoadonPK chitiethoadonPK) {
        this.chitiethoadonPK = chitiethoadonPK;
    }

    public Chitiethoadon(ChitiethoadonPK chitiethoadonPK, short soLuong, short trangThai) {
        this.chitiethoadonPK = chitiethoadonPK;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    public Chitiethoadon(int maHD, int maThuoc) {
        this.chitiethoadonPK = new ChitiethoadonPK(maHD, maThuoc);
    }

    public ChitiethoadonPK getChitiethoadonPK() {
        return chitiethoadonPK;
    }

    public void setChitiethoadonPK(ChitiethoadonPK chitiethoadonPK) {
        this.chitiethoadonPK = chitiethoadonPK;
    }

    public short getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(short soLuong) {
        this.soLuong = soLuong;
    }

    public short getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(short trangThai) {
        this.trangThai = trangThai;
    }

    public Hoadon getHoadon() {
        return hoadon;
    }

    public void setHoadon(Hoadon hoadon) {
        this.hoadon = hoadon;
    }

    public Khothuoc getKhothuoc() {
        return khothuoc;
    }

    public void setKhothuoc(Khothuoc khothuoc) {
        this.khothuoc = khothuoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (chitiethoadonPK != null ? chitiethoadonPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chitiethoadon)) {
            return false;
        }
        Chitiethoadon other = (Chitiethoadon) object;
        if ((this.chitiethoadonPK == null && other.chitiethoadonPK != null) || (this.chitiethoadonPK != null && !this.chitiethoadonPK.equals(other.chitiethoadonPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.Chitiethoadon[ chitiethoadonPK=" + chitiethoadonPK + " ]";
    }
    
}
