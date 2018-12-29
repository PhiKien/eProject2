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
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
    private TextField txtGioiTinh;
    @FXML
    private DatePicker datePickerNgaySinh;
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
    @FXML
    private Label lblStatusNV;

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
        Nhanvien nhanvien = new Nhanvien();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        NhanvienJpaController jpaController = new NhanvienJpaController(emf);

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
            resultListNV = createNamedQuery.getResultList();

            if (txtDiaChi.getText() != null && txtGioiTinh.getText() != null && txtHoTen.getText() != null && txtPass.getText() != null && txtUser.getText() != null && datePickerNgaySinh.getValue() != null) {
                if (txtGioiTinh.getText().equalsIgnoreCase("Nam") || txtGioiTinh.getText().equalsIgnoreCase("Nu")) {
                    if (txtHoTen.getText().length() > 2 && txtUser.getText().length() > 2 && txtPass.getText().length() > 2) {
                        if (kiemTra(txtHoTen.getText())) {
                            nhanvien.setHoTenNV(txtHoTen.getText());
                            nhanvien.setDiaChi(txtDiaChi.getText());
                            Date date = Date.from(datePickerNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                            nhanvien.setNgaySinh(date);
                            nhanvien.setUsernane(txtUser.getText());
                            nhanvien.setPassword(txtPass.getText());
                            nhanvien.setGioiTinh(txtGioiTinh.getText());
                            jpaController.create(nhanvien);
                            lblStatusNV.setText("Edit thành công!");
                            System.out.println("Edit thanh cong!");
                        } else {
                            lblStatusNV.setText("Username đã tồn tại!!");
                        }
                    } else {
                        lblStatusNV.setText("Họ tên và user, pass phải lơn hơn 2 kí tự!");
                    }
                } else {
                    lblStatusNV.setText("Giới tính phải là Nam hoặc Nữ!");
                }
            } else {
                lblStatusNV.setText("Không được để trống các ô!");
            }
        } catch (Exception e) {
            e.getMessage();
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
    private void btnSua_Click(ActionEvent event) {
        Nhanvien nhanvien = new Nhanvien();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        NhanvienJpaController jpaController = new NhanvienJpaController(emf);

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
            resultListNV = createNamedQuery.getResultList();

            if (txtDiaChi.getText() != null && txtGioiTinh.getText() != null && txtHoTen.getText() != null && txtPass.getText() != null && txtUser.getText() != null && datePickerNgaySinh.getValue() != null) {
                if (txtGioiTinh.getText().equalsIgnoreCase("Nam") || txtGioiTinh.getText().equalsIgnoreCase("Nu")) {
                    if (txtHoTen.getText().length() > 2 && txtUser.getText().length() > 2 && txtPass.getText().length() > 2) {
                        if (kiemTra(txtHoTen.getText())) {
                            nhanvien.setHoTenNV(txtHoTen.getText());
                            nhanvien.setDiaChi(txtDiaChi.getText());
                            Date date = Date.from(datePickerNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                            nhanvien.setNgaySinh(date);
                            nhanvien.setUsernane(txtUser.getText());
                            nhanvien.setPassword(txtPass.getText());
                            nhanvien.setGioiTinh(txtGioiTinh.getText());
                            jpaController.edit(nhanvien);
                            lblStatusNV.setText("Thêm mới thành công!");
                            System.out.println("Dang ky thanh cong!");
                        } else {
                            lblStatusNV.setText("Username đã tồn tại!!");
                        }
                    } else {
                        lblStatusNV.setText("Họ tên và user, pass phải lơn hơn 2 kí tự!");
                    }
                } else {
                    lblStatusNV.setText("Giới tính phải là Nam hoặc Nữ!");
                }
            } else {
                lblStatusNV.setText("Không được để trống các ô!");
            }
        } catch (Exception e) {
            e.getMessage();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
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
        txtGioiTinh.clear();
        datePickerNgaySinh.setValue(LocalDate.of(1970, 01, 01));
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
        resultListNV.forEach((nv) -> {
            if (nv.getHoTenNV().equals(txtTimKiem.getText())) {
                txtHoTen.setText(nv.getHoTenNV());
                txtDiaChi.setText(nv.getDiaChi());
                txtGioiTinh.setText(nv.getGioiTinh());
                txtUser.setText(nv.getUsernane());
                txtPass.setText(nv.getPassword());
                datePickerNgaySinh.setValue(nv.getNgaySinh().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            } else {
                System.out.println("Khong tim thay!");
            }
        });
    }

    @FXML
    private void tabDsDuyet_Click(MouseEvent event) {
        Nhanvien nhanVien = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (nhanVien == null) {
            return;
        } else {
            String hoTen = nhanVien.getHoTenNV();
            String diaChi = nhanVien.getDiaChi();
            String gioiTinh = nhanVien.getGioiTinh();
            String user = nhanVien.getUsernane();
            String pass = nhanVien.getPassword();

            txtHoTen.setText(hoTen);
            txtDiaChi.setText(diaChi);
            txtGioiTinh.setText(gioiTinh);
            txtUser.setText(user);
            txtPass.setText(pass);
        }
    }

}
