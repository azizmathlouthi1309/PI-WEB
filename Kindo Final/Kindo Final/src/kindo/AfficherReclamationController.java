/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Reclamation;
import Services.ClassService;
import Services.ServiceReclamation;
import Utilsahmed.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.crypto.Data;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableview1;
    @FXML
    private TableColumn<Reclamation,String> RecType;
    @FXML
    private TableColumn<Reclamation,String> Subject;
    @FXML
    private TableColumn<Reclamation,String> Message;

    @FXML
    private TableColumn<Reclamation,Integer> Status;
    @FXML
    private Button menu;  
    @FXML
    private TextField idR;
    @FXML
    private Button idRep;
    @FXML
    private Label returnidrec;
    @FXML
    private TableColumn<Reclamation, Integer> id;
    @FXML
    private TableColumn<Reclamation,String> Reponsee;
  
   

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
       RecType.setCellValueFactory(new PropertyValueFactory<>("type"));
       Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
       Message.setCellValueFactory(new PropertyValueFactory<>("message"));
       Status.setCellValueFactory(new PropertyValueFactory<>("status"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Reponsee.setCellValueFactory(new PropertyValueFactory<>("response"));
       
      addButtonToTable();
      addButtonToTable2();
     idR.setVisible(false);
       idRep.setVisible(false);
       id.setVisible(false);
        try {
            tableview1.setItems(getReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
private void addButtonToTable() {
    ServiceReclamation sr = new ServiceReclamation();
        TableColumn<Reclamation, Void> colBtn = new TableColumn("delete");
        Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>> cellFactory
                = new Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>>() {
                    @Override
                    public TableCell<Reclamation, Void> call(final TableColumn<Reclamation, Void> param) {
                        final TableCell<Reclamation, Void> cell = new TableCell<Reclamation, Void>() {
                            private final Button btn = new Button("delete");

                            {
                                btn.setStyle("-fx-background-color: linear-gradient( #74BBE4, #be1d00);\n"
                                        + "    -fx-background-radius: 30;\n"
                                        + "    -fx-background-insets: 0;\n"
                                        + "    -fx-text-fill: white;");
                                btn.setOnAction(
                                        (ActionEvent event) -> {
                                            Reclamation p = getTableView().getItems().get(getIndex());
                                            try {
                                               
                                                    sr.supprimerReclamation(p.getId());
                                                    RecType.setCellValueFactory(new PropertyValueFactory<>("type"));
       Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
       Message.setCellValueFactory(new PropertyValueFactory<>("message"));
       Status.setCellValueFactory(new PropertyValueFactory<>("status"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
    
     idR.setVisible(false);
       idRep.setVisible(false);
        try {
            tableview1.setItems(getReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
                                                    //refrechTable();
                                               
                                            } catch (Exception e) {
                                            }
                                            System.out.println("selectedData: " + p);
                                        }
                                );
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn);
                                }
                            }
                        };
                        return cell;
                    }
               
        
                };
        colBtn.setCellFactory(cellFactory);
       
        tableview1.getColumns().add(colBtn);

    }

    private void addButtonToTable2() {

ServiceReclamation sr = new ServiceReclamation();
        TableColumn<Reclamation, Void> colBtn1 = new TableColumn("response");
        Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>> cellFactory1
                = new Callback<TableColumn<Reclamation, Void>, TableCell<Reclamation, Void>>() {
                    @Override
                    public TableCell<Reclamation, Void> call(final TableColumn<Reclamation, Void> param) {
                        final TableCell<Reclamation, Void> cell = new TableCell<Reclamation, Void>() {
                            private final Button btn1 = new Button("response");

                            {
                                btn1.setStyle("-fx-background-color: linear-gradient( #74BBE4, #be1d00);\n"
                                        + "    -fx-background-radius: 30;\n"
                                        + "    -fx-background-insets: 0;\n"
                                        + "    -fx-text-fill: white;");
                                btn1.setOnAction(
                                        (ActionEvent event) -> {
                                            Reclamation p1 = getTableView().getItems().get(getIndex());
                                            try {
                                               idR.setVisible(true);
                                               idRep.setVisible(true);
                                             
                                               returnidrec.setText(""+p1.getId());
                                                    //sr.Reponse(,p.getResponse(),p.getId());
                                                    //refrechTable();
                                               
                                            } catch (Exception e) {
                                            }
                                            System.out.println("selectedData: " + p1);
                                        }
                                );
                            }

                            @Override
                            public void updateItem(Void item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                } else {
                                    setGraphic(btn1);
                                }
                            }
                        };
                        return cell;
                    }
               
        
                };
        colBtn1.setCellFactory(cellFactory1);
       
        tableview1.getColumns().add(colBtn1);
 }



    private ObservableList<Reclamation> getReclamation() throws SQLException {
       
        ObservableList<Reclamation> ListRec = FXCollections.observableArrayList();
         ServiceReclamation sc =new ServiceReclamation();
            ClassService srv=new ClassService();
            for(Reclamation r:sc.readAll())
            {
                ListRec.add(r);
            }
            return ListRec;
    }

    @FXML
    private void menu(ActionEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuBack.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                
                MenuBackController ctc=loader.getController();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
               //ctc.setid(Integer.parseInt(id.getText()));
//                Scene scene=new Scene(root);
//                primaryStage.setScene(scene);
//                primaryStage.show();
    }

    @FXML
    private void envoyer(ActionEvent event ) throws SQLException, InterruptedException {
         idR.setVisible(false);
         idRep.setVisible(false);
       
       ServiceReclamation sr = new ServiceReclamation();
       sr.Reponse(idR.getText(),Integer.parseInt(returnidrec.getText()) );
       alert a=new alert();
       a.showalertinformation("Your response has been added");
      RecType.setCellValueFactory(new PropertyValueFactory<>("type"));
       Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
       Message.setCellValueFactory(new PropertyValueFactory<>("message"));
       Status.setCellValueFactory(new PropertyValueFactory<>("status"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Reponsee.setCellValueFactory(new PropertyValueFactory<>("response"));
       
  
     idR.setVisible(false);
       idRep.setVisible(false);
       id.setVisible(false);
        try {
      // addButtonToTable();
     addButtonToTable2();
            tableview1.setItems(getReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //tableview1.setItems(getReclamation());
        //refresh();
        
         
    }
    private void refresh(){
         RecType.setCellValueFactory(new PropertyValueFactory<>("type"));
       Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
       Message.setCellValueFactory(new PropertyValueFactory<>("message"));
       Status.setCellValueFactory(new PropertyValueFactory<>("status"));
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
        Reponsee.setCellValueFactory(new PropertyValueFactory<>("response"));
       
     // addButtonToTable();
    //  addButtonToTable2();
     idR.setVisible(false);
       idRep.setVisible(false);
       id.setVisible(false);
        try {
            tableview1.setItems(getReclamation());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    }
        
    

