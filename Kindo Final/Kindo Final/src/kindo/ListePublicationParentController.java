/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.publication;
import Services.PublicationService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
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
public class ListePublicationParentController implements Initializable {
private PublicationService as=new PublicationService();
            ArrayList<publication> liste=new ArrayList<>();

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
    private TableColumn<publication, String> classtab;
    @FXML
    private TableView<publication> tabact;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
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
    
}   catch (SQLException ex) {
        Logger.getLogger(ListePublicationParentController.class.getName()).log(Level.SEVERE, null, ex);
    } }
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

    @FXML
    private void returnMaissa(ActionEvent event) throws IOException {
           FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAhmed.fxml"));
                                         Stage primaryStage=new Stage();
               Parent root = loader.load();
                MenuAhmedController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpass(password_label.getText());
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
               public void setid(int id)
    {
        this.id_label.setText(""+id);
        this.id_label.setVisible(false);
    }
    public void setpass(String pass)
    {
        this.password_label.setText(pass);
        this.password_label.setVisible(false);
    }
}
