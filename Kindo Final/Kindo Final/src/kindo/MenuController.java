/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.UserService;
import Utils.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MenuController implements Initializable {

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
    private void showuserprofile(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Home.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                HomeController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpassword(password_label.getText());
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
    public void setpass(String pass)
    {
        this.password_label.setText(pass);
        this.password_label.setVisible(false);
    }

    @FXML
    private void disconnect(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CreateandLogin.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                CreateandLoginController ctc=loader.getController();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gototeacherspace(ActionEvent event) throws IOException, SQLException {
        UserService us=new UserService();
        if(us.getUserType(Integer.parseInt(id_label.getText())).equals("Parent"))
        {
     
            Alert e=new Alert(Alert.AlertType.WARNING);
			e.setTitle("Error!");
			e.setHeaderText(null);
			e.setContentText("Sorry you are not a Teacher");
			Optional <ButtonType> action = e.showAndWait();	
        }
        else
        {
                 FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuTT.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                MenuTTController ctc=loader.getController();
                ctc.setid(Integer.parseInt(id_label.getText()));
                ctc.setpassword(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
        }
    }

    @FXML
    private void gotoevantsspace(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("displayEventF.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                DisplayEventFController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpass(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void gotorestauration(ActionEvent event) throws IOException, SQLException {
        UserService us=new UserService();
        if(us.getUserType(Integer.parseInt(id_label.getText())).equals("Teacher"))
        {
            alert a =new alert();
            a.showalertwarning("sorry you are not a parent");
        }
        else
        {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("WelcomeParentInterf.fxml"));
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
    @FXML
    private void gotoparentspace(ActionEvent event) throws IOException, SQLException {
    UserService us=new UserService();
        if(us.getUserType(Integer.parseInt(id_label.getText())).equals("Teacher"))
        {
     
            Alert e=new Alert(Alert.AlertType.WARNING);
			e.setTitle("Error!");
			e.setHeaderText(null);
			e.setContentText("Sorry you are not a Parent");
			Optional <ButtonType> action = e.showAndWait();	
        }
        else
        {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAhmed.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                // Scene homescene=new Scene(root);
                MenuAhmedController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpass(password_label.getText());
               Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    }
    
}
