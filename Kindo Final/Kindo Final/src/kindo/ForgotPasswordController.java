/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.User;
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
public class ForgotPasswordController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField code;
    @FXML
    private Button send;
    @FXML
    private Label code_label;
    @FXML
    private Label idhidden;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        code_label.setVisible(false);
        idhidden.setVisible(false);
    }    

    @FXML
    private void send(ActionEvent event) throws SQLException {
        UserService us=new UserService();
       int act_code=us.Random6Digits();
        User u=us.getbymail(email.getText());
        if(u!=null)
        {
        us.SendactivationByMail(email.getText(),act_code);
        alert a=new alert();
        a.showalertinfo("We sent a renew code to you mail please check");
        email.setText("");
        code_label.setText(""+act_code);
        code_label.setVisible(false);
        idhidden.setText(""+u.getId());
        idhidden.setVisible(false);
        }
        else
        {
            alert a =new alert();
            a.showalertwarning("this email is not in our database");
        }
        //System.out.println(us.getbymail(email.getText()));
    }

    @FXML
    private void verify(ActionEvent event) throws IOException {
        if(code_label.getText().equals(code.getText()))
        {
            
            alert a =new alert();
            a.showalertinfo("now you have the right to change your password");
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("NewPassword.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                NewPasswordController npc = loader.getController();
                npc.setid(Integer.parseInt(this.idhidden.getText()));
                //Parent home=FXMLLoader.load(getClass().getResource("BackHome.fxml"));
                    Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
        }
        else
        {
            alert a =new alert();
            a.showalertwarning("Please verify again your code");
        }
    }
    
}
