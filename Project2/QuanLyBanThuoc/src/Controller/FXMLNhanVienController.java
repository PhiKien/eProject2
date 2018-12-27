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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class FXMLNhanVienController implements Initializable {

    @FXML
    private MenuItem mnItemThem;
    @FXML
    private MenuItem mnItemSua;
    @FXML
    private MenuItem mnItemXoa;
    @FXML
    private MenuItem mnItemTKNC;
    @FXML
    private MenuItem mnItemXuatExcel;
    @FXML
    private MenuItem mnItemNhapExcel;
    @FXML
    private MenuItem mnItemNhanVien;
    @FXML
    private MenuItem mnItemKhachHang;
    @FXML
    private MenuItem mnItemKhoThuoc;
    @FXML
    private MenuItem mnItemHoaDon;
    @FXML
    private MenuItem mnItemThoat;
    @FXML
    private MenuItem mnItemTroGiup;
    @FXML
    private MenuItem mnItemDanhGia;
    @FXML
    private MenuItem mnItemBaoCao;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private RadioButton rbNam;
    @FXML
    private RadioButton rbNu;
    @FXML
    private Button btnThem;
    @FXML
    private Button btnSua;
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnLamMoi;
    @FXML
    private Button btnTimKiem;
    @FXML
    private TableView<Nhanvien> tabDsDuyet;
    @FXML
    private TableColumn<Nhanvien, String> tcHoTen;
    @FXML
    private TableColumn<Nhanvien, String> tcDiaChi;
    @FXML
    private TableColumn<Nhanvien, String> tcGioiTinh;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtUser;
    @FXML
    private TableColumn<Nhanvien, String> tcUser;
    @FXML
    private TableColumn<Nhanvien, String> tcPass;
    @FXML
    private Button btnXacNhan;
    @FXML
    private Button btnTuChoi;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
    EntityManager em = emf.createEntityManager();
    TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
    List<Nhanvien> resultListNV = createNamedQuery.getResultList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tabDsDuyet.setItems(getStudentData());
    }

    public void initColumns() {
        tcHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTenNV"));
        tcDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        tcGioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("Usernane"));
        tcPass.setCellValueFactory(new PropertyValueFactory<>("Password"));
    }

    private ObservableList<Nhanvien> data;

    public ObservableList<Nhanvien> getStudentData() {
        data = FXCollections.observableArrayList(resultListNV);
        if (data == null) {
            return FXCollections.observableArrayList();
        }
        return data;
    }

    @FXML
    private void mnItemThem_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemSua_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemXoa_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemTKNC_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLTKNCNhanVien.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Nhân viên");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mnItemXuatExcel_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemNhapExcel_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemNhanVien_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLNhanVien.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Nhân viên");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mnItemKhachHang_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLKhachHang.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Khách Hàng");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mnItemKhoThuoc_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLKhoThuoc.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Kho Thuốc");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mnItemHoaDon_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLHoaDon.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Hóa Đơn");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void mnItemThoat_Click(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void mnItemTroGiup_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemDanhGia_Click(ActionEvent event) {
    }

    @FXML
    private void mnItemBaoCao_Click(ActionEvent event) {
    }

    @FXML
    private void btnThem_Click(ActionEvent event) {
    }

    @FXML
    private void btnSua_Click(ActionEvent event) {
    }

    @FXML
    private void btnXoa_Click(ActionEvent event) {
    }

    @FXML
    private void btnLamMoi_Click(ActionEvent event) {
        txtDiaChi.clear();
        txtHoTen.clear();
        txtPass.clear();
        txtTimKiem.clear();
        txtUser.clear();
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
    }

    @FXML
    private void btnXacNhan_Click(ActionEvent event) {
    }

    @FXML
    private void btnTuChoi_Click(ActionEvent event) {
    }

}
