/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Activity;
import Entities.Classe;
import Services.ActivityService;
import Services.ClassService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import manage.Mail;

/**
 * FXML Controller class
 *
 * @author maiss
 */
public class ActivitiesController implements Initializable {
    private ActivityService ser=new ActivityService();

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView <Activity> tabact;
    @FXML
    private TextField tfsearch;
    ActivityService as=new ActivityService();
    ArrayList<Activity> liste=new ArrayList<>();
    Activity temp=new Activity();
    private Date lastClickTime;
    @FXML
    private TextField tfnom;
    @FXML
    private TableColumn<Activity, String> activitynametab;
    @FXML
    private Button res;
    @FXML
    private TextField tfdescription;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<Activity, String> descriptiontab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            tab();
            tfsearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
          catch (SQLException ex) {
            Logger.getLogger(ActivitiesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Add(ActionEvent event) {
        
     
             try {
                 if(tfnom.getText().isEmpty()||tfdescription.getText().isEmpty()) 
                    {
			Alert e=new Alert(Alert.AlertType.WARNING);
			e.setTitle("Error, You can't Add");
			e.setHeaderText(null);
			e.setContentText("Please complete all fields !");
			Optional <ButtonType> action = e.showAndWait();	
                     }
           
                       else{
            String nom = tfnom.getText();
            String des = tfdescription.getText();
                     System.out.println(des);
            ClassService cs = new ClassService();
            Activity a = new  Activity(nom,des);
            as.add(a);
            liste.clear();
            as.readAll().stream().forEach(e->System.out.println(e));
            tab();
              
                 Reset();
        }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Alert e=new Alert(Alert.AlertType.CONFIRMATION);
			e.setTitle("Delete Confirmation ");
			e.setHeaderText(null);
			e.setContentText("Do You really want to delete class ?");
			Optional <ButtonType> action = e.showAndWait();
			if(action.get()==ButtonType.OK) {
				
				ActivityService cs = new ActivityService();
                                String id=tfnom.getText();
				cs.delete(id);
				liste.clear();
				tab();
				Reset();
        
    }
    }
    

    @FXML
    private void Reset() {
                        tfnom.setText("");
                        tfdescription.setText("");
	        btnAdd.setDisable(false);
    }
//double Click
    @FXML
    private void handleRowSelect(MouseEvent event) {
         Activity row = tabact.getSelectionModel().getSelectedItem();
	      
	       if (row == null) return;
	       if(row != temp)
               {
	           temp = row;
             lastClickTime = new Date();
	       } else if(row == temp) {
	           Date now = new Date();
	           long diff = now.getTime() - lastClickTime.getTime();
	           if (diff < 300){ //another click registered in 300 millis
	              //  System.out.println(row.getNom());
	                tfnom.setText(row.getName());
                        tfdescription.setText(row.getDescription());
	                btnAdd.setDisable(true);
	                btnDelete.setDisable(false);  
	           } else {
	               lastClickTime = new Date();
	           }
	       }
        
    
    }

    @FXML
    private void Search(ActionEvent event) {
        
    }
     private void tab() throws SQLException  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
      
    ObservableList<Activity> data = FXCollections.observableArrayList(liste);
    data.addAll(as.readAll());
    System.out.println(data.size());
   // for(Classe row : liste){
	    		//System.out.println(row.toString());
   // }
   
    activitynametab.setCellValueFactory(new PropertyValueFactory<Activity, String>("name")); 
    descriptiontab.setCellValueFactory(new PropertyValueFactory<Activity, String>("description")); 
    tabact.setItems(data);
   
    
    
}

    @FXML
    private void Update(ActionEvent event) throws SQLException {
                Alert e=new Alert(Alert.AlertType.CONFIRMATION);
		e.setTitle("Update Confirmation ");
		e.setHeaderText(null);
		e.setContentText("Do You want Update  ?");
		Optional <ButtonType> action = e.showAndWait();
		if(action.get()==ButtonType.OK) {
	             String idf=tfnom.getText();
                     String nomC = tfdescription.getText();
                     ActivityService cs = new ActivityService();

                          System.out.println(ser.update(idf,nomC));
                        
			
			liste.clear();
                        tab();
			Reset();
			
		}
    
    }
    
}