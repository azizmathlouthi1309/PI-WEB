/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.MenuService;
import Entities.Menu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class AffichageMenusController implements Initializable {
     @FXML
    private TableView<Menu> TableMenus;
    @FXML
    private TableColumn<Menu,String> DateC;
    @FXML
    private TableColumn<Menu,String> P1C;
    @FXML
    private TableColumn<Menu, String> P2C;
    @FXML
    private TableColumn<Menu, String> P3C;
       ObservableList<Menu> listM = FXCollections.observableArrayList();


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       MenuService SM=new MenuService();
        try {
            listM.addAll(SM.readAll());
            System.out.println(listM.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        DateC.setCellValueFactory(new PropertyValueFactory<Menu, String>("date_day"));
        P1C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate1"));
        P2C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate2"));
        P3C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate3"));
     

        TableMenus.setItems(listM);
    }    

    @FXML
    private void returnback(MouseEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("TestResto.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                /*HomeController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpassword(password_label.getText());*/
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
}
