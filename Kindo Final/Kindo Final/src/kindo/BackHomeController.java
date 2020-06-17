/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.User;
import Services.UserService;
import Utils.Pdf;
import Utils.Pdftable;
import Utils.alert;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class BackHomeController implements Initializable {

    @FXML
    private Button delete;
    @FXML
    private Button desactivate;
    @FXML
    private Button add;
    @FXML
    private Button generatepdf;
    @FXML
    private TableView<User> tableview;
    @FXML
    private TableColumn<User,String> id;
    @FXML
    private TableColumn<User,String> username;
    @FXML
    private TableColumn<User,String> email;
    @FXML
    private TableColumn<User, String> password;
    @FXML
    private TableColumn<User,String> accounttype;
    @FXML
    private TextField identifiant;
    @FXML
    private TextField username_text;
    @FXML
    private Button back;
    @FXML
    private Button activate;
    @FXML
    private TableColumn<User,String> Status;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
       username.setCellValueFactory(new PropertyValueFactory<>("username"));
       email.setCellValueFactory(new PropertyValueFactory<>("email"));
       password.setCellValueFactory(new PropertyValueFactory<>("password"));
       accounttype.setCellValueFactory(new PropertyValueFactory<>("account_type"));
       Status.setCellValueFactory(new PropertyValueFactory<>("enabled"));
        try {
            tableview.setItems(getuserss());
        } catch (SQLException ex) {
            Logger.getLogger(BackHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    


    @FXML
    private void desactivate(ActionEvent event) throws SQLException {
        UserService us=new UserService();
        if(us.exist(Integer.parseInt(identifiant.getText())))
        {
            us.desactivateall(Integer.parseInt(identifiant.getText()));
            alert a=new alert();
            a.showalertinfo("account desactivated");
        }
        else
        {
            alert a=new alert();
                   a.showalertwarning("User doesnt exist");
        }
        }

    @FXML
    private void adduser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AddUsers.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void generatepdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
       // Pdf pdf=new Pdf();
        //pdf.GeneratePdf("liste users");
        Pdftable.main();
    }

    private ObservableList<User> getuserss() throws SQLException {
        ObservableList<User> users=FXCollections.observableArrayList();
        UserService us=new UserService();
        for(User u:us.getUsers())
        {
            users.add(u);
        }
        return users;
    }


    private void find(ActionEvent event) throws SQLException {
     UserService us=new UserService();
     ObservableList<User> users=FXCollections.observableArrayList();
     for(User u:us.findby(username_text.getText()))
             {
                 users.add(u);
             }
     tableview.setItems(users);
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
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

    @FXML
    private void stats(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Stats.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
        
    }

    @FXML
    private void activate(ActionEvent event) throws SQLException {
            UserService us=new UserService();
            us.activateall(Integer.parseInt(identifiant.getText()));
            alert a=new alert();
            a.showalertinfo("account activated");
    }

    @FXML
    private void findd(KeyEvent event) throws SQLException {
        UserService us=new UserService();
     ObservableList<User> users=FXCollections.observableArrayList();
     for(User u:us.findby(username_text.getText()))
             {
                 users.add(u);
             }
     tableview.setItems(users);
    }

    @FXML
    private void refresh(ActionEvent event) throws SQLException {
        tableview.setItems(getuserss());
    }
        
    
    
}
