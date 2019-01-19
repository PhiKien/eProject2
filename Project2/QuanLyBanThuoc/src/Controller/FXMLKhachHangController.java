/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ctrl.KhachhangJpaController;
import Model.Khachhang;
import Util.StringToDate;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
public class FXMLKhachHangController implements Initializable {

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
    private TextField txtHoTen;
    private TextField txtSDT;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private RadioButton rbNam;
    @FXML
    private RadioButton rbNu;
    @FXML
    private TableView<Khachhang> tabDsDuyet;
    @FXML
    private TableColumn<?, ?> tcHoTen;
    @FXML
    private TableColumn<?, ?> tcDiaChi;
    @FXML
    private TableColumn<?, ?> tcGioiTinh;
    @FXML
    private DatePicker dpNgaySinh;
    @FXML
    private TextField txtTrieuChung;
    private TextField txttxtChuanDoan;
    @FXML
    private TableColumn<?, ?> tcTrieuChung;
    @FXML
    private TableColumn<?, ?> tcChuanDoan;
    @FXML
    private ToggleGroup gioiTinh;
    @FXML
    private TextField txtChuanDoan;
    @FXML
    private TextField txtMaKH;
    @FXML
    private TableColumn<?, ?> tcMaKH;
    @FXML
    private TableColumn<Khachhang, Date> tcNgaySinh;

    /**
     * Initializes the controller class.
     */
    private static final short HOAT_DONG =1;
    private static final short KHONG_HOAT_DONG =0;
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
    EntityManager em = emf.createEntityManager();
    TypedQuery<Khachhang> createNamedQuery = em.createNamedQuery("Khachhang.findAll", Khachhang.class);
    List<Khachhang> resultListKH = createNamedQuery.getResultList();
    KhachhangJpaController jpaController = new KhachhangJpaController(emf);
    @FXML
    private Label lblStatusKH;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tabDsDuyet.setItems(getKhachHangData());
    }
    
    public void initColumns() {
        tcMaKH.setCellValueFactory(new PropertyValueFactory<>("MaKH"));
        tcHoTen.setCellValueFactory(new PropertyValueFactory<>("HoTenKH")); 
        tcNgaySinh.setCellFactory(column -> {
            TableCell<Khachhang, Date> cell = new TableCell<Khachhang, Date>() {
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
        tcNgaySinh.setCellValueFactory(new PropertyValueFactory<>("NgaySinh"));
        tcGioiTinh.setCellValueFactory(new PropertyValueFactory<>("GioiTinh"));
        tcDiaChi.setCellValueFactory(new PropertyValueFactory<>("DiaChi"));
        tcTrieuChung.setCellValueFactory(new PropertyValueFactory<>("TrieuChung"));
        tcChuanDoan.setCellValueFactory(new PropertyValueFactory<>("ChuanDoan"));      
    }

    private ObservableList<Khachhang> data;

    public ObservableList<Khachhang> getKhachHangData() {
        data = FXCollections.observableArrayList(resultListKH);
        if (data == null) {
            return FXCollections.observableArrayList();
        }
        return data;
    }

    private void ReloadData() {
        data.clear();
        EntityManagerFactory newEmf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager newEm = newEmf.createEntityManager();
        TypedQuery<Khachhang> getAll = newEm.createNamedQuery("Khachhang.findAll", Khachhang.class);
        List<Khachhang> allKhachHang = getAll.getResultList();
        data = FXCollections.observableArrayList(allKhachHang);
        tabDsDuyet.setItems(data);
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
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLTKNCKhachHang.fxml"));
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
        Khachhang khachhang = new Khachhang();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (txtDiaChi.getText() != null && txtHoTen.getText() != null && dpNgaySinh.getValue() != null) {
                if (txtHoTen.getText().length() > 2) {
                        khachhang.setHoTenKH(txtHoTen.getText());
                        khachhang.setDiaChi(txtDiaChi.getText());
                        Date date = Date.from(dpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                        khachhang.setNgaySinh(date);
                        khachhang.setTrieuChung(txtTrieuChung.getText());
                        khachhang.setChuanDoan(txtChuanDoan.getText());
                        if (rbNam.isSelected()) {
                            khachhang.setGioiTinh("Nam");
                        } else {
                            khachhang.setGioiTinh("Nữ");
                        }
                        khachhang.setTrangThai(HOAT_DONG);
                        jpaController.create(khachhang);
                        btnLamMoi_Click(event);
                        lblStatusKH.setText("Thêm mới thành công!");                   
                } else {
                    lblStatusKH.setText("Họ tên phải lớn hơn 2 kí tự!");
                }
            } else {
                lblStatusKH.setText("Không được để trống các ô!");
            }
        } catch (Exception e) {
            e.getMessage();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @FXML
    private void btnSua_Click(ActionEvent event) {
        Khachhang khachhang = new Khachhang();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (txtDiaChi.getText() != null && txtHoTen.getText() != null && dpNgaySinh.getValue() != null) {
                if (txtHoTen.getText().length() > 2) {
                    int maKH = Integer.parseInt(txtMaKH.getText());
                    khachhang.setMaKH(maKH);
                    khachhang.setHoTenKH(txtHoTen.getText());
                    khachhang.setDiaChi(txtDiaChi.getText());
                    Date date = Date.from(dpNgaySinh.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                    khachhang.setNgaySinh(date);
                    khachhang.setTrieuChung(txtTrieuChung.getText());
                    khachhang.setChuanDoan(txtChuanDoan.getText());
                    if (rbNam.isSelected()) {
                        khachhang.setGioiTinh("Nam");
                    } else {
                        khachhang.setGioiTinh("Nữ");
                    }
                    khachhang.setTrangThai(HOAT_DONG);
                    jpaController.editKhachHang(khachhang);
                    btnLamMoi_Click(event);                   
                    lblStatusKH.setText("Sửa thành công!");                   
                } else {
                    lblStatusKH.setText("Họ tên phải lớn hơn 2 kí tự!");
                }
            } else {
                lblStatusKH.setText("Không được để trống các ô!");
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
        Khachhang khachHang = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (khachHang == null) {
            lblStatusKH.setText("Mời chọn khách hàng muốn xóa");
            return;
        } else {
            try {
               jpaController.delete(khachHang.getMaKH());
                btnLamMoi_Click(event);
                lblStatusKH.setText("Xóa nhân viên " + khachHang.getHoTenKH() + " thành công");
            } catch (Exception e) {
                e.getMessage();
                em.getTransaction().rollback();
            }
        }
    }

    @FXML
    private void btnLamMoi_Click(ActionEvent event) {
        txtMaKH.clear();
        txtHoTen.clear();
        txtTrieuChung.clear();
        txtChuanDoan.clear();
        txtDiaChi.clear();
        dpNgaySinh.setValue(null);
        txtTimKiem.clear();
        rbNam.setSelected(true);       
        lblStatusKH.setText("");
        ReloadData();
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
         for (Khachhang kh : resultListKH) {
            if (kh.getHoTenKH().equals(txtTimKiem.getText())) {
                data.clear();
                TypedQuery<Khachhang> findByName = em.createNamedQuery("Khachhang.findByHoTenKH", Khachhang.class);
                findByName.setParameter("hoTenKH", '%'+ kh.getHoTenKH() +'%');
                resultListKH = findByName.getResultList();
                data = FXCollections.observableArrayList(resultListKH);
                tabDsDuyet.setItems(data);
                lblStatusKH.setText("");
                return;
            } else {
                lblStatusKH.setText("Không tìm thấy !");
            }
        };
    }

    @FXML
    private void tabDsDuyet_Click(MouseEvent event) {
        Khachhang khachHang = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (khachHang == null) {
            return;
        } else {
            Integer maNV = khachHang.getMaKH();
            String hoTen = khachHang.getHoTenKH();
            String diaChi = khachHang.getDiaChi();
            String gioiTinh = khachHang.getGioiTinh();
            String trieuChung = khachHang.getTrieuChung();
            String chuanDoan = khachHang.getChuanDoan();
            Date input = khachHang.getNgaySinh();
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            txtMaKH.setText(maNV.toString());
            txtHoTen.setText(hoTen);
            dpNgaySinh.setValue(date);
            txtDiaChi.setText(diaChi);
            txtTrieuChung.setText(trieuChung);
            txtChuanDoan.setText(chuanDoan);
            if (khachHang.getGioiTinh().equals("Nam")) {
                rbNam.setSelected(true);
            } else {
                rbNu.setSelected(true);
            }
        }
    }

}
