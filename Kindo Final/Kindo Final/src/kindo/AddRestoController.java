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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class AddRestoController implements Initializable {

    @FXML
    private TextField idF;
    @FXML
    private TextField startF;
    @FXML
    private TextField endF;
    @FXML
    private Button sumbitbtn;
    @FXML
    private Label label_password;
    @FXML
    private Label label_id;

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
    private void addresto(MouseEvent event) throws SQLException 
    {
          if(idF.getText().isEmpty()||startF.getText().isEmpty()||endF.getText().isEmpty())                   
 {
            Alert e=new Alert(Alert.AlertType.WARNING);
            e.setTitle("Error, You can't Add");
            e.setHeaderText(null);
            e.setContentText("Please complete all fields !");
            Optional <ButtonType> action = e.showAndWait();
        }
        else{
            String id1 = idF.getText();
            String date_begin = startF.getText();
            String date_end = endF.getText();
     
            int id = Integer.parseInt(id1);
            SaveRestoService cs = new SaveRestoService();
            SaveResto a;
              a = new  SaveResto(id,date_begin,date_end);
            cs.ajouter(a);
            System.out.println("YAY");

        }  
    }

    @FXML
    private void returnto(MouseEvent event) throws IOException {
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
