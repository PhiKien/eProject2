/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author drago
 */
public class FXMLMainHomeController implements Initializable {

    @FXML
    private Pane paneDangNhap,paneDangKy;
    
    @FXML
    private Button btnDangNhap, btnDangKy;
    @FXML
    private Button btnHuongDanSuDung;
    @FXML
    private Button btnNhaPhatTrien;
    @FXML
    private Button btnThoat;
    @FXML
    private TextField inputText1;
    @FXML
    private TextField inputText11;
    @FXML
    private TextField inputText111;
    @FXML
    private TextField txtTenDangNhap;
    @FXML
    private TextField txtMatKhau;
    @FXML
    private Pane paneNhaPhatTrien;
    @FXML
    private Pane paneHuongDanSuDung;
    
    /**
     * Initializes the controller class.
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
    }

    @FXML
    private void btnDangKy_Click(ActionEvent event) {
        paneDangKy.toFront();
    }
    
}
