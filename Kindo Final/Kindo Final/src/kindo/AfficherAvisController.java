/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Avis;
import Services.ClassService;
import Services.ServiceAvis;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherAvisController implements Initializable {

    @FXML
    private TableColumn<Avis, String> SenderName;
    @FXML
    private TableColumn<Avis, String> SubjectAvis;
    @FXML
    private TableColumn<Avis, String> MessageAvis;
    @FXML
    private TableColumn<Avis, Integer> StarsNumber;
    @FXML
    private Label moyenneAvis;
    @FXML
    private TableView<Avis> tableView;
    @FXML
    private Button reurn;
    @FXML
    private Button Delete;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       SenderName.setCellValueFactory(new PropertyValueFactory<>("name"));
       StarsNumber.setCellValueFactory(new PropertyValueFactory<>("nb_stars"));
       SubjectAvis.setCellValueFactory(new PropertyValueFactory<>("subject"));
       MessageAvis.setCellValueFactory(new PropertyValueFactory<>("message"));
       addButtonToTable();
       
       
            
        try {
            
            tableView.setItems(getAvis());
        
            ServiceAvis sa=new ServiceAvis();
            if(sa.moyenneAvis() >=0 && sa.moyenneAvis() < 1)
            {
                moyenneAvis.setText("");
            }
            if(sa.moyenneAvis() >=1 && sa.moyenneAvis() < 2)
            {
                moyenneAvis.setText("★");
            }
            if(sa.moyenneAvis() >=2 && sa.moyenneAvis() < 3)
            {
                moyenneAvis.setText("★★");
            }
            if(sa.moyenneAvis() >=3 && sa.moyenneAvis() < 4)
            {
                moyenneAvis.setText("★★★");
            }
            if(sa.moyenneAvis() >=4 && sa.moyenneAvis() < 5)
            {
                moyenneAvis.setText("★★★★");
            }
            if(sa.moyenneAvis() ==5 )
            {
                moyenneAvis.setText("★★★★★");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
          }
        

    }
    private void addButtonToTable() {
    ServiceAvis sr = new ServiceAvis();
        TableColumn<Avis, Void> colBtn = new TableColumn("delete");
        Callback<TableColumn<Avis, Void>, TableCell<Avis, Void>> cellFactory;
                cellFactory = (
                        final TableColumn<Avis, Void> param) -> {
            final TableCell<Avis, Void> cell;
        cell = new TableCell<Avis, Void>() {
            private final Button btn = new Button("delete");
            
            { btn.setStyle("-fx-background-color: linear-gradient( #74BBE4, #be1d00);\n"
                    + "    -fx-background-radius: 30;\n"
                    + "    -fx-background-insets: 0;\n"
                    + "    -fx-text-fill: white;");
            btn.setOnAction(
                    (ActionEvent event) -> {
                        Avis p = getTableView().getItems().get(getIndex());
                        try {
                            
                            sr.supprimerAvis(p.getId());
                            SenderName.setCellValueFactory(new PropertyValueFactory<>("name"));
                            StarsNumber.setCellValueFactory(new PropertyValueFactory<>("nb_stars"));
                            SubjectAvis.setCellValueFactory(new PropertyValueFactory<>("subject"));
                            MessageAvis.setCellValueFactory(new PropertyValueFactory<>("message"));
                            
                            
                            
                            try {
                                tableView.setItems(getAvis());
                            } catch (SQLException ex) {
                                Logger.getLogger(AfficherAvisController.class.getName()).log(Level.SEVERE, null, ex);
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
        };
        colBtn.setCellFactory(cellFactory);
       
        tableView.getColumns().add(colBtn);

    }

   
    
      
     

    private ObservableList<Avis> getAvis() throws SQLException {
       
        ObservableList<Avis> ListAvis = FXCollections.observableArrayList();
         ServiceAvis sc =new ServiceAvis();
            ClassService srv=new ClassService();
            for(Avis av:sc.readAll())
            {
                ListAvis.add(av);
            }
            return ListAvis;
    }

    @FXML
    private void returnA(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuBack.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                MenuBackController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
//                Scene scene=new Scene(root);
//                primaryStage.setScene(scene);
//                primaryStage.show();
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("SupprimerAvis.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
               
                 Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                AfficherAvisController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }


    
    
}
