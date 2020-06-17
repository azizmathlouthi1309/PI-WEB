/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Activity;
import Entities.Classe;
import Entities.Teacher;
import Entities.publication;
import Services.ActivityService;
import Services.ClassService;
import Services.PublicationService;
import Services.TeacherService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
 * @author maiss
 */
public class PublicationController implements Initializable {

        private PublicationService as=new PublicationService();
            ArrayList<publication> liste=new ArrayList<>();


    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button res;
    @FXML
    private TextField tfsubject;
    @FXML
    private TextField tfcontent;
    @FXML
    private Button btnUpdate;
    private ComboBox<String> teacherbox;
    @FXML
    private TableView<publication> tabact;
    @FXML
    private TableColumn<publication, String> descriptiontab;
    @FXML
    private TextField tfsearch;
    
    @FXML
    private TableColumn<publication, String> subjectab;
    private TableColumn<publication, String> teachertab;
        private Date lastClickTime;

        publication temp=new publication();

    ObservableList <String> list = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> classbox;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
    @FXML
    private TableColumn<publication, String> classtab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                  tab();
            tfsearch.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                  //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        if(tfsearch.getText()!="")
		    	{
                       try {
                           publication c = new publication();
                           PublicationService cs= new PublicationService();
                           liste.clear();
                           for (publication a : as.search(newValue))
                               liste.add(new publication(a.getId(),a.getSubject(),a.getContent(),a.getClass_name(),a.getTeacher_id()));
                           
                           ObservableList<publication> data = FXCollections.observableArrayList(liste);
                           
                                       // idtab.setCellValueFactory(new PropertyValueFactory<>("id")); 
                                        subjectab.setCellValueFactory(new PropertyValueFactory<>("subject")); 
                                        descriptiontab.setCellValueFactory(new PropertyValueFactory<>("content")); 
                                          classtab.setCellValueFactory(new PropertyValueFactory<>("class_name")); 
                                       tabact.setItems(data);
   
                           ;                  } catch (SQLException ex) {
                           System.out.println(ex);
                       }
		    	}
				
                }
    	});
          
                // TODO
                        ClassService sr = new ClassService();
        List<Classe> listaa=sr.readAll();
        ObservableList<Classe> clss =FXCollections.observableArrayList();
        for(Classe aux : listaa){
            classbox.getItems().addAll(aux.getNom());
            }
            } catch (SQLException ex) {
                Logger.getLogger(PublicationController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    

    @FXML
    private void Add(ActionEvent event) {
        
             try {
                 if(tfcontent.getText().isEmpty()||tfsubject.getText().isEmpty()) 
                     
                    {
			Alert e=new Alert(Alert.AlertType.WARNING);
			e.setTitle("Error, You can't Add");
			e.setHeaderText(null);
			e.setContentText("Please complete all fields !");
			Optional <ButtonType> action = e.showAndWait();	
                     }
                 //(!tfcontent.getText().matches("[a-z]*") ||  ! tfsubject.getText().matches("[a-z]*") 
          
                         else{      
            String suj = tfsubject.getText();
            String cont = tfcontent.getText();
            String nomp = classbox.getValue().toString();
            PublicationService cs = new PublicationService();
            publication a = new  publication(suj,cont,nomp,Integer.parseInt(id_label.getText()));
            as.add(a,Integer.parseInt(id_label.getText()));
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
    private void Reset() {
                        tfcontent.setText("");
                        tfsubject.setText("");
	        btnAdd.setDisable(false);
    }
    @FXML
    private void Delete(ActionEvent event) throws SQLException {
                Alert e=new Alert(Alert.AlertType.CONFIRMATION);
			e.setTitle("Delete Confirmation ");
			e.setHeaderText(null);
			e.setContentText("Do You really want to delete class ?");
			Optional <ButtonType> action = e.showAndWait();
			if(action.get()==ButtonType.OK) {
				
				PublicationService cs = new PublicationService();
                                ObservableList<publication> ahawa;
                                ahawa = tabact.getSelectionModel().getSelectedItems();
                                publication p = ahawa.get(0);
       				cs.delete(p.getId());
				liste.clear();
				tab();
				Reset();
        
    }
    }

    private void Reset(ActionEvent event) {
                                tfcontent.setText("");
                        tfsubject.setText("");
	        btnAdd.setDisable(false);
    }
    private PublicationService ser=new PublicationService();

    @FXML
    private void Update(ActionEvent event) throws SQLException {
                        Alert e=new Alert(Alert.AlertType.CONFIRMATION);
		e.setTitle("Update Confirmation ");
		e.setHeaderText(null);
		e.setContentText("Do You want Update  ?");
		Optional <ButtonType> action = e.showAndWait();
		if(action.get()==ButtonType.OK) {
	             String suj=tfsubject.getText();
                     String cont = tfcontent.getText();
                     String teach = classbox.getValue();
                     PublicationService cs = new PublicationService();
                                ObservableList<publication> up;
                                up = tabact.getSelectionModel().getSelectedItems();
                                publication p = up.get(0);
                                System.out.println(up.get(0));
                          System.out.println(ser.update(p.getId(),suj,cont,teach));
                        
			
			liste.clear();
                        tab();
			Reset();
			
		}
    
    }
    

    @FXML
    private void handleRowSelect(MouseEvent event) {
                 publication row = tabact.getSelectionModel().getSelectedItem();
	      
	       if (row == null) return;
	       if(row != temp){
	           temp = row;
             lastClickTime = new Date();
	       } else if(row == temp) {
	           Date now = new Date();
	           long diff = now.getTime() - lastClickTime.getTime();
	           if (diff < 300){ //another click registered in 300 millis
	              //  System.out.println(row.getNom());
	                tfcontent.setText(row.getContent());
                        tfsubject.setText(row.getSubject());
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
          
      
    ObservableList<publication> data = FXCollections.observableArrayList(liste);
    data.addAll(as.readAll());
    System.out.println(data.size());
   // for(Classe row : liste){
	    		//System.out.println(row.toString());
   // }
   
//    idtab.setCellValueFactory(new PropertyValueFactory<>("id")); 
    subjectab.setCellValueFactory(new PropertyValueFactory<>("subject")); 
    descriptiontab.setCellValueFactory(new PropertyValueFactory<>("content")); 
    classtab.setCellValueFactory(new PropertyValueFactory<>("class_name")); 
    tabact.setItems(data);
   
    
    
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
    private void goback(ActionEvent event) throws IOException {
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
}
