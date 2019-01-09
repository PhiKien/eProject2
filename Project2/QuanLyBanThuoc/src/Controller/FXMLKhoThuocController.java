/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ctrl.KhothuocJpaController;
import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Khothuoc;
import Util.StringToDate;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FXMLKhoThuocController implements Initializable {

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
    private TextField txtMaThuoc;
    @FXML
    private TextField txtTenThuoc;
    @FXML
    private TextField txtDonVi;
    @FXML
    private DatePicker dpNSX;
    @FXML
    private DatePicker dpHSD;
    @FXML
    private TableView<Khothuoc> tabDsThuoc;
    @FXML
    private TableColumn<Khothuoc, String> tcTenThuoc;
    @FXML
    private TableColumn<Khothuoc, String> tcDonVi;
    @FXML
    private TableColumn<Khothuoc, Date> tcNSX;
    @FXML
    private TableColumn<Khothuoc, Date> tcHSD;
    @FXML
    private TableColumn<Khothuoc, Integer> tcDonGia;
    @FXML
    private TableColumn<Khothuoc, Integer> tcMaThuoc;
    @FXML
    private TextField txtDonGia;
    @FXML
    private Label lblStatusThuoc;

    /**
     * Initializes the controller class.
     */
    private static final short HOAT_DONG = 1;
    private static final short KHONG_HOAT_DONG = 0;
    private final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
    EntityManager em = emf.createEntityManager();
    TypedQuery<Khothuoc> createNamedQuery = em.createNamedQuery("Khothuoc.findAll", Khothuoc.class);
    List<Khothuoc> resultListKT = createNamedQuery.getResultList();
    KhothuocJpaController jpaController = new KhothuocJpaController(emf);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        tabDsThuoc.setItems(getKhoThuocData());
    }

    public void initColumns() {
        tcMaThuoc.setCellValueFactory(new PropertyValueFactory<>("MaThuoc"));
        tcTenThuoc.setCellValueFactory(new PropertyValueFactory<>("TenThuoc"));

        tcNSX.setCellFactory(column -> {
            TableCell<Khothuoc, Date> cell = new TableCell<Khothuoc, Date>() {
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
        tcNSX.setCellValueFactory(new PropertyValueFactory<>("nsx"));

        tcHSD.setCellFactory(column -> {
            TableCell<Khothuoc, Date> cell = new TableCell<Khothuoc, Date>() {
                private final SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        this.setText(format2.format(item));

                    }
                }
            };
            return cell;
        });
        tcHSD.setCellValueFactory(new PropertyValueFactory<>("hsd"));
        
        tcDonVi.setCellValueFactory(new PropertyValueFactory<>("DonVi"));
        tcDonGia.setCellValueFactory(new PropertyValueFactory<>("DonGia"));
    }

    private ObservableList<Khothuoc> data;

    public ObservableList<Khothuoc> getKhoThuocData() {
        data = FXCollections.observableArrayList(resultListKT);
        if (data == null) {
            return FXCollections.observableArrayList();
        }
        return data;
    }

    private void ReloadData() {
        data.clear();
        EntityManagerFactory newEmf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager newEm = newEmf.createEntityManager();
        TypedQuery<Khothuoc> getAll = newEm.createNamedQuery("Khothuoc.findAll", Khothuoc.class);
        List<Khothuoc> allKhachHang = getAll.getResultList();
        data = FXCollections.observableArrayList(allKhachHang);
        tabDsThuoc.setItems(data);
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
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLTKNCKhoThuoc.fxml"));
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
        Khothuoc thuoc = new Khothuoc();
        StringToDate stringToDate = new StringToDate();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (txtTenThuoc.getText() != null && txtDonVi.getText() != null && txtDonGia.getText() != null && dpNSX.getValue() != null && dpHSD.getValue() != null) {
                thuoc.setTenThuoc(txtTenThuoc.getText());
                thuoc.setDonVi(txtDonVi.getText());
                int gia = Integer.parseInt(txtDonGia.getText());
                thuoc.setDonGia(gia);
                Date dateNSX = Date.from(dpNSX.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                thuoc.setNsx(dateNSX);
                Date dateHSD = Date.from(dpHSD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                thuoc.setHsd(dateHSD);
                thuoc.setTrangThai(HOAT_DONG);
                jpaController.create(thuoc);
                btnLamMoi_Click(event);
                lblStatusThuoc.setText("Thêm mới thành công!");
            } else {
                lblStatusThuoc.setText("Không được để trống các ô!");
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
        Khothuoc thuoc = new Khothuoc();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (txtTenThuoc.getText() != null && txtDonVi.getText() != null && txtDonGia.getText() != null && dpNSX.getValue() != null && dpHSD.getValue() != null) {
                
                thuoc.setMaThuoc(Integer.parseInt(txtMaThuoc.getText()));
                
                thuoc.setTenThuoc(txtTenThuoc.getText());
                
                thuoc.setDonVi(txtDonVi.getText());
                
                int gia = Integer.parseInt(txtDonGia.getText());
                thuoc.setDonGia(gia);
                
                Date dateNSX = Date.from(dpNSX.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                thuoc.setNsx(dateNSX);
                
                Date dateHSD = Date.from(dpHSD.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                thuoc.setHsd(dateHSD);
                
                thuoc.setTrangThai(HOAT_DONG);
                
                System.out.println(thuoc);
                jpaController.editKhoThuoc(thuoc);
                btnLamMoi_Click(event);
                lblStatusThuoc.setText("Sửa thành công!");
            } else {
                lblStatusThuoc.setText("Không được để trống các ô!");
            }
        } catch (NumberFormatException e) {
            e.getMessage();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @FXML
    private void btnXoa_Click(ActionEvent event) {
        Khothuoc khothuoc = tabDsThuoc.getSelectionModel().getSelectedItem();
        if (khothuoc == null) {
            lblStatusThuoc.setText("Mời chọn thuốc muốn xóa!");
            return;
        } else {
            try {
               jpaController.destroy(khothuoc.getMaThuoc());
                btnLamMoi_Click(event);
                lblStatusThuoc.setText("Xóa thuốc " + khothuoc.getTenThuoc()+ " thành công!");
            } catch (Exception e) {
                e.getMessage();
                em.getTransaction().rollback();
            }
        }
    }

    @FXML
    private void btnLamMoi_Click(ActionEvent event) {
        txtMaThuoc.clear();
        txtTenThuoc.clear();
        txtDonVi.clear();
        txtDonGia.clear();
        txtTimKiem.clear();
        dpNSX.setValue(null);
        dpHSD.setValue(null);
        lblStatusThuoc.setText("");
        ReloadData();
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
        for (Khothuoc kt : resultListKT) {
            if (kt.getTenThuoc().equals(txtTimKiem.getText())) {
                data.clear();
                TypedQuery<Khothuoc> findByName = em.createNamedQuery("Khothuoc.findByTenThuoc", Khothuoc.class);
                findByName.setParameter("tenThuoc", kt.getTenThuoc());
                resultListKT = findByName.getResultList();
                data = FXCollections.observableArrayList(resultListKT);
                tabDsThuoc.setItems(data);
                lblStatusThuoc.setText("");
                return;
            } else {
                lblStatusThuoc.setText("Không tìm thấy !");
            }
        };
    }

    @FXML
    private void tabDsThuoc_Click(MouseEvent event) {
        Khothuoc khothuoc = tabDsThuoc.getSelectionModel().getSelectedItem();
        if (khothuoc == null) {
            return;
        } else {
            Integer maThuoc = khothuoc.getMaThuoc();
            String tenThuoc = khothuoc.getTenThuoc();
            String donVi = khothuoc.getDonVi();
            Date dateHSD = khothuoc.getHsd();
            LocalDate HSD = dateHSD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Date dateNSX = khothuoc.getNsx();
            LocalDate NSX = dateNSX.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Integer donGia = khothuoc.getDonGia();

            txtMaThuoc.setText(maThuoc.toString());
            txtTenThuoc.setText(tenThuoc);
            dpHSD.setValue(HSD);
            dpNSX.setValue(NSX);
            txtDonGia.setText(donGia.toString());
            txtDonVi.setText(donVi);
        }
    }
}


