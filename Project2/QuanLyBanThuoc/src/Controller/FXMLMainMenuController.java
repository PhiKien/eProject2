/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Ctrl.NhanvienJpaController;
import Model.Nhanvien;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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
public class FXMLMainMenuController implements Initializable {

    @FXML
    private Pane paneNhanVien;
    @FXML
    private Pane paneKhachHang;
    @FXML
    private Pane paneKhoThuoc;
    @FXML
    private Pane paneHoaDon;

    public static String userName;
    @FXML
    private Label lblStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void showAlert(){
        Alert al = new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("Thông báo!");
        al.setHeaderText(null);
        al.setContentText("Chỉ có admin được sử dụng chức năng này!");
        al.showAndWait();
    }
    
    @FXML
    private void paneNhanVien_Click(MouseEvent event) {
        if (userName.equals("admin")) {
            try {
                //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
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
        } else {
            showAlert();
        }
    }

    @FXML
    private void paneKhachHang_Click(MouseEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLKhachHang.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Khách hàng");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void paneKhoThuoc_Click(MouseEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLKhoThuoc.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Kho thuốc");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void paneHoaDon_Click(MouseEvent event) {
        try {
            //((Node) event.getSource()).getScene().getWindow().hide();    an di from home
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/View/FXMLHoaDon.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage window = new Stage();
            window.setTitle("Hóa đơn");
            window.setScene(scene);
            window.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
