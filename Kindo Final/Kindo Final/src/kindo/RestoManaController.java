/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;


import Services.SaveRestoService;
import Entities.SaveResto;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class RestoManaController implements Initializable {

    @FXML
    private Button MenuMana;
    @FXML
    private Button RestoMana;
    @FXML
    private Button NursMana;
    @FXML
    private Button QuitBtn;
    @FXML
    private TableColumn<SaveResto, String> begindate;
    @FXML
    private TableColumn<SaveResto, String> enddate;
    @FXML
    private TableView<SaveResto> DataResto;
    @FXML
    private Button DeleteBtn;
    @FXML
    private TableColumn<SaveResto,Integer> idC;
    @FXML
    private Button addbtn;

    
    
       ObservableList<SaveResto> listR = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        SaveRestoService SM=new SaveRestoService();
        try {
            listR.addAll(SM.readAll());
            System.out.println(listR.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        idC.setCellValueFactory(new PropertyValueFactory<SaveResto, Integer>("id"));
        begindate.setCellValueFactory(new PropertyValueFactory<SaveResto, String>("date_begin"));
        enddate.setCellValueFactory(new PropertyValueFactory<SaveResto, String>("date_end"));

        DataResto.setItems(listR);
   
    }    


    @FXML
    private void deleteitem(MouseEvent event) throws SQLException {
        SaveRestoService SM = new SaveRestoService();
        SM.delete(idC.getCellData(DataResto.getSelectionModel().getSelectedIndex()));
        JOptionPane.showMessageDialog(null, "SUB Deleted");
        DataResto.getItems().clear();
        DataResto.getItems().addAll(listR);
        refreshaffich();
    }

    @FXML
    private void addsubresto(MouseEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddResto.fxml"));
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
    public void refreshaffich()
    {
     SaveRestoService SM=new SaveRestoService();
        try {
           listR.addAll(SM.readAll());
            System.out.println(listR.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        idC.setCellValueFactory(new PropertyValueFactory<SaveResto, Integer>("id"));
        begindate.setCellValueFactory(new PropertyValueFactory<SaveResto, String>("date_begin"));
        enddate.setCellValueFactory(new PropertyValueFactory<SaveResto, String>("date_end"));
     
     

        DataResto.setItems(listR);
        
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
