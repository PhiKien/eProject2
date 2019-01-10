/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Cart;
import Model.Chitiethoadon;
import Model.ChitiethoadonPK;
import Model.Ctrl.ChitiethoadonJpaController;
import Model.Ctrl.HoadonJpaController;
import Model.Ctrl.KhothuocJpaController;
import Model.Ctrl.exceptions.IllegalOrphanException;
import Model.Ctrl.exceptions.NonexistentEntityException;
import Model.Hoadon;
import Model.Khachhang;
import Model.Khothuoc;
import Model.Nhanvien;
import Util.StringToDate;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
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

    private TextField txtTenKH;
    private TextField txtTenNguoiBan;

    private static final short HOAT_DONG = 1;
    private static final short KHONG_HOAT_DONG = 0;

    List<Cart> listCart;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
    EntityManager em = emf.createEntityManager();

    TypedQuery<Hoadon> createNamedQuery = em.createNamedQuery("Hoadon.findAll", Hoadon.class);
    List<Hoadon> resultListHD = createNamedQuery.getResultList();

    HoadonJpaController jpaController = new HoadonJpaController(emf);

    TypedQuery<Khachhang> createNamedQueryKH = em.createNamedQuery("Khachhang.findAll", Khachhang.class);
    List<Khachhang> resultListKH = createNamedQueryKH.getResultList();
    
    TypedQuery<Chitiethoadon> createNamedQueryCT = em.createNamedQuery("Chitiethoadon.findAll", Chitiethoadon.class);
    List<Chitiethoadon> resultListCT = createNamedQueryCT.getResultList();

    TypedQuery<Khothuoc> khoThuoc = em.createNamedQuery("Khothuoc.findAll", Khothuoc.class);
    List<Khothuoc> resultListThuoc = khoThuoc.getResultList();

    TypedQuery<Nhanvien> createNamedQueryNV = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
    List<Nhanvien> resultListNV = createNamedQueryNV.getResultList();

    @FXML
    private TableColumn<Hoadon, String> tcHoTenKH;
    @FXML
    private TableColumn<Hoadon, String> tcHoTenNV;
    @FXML
    private ComboBox<Nhanvien> cbNhanVien;
    @FXML
    private ComboBox<Khachhang> cbKhachHang;
    @FXML
    private ComboBox<Khothuoc> cbLoaiThuoc;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private Button btnThemThuoc;
    @FXML
    private Button btnBotThuoc;
    @FXML
    private Button btnXoaThuoc;
    @FXML
    private TextField txtMaHoaDon1;
    @FXML
    private TableView<Cart> tabCart;
    @FXML
    private TableColumn<Cart, String> tcTenLoaiThuoc;
    @FXML
    private TableColumn<Cart, Integer> tcSoLuong;
    @FXML
    private TableColumn<Cart, Integer> tcDonGia;
    @FXML
    private TableColumn<Cart, Integer> tcThanhTien;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initColumns();
        listCart = new ArrayList<>();
        tabDsDuyet.setItems(getHoaDonData());

        //Load combo box Khach Hang
        cbKhachHang.setItems(getKhachHangData());
        cbKhachHang.setCellFactory((ListView<Khachhang> l) -> new ListCell<Khachhang>() {
            @Override
            protected void updateItem(Khachhang item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getMaKH() + "\t" + item.getHoTenKH());
                }
            }
        });

        cbKhachHang.setConverter(new StringConverter<Khachhang>() {
            @Override
            public String toString(Khachhang user) {
                if (user == null) {
                    return null;
                } else {
                    return user.getMaKH() + " - " + user.getHoTenKH();
                }
            }

            @Override
            public Khachhang fromString(String userId) {
                return null;
            }
        });

        //Load combo Box Nhan Vien
        cbNhanVien.setItems(getNhanVienData());
        cbNhanVien.setCellFactory((ListView<Nhanvien> l) -> new ListCell<Nhanvien>() {
            @Override
            protected void updateItem(Nhanvien item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getMaNV() + "\t" + item.getHoTenNV());
                }
            }
        });

        cbNhanVien.setConverter(new StringConverter<Nhanvien>() {
            @Override
            public String toString(Nhanvien user) {
                if (user == null) {
                    return null;
                } else {
                    return user.getMaNV() + " - " + user.getHoTenNV();
                }
            }

            @Override
            public Nhanvien fromString(String userId) {
                return null;
            }
        });

        //Load combo Box Thuốc
        cbLoaiThuoc.setItems(getKhoThuocData());
        cbLoaiThuoc.setCellFactory((ListView<Khothuoc> l) -> new ListCell<Khothuoc>() {
            @Override
            protected void updateItem(Khothuoc item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    setText(item.getTenThuoc());
                }
            }
        });

        cbLoaiThuoc.setConverter(new StringConverter<Khothuoc>() {
            @Override
            public String toString(Khothuoc user) {
                if (user == null) {
                    return null;
                } else {
                    return user.getTenThuoc();
                }
            }

            @Override
            public Khothuoc fromString(String userId) {
                return null;
            }
        });
    }
    
    private void ReloadData() {
        data.clear();
        EntityManagerFactory newEmf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager newEm = newEmf.createEntityManager();
        TypedQuery<Hoadon> getAll = newEm.createNamedQuery("Hoadon.findAll", Hoadon.class);
        List<Hoadon> allHoaDon = getAll.getResultList();
        data = FXCollections.observableArrayList(allHoaDon);
        tabDsDuyet.setItems(data);
    }

    private void ReloadCart() {
        cartData.clear();
        List<Cart> newList = listCart;
        cartData = FXCollections.observableArrayList(newList);
        tabCart.setItems(cartData);
    }
    
    private void ReloadDataChiTietHoaDon(){
        cartData.clear();
        EntityManagerFactory newEmf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
        EntityManager newEm = newEmf.createEntityManager();
        TypedQuery<Chitiethoadon> getAll = newEm.createNamedQuery("Chitiethoadon.findAll", Chitiethoadon.class);
        List<Chitiethoadon> allCTHoaDon = getAll.getResultList();
        List<Cart> newList = new ArrayList<>();
        for (Chitiethoadon chitiethoadon : allCTHoaDon) {
            Cart ca = new Cart();
            ca.setMaThuoc(chitiethoadon.getKhothuoc().getMaThuoc());
            ca.setTenThuoc(chitiethoadon.getKhothuoc().getTenThuoc());
            ca.setSoLuong(chitiethoadon.getSoLuong());
            ca.setDonGia(chitiethoadon.getKhothuoc().getDonGia());
            ca.setThanhTien(chitiethoadon.getSoLuong() * chitiethoadon.getKhothuoc().getDonGia());
            newList.add(ca);
        }
        cartData = FXCollections.observableArrayList(newList);
        tabCart.setItems(cartData);
    }

    public void initColumns() {
        tcMaHD.setCellValueFactory(new PropertyValueFactory<>("maHD"));
        tcHoTenKH.setCellValueFactory((TableColumn.CellDataFeatures<Hoadon, String> param) -> new SimpleStringProperty(param.getValue().getMaKH().getHoTenKH()));
        tcHoTenNV.setCellValueFactory((TableColumn.CellDataFeatures<Hoadon, String> param) -> new SimpleStringProperty(param.getValue().getMaNV().getHoTenNV()));
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

    private ObservableList<Cart> cartData;
    
    private ObservableList<Chitiethoadon> chiTietData;
    
    public ObservableList<Chitiethoadon> getCTData(){
        chiTietData = FXCollections.observableArrayList(resultListCT);
        if (chiTietData == null) {
            return FXCollections.observableArrayList();
        }
        return chiTietData;
    }
    
    public ObservableList<Cart> getCartData() {
        cartData = FXCollections.observableArrayList(listCart);
        if (data == null) {
            return FXCollections.observableArrayList();
        }
        return cartData;
    }

    public ObservableList<Hoadon> getHoaDonData() {
        data = FXCollections.observableArrayList(resultListHD);
        if (data == null) {
            return FXCollections.observableArrayList();
        }
        return data;
    }

    public ObservableList<Khachhang> getKhachHangData() {
        return FXCollections.observableArrayList(resultListKH);
    }

    public ObservableList<Khothuoc> getKhoThuocData() {
        return FXCollections.observableArrayList(resultListThuoc);
    }

    public ObservableList<Nhanvien> getNhanVienData() {
        return FXCollections.observableArrayList(resultListNV);
    }

    @FXML
    private void mnItemThem_Click(ActionEvent event) {
        btnThem_Click(event);
    }

    @FXML
    private void mnItemSua_Click(ActionEvent event) {
        btnSua_Click(event);
    }

    @FXML
    private void mnItemXoa_Click(ActionEvent event) {
        btnXoa_Click(event);
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
        Hoadon hoaDon = new Hoadon();
        Chitiethoadon chiTiet = new Chitiethoadon();
        ChitiethoadonJpaController ctrl = new ChitiethoadonJpaController(emf);
        KhothuocJpaController thuocCtrl = new KhothuocJpaController(emf);
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (cbNhanVien.getValue() != null && cbKhachHang.getValue() != null && dpNgayBan.getValue() != null) {
                hoaDon.setMaKH(cbKhachHang.getValue());
                hoaDon.setMaNV(cbNhanVien.getValue());
                Date date = Date.from(dpNgayBan.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                hoaDon.setNgayLapHD(date);
                hoaDon.setTongTien(Integer.parseInt(txtTongTien.getText()));
                hoaDon.setTrangThai(HOAT_DONG);
                jpaController.create(hoaDon);
                if(listCart != null || listCart.size() > 0){
                    for (Cart cart : listCart) {
                        chiTiet.setHoadon(hoaDon);
                        Khothuoc thuoc = thuocCtrl.findKhothuoc(cart.getMaThuoc());
                        chiTiet.setKhothuoc(thuoc);
                        chiTiet.setSoLuong(cart.getSoLuong());
                        chiTiet.setTrangThai(HOAT_DONG);
                        ctrl.create(chiTiet);
                    }
                }
                btnLamMoi_Click(event);
                lblStatusHD.setText("Thêm mới thành công!");
            } else {
                lblStatusHD.setText("Không được để trống các ô!");
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
        Hoadon hoaDon = new Hoadon();
        StringToDate stringToDate = new StringToDate();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");

        EntityManager em = emf.createEntityManager();
        //bắt đầu tạo transaction
        em.getTransaction().begin();
        try {
            if (cbKhachHang.getValue()!= null && cbNhanVien.getValue() != null && dpNgayBan.getValue() != null) {
                    int maHD = Integer.parseInt(txtMaHoaDon.getText());
                    hoaDon.setMaHD(maHD);
                    hoaDon.setMaKH(cbKhachHang.getValue());
                    hoaDon.setMaNV(cbNhanVien.getValue());
                    Date date = Date.from(dpNgayBan.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());//convert localDate -> date
                    hoaDon.setNgayLapHD(date);
                    hoaDon.setTrangThai(HOAT_DONG);
                    int tongTien = Integer.parseInt(txtTongTien.getText());
                    hoaDon.setTongTien(tongTien);
                    jpaController.editHoaDon(hoaDon);
                    if(listCart != null || listCart.size() > 0){
                        for (Cart cart : listCart) {
                            Chitiethoadon ct = new Chitiethoadon();
                            KhothuocJpaController ktJPACtrl = new KhothuocJpaController(emf);
                            Khothuoc kt = ktJPACtrl.findKhothuoc(cart.getMaThuoc());
                            ChitiethoadonPK ctPK = new ChitiethoadonPK(hoaDon.getMaHD(), kt.getMaThuoc());
                            ct.setChitiethoadonPK(ctPK);
                            ct.setHoadon(hoaDon);
                            ct.setKhothuoc(kt);
                            ct.setSoLuong(cart.getSoLuong());
                            ct.setTrangThai(HOAT_DONG);
                            Cart newCart = new Cart();
                            newCart.setMaThuoc(ct.getKhothuoc().getMaThuoc());
                            newCart.setTenThuoc(ct.getKhothuoc().getTenThuoc());
                            newCart.setSoLuong(ct.getSoLuong());
                            newCart.setDonGia(ct.getKhothuoc().getDonGia());
                            newCart.setThanhTien(ct.getSoLuong() * ct.getKhothuoc().getDonGia());
                            cart = newCart;
                            System.out.println(listCart);
                            ctJPACtrol.editChiTietHoaDon(ct);
                        }
                    }
                    btnLamMoi_Click(event);                   
                    lblStatusHD.setText("Sửa thành công!");                                  
            } else {
                lblStatusHD.setText("Không được để trống các ô!");
            }
        } catch (Exception e) {
            e.getMessage();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    private final ChitiethoadonJpaController ctJPACtrol = new ChitiethoadonJpaController(emf);

    @FXML
    private void btnXoa_Click(ActionEvent event) {
        Hoadon hoaDon = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (hoaDon == null) {
            lblStatusHD.setText("Mời chọn hóa đơn muốn xóa!");
            return;
        } else {
            try {
                for (Cart cart : listCart) {
                    ChitiethoadonPK pk = new ChitiethoadonPK(hoaDon.getMaHD(), cart.getMaThuoc());
                    ctJPACtrol.destroy(pk);
                }
                jpaController.destroy(hoaDon.getMaHD());
                btnLamMoi_Click(event);
                lblStatusHD.setText("Xóa hóa đơn " + hoaDon.getMaHD() + " thành công!");
            } catch (IllegalOrphanException | NonexistentEntityException e) {
                e.getMessage();
                em.getTransaction().rollback();
            }
        }
    }

    @FXML
    private void btnLamMoi_Click(ActionEvent event) {
        txtMaHoaDon.clear();
        txtTimKiem.clear();
        cbNhanVien.setValue(null);
        cbKhachHang.setValue(null);
        cbLoaiThuoc.setValue(null);
        txtSoLuong.clear();
        dpNgayBan.setValue(null);
        lblStatusHD.setText(null);
        txtTongTien.clear();
        listCart = new ArrayList<>();
        txtSoLuong.clear();
        ReloadData();
        ReloadCart();
    }

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
        for (Hoadon hd : resultListHD) {
            if (hd.getMaKH().getHoTenKH().equals(txtTimKiem.getText())) {
                data.clear();
                TypedQuery<Hoadon> findByName = em.createNamedQuery("Hoadon.findByMaHD", Hoadon.class);
                findByName.setParameter("maHD", hd.getMaHD());
                resultListHD = findByName.getResultList();
                data = FXCollections.observableArrayList(resultListHD);
                tabDsDuyet.setItems(data);
                lblStatusHD.setText("");
                return;
            } else {
                lblStatusHD.setText("Không tìm thấy !");
            }
        };
    }
    
    public List<Chitiethoadon> getCTHoaDonByMa(int maHD){
        TypedQuery<Chitiethoadon> findByMaHD = em.createNamedQuery("Chitiethoadon.findByMaHD", Chitiethoadon.class);
        findByMaHD.setParameter("maHD", maHD);
        List<Chitiethoadon> listCTbyMaHD = findByMaHD.getResultList();
        System.out.println(listCTbyMaHD);
        return listCTbyMaHD;
    }

    @FXML
    private void tabDsDuyet_Click(MouseEvent event) {
        Hoadon hoaDon = tabDsDuyet.getSelectionModel().getSelectedItem();
        if (hoaDon == null) {
            return;
        } else {
            Integer maHD = hoaDon.getMaHD();
            Date input = hoaDon.getNgayLapHD();
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Integer tongTien = hoaDon.getTongTien();
            txtMaHoaDon.setText(maHD.toString());
            cbKhachHang.setValue(hoaDon.getMaKH());
            cbNhanVien.setValue(hoaDon.getMaNV());
            dpNgayBan.setValue(date);
            txtTongTien.setText(tongTien.toString());
            List<Chitiethoadon> listCTbyMaHD = getCTHoaDonByMa(hoaDon.getMaHD());
            listCart = new ArrayList<>();     
            for (Chitiethoadon ct : listCTbyMaHD) {
                Cart newCart = new Cart();
                newCart.setMaThuoc(ct.getKhothuoc().getMaThuoc());
                newCart.setTenThuoc(ct.getKhothuoc().getTenThuoc());
                newCart.setDonGia(ct.getKhothuoc().getDonGia());
                newCart.setSoLuong(ct.getSoLuong());
                newCart.setThanhTien(ct.getSoLuong() * ct.getKhothuoc().getDonGia());
                listCart.add(newCart);
            }
            cartData = FXCollections.observableArrayList(listCart);
            initColumnsCart();
            tabCart.setItems(cartData);
        }
    }

    @FXML
    private void btnThemLoaiThuoc_Click(ActionEvent event) {
        for (Cart cart : listCart) {
            if (cbLoaiThuoc.getValue().getMaThuoc().equals(cart.getMaThuoc())) {
                int sl = Integer.parseInt(txtSoLuong.getText());
                cart.setSoLuong(cart.getSoLuong() + sl);
                int thanhTien = sl * cbLoaiThuoc.getValue().getDonGia();
                cart.setThanhTien(cart.getThanhTien() + thanhTien);
                ReloadCart();
                tinhTongTienHoaDon();
                return;
            }
        }

        String tenThuoc = cbLoaiThuoc.getValue().getTenThuoc();
        int maThuoc = cbLoaiThuoc.getValue().getMaThuoc();
        int sl = Integer.parseInt(txtSoLuong.getText());
        int thanhTien = sl * cbLoaiThuoc.getValue().getDonGia();
        int donGia = cbLoaiThuoc.getValue().getDonGia();
        Cart c = new Cart(maThuoc, tenThuoc, sl, donGia, thanhTien);
        listCart.add(c);
        tinhTongTienHoaDon();
        initColumnsCart();
        tabCart.setItems(getCartData());
    }

    private void tinhTongTienHoaDon() {
        int tongTien = 0;
        for (Cart cart : listCart) {
            tongTien += cart.getThanhTien();
        }
        txtTongTien.setText(String.valueOf(tongTien));
    }

    public void initColumnsCart() {
        tcTenLoaiThuoc.setCellValueFactory((TableColumn.CellDataFeatures<Cart, String> param) -> new SimpleStringProperty(param.getValue().getTenThuoc()));
        tcSoLuong.setCellValueFactory(new PropertyValueFactory<>("soLuong"));
        tcDonGia.setCellValueFactory(new PropertyValueFactory<>("donGia"));
        tcThanhTien.setCellValueFactory(new PropertyValueFactory<>("thanhTien"));
    }

    @FXML
    private void btnBotLoaiThuoc_Click(ActionEvent event) {
        for (Cart cart : listCart) {
            if (cbLoaiThuoc.getValue().getTenThuoc().equals(cart.getTenThuoc())) {
                int sl = Integer.parseInt(txtSoLuong.getText());
                if (sl >= cart.getSoLuong()) {
                    listCart.remove(cart);
                    ReloadCart();
                    tinhTongTienHoaDon();
                    return;
                } else {
                    cart.setSoLuong(cart.getSoLuong() - sl);
                }
                int thanhTien = sl * cbLoaiThuoc.getValue().getDonGia();
                cart.setThanhTien(cart.getThanhTien() - thanhTien);
                ReloadCart();
                tinhTongTienHoaDon();
            }
        }
    }

    @FXML
    private void btnXoaLoaiThuoc_Click(ActionEvent event) {
        for (Cart cart : listCart) {
            if (cbLoaiThuoc.getValue().getTenThuoc().equals(cart.getTenThuoc())) {
                listCart.remove(cart);
                ReloadCart();
                tinhTongTienHoaDon();
                return;
            }
        }
    }

    @FXML
    private void tabCart_Click(MouseEvent event) {
        Cart cartSelected = tabCart.getSelectionModel().getSelectedItem();
        if (cartSelected == null) {
            return;
        } else {
            Integer maThuoc = cartSelected.getMaThuoc();
            String tenThuoc = cartSelected.getTenThuoc();
            int soLuong = cartSelected.getSoLuong();
            int donGia = cartSelected.getDonGia();
            int thanhTien = cartSelected.getThanhTien();
            KhothuocJpaController ktCtrl = new KhothuocJpaController(emf);
            Khothuoc t = ktCtrl.findKhothuoc(maThuoc);
            cbLoaiThuoc.setValue(t);
            txtSoLuong.setText(String.valueOf(soLuong));
        }
    }

}
