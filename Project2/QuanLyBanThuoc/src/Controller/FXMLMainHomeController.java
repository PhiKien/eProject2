/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ctrl.NhanvienJpaController;
import Model.Nhanvien;
import Util.StringToDate;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    @FXML
    private Label lblStatus;
    @FXML
    private Label lblStatus1;
    @FXML
    private RadioButton rbNam;
    @FXML
    private RadioButton rbNu;
    @FXML
    private ToggleGroup gioiTinh;

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
        //tạo entity
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            //tạo list Usename ở nhân viên
            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
            //lấy list username
            resultListNV = createNamedQuery.getResultList();
            if (txtTenDangNhap_DangNhap.getText().length() > 4 && txtTenDangNhap_DangNhap.getText() != null) {
                        if (kiemTraDangNhap(txtTenDangNhap_DangNhap.getText(), txtMatKhau_DangNhap.getText())) {
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
                            lblStatus.setText("Sai Username hoặc Password!");
                        }                         
            } else {
                lblStatus.setText("Không được để trống Username và Password!");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
   
    List<Nhanvien> resultListNV;
    
    private boolean kiemTraDangNhap(String user, String pass) {        
        for (int i = 0; i < resultListNV.size(); i++) {
            if (resultListNV.get(i).getUsernane().equals(user) && resultListNV.get(i).getPassword().equals(pass)) {              
                    return true;                
            }
        }
        return false;
    }
    
    @FXML
    private void btnDangKy_Click(ActionEvent event) {
        paneDangKy.toFront();
    }

    @FXML
    private void btnXacNhanDangKy_Click(ActionEvent event) {
        Nhanvien nhanvien = new Nhanvien();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        NhanvienJpaController jpaController = new NhanvienJpaController(emf);

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            //tạo list Usename ở nhân viên
            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
            //lấy list username
            resultListNV = createNamedQuery.getResultList();

            if (txtTenDangNhap_DangKy.getText().length() >= 4 && txtTenDangNhap_DangKy.getText() != null) {
                if (kiemTra(txtTenDangNhap_DangKy.getText())) {
                    if (txtMatKhau_DangKy.getText().length() >= 5 && txtMatKhau_DangKy.getText() != null) {
                        if (txtHoVaTen.getText().length() > 0 && txtHoVaTen.getText() != null) {
                            if (txtNgaySinh.getText().length() > 0 && txtNgaySinh.getText() != null) {
                                if (txtNhapLaiMatKhau.getText().equals(txtMatKhau_DangKy.getText())) {
                                    String userName = txtTenDangNhap_DangKy.getText();
                                    nhanvien.setUsernane(userName);
                                    String pass = txtMatKhau_DangKy.getText();
                                    nhanvien.setPassword(pass);
                                    String hoTen = txtHoVaTen.getText();
                                    nhanvien.setHoTenNV(hoTen);
                                    String dob = txtNgaySinh.getText();
                                    Date date = stringToDate.String2Date(dob);
                                    nhanvien.setNgaySinh(date);
                                    if(rbNam.isSelected()){
                                        nhanvien.setGioiTinh("Nam");
                                    } else {
                                        nhanvien.setGioiTinh("Nữ");
                                    }
                                    jpaController.create(nhanvien);
                                    paneDangNhap.toFront();
                                    lblStatus.setText("Đăng ký thành công!");
                                } else {
                                    lblStatus1.setText("Mật khẩu và mật khẩu nhập lại không khớp!");
                                }
                            } else {
                                lblStatus1.setText("Ngay sinh nhập sai mời nhập lại!");
                            }
                        } else {
                            lblStatus1.setText("Họ và tên phải từ 4 kí tự trở nên!");
                        }
                    } else {
                        lblStatus1.setText("Password phải từ 5 kí tự trở nên!");
                    }
                } else {
                    lblStatus1.setText("Username đã tồn tại!");
                }
            } else {
                lblStatus1.setText("Tên đăng nhập phải từ 4 kí tự trở nên!");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private boolean kiemTra(String text) {
        for (Nhanvien nhanvien : resultListNV) {
            if (nhanvien.getUsernane().equals(text)) {
                return false;
            }
        }
        return true;
    }

    @FXML
    private void btnLamMoi_Click(MouseEvent event) {
        txtTenDangNhap_DangKy.clear();
        txtMatKhau_DangKy.clear();
        txtNhapLaiMatKhau.clear();
        txtNgaySinh.clear();
        txtHoVaTen.clear();
        rbNam.setSelected(true);
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

    @FXML
    private void rbNam_Click(ActionEvent event) {
    }

    @FXML
    private void rbNu_Click(ActionEvent event) {
    }

}
