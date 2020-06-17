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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MenuTTController implements Initializable {

    @FXML
    private Label id_label;
    @FXML
    private Label password_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void gotoactivities(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Listeactivities.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                ListeactivitiesController ctc=loader.getController();
                ctc.setid(Integer.parseInt(id_label.getText()));
                ctc.password(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotoposts(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Publication.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                PublicationController ctc=loader.getController();
                ctc.setid(Integer.parseInt(id_label.getText()));
                ctc.password(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotomyclasses(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ListeClassesTeacher.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                ListeClassesTeacherController ctc=loader.getController();
                ctc.setid(Integer.parseInt(id_label.getText()));
                ctc.setpassword(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotomenu(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Menu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                MenuController ctc=loader.getController();
                ctc.setid(Integer.parseInt(id_label.getText()));
                ctc.setpass(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    public void setid(int id)
    {
        this.id_label.setText(""+id);
        this.id_label.setVisible(false);
    }
        public void setpassword(String msg)
    {
        this.password_label.setText(msg);
        this.password_label.setVisible(false);
    }
}
