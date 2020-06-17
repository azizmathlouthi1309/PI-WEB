/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Activity;
import Services.ActivityService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class ListeactivitiesController implements Initializable {

    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
    @FXML
    private TableView<Activity> tabact;
    @FXML
    private TableColumn<Activity, String> activitynametab;
    @FXML
    private TableColumn<Activity, String>  descriptiontab;
    @FXML
    private TextField tfsearch;
 ActivityService as=new ActivityService();
    ArrayList<Activity> liste=new ArrayList<>();
    Activity temp=new Activity();
 //   private Date lastClickTime;
    /**
     * Initializes the controller class.
     */
    @Override
   public void initialize(URL url, ResourceBundle rb) {
       
        try {
            // TODO
            tab();
        } catch (SQLException ex) {
            Logger.getLogger(ListeactivitiesController.class.getName()).log(Level.SEVERE, null, ex);
        }
            tfsearch.textProperty().addListener(new ChangeListener<String>() {
      

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            if(tfsearch.getText()!="")
		    	{
                       try {
                           Activity c = new Activity();
                           ActivityService cs= new ActivityService();
                           liste.clear();
                           for (Activity a : as.search(newValue))
                               liste.add(new Activity(a.getName(),a.getDescription()));
                           
                           ObservableList<Activity> data = FXCollections.observableArrayList(liste);
                           
                               activitynametab.setCellValueFactory(new PropertyValueFactory<>("name")); 
                               descriptiontab.setCellValueFactory(new PropertyValueFactory<>("description")); 
                               tabact.setItems(data);
   
                           ;                  } catch (SQLException ex) {
                           System.out.println(ex);
                       }
		    	}
		
                  }
    	});
          
        
    }  
          
    

public void setid(int id)
    {
        this.id_label.setText(""+id);
        this.id_label.setVisible(false);
    }
 public void password(String msg)
 {
     this.password_label.setText(msg);
        this.password_label.setVisible(false);
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
    private void tab() throws SQLException  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
      
    ObservableList<Activity> data = FXCollections.observableArrayList(liste);
    data.addAll(as.readAll());
    System.out.println(data.size());
   //     System.out.println(Integer.parseInt(id_label.getText()));
   // for(Classe row : liste){
	    		//System.out.println(row.toString());
   // }
   
    activitynametab.setCellValueFactory(new PropertyValueFactory<Activity, String>("name")); 
    descriptiontab.setCellValueFactory(new PropertyValueFactory<Activity, String>("description")); 
    tabact.setItems(data);
   
    
    
}

}
