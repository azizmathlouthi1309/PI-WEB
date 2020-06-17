/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.User;
import Services.UserService;
import Utils.ConnectedUser;
import Utils.alert;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class CreateandLoginController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private Button create;
    @FXML
    private PasswordField login_password;
    @FXML
    private TextField login_username;
    @FXML
    private TextField username_creation;
    @FXML
    private TextField email_creation;
    @FXML
    private PasswordField password_creation;
    @FXML
    private Button forgot;
    @FXML
    private TextField confirm_pass;

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
    private void createaccount(ActionEvent event) throws IOException, SQLException {
        UserService us=new UserService();
        if(username_creation.getText().isEmpty() || email_creation.getText().isEmpty() || password_creation.getText().isEmpty())
        {
            alert a =new alert();
            a.showalertwarning("All field should be filled !");
        }
        else{
            if(!confirm_pass.getText().equals(password_creation.getText()))
            {
                alert a =new alert();
            a.showalertwarning("password mismatch");
            }
            else{
        User u =new User(0,username_creation.getText(),"username_canonical",email_creation.getText(),"email_canonical",0,"salt",password_creation.getText(),"confirmation_token","roles","account_type");
        
        
            if(us.addUser(u)==1)
            {
                                alert a =new alert();
                                a.showalertinfo("Account Created With success please check email for activation code");
                                        int act_code=us.Random6Digits();
                                        us.SendactivationByMail(u.getEmail(),act_code);
                                         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ValidateAccount.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                ValidateAccountController vac = loader.getController();
                User u1=us.getByEmail(email_creation.getText());
                //System.out.println(u1);
                vac.setid(u1.getId());
                vac.setcode(act_code);
                vac.setpass(password_creation.getText());
                //Parent home=FXMLLoader.load(getClass().getResource("BackHome.fxml"));
                    Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
            }}
        }
    }
    @FXML
    private void login(ActionEvent event) throws IOException {
        UserService us=new UserService();
        String Username=login_username.getText();
        String Password=login_password.getText();
        try {
            if(us.Login(Username, Password))
            {
                User userrrrrr=us.getbyusernameandpassword(Username, Password);
                alert a=new alert();
            a.showalertinfo("Welcome");
                FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Menu.fxml"));
                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                MenuController hc = loader.getController();
                hc.setid(us.GetUserId(Username, Password));
                hc.setpass(Password);
            }
                    } catch (SQLException ex) {
            Logger.getLogger(CreateandLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void forgot(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ForgotPassword.fxml"));
                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
    
}
