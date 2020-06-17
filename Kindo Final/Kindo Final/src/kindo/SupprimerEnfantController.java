/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.ServiceEnfant;
import Utilsahmed.alert;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class SupprimerEnfantController implements Initializable {

    @FXML
    private TextField idEnfant;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private Button DeleteChild;
    @FXML
    private ImageView tajrba;
    @FXML
    private Button menu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
    

    }    

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        ServiceEnfant se = new ServiceEnfant();
        se.supprimerEnfant(Integer.parseInt(idEnfant.getText()));
        alert a=new alert();
       a.showalertinformation("Child deleted");
    }

    @FXML
    private void afficher(MouseEvent event) throws SQLException {
        ServiceEnfant se = new ServiceEnfant();
        FirstName.setText(se.getenfant(Integer.parseInt(idEnfant.getText())).get(0).getPrenom());
        LastName.setText(se.getenfant(Integer.parseInt(idEnfant.getText())).get(0).getNom());
           File file;
        try {
            int k = Integer.parseInt(idEnfant.getText());
           file = new File(se.getById(k).getPhoto());
        Image image = new Image(file.toURI().toString());
       // if (image.isError()) {
       
        tajrba.setImage(image);
        }  catch (SQLException ex) {
            Logger.getLogger(SupprimerEnfantController.class.getName()).log(Level.SEVERE, null, ex);
        }
   
   
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuBack.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                MenuBackController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }
    
   
    
}
