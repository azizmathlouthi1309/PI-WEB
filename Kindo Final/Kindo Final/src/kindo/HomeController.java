/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.User;
import Services.TeacherService;
import Services.UserService;
import Utils.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class HomeController implements Initializable {

    @FXML
    private Button desactivate;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private Label type;
    @FXML
    private TextField password;
    @FXML
    private Label id;
    @FXML
    private Button update;
    @FXML
    private Button show;
    @FXML
    private Button logout;
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
    private void desactivate(ActionEvent event) throws SQLException {
        UserService us=new UserService();
        us.desactivateall(Integer.parseInt(id.getText()));
        alert a =new alert();
        a.showalertwarning("Account desactivated");
    }
   public void setid(int id) {
        this.id.setText(""+id);
        this.id.setVisible(false);
    }
   public void setpassword(String password)
   {
       this.password_label.setText(password);
       this.password_label.setVisible(false);
   }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        String new_username=username.getText();
        String new_email=email.getText();
        String new_password=password.getText();
        UserService us=new UserService();
        if(new_username.isEmpty() || new_email.isEmpty() || new_password.isEmpty())
        {
            alert a =new alert();
            a.showalertwarning("please fill all the fields");
        }
        else{
        us.UpdateAll(Integer.parseInt(id.getText()),new_username,new_password,new_email);
        alert a =new alert();
        a.showalertinfo("Account updated");
    }}

    @FXML
    private void show(ActionEvent event) throws SQLException {
        UserService us=new UserService();
        User u=us.getById(Integer.parseInt(id.getText()));
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        type.setText(u.getAccount_type());
        password.setText(password_label.getText());
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Menu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                MenuController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id.getText()));
               ctc.setpass(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
}
