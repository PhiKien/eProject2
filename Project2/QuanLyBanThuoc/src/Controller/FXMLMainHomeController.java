/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Nhanvien;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 * FXML Controller class
 *
 * @author drago
 */
public class FXMLMainHomeController implements Initializable {

    @FXML
    private Pane paneDangNhap, paneDangKy;

    @FXML
    private Button btnDangNhap, btnDangKy;
    @FXML
    private Button btnHuongDanSuDung;
    @FXML
    private Button btnNhaPhatTrien;
    @FXML
    private Button btnThoat;
    @FXML
    private Pane paneNhaPhatTrien;
    @FXML
    private Pane paneHuongDanSuDung;
    @FXML
    private Button btnLamMoi;
    @FXML
    private Button btnXacNhanDangKy;
    @FXML
    private TextField txtHoVaTen;
    @FXML
    private TextField txtNhapLaiMatKhau;
    @FXML
    private TextField txtNgaySinh;
    @FXML
    private TextField txtTenDangNhap_DangKy;
    @FXML
    private TextField txtMatKhau_DangKy;
    @FXML
    private TextField txtTenDangNhap_DangNhap;
    @FXML
    private TextField txtMatKhau_DangNhap;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnDangNhapShowForm_Click(ActionEvent event) {
        paneDangNhap.toFront();
    }

    @FXML
    private void btnDangKyShowForm_Click(ActionEvent event) {
        paneDangKy.toFront();
    }

    @FXML
    private void btnHuongDanSuDungShowForm_Click(ActionEvent event) {
        paneHuongDanSuDung.toFront();
    }

    @FXML
    private void btnNhaPhatTrienShowForm_Click(ActionEvent event) {
        paneNhaPhatTrien.toFront();
    }

    @FXML
    private void btnThoat_Click(ActionEvent event) {
        Stage stage = (Stage) btnThoat.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnDangNhap_Click(ActionEvent event) {
        Nhanvien nhanvien = new Nhanvien();
        //tạo entity
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            //tạo list Usename ở nhân viên
            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
            //lấy list username
            List<Nhanvien> resultListNV = createNamedQuery.getResultList();
            if (txtTenDangNhap_DangNhap.getText().length() > 4 && txtTenDangNhap_DangNhap.getText() != null) {
                resultListNV.forEach((NV) -> {
                    if (NV.getUsernane().equals(txtTenDangNhap_DangNhap.getText())) {
                        if (NV.getPassword().equals(txtMatKhau_DangNhap.getText())) {
                            try {
                                ((Node) event.getSource()).getScene().getWindow().hide();    
                                FXMLLoader fxmlLoader = new FXMLLoader();
                                fxmlLoader.setLocation(getClass().getResource("/View/FXMLMainMenu.fxml"));
                                Scene scene = new Scene(fxmlLoader.load());
                                Stage window = new Stage();
                                window.setTitle("Menu");
                                window.setScene(scene);
                                window.show();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("Sai Username hoặc Password!");
                        }
                    } else {
                        System.out.println("Username chưa tồn tại!");
                    }
                });
            } else {
                System.out.println("Không được để trống Username và Password!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @FXML
    private void btnDangKy_Click(ActionEvent event) {
        paneDangKy.toFront();
    }

    @FXML
    private void btnXacNhanDangKy_Click(ActionEvent event) {
    }

    @FXML
    private void btnLamMoi_Click(MouseEvent event) {
        txtTenDangNhap_DangKy.clear();
        txtMatKhau_DangKy.clear();
        txtNhapLaiMatKhau.clear();
        txtNgaySinh.clear();
        txtHoVaTen.clear();
    }

    @FXML
    private void btnChung_Entered(MouseEvent event) {
    }

    public void persist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @FXML
    private void btnLamMoiDK_Click(ActionEvent event) {
    }

}
