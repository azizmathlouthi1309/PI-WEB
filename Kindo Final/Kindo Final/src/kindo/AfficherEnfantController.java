/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import Entities.Classe;
import Entities.Enfant;
import Services.ClassService;
import Services.ServiceEnfant;
import Utilsahmed.Pdf;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.stream.Collectors;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AfficherEnfantController implements Initializable {

    @FXML
    private TableView<Enfant> tableview;
    @FXML
    private TableColumn<Enfant, String> ChildId;
    @FXML
    private TableColumn<Enfant,String> ParentId;
    @FXML
    private TableColumn<Enfant,String> FirstNameChild;
    @FXML
    private TableColumn<Enfant,String> LastNameChild;
    @FXML
    private TableColumn<Enfant,String> Level;
    @FXML
    private TableColumn<Class,String> ClassName;
    @FXML
    private Button menu;
    @FXML
    private TableColumn<Enfant,Integer> Age;
    @FXML
    private Button pdf;
    @FXML
    private Button Delete;
    @FXML
    private TextField prenom;

    
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ChildId.setCellValueFactory(new PropertyValueFactory<>("id"));
       ParentId.setCellValueFactory(new PropertyValueFactory<>("parent_id"));
       FirstNameChild.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       LastNameChild.setCellValueFactory(new PropertyValueFactory<>("nom"));
       Level.setCellValueFactory(new PropertyValueFactory<>("niveau"));
       Age.setCellValueFactory(new PropertyValueFactory<>("age"));
       
       //LastNameChild.setCellValueFactory(new PropertyValueFactory<>("lastname"));
       
       ClassName.setCellValueFactory(new PropertyValueFactory<>("nom"));
       //ParentId.setCellValueFactory(new PropertyValueFactory<>("parent_id"));
                addButtonToTable();

        try {
            tableview.setItems(getChildrene());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnfantController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
    }
     private void addButtonToTable() {
    ServiceEnfant sr = new ServiceEnfant();
        TableColumn<Enfant, Void> colBtn = new TableColumn("delete");
        Callback<TableColumn<Enfant, Void>, TableCell<Enfant, Void>> cellFactory;
        cellFactory = new Callback<TableColumn<Enfant, Void>, TableCell<Enfant, Void>>() {
            @Override
            public TableCell<Enfant, Void> call(final TableColumn<Enfant, Void> param) {
                final TableCell<Enfant, Void> cell;
                cell = new TableCell<Enfant, Void>() {
                    private final Button btn = new Button("delete");
                    
                    {
                        btn.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n"
                                + "    -fx-background-radius: 30;\n"
                                + "    -fx-background-insets: 0;\n"
                                + "    -fx-text-fill: white;");
                        btn.setOnAction(
                                (ActionEvent event) -> {
                                    Enfant p = getTableView().getItems().get(getIndex());
                                    
                                   
                                        try {
                                        sr.supprimerEnfant(p.getId());
                                        
                                         
         ;
     
                                        
                                        
                                        ChildId.setCellValueFactory(new PropertyValueFactory<>("id"));
                                        ParentId.setCellValueFactory(new PropertyValueFactory<>("parent_id"));
                                        FirstNameChild.setCellValueFactory(new PropertyValueFactory<>("prenom"));
                                        LastNameChild.setCellValueFactory(new PropertyValueFactory<>("nom"));
                                        Level.setCellValueFactory(new PropertyValueFactory<>("niveau"));
                                        Age.setCellValueFactory(new PropertyValueFactory<>("age"));
                                        //LastNameChild.setCellValueFactory(new PropertyValueFactory<>("lastname"));
                                        
                                        ClassName.setCellValueFactory(new PropertyValueFactory<>("nom"));
                                           try {
            tableview.setItems(getChildrene());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEnfantController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                                        
                                           } catch (SQLException ex) {
            Logger.getLogger(AfficherEnfantController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                                        
                                        
                                        
                                          
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
       
        tableview.getColumns().add(colBtn);

    }

   
        
     
   
    @FXML
    private void ReturnMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuBack.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
//                MenuBackController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }
    

    private ObservableList<Classe> getClassName() throws SQLException {
        ObservableList<Classe> ListClasse = FXCollections.observableArrayList();
        // ServiceEnfant sc =new ServiceEnfant();
            ClassService srv=new ClassService();
            for(Classe e:srv.readAll())
            {
                ListClasse.add(e);
            }
            return ListClasse;
    }

    private ObservableList<Enfant> getChildrene() throws SQLException {
        ObservableList<Enfant> ListEnfant = FXCollections.observableArrayList();
         ServiceEnfant sc =new ServiceEnfant();
            ClassService srv=new ClassService();
            for(Enfant e:sc.readAll())
            {
                ListEnfant.add(e);
            }
            return ListEnfant;
    }

    @FXML
    private void GeneratePDF(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        ServiceEnfant se = new ServiceEnfant();
        Pdf.main();
    }

    @FXML
    private void supprimerEnfant(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("SupprimerEnfant.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
                //SupprimerEnfantController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }

    @FXML
    private void ChercherEnfant(KeyEvent event) throws SQLException {
        ServiceEnfant se=new ServiceEnfant();
        List<Enfant> list=se.readAll().stream().filter(e->e.getPrenom().startsWith(prenom.getText())).collect(Collectors.toList());
        ObservableList<Enfant> ListEnfant = FXCollections.observableArrayList();
        for(Enfant e:list)
        {
            ListEnfant.add(e);
        }
        tableview.setItems(ListEnfant);
    }
    
}
