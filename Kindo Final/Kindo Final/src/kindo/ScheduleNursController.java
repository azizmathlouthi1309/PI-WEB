/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import Utils.Pdf;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class ScheduleNursController implements Initializable {

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
    private void downloadpdf(ActionEvent event) throws IOException {
        Pdf p =new Pdf();
        p.runpdf("policy");
    }

  

    @FXML
    private void returnB(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGarderie.fxml"));
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
