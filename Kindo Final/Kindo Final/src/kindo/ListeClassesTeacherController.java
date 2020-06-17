/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Classe;
import Entities.User;
import Services.ClassService;
import Services.TeacherService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ListeClassesTeacherController implements Initializable {

    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
    @FXML
    private TableColumn <Classe, String> clclass;
    @FXML
    private TableColumn<Classe, Integer> clcap;
    @FXML
    private TableColumn <Classe, Integer>cllevel;
    @FXML
    private TableView<Classe> tabclass;
    private ClassService ser=new ClassService();
    private Classe cl = new Classe();
        ArrayList<Classe> liste=new ArrayList<>();
    
    @FXML
    private TextField tfsearch;
   
    /**
     * Initializes the controller class.
     */
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //tfid.setDisable(true);
        //tab();
        
        
        tfsearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                UserService us = new UserService();
   
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
             ObservableList<Classe> data = FXCollections.observableArrayList(liste);
          
   
             clclass.setCellValueFactory(new PropertyValueFactory("nom"));      
             clcap.setCellValueFactory(new PropertyValueFactory("nb_child"));
             cllevel.setCellValueFactory(new PropertyValueFactory("level"));       
        
             tabclass.setItems(data);
   
            }
        }); 
    }    

    @FXML
    private void returnback(ActionEvent event) throws IOException {
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

    @FXML
    private void Search(ActionEvent event) {
        
    }
    


    
    public void setid(int id)
    {
        this.id_label.setText(""+id);
        this.id_label.setVisible(false);
    }
        public void setpassword(String msg)
    {
        this.password_label.setText(msg);
        this.password_label.setVisible(false);
    }

    @FXML
    private void showvalues(ActionEvent event) throws SQLException {
        UserService us = new UserService();
   
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
             ObservableList<Classe> data = FXCollections.observableArrayList(liste);
              User u= us.getById(Integer.parseInt(this.id_label.getText()));
              //User u=us.getById(11);
                   data.addAll(ser.readAllbyTeacher(u.getUsername()));
            //System.out.println(u);
            //System.out.println(id_label.getText());
   // for(Classe row : liste){
	    		//System.out.println(row.toString());
   // }
   
             clclass.setCellValueFactory(new PropertyValueFactory("nom"));      
             clcap.setCellValueFactory(new PropertyValueFactory("nb_child"));
             cllevel.setCellValueFactory(new PropertyValueFactory("level"));       
        
             tabclass.setItems(data);
   
    
   }
                
    }
    

