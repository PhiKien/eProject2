/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Nhanvien;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author drago
 */
public class FXMainHome extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLMainHome.fxml"));
            primaryStage.setTitle("Đăng nhập");
            primaryStage.setScene(new Scene(root));
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMainHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
//        Nhanvien nhanvien = new Nhanvien();
//        //tạo entity
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuanLyBanThuocPU");
//        EntityManager em = emf.createEntityManager();
//        //bắt đầu tạo transaction
//        em.getTransaction().begin();
//      
//            //tạo list Usename ở nhân viên
//            TypedQuery<Nhanvien> createNamedQuery = em.createNamedQuery("Nhanvien.findAll", Nhanvien.class);
//            //lấy list username
//            List<Nhanvien> resultListNVUser = createNamedQuery.getResultList();
//            
//            resultListNVUser.forEach((t) -> {
//                System.out.println(t.getUsernane());
//            });
//            
    }

}
