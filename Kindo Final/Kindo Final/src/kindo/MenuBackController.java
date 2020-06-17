/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class MenuBackController implements Initializable {

    @FXML
    private Button enfant;
    @FXML
    private Button rec;
    @FXML
    private Button avis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AfficherEnfant(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherEnfant.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
               
                 Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                AfficherEnfantController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }

    @FXML
    private void AfficherRec(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherReclamation.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
               
                 Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                //AfficherReclamationController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
//                Scene scene=new Scene(root);
//                primaryStage.setScene(scene);
//                primaryStage.show();
    }

    @FXML
    private void afficherAvis(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherAvis.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
               
               Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                //AfficherAvisController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
//                Scene scene=new Scene(root);
//                primaryStage.setScene(scene);
//                primaryStage.show();
    }

    @FXML
    private void gobackback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAdmin.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
               
                 Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                //AfficherAvisController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
//                Scene scene=new Scene(root);
//                primaryStage.setScene(scene);
//                primaryStage.show();
    }
    
}
