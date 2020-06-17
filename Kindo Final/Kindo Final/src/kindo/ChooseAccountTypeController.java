/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Utils.alert;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ChooseAccountTypeController implements Initializable {

    @FXML
    private CheckBox parent;
    @FXML
    private Label id;
    @FXML
    private CheckBox teacher;
    @FXML
    private TextField subject;
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
    private void validate(ActionEvent event) throws SQLException, IOException {
        UserService us=new UserService();
        if(parent.isSelected() && teacher.isSelected())
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("Error");
                                alert.setHeaderText("Wrong choice");
                                    alert.setContentText("you need to choose one and one type only");
                                        alert.showAndWait();
        }
        else if(teacher.isSelected())
        {
            
            us.ChooseType_affect(Integer.parseInt(id.getText()),"Teacher",subject.getText(),0);
            alert a=new alert();
            a.showalertinfo("You successfully updated your profile type");
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
            
        else if(parent.isSelected())
        {
            us.ChooseType_affect(Integer.parseInt(id.getText()),"Parent",subject.getText(),0);
            alert a=new alert();
            a.showalertinfo("You successfully updated your profile type");
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Menu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                HomeController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id.getText()));
               ctc.setpassword(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
          
        }
    }
    public void setid(int id)
    {
        this.id.setText(""+id);
        this.id.setVisible(false);
    }
    public void setpass(String pass)
    {
        this.password_label.setText(pass);
        this.password_label.setVisible(false);
    }
    
}
