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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class BackOffMenuController implements Initializable {

    @FXML
    private Button MenuMana;
    @FXML
    private Button RestoMana;
    @FXML
    private Button NursMana;
    @FXML
    private Button QuitBtn;

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
    private void displayMenuMana(MouseEvent event) throws IOException{
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
    private void displayRestoMana(MouseEvent event) throws IOException {
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
    private void displayNursMana(MouseEvent event) throws IOException {
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
    private void backto(MouseEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAdmin.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }

    @FXML
    private void Backtotoo(ActionEvent event) {
    }
    
}
