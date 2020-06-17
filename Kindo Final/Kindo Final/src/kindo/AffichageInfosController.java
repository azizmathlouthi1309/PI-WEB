/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.util.Date;
import Services.SaveRestoService;
import Entities.SaveResto;
import java.io.IOException;
import java.net.URL;
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
public class AffichageInfosController implements Initializable {

    @FXML
    private TableView<SaveResto> TableSubsData;
    @FXML
    private TableColumn<SaveResto, Date> startC;
    @FXML
    private TableColumn<SaveResto, Date> endC;
       ObservableList<SaveResto> listRes = FXCollections.observableArrayList();
    @FXML
    private Button notbtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SaveRestoService SM=new SaveRestoService();
        try {
            listRes.addAll(SM.readAll());
            System.out.println(listRes.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        startC.setCellValueFactory(new PropertyValueFactory<SaveResto, Date>("date_begin"));
        endC.setCellValueFactory(new PropertyValueFactory<SaveResto, Date>("date_end"));
        
        TableSubsData.setItems(listRes);

      
      
    }  
    @FXML
    public void notifying()
    {
        
        String firstDate1 = "2020-02-28";
 
        if(TableSubsData.getItems().get(0).getDate_end().equals(firstDate1))
        {
           JOptionPane.showMessageDialog(null, "Your subscription date is expired!!! ");
        }   
        else
        {
                       JOptionPane.showMessageDialog(null, "You still have enough time to renew your subscription");

        }
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
