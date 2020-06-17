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
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class AddNurseryController implements Initializable {

    @FXML
    private TextField idF;
    @FXML
    private TextField startF;
    @FXML
    private TextField endF;
    @FXML
    private Button submitbtn;
    @FXML
    private Label idlabel1;

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
    private void ajouterSubNurs(MouseEvent event) throws SQLException {

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
            SaveNurseService cs = new SaveNurseService();
            SaveNurse a = new  SaveNurse(id,date_begin,date_end);
            cs.ajouter(a);
            System.out.println("YAY");

        }
    
     }

    @FXML
    private void returnto(MouseEvent event) throws IOException {
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

    @FXML
    private void displayiderror(KeyEvent event) {
        String PATTERN="^[0-9]{0,12}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(idF.getText());
        if(!match.matches())
        {
            idlabel1.setText("ID  is incorrect , Write a number");
        }
        else
        {
            idlabel1.setText(null);
        }
    }
    
}
