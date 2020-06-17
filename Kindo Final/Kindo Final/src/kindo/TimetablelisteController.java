/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Activity;
import Entities.Parent;
import Entities.TimeTable;
import Entities.User;
import Services.ActivityService;
import Services.ParentService;
import Services.TimeTableService;
import Services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import manage.Mail;

/**
 * FXML Controller class
 *
 * @author maiss
 */
public class TimetablelisteController implements Initializable {
     ArrayList<TimeTable> liste=new ArrayList<>();
    @FXML
    private TextField tfclass;
    @FXML
    private TextField tfsemaine;
    @FXML
    private TableView<TimeTable> emploi;
    @FXML
    private TableColumn act1;
    @FXML
    private TableColumn act2;
    @FXML
    private TableColumn act3;
    @FXML
    private TableColumn act4;
    @FXML
    private TextField tfact1;
    @FXML
    private TextField tfact4;
    @FXML
    private TextField tfact3;
    @FXML
    private TextField tfact2;
    @FXML
    private TableColumn day;
    @FXML
    private TableColumn tabclass;
    @FXML
    private TableColumn  tabsem;
    private Connection con;
    private Statement ste;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnshow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ShowTimeTable(ActionEvent event) throws SQLException {
        TimeTableService tts= new TimeTableService();
      
                           TimeTable tb = new TimeTable();
                        
                         
                                for (TimeTable tt :tts.getTimetable(tfclass.getText(), tfsemaine.getText()))
                               liste.add(new TimeTable(tt.getActivity1(),tt.getActivity2(),tt.getActivity3(),tt.getActivity4(),tt.getClasse(),tt.getDate(),tt.getJour()));
                           
                           ObservableList<TimeTable> data = FXCollections.observableArrayList(liste);
                           
                           act1.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("Activity1"));
                           act2.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity2"));
                           act3.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity3"));
                           act4.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity4"));
                           day.setCellValueFactory(new PropertyValueFactory<TimeTable, Integer> ("jour"));
                           tabclass.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("classe"));
                           tabsem.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("date"));
                      //     tfact1.setText(liste.addAll(tb.getActivity1()));
                           
                           
                           emploi.setItems(data);
                          // System.out.println(tts.getTimetable(tfclass.getText(), tfsemaine.getText()));
                   btnshow.setVisible(false);
                           ActivityService ser = new ActivityService();
                             
                             // ObservableList<Activity> cls =FXCollections.observableArrayList();
                              
    
    }
    public void tab() throws SQLException
    {
             TimeTableService tts= new TimeTableService();
      
                           TimeTable tb = new TimeTable();
                        
                         
                                for (TimeTable tt :tts.getTimetable(tfclass.getText(), tfsemaine.getText()))
                               liste.add(new TimeTable(tt.getActivity1(),tt.getActivity2(),tt.getActivity3(),tt.getActivity4(),tt.getClasse(),tt.getDate(),tt.getJour()));
                           
                           ObservableList<TimeTable> data = FXCollections.observableArrayList(liste);
                           
                           act1.setCellValueFactory(new PropertyValueFactory<TimeTable, String>("Activity1"));
                           act2.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity2"));
                           act3.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity3"));
                           act4.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("Activity4"));
                           day.setCellValueFactory(new PropertyValueFactory<TimeTable, Integer> ("jour"));
                           tabclass.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("classe"));
                           tabsem.setCellValueFactory(new PropertyValueFactory<TimeTable, String> ("date"));
                      //     tfact1.setText(liste.addAll(tb.getActivity1()));
                           
                           
                           emploi.setItems(data);
                         
                           
                          // System.out.println(tts.getTimetable(tfclass.getText(), tfsemaine.getText()));
   
                           ActivityService ser = new ActivityService();
                             
                             // ObservableList<Activity> cls =FXCollections.observableArrayList();
                              
    
    }
                             

    @FXML
    private void update(ActionEvent event) throws SQLException {
                   
                      String a1 = tfact1.getText();
                      String a2 = tfact2.getText();
                      String a3=tfact3.getText();
                      String a4=tfact4.getText();
                      
                        TimeTableService tts= new TimeTableService();
                          ObservableList<TimeTable> up;
                          up = emploi.getSelectionModel().getSelectedItems();
                          TimeTable p = up.get(0);
                       //   System.out.println(up);
                          System.out.println(tts.updatefinal(p.getJour(), p.getClasse(),p.getDate(),a1,a2,a3,a4));
                          liste.clear();
                          tab();
                   Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Activitie updated to timetable Pdf successfully !");
                alert.showAndWait();
        
    }
    
    
    
    
    
        TimeTable temp=new TimeTable();
        private Date lastClickTime;
    @FXML
    private void handleRowSelect(MouseEvent event) {
          TimeTable row = emploi.getSelectionModel().getSelectedItem();
	      
	       if (row == null) return;
	       if(row != temp){
	           temp = row;
              lastClickTime = new Date();
	       } else if(row == temp) {
	           Date now = new Date();
	           long diff = now.getTime() - lastClickTime.getTime();
	           if (diff < 300){ //another click registered in 300 millis
	              //  System.out.println(row.getNom());
	                tfact1.setText(row.getActivity1());
                        tfact2.setText(row.getActivity2());
                        tfact3.setText(row.getActivity3());
                        tfact4.setText(row.getActivity4());
                        tfclass.setText(tfclass.getText());
                        row.getDate();
                        row.getJour();
                          
	           } else {
	               lastClickTime = new Date();
	           }
	       }
    }

    @FXML
    private void Resend(ActionEvent event) throws SQLException, Exception {
        
       //  ste=con.createStatement();
       //String query="select * from user ";
       //String query="select * from user where role='parent' "+us.getRoles();
        //ResultSet rs=ste.executeQuery(query);
        Mail m = new Mail();
        ParentService ps=new ParentService();
        
                
                
      for(Parent p :ps.getParents())
      {
                 Mail.sendMail(p.getEmail());
         }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Timetable sent  successfully !");
                alert.showAndWait();
    }

    @FXML
    private void CreateTimeTable(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Timetable.fxml"));
                                         Stage primaryStage=new Stage();
                javafx.scene.Parent root = loader.load();
                //PublicationController ctc=loader.getController();
                //ctc.setid(Integer.parseInt(label_id.getText()));
        //loadPage("Publication");
        Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
}
