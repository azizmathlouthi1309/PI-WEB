/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;


import Entities.*;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AddUsersController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private TextField subject;
    @FXML
    private TextField cin;
    @FXML
    private Label label_subject;
    @FXML
    private Label label_cin;
    @FXML
    private CheckBox teacher;
    @FXML
    private CheckBox admin;
    @FXML
    private CheckBox parent;
    @FXML
    private Button show;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void add(ActionEvent event) throws SQLException {
        UserService us=new UserService();
        if(teacher.isSelected())
                {
        User u =new User(Integer.parseInt(id.getText()),username.getText(),"username_canonical",email.getText(),"email_canonical",1,"salt",password.getText(),"confirmation_token","roles","account_type");
        us.addUser(u);
        us.ChooseType_affect(Integer.parseInt(id.getText()),"Teacher",subject.getText(),0);
        alert a=new alert();
                   a.showalertinfo("User added");
                }
        else if(parent.isSelected())
        {
            User u =new User(Integer.parseInt(id.getText()),username.getText(),"username_canonical",email.getText(),"email_canonical",1,"salt",password.getText(),"confirmation_token","roles","account_type");
        us.addUser(u);
        us.ChooseType_affect(Integer.parseInt(id.getText()),"Parent",subject.getText(),0);
        alert a=new alert();
                   a.showalertinfo("User added");
        }
        else if(admin.isSelected())
        {
            User u =new User(Integer.parseInt(id.getText()),username.getText(),"username_canonical",email.getText(),"email_canonical",1,"salt",password.getText(),"confirmation_token","roles","account_type");
        us.addUser(u);
        int cin=Integer.parseInt(this.cin.getText());
        us.ChooseType_affect(Integer.parseInt(id.getText()),"Admin",subject.getText(),cin);
        alert a=new alert();
                   a.showalertinfo("User added");
        }
        else
        {
            alert a=new alert();
                   a.showalertwarning("you can choose 1 type only");
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
            String new_username=username.getText();
        String new_email=email.getText();
        String new_password=password.getText();
        UserService us=new UserService();
        us.UpdateAll(Integer.parseInt(id.getText()),new_username,new_password,new_email);
        alert a =new alert();
        a.showalertinfo("User updated");
    }



    @FXML
    private void show(ActionEvent event) throws SQLException {
           // private void show(KeyEvent event) throws SQLException {
        UserService us=new UserService();
        User u =us.getById(Integer.parseInt(id.getText()));
        username.setText(u.getUsername());
        email.setText(u.getEmail());
        cin.setText("");
        subject.setText("");
    
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("BackHome.fxml"));
                                         Stage primaryStage=new Stage();
                javafx.scene.Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                   //alert a=new alert();
                   //a.showalertinfo("you are logged out");
                window.show();
    }

    
}
