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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Services.mailService;
import static Services.mailService.sendMail;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author nada
 */
public class MenuManaController implements Initializable {
    @FXML
    private TableView<Menu> TableData;
    @FXML
    private Button MenuMana;
    @FXML
    private Button RestoMana;
    @FXML
    private Button NursMana;
    @FXML
    private Button QuitBtn;
    @FXML
    private TableColumn<Menu, Integer> IdC;
    @FXML
    private TableColumn<Menu, String> DateC;
    @FXML
    private TableColumn<Menu,String> P1C;
    @FXML
    private TableColumn<Menu, String> P2C;
    @FXML
    private TableColumn<Menu, String> P3C;
           ObservableList<Menu> listM = FXCollections.observableArrayList();

    @FXML
    private Button DeleteBtn;
    @FXML
    private Button sendmails;
    @FXML
    private Button AddBtn;


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
        IdC.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
        DateC.setCellValueFactory(new PropertyValueFactory<Menu, String>("date_day"));
        P1C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate1"));
        P2C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate2"));
        P3C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate3"));
     

        TableData.setItems(listM);
    }    

 

    @FXML
    private void deleteitem(MouseEvent event) throws SQLException {
        MenuService SM = new MenuService();
        SM.delete(IdC.getCellData(TableData.getSelectionModel().getSelectedIndex()));
        JOptionPane.showMessageDialog(null, "Menu Deleted");
        TableData.getItems().clear();
        TableData.getItems().addAll(listM);
        refreshaffich();

    }

    @FXML
    private void sendit(MouseEvent event) throws Exception {
        sendMail("khaoula.mekni@esprit.tn");
    }

    @FXML
    private void addsurface(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddMenu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
    public void refreshaffich()
    {
     MenuService SM=new MenuService();
        try {
           listM.addAll(SM.readAll());
            System.out.println(listM.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        IdC.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
        DateC.setCellValueFactory(new PropertyValueFactory<Menu, String>("date_day"));
        P1C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate1"));
        P2C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate2"));
        P3C.setCellValueFactory(new PropertyValueFactory<Menu, String>("plate3"));
     

        TableData.setItems(listM);
        
    }

    @FXML
    private void returntomenu(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackOffMenu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotoresto(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("RestoMana.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotonurs(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("NurseryMana.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
}
