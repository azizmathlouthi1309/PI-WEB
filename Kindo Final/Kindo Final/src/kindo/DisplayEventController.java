/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.awt.AWTException;
import java.awt.Image;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
//import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

import Entities.event;
import Services.ServiceEvent;
import Services.mailService;
import Utils.TrayIconDemok;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class DisplayEventController implements Initializable {

    @FXML
    private TableView<event> table;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn description;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn hour_begin;
    @FXML
    private TableColumn hour_end;
    @FXML
    private TableColumn capacity;
    @FXML
    private TextField tsearch;
    @FXML
    private Button add;

    private Date lastClickTime;

    @FXML
    private TextField tfname;
    @FXML
    private TextField descripition;
    @FXML
    private TextField addcapacity;
    @FXML
    private TextField addhour_begin;
    @FXML
    private TextField addhour_end;
    @FXML
    private Button edit;
    @FXML
    private DatePicker addDate;

    /**
     * Initializes the controller class.
     */
    ServiceEvent se = new ServiceEvent();
    event e1 = new event();

    ArrayList<event> liste = new ArrayList<>();
    @FXML
    private Button edit1;
    @FXML
    private Button update;
    ObservableList<event> data = FXCollections.observableArrayList();
    @FXML
    private Button participation;

    public void table() {

        try {
            data.addAll(se.readAll());
            System.out.println(data.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        name.setCellValueFactory(new PropertyValueFactory<event, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<event, Date>("date"));
        hour_begin.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_begin"));
        hour_end.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_end"));
        capacity.setCellValueFactory(new PropertyValueFactory<event, Integer>("capacity"));
        table.setItems(data);
        reset();
//          Date dateDuJour =new Date();
//            System.out.println(dateDuJour); 

    }
    private void reset() {
        // TODO Auto-generated method stub
        tfname.setText("");
        descripition.setText("");
        addcapacity.setText("");
        addhour_begin.setText("");
        addhour_end.setText("");
        addDate.setValue(LocalDate.now());
        add.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        table();
        tsearch.textProperty().addListener(new ChangeListener<String>() {
//Rechercher
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (tsearch.getText() != "") {
                    try {
                        event e = new event();
                        ServiceEvent se = new ServiceEvent();
                        liste.clear();

                        for (event e1 : se.search1(newValue)) {
                            liste.add(new event(e1.getId(), e1.getName(), e1.getDescription(), e1.getDate(), e1.getHour_begin(), e1.getHour_end(), e1.getCapacity()));
                        }
                        ObservableList<event> data = FXCollections.observableArrayList(liste);
                        name.setCellValueFactory(new PropertyValueFactory<event, String>("name"));
                        description.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
                        date.setCellValueFactory(new PropertyValueFactory<event, Date>("date"));
                        hour_begin.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_begin"));
                        hour_end.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_end"));
                        capacity.setCellValueFactory(new PropertyValueFactory<event, Integer>("capacity"));
                        table.setItems(data);

                        System.out.println("exist");

                    } catch (SQLException ex) {
                        System.out.println(ex);
                    }

                }

            }
        });

    }

    // TODO
    private void refresh(ActionEvent event) {
        table.refresh();

    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Alert e = new Alert(AlertType.CONFIRMATION);
        e.setTitle("Confirmation");
        e.setHeaderText(null);
        e.setContentText("Delete event");
        Optional<ButtonType> action = e.showAndWait();
        if (action.get() == ButtonType.OK) {
            ServiceEvent se = new ServiceEvent();
            int selectedIndex = table.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                se.cancel(table.getItems().remove(selectedIndex));
                liste.clear();
            }
            reset();
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException, Exception {

        try {
            String Name = tfname.getText();
            String Desc = descripition.getText();

//            java.sql.Date dateDuJour = (java.sql.Date) new Date();
                    //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            java.sql.Date addDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            String Capacity = addcapacity.getText();
            int cap = Integer.parseInt(Capacity);

            String Hour_begin = addhour_begin.getText();
            int hb = Integer.parseInt(Hour_begin);

            String Hour_end = addhour_end.getText();
            int he = Integer.parseInt(Hour_end);
            if(hb<he && hb>=8 && hb<=17 && he>=8 && he<=17)
            {
            event e = new event(Name, Desc, addDate, hb, he, cap);
            ServiceEvent se = new ServiceEvent();
            se.add(e);
            liste.clear();
              mailService.sendMail("khaoulamekni59@gmail.com");
            // table.refresh();
                try {
                    TrayIconDemok.main(1);
                } catch (AWTException ex) {

                }
            //se.readAll().stream().forEach(e1 -> System.out.println(e1));
            }
            else 
            {
            Alert e=new Alert(AlertType.WARNING);
			e.setTitle("Erreur d'insertion");
			e.setHeaderText(null);
			e.setContentText("verifiez les champs des heures !");
			Optional <ButtonType> action = e.showAndWait();	
            }
          
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        reset();
        liste.clear();
        table();
    }


    @FXML
    private void handleRowSelect(MouseEvent event) {

        if (event.getClickCount() > 0) {
            if (table.getSelectionModel().getSelectedItem() != null) {

            }
            event ls = table.getSelectionModel().getSelectedItem();
            tfname.setText(ls.getName());
            descripition.setText(ls.getDescription());
            addcapacity.setText(String.valueOf(ls.getCapacity()));
            addhour_begin.setText(String.valueOf(ls.getHour_begin()));
            addhour_end.setText(String.valueOf(ls.getHour_end()));
            addDate.setValue(ls.getDate().toLocalDate());
       
        }
    }

    @FXML
    private void delay(ActionEvent event) throws SQLException, Exception {
        Alert e = new Alert(AlertType.CONFIRMATION);
        e.setTitle("Confirmation");
        e.setHeaderText(null);
        e.setContentText("Update event");
        Optional<ButtonType> action = e.showAndWait();
        if (action.get() == ButtonType.OK) {
            ServiceEvent se = new ServiceEvent();
            event e1 = new event();
            e1 = table.getSelectionModel().getSelectedItem();
            liste.clear();
            //mailService.sendMail("khaoulamekni59@gmail.com");
            se.delay(tfname.getText(),Integer.valueOf(addhour_begin.getText()), Integer.valueOf(addhour_end.getText()));

            reset();
            liste.clear();
            table.refresh();
        }
    }

    @FXML
    private void update(ActionEvent event) throws SQLException {
        Alert e = new Alert(AlertType.CONFIRMATION);
        e.setTitle("Confirmation");
        e.setHeaderText(null);
        e.setContentText("Update event");
        Optional<ButtonType> action = e.showAndWait();
        if (action.get() == ButtonType.OK) {
            ServiceEvent se = new ServiceEvent();
            event e1 = new event();
            //e1 = table.getSelectionModel().getSelectedItem();
           java.sql.Date addDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            se.update(addDate , tfname.getText(), descripition.getText(), Integer.valueOf(addhour_begin.getText()), Integer.valueOf(addhour_end.getText()), Integer.valueOf(addcapacity.getText()));
           
            reset();
            refresh();
        }
    }
    Parent root;

    private void refresh() {
         ObservableList<event> data = FXCollections.observableArrayList();
        name.setCellValueFactory(new PropertyValueFactory<event, String>("name"));
        description.setCellValueFactory(new PropertyValueFactory<event, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<event, Date>("date"));
        hour_begin.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_begin"));
        hour_end.setCellValueFactory(new PropertyValueFactory<event, Integer>("hour_end"));
        capacity.setCellValueFactory(new PropertyValueFactory<event, Integer>("capacity"));

        table.setItems(data);

    }

    @FXML
    private void participation(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("addParticipation.fxml"));
        Parent root = loader.load();
        participation.getScene().setRoot(root);
            //Stage stage = new Stage();
            //stage.setScene(new Scene(root));  
            //stage.show();
            //main.stg.close();
        //Parent root =loader.load();
        
    }

    @FXML
    private void gobackto(ActionEvent event) throws IOException {
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

}
