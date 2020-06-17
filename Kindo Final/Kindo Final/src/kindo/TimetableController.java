/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Activity;
import Entities.Classe;
import Entities.Parent;
import Entities.TimeTable;
import Entities.User;
import Services.ActivityService;
import Services.ClassService;
import Services.ParentService;
import Services.TimeTableService;
import Services.UserService;
import Utils.DataBase;
import com.itextpdf.text.DocumentException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import manage.Mail;
import manage.Pdf;

/**
 * FXML Controller class
 *
 * @author maiss
 */
public class TimetableController implements Initializable {

    @FXML
    private ComboBox<String> Lundi1;
    @FXML
    private ComboBox<String> Lundi2;
    @FXML
    private ComboBox<String> Mardi1;
    @FXML
    private ComboBox<String> Mardi2;
    @FXML
    private ComboBox<String> Mercredi1;
    @FXML
    private ComboBox<String> Mercredi2;
    @FXML
    private ComboBox<String> Jeudi1;
    @FXML
    private ComboBox<String> Jeudi2;
    @FXML
    private ComboBox<String> Vendredi1;
    @FXML
    private ComboBox<String> Vendredi2;
    @FXML
    private ComboBox<String> classe;

    ObservableList <String> list = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     * 
     */
    private Connection con;
    private Statement ste;
    @FXML
    private ComboBox<String> Lundi3;
    @FXML
    private ComboBox<String> Lundi4;
    @FXML
    private ComboBox<String> Mardi3;
    @FXML
    private ComboBox<String> Mardi4;
    @FXML
    private ComboBox<String> Mercredi3;
    @FXML
    private ComboBox<String> Mercredi4;
    @FXML
    private ComboBox<String> Jeudi3;
    @FXML
    private ComboBox<String> Jeudi4;
    @FXML
    private ComboBox<String> Vendredi3;
    @FXML
    private ComboBox<String> Vendredi4;
    @FXML
    private TextField tfdate;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           con = DataBase.getInstance().getConnection();
        try {
            //
        ActivityService ser = new ActivityService();
        List<Activity> lista=ser.readAll();
        ObservableList<Activity> cls =FXCollections.observableArrayList();
        for(Activity aux : lista){
            Lundi1.getItems().addAll(aux.getName());
            Lundi2.getItems().addAll(aux.getName());
            Mardi1.getItems().addAll(aux.getName());
            Mardi2.getItems().addAll(aux.getName());
            Mercredi1.getItems().addAll(aux.getName());
            Mercredi2.getItems().addAll(aux.getName());
            Jeudi1.getItems().addAll(aux.getName());
            Jeudi2.getItems().addAll(aux.getName());
            Vendredi1.getItems().addAll(aux.getName());
            Vendredi2.getItems().addAll(aux.getName());
            Lundi3.getItems().addAll(aux.getName());
            Lundi4.getItems().addAll(aux.getName());
            Mardi3.getItems().addAll(aux.getName());
            Mardi4.getItems().addAll(aux.getName());
            Mercredi3.getItems().addAll(aux.getName());
            Mercredi4.getItems().addAll(aux.getName());
            Jeudi3.getItems().addAll(aux.getName());
            Jeudi4.getItems().addAll(aux.getName());
            Vendredi3.getItems().addAll(aux.getName());
            Vendredi4.getItems().addAll(aux.getName());
            
            }
                //CLASS
                     ClassService sr = new ClassService();
        List<Classe> listaa=sr.readAll();
        ObservableList<Classe> clss =FXCollections.observableArrayList();
        for(Classe aux : listaa){
            classe.getItems().addAll(aux.getNom());
            }
        
        }
        catch (Exception e) {
        }
        

    }    
   

    @FXML
    private void GeneratPDF(ActionEvent event) throws FileNotFoundException, SQLException, DocumentException, Exception {
        Pdf Pdf=new Pdf();
        Pdf.add("Timetable.pdf",tfdate.getText(),classe.getValue().toString(),Lundi1.getValue(),Lundi2.getValue(),Lundi3.getValue(),Lundi4.getValue(),Mardi1.getValue(),Mardi2.getValue(),Mardi3.getValue()
                ,
        Mardi4.getValue(),Mercredi1.getValue(),Mercredi2.getValue(),Mercredi3.getValue(),Mercredi4.getValue()
            , Jeudi1.getValue(),Jeudi2.getValue() ,Jeudi3.getValue(),Jeudi4.getValue(),Vendredi1.getValue(),Vendredi2.getValue()  ,Vendredi3.getValue(),Vendredi4.getValue());
        /* User us= new User();
         UserService sr = new UserService();
          List<User> lis=sr.readAll();
         for(User aux : lis ){*/
        TimeTableService tt= new TimeTableService();
         TimeTableService tt2= new TimeTableService();
          TimeTableService tt3= new TimeTableService();
           TimeTableService tt4= new TimeTableService();
        String L1 = Lundi1.getValue();
        String L2 = Lundi2.getValue();
        String L3 = Lundi3.getValue();
        String L4 = Lundi4.getValue();
        String M1 = Mardi1.getValue();
        String M2 = Mardi2.getValue();
        String M3 = Mardi3.getValue();
        String M4 = Mardi4.getValue();
        String Mer1 = Mercredi1.getValue();
        String Mer2 = Mercredi2.getValue();
        String Mer3 = Mercredi3.getValue();
        String Mer4 = Mercredi4.getValue();
        String J1 = Jeudi1.getValue();
        String J2 = Jeudi2.getValue();
        String J3 = Jeudi3.getValue();
        String J4 = Jeudi4.getValue();
        String V1 = Vendredi1.getValue();
        String V2 = Vendredi2.getValue();
        String V3 = Vendredi3.getValue();
        String V4 = Vendredi4.getValue();
        String cl= classe.getValue();
        String d1=tfdate.getText();
        TimeTable emploi= new TimeTable(L1,L2,L3,L4,cl,d1,1);
        TimeTable emploi2= new TimeTable(M1,M2,M3,M4,cl,d1,2);
        TimeTable emploi3= new TimeTable(Mer1,Mer2,Mer3,Mer4,cl,d1,3);
        TimeTable emploi4= new TimeTable(J1,J2,J3,J4,cl,d1,4);
         TimeTable emploi5= new TimeTable(V1,V2,V3,V4,cl,d1,5);
         tt.add(emploi);
         tt.add(emploi2);
         tt.add(emploi3);
         tt.add(emploi4);
         tt.add(emploi5);
       
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Activities added to timetable pdf successfully !");
                alert.showAndWait();
        
        
        Mail m = new Mail();
        ParentService ps=new ParentService();
        
              
                
      for(Parent p :ps.getParents())
      {
                 Mail.sendMail(p.getEmail());
              
            
         }
          alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("New TimeTable is sent to all Parents  !");
                alert.showAndWait();
            
    }

    @FXML
    private void gobacktothefuture(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuTeacher.fxml"));
                                         Stage primaryStage=new Stage();
                javafx.scene.Parent root = loader.load();
//                PublicationController ctc=loader.getController();
                //ctc.setid(Integer.parseInt(label_id.getText()));
        //loadPage("Publication");
        Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
}