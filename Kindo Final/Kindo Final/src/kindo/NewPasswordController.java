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
public class NewPasswordController implements Initializable {

    @FXML
    private TextField pass;
    @FXML
    private TextField confirm_pass;
    @FXML
    private Button submit;
    @FXML
    private Label id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void submit(ActionEvent event) throws SQLException, IOException {
        
        if(pass.getText().equals(confirm_pass.getText()))
        {
            UserService us=new UserService();
            User u =us.getById(Integer.parseInt(this.id.getText()));
            us.UpdateAll(u.getId(),u.getUsername(),pass.getText(),u.getEmail());
            alert a =new alert();
            a.showalertinfo("you successfully updated your password");
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("CreateandLogin.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
        }
        else
        {
            alert a =new alert();
            a.showalertwarning("not matching");
        }
    }
    public  void setid(int id)
    {
        this.id.setText(""+id);
        this.id.setVisible(false);
    }
    
}
