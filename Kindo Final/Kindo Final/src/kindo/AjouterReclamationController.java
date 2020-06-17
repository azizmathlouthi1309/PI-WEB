/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Enfant;
import Entities.Reclamation;
import Services.ServiceEnfant;
import Services.ServiceReclamation;
import Utilsahmed.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private ComboBox<String> TypeRec;
    @FXML
    private TextField SubjectRec;
    @FXML
    private TextField MessageRec;
    @FXML
    private Button menu;
    @FXML
    private Button AjouterReclamation;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
   
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeRec.getItems().removeAll(TypeRec.getItems());
    TypeRec.getItems().addAll("teacher", "restaurant services", "nursary services");
        
    }    

    @FXML
    private void ajouterReclamation(ActionEvent event) throws SQLException, IOException {
         if(("".equals(MessageRec.getText()) || "".equals(TypeRec.getValue())) || "".equals(SubjectRec.getText()))
         {
             alert a=new alert();
       a.showalertwarning("fill the blanks");
             
         }
        else
         {
             try {
                 String Type = TypeRec.getValue();
                 String subject = SubjectRec.getText();
                 String message = MessageRec.getText();
                 
                 ServiceReclamation rec = new ServiceReclamation();
                 
                 Reclamation recl = new Reclamation(0, subject, message, 0, Type, 0, 0, message);
                 rec.ajouterReclamation(recl);
//                 FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
//                 Parent root = loader.load();
//                 Scene homescene=new Scene(root);
//                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setScene(homescene);
//                    window.show();

                 
      
                 //try {
                 //
                 /*AfficherEnfantController apc = loader.getController();
                 System.out.println(LastName);
                 apc.setResNom(LastName);
                 apc.setResPrenom(FirstName);
                 FirstName.getScene().setRoot(root);
                 } catch (IOException ex) {
                 System.out.println();
                 }
                 */
             } catch (SQLException ex) {
                 System.out.println("");
             }
         }
    }

    @FXML
    private void menu(ActionEvent event) throws IOException, IOException {
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
