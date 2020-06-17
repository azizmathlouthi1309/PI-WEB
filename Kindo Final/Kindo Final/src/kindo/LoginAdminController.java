/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

//import Entities.Parent;
import Entities.User;
import Services.UserService;
import Utils.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class LoginAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private PasswordField login_password;
    @FXML
    private TextField login_username;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        UserService us=new UserService();
        List<User> list=us.getUsers();
        
        List<User>admins=list.stream().filter(e->e.getAccount_type().equals("Admin")).collect(Collectors.toList());
        for(User u:admins)
        {
        if(login_username.getText().equals(u.getUsername())&&(BCrypt.checkpw(login_password.getText(),u.getPassword())))
        {
            alert a =new alert();
            a.showalertinfo("welcome admin");
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAdmin.fxml"));
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
            a.showalertwarning("not an admin");
        }
        
        }
        }
    
}
