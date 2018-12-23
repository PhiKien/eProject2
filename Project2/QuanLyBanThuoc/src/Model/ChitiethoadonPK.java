/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author drago
 */
@Embeddable
public class ChitiethoadonPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "MaHD")
    private int maHD;
    @Basic(optional = false)
    @Column(name = "MaThuoc")
    private int maThuoc;

    public ChitiethoadonPK() {
    }

    public ChitiethoadonPK(int maHD, int maThuoc) {
        this.maHD = maHD;
        this.maThuoc = maThuoc;
    }

    public int getMaHD() {
        return maHD;
    }

    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) maHD;
        hash += (int) maThuoc;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ChitiethoadonPK)) {
            return false;
        }
        ChitiethoadonPK other = (ChitiethoadonPK) object;
        if (this.maHD != other.maHD) {
            return false;
        }
        if (this.maThuoc != other.maThuoc) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Model.ChitiethoadonPK[ maHD=" + maHD + ", maThuoc=" + maThuoc + " ]";
    }
    
}
