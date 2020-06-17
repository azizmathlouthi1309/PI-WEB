/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.UserService;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ValidateAccountController implements Initializable {

    @FXML
    private TextField code_act;
    @FXML
    private Button validate;
    @FXML
    private Label code;
    @FXML
    private Label id;
    @FXML
    private AnchorPane pane;
    @FXML
    private Label password_label;
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
    private void CodeValidate(ActionEvent event) throws SQLException, IOException {
       if(code_act.getText().equals(code.getText()))
       {
           UserService us=new UserService();
           if(us.activateaccount(Integer.parseInt(id.getText())))
           {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("Success");
                                alert.setHeaderText("Your account has been activated");
                                    alert.setContentText("One More Step now choose your account type ");
                                        alert.showAndWait();
                                        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("ChooseAccountType.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                ChooseAccountTypeController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id.getText()));
               ctc.setpass(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                                        
           }
           
       }
       else{
               Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("Error");
                                alert.setHeaderText("Wrong code");
                                    alert.setContentText("please verify your mail");
                                        alert.showAndWait();
           }
    }
    public void setid(int id) {
        this.id.setText(""+id);
        this.id.setVisible(false);
    }
    
    public void setcode(int act_code) {
        this.code.setText(""+act_code);
        this.code.setVisible(false);
    }
    public void setpass(String pass)
    {
        this.password_label.setText(pass);
        this.password_label.setVisible(false);
    }
}
