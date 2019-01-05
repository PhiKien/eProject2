/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ctrl.HoadonJpaController;
import Model.Hoadon;
import Model.Khachhang;
import Model.Nhanvien;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.TableCell;
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
public class FXMLHoaDonController implements Initializable {

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
    private TextField txtTimKiem;
    @FXML
    private TextField txtMaHoaDon;
    @FXML
    private DatePicker dpNgayBan;
    @FXML
    private Button btnCTHoaDon;
    @FXML
    private Button btnInHoaDon;
    @FXML
    private TextField txtTongTien;

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lblStatusHD;
    @FXML
    private TableView<Hoadon> tabDsDuyet;
    @FXML
    private TableColumn<Hoadon, Integer> tcMaHD;
    @FXML
    private TableColumn<Hoadon, Date> tcNgayBan;
    @FXML
    private TableColumn<Hoadon, Integer> tcTongTien;
     @FXML
    private TableColumn<Hoadon, Integer> tcMaKH;
    @FXML
    private TableColumn<Hoadon, Integer> tcMaNV;

    @FXML
    private TextField txtTenKH;
    @FXML
    private TextField txtTenNguoiBan;

    private static final short HOAT_DONG = 1;
    private static final short KHONG_HOAT_DONG = 0;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
    EntityManager em = emf.createEntityManager();

    TypedQuery<Hoadon> createNamedQuery = em.createNamedQuery("Hoadon.findAll", Hoadon.class);
    List<Hoadon> resultListHD = createNamedQuery.getResultList();

    HoadonJpaController jpaController = new HoadonJpaController(emf);

    TypedQuery<Khachhang> createNamedQueryKH = em.createNamedQuery("Khachhang.findAll", Khachhang.class);
    List<Khachhang> resultListKH = createNamedQueryKH.getResultList();

    TypedQuery<Nhanvien> createNamedQueryNV = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
    List<Nhanvien> resultListNV = createNamedQueryNV.getResultList();
   
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
//    public List<Model_NhanVien_KhachHang> nhanVien_KhachHangs() {
//        
//    }
    
    public String getTenNVByMaNV(int maNV) {
        String tenNV = null;

        for (Nhanvien nhanvien : resultListNV) {
            if (nhanvien.getMaNV() == maNV) {
                tenNV = nhanvien.getHoTenNV();
                break;
            }
        }

        return tenNV;
    }

    public String getTenKHByMaKH(int maKH) {
        String tenKH = null;

        for (Khachhang khachhang : resultListKH) {
            if (khachhang.getMaKH()== maKH) {
                tenKH = khachhang.getHoTenKH();
                break;
            }
        }

        return tenKH;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tabDsDuyet.setItems(getHoaDonData());
    }

    public void initColumns() {
        tcMaHD.setCellValueFactory(new PropertyValueFactory<>("MaHD"));
        tcMaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH")); 
        tcMaNV.setCellValueFactory(new PropertyValueFactory<>("MaNV"));
        tcNgayBan.setCellFactory(column -> {
            TableCell<Hoadon, Date> cell = new TableCell<Hoadon, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });
        tcNgayBan.setCellValueFactory(new PropertyValueFactory<>("NgayLapHD"));
        tcTongTien.setCellValueFactory(new PropertyValueFactory<>("TongTien"));
    }

    private ObservableList<Hoadon> data;

    public ObservableList<Hoadon> getHoaDonData() {
        data = FXCollections.observableArrayList(resultListHD);
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
            //((Node) event.getSource()).getScene().getWindow().hide();    
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLTKNCHoaDon.fxml"));
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
        txtMaHoaDon.clear();
        txtTimKiem.clear();
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {

    }

    @FXML
    private void btnCTHoaDon_Click(ActionEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLChiTietHoaDon.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Chi tiết hóa đơn");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnInHoaDon_Click(ActionEvent event) {
    }

    @FXML
    private void tabDsDuyet_Click(MouseEvent event) {
         Hoadon hoaDon = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (hoaDon == null) {
            return;
        } else {
            Integer maHD = hoaDon.getMaHD();
            String hoTenKH = getTenKHByMaKH(hoaDon.getMaKH().getMaKH().intValue());
            String hoTenNV = getTenNVByMaNV(hoaDon.getMaNV().getMaNV());
            Date input = hoaDon.getNgayLapHD();
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Integer tongTien = hoaDon.getTongTien();
            txtMaHoaDon.setText(maHD.toString());
            txtTenKH.setText(hoTenKH);
            txtTenNguoiBan.setText(hoTenNV);
            dpNgayBan.setValue(date);
            txtTongTien.setText(tongTien.toString());
        }
    }

}
