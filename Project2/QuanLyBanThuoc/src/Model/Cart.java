/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Dell 5537
 */
public class Cart {
    private String tenThuoc;
    private int soLuong;
    private int donGia;
    private int thanhTien;

    public Cart() {
    }

    public Cart(String tenThuoc, int soLuong, int donGia, int thanhTien) {
        this.tenThuoc = tenThuoc;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getDonGia() {
        return donGia;
    }

    public void setDonGia(int donGia) {
        this.donGia = donGia;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }

    @Override
    public String toString() {
        return "Cart{" + "tenThuoc=" + tenThuoc + ", soLuong=" + soLuong + ", donGia=" + donGia + ", thanhTien=" + thanhTien + '}';
    }
    
}
