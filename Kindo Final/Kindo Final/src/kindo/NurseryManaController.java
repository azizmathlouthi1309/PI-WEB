/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.SaveNurseService;
import Entities.SaveNurse;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class NurseryManaController implements Initializable {

    @FXML
    private Button MenuMana;
    @FXML
    private Button RestoMana;
    @FXML
    private Button NursMana;
    @FXML
    private Button QuitBtn;
    @FXML
    private TableView<SaveNurse> DataNurs;
    @FXML
    private TableColumn<SaveNurse, String> beginD;
    @FXML
    private TableColumn<SaveNurse, String> endD;
       ObservableList<SaveNurse> listN = FXCollections.observableArrayList();
    @FXML
    private TableColumn<SaveNurse, Integer> idC;
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button addbtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SaveNurseService SM=new SaveNurseService();
        try {
            listN.addAll(SM.readAll());
            System.out.println(listN.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        idC.setCellValueFactory(new PropertyValueFactory<SaveNurse, Integer>("id"));
        beginD.setCellValueFactory(new PropertyValueFactory<SaveNurse, String>("date_begin"));
        endD.setCellValueFactory(new PropertyValueFactory<SaveNurse, String>("date_end"));
        
        DataNurs.setItems(listN);
       
    }    

    @FXML
    private void deleteitem(MouseEvent event) throws SQLException {
         SaveNurseService SM = new SaveNurseService();
        SM.delete(idC.getCellData(DataNurs.getSelectionModel().getSelectedIndex()));
        JOptionPane.showMessageDialog(null, "Subscription Deleted");
        DataNurs.getItems().clear();
        DataNurs.getItems().addAll(listN);
    }

    @FXML
    private void addnursery(MouseEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddNursery.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void returnmenu(MouseEvent event) throws IOException {
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
    private void gotomenu(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuMana.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotorest(MouseEvent event) throws IOException {
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


    
}
