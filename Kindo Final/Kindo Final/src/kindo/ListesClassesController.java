/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Classe;
import Entities.Teacher;
import Services.ClassService;
import Services.TeacherService;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author maiss
 */
public class ListesClassesController implements Initializable {

    
    ArrayList<Classe> liste=new ArrayList<>();
    
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
    @FXML
    private TextField tfcapacity;
    @FXML
    private TextField tflevel;
    @FXML
    private TextField tfclass;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfsearch;
    private Date lastClickTime;
    
    @FXML
    private TableColumn <Classe, String> clteacher;
    @FXML
    private ComboBox<String> teacherbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            //tfid.setDisable(true);
            tab();
            
            
		tfsearch.textProperty().addListener(new ChangeListener<String>() {
//Rechercher
		

                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                   if(tfsearch.getText()!="")
		    	{
                       try {
                         //  Classe c = new Classe();
                           ClassService cs= new ClassService();
                           liste.clear();
                           for (Classe cl : cs.search(newValue))
                               liste.add(new Classe(cl.getId(),cl.getNom(),cl.getNb_child(),cl.getLevel(),cl.getTeacher_name()));
                           
                           ObservableList<Classe> data = FXCollections.observableArrayList(liste);
                           
                           clclass.setCellValueFactory(new PropertyValueFactory("nom"));
                           clcap.setCellValueFactory(new PropertyValueFactory("nb_child"));
                           cllevel.setCellValueFactory(new PropertyValueFactory("level"));
                           clteacher.setCellValueFactory(new PropertyValueFactory("teacher_name")); 
                           tabclass.setItems(data);
                                             } catch (SQLException ex) {
                           System.out.println(ex);
                       }
		    	}
				
                }
    	});
                TeacherService sr = new TeacherService();
                        List<Teacher> listaa=sr.getTeachers();
                       // ObservableList<Teacher> clss =FXCollections.observableArrayList();
                           for(Teacher aux : listaa){
                                      teacherbox.getItems().addAll(aux.getUsername());
                             }
        } catch (SQLException ex) {
            Logger.getLogger(ListesClassesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Add(ActionEvent event) {
             
            ClassService cs = new ClassService();
           
             try {
       
              if(tfclass.getText().isEmpty()||tfcapacity.getText().isEmpty()||tflevel.getText().isEmpty()) 
                    {     
			Alert e=new Alert(AlertType.WARNING);
			e.setTitle("Error!");
			e.setHeaderText(null);
			e.setContentText("All field should be filled !");
			Optional <ButtonType> action = e.showAndWait();	
                     }
              
                 
               else      if (!tfcapacity.getText().matches("[1-25]*")|| !tflevel.getText().matches("[1-5]*")||!tfclass.getText().matches("[a-z]*")) 
                     {   Reset();
                    	Alert e=new Alert(AlertType.WARNING);
			e.setTitle("Error!");
			e.setHeaderText(null);
			e.setContentText("Incorrect Type , Please repeat !");
			Optional <ButtonType> action = e.showAndWait();
                     }
              else   {
                
             //tfid.setVisible(false);
           String nomC = tfclass.getText();
            String capacite = tfcapacity.getText();
            String level= tflevel.getText();
          
             int cap = Integer.parseInt(capacite);
            int lev = Integer.parseInt(level);
    
            String nomp = teacherbox.getValue().toString();
            Classe c = new Classe(nomC, cap, lev,nomp);
            cs.add(c);
            liste.clear();
            ser.readAll().stream().forEach(e->System.out.println(e));
               
                     
                 
                             tab();

                 Reset();
              }  
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     
    }
        
     private void tab() throws SQLException  {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
          
             ObservableList<Classe> data = FXCollections.observableArrayList(liste);
             data.addAll(ser.readAll());
            
   // for(Classe row : liste){
	    		//System.out.println(row.toString());
   // }
   
             clclass.setCellValueFactory(new PropertyValueFactory("nom"));      
             clcap.setCellValueFactory(new PropertyValueFactory("nb_child"));
             cllevel.setCellValueFactory(new PropertyValueFactory("level"));       
             clteacher.setCellValueFactory(new PropertyValueFactory("teacher_name")); 
             tabclass.setItems(data);
   
    
   }

  
    @FXML
    private void Reset() {
               // tfid.setText("");
                tfclass.setText("");
	   	tfcapacity.setText("");
	   	tflevel.setText("");
	        btnAdd.setDisable(false);
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Alert e=new Alert(AlertType.CONFIRMATION);
			e.setTitle("Delete Confirmation ");
			e.setHeaderText(null);
			e.setContentText("Do You really want to delete class ?");
			Optional <ButtonType> action = e.showAndWait();
			if(action.get()==ButtonType.OK) {
				
				ClassService cs = new ClassService();
                               // String idf=tfid.getText();
                                ObservableList<Classe> del;
                               del = tabclass.getSelectionModel().getSelectedItems();
                                   Classe p = del.get(0);
       				cs.delete(p.getId());
				liste.clear();
				tab();
				Reset();
        
    }
    }

    @FXML
    private void Update(ActionEvent event) throws SQLException {
        Alert e=new Alert(AlertType.CONFIRMATION);
		e.setTitle("Update Confirmation ");
		e.setHeaderText(null);
		e.setContentText("Do You want Update  ?");
		Optional <ButtonType> action = e.showAndWait();
		if(action.get()==ButtonType.OK) {
	           //  String idf=tfid.getText();
                     String nomC = tfclass.getText();
                     String capacite = tfcapacity.getText();
                     String level= tflevel.getText();
                     ClassService cs = new ClassService();
                   //  int id=Integer.parseInt(idf);
                     int cap = Integer.parseInt(capacite);
                     int lev = Integer.parseInt(level);
                     String teach = teacherbox.getValue();
                           ObservableList<Classe> ahawa;
                                ahawa = tabclass.getSelectionModel().getSelectedItems();
                                Classe c = ahawa.get(0);
                               // System.out.println(ahawa.get(-1));
                                //System.out.println(c);
                               // System.out.println(ser.getidbyname(nomC));
       				System.out.println(ser.update(c.getId(),nomC,cap,lev,teach));
				liste.clear();
				tab();
				Reset();
                              
			
		}
    }

    @FXML
    private void Search(ActionEvent event) throws SQLException {
        if(tfsearch.getText()!="")
	    	{
		        Classe c= new Classe();
				ClassService cs = new ClassService();
          
				liste.clear();
				for (Classe cl : ser.readAll())
	   	                liste.add(new Classe(cl.getId(),cl.getNom(),cl.getNb_child(),cl.getLevel(),cl.getTeacher_name()));
				
		    	//System.out.println("test ok");
		    /*	for(classe row : liste){
		    		System.out.println(row.getCode()+" "+row.getNom()+" "+row.getVille());
		    	}*/
		    	 ObservableList<Classe> data = FXCollections.observableArrayList(liste);
		    	 clclass.setCellValueFactory(new PropertyValueFactory("nom"));      
                         clcap.setCellValueFactory(new PropertyValueFactory("nb_child"));
                         cllevel.setCellValueFactory(new PropertyValueFactory("level"));
                          clteacher.setCellValueFactory(new PropertyValueFactory("teacher_name"));
                          tabclass.setItems(data);
    
        
    }

    } 
    //Double click
   Classe temp= new Classe();
    @FXML
    private void handleRowSelect(MouseEvent event) {
         Classe row = tabclass.getSelectionModel().getSelectedItem();
	      
	       if (row == null) return;
	       if(row != temp){
	           temp = row;
             lastClickTime = new Date();
	       } else if(row == temp) {
	           Date now = new Date();
	           long diff = now.getTime() - lastClickTime.getTime();
	           if (diff < 300){ //another click registered in 300 millis
	                System.out.println(row.getNom());
                        //tfid.setText(String.valueOf(row.getId()));
	                tfclass.setText(row.getNom());
                        
	                tfcapacity.setText(String.valueOf(row.getNb_child()));
	                tflevel.setText(String.valueOf(row.getLevel()));
	                btnAdd.setDisable(true);
	                btnUpdate.setDisable(false);
	                btnDelete.setDisable(false); 
	                
	                
	           } else {
	               lastClickTime = new Date();
	           }
	       }
        
    }
}
    
    

