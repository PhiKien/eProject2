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

/**
 * FXML Controller class
 *
 * @author drago
 */
public class FXMLTKNCNhanVienController implements Initializable {

    @FXML
    private Button btnTimKiem;
    @FXML
    private Button btnLamMoi;
    @FXML
    private TextField txtMaBenhNhan;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtDiaChi;
    @FXML
    private TextField txtSDT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnTimKiem_Click(ActionEvent event) {
    }

    @FXML
    private void btnLamMoi_Click(ActionEvent event) {
    }
    
}
