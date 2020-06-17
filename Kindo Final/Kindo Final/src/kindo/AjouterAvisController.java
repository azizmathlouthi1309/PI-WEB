/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;
import java.util.regex.*;
import Entities.Avis;
import Entities.Reclamation;
import Services.ServiceAvis;
import Services.ServiceReclamation;
import Utilsahmed.alert;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private TextField SubjectAvis;
    @FXML
    private TextField MessageAvis;
    @FXML
    private TextField rate;
    @FXML
    private TextField Name;
    @FXML
    private Button AjouterAvis;
    @FXML
    private Button menu;
    @FXML
    private Label nameKey;
    @FXML
    private Label SubjectK;
    @FXML
    private Label messageK;
    @FXML
    private Label rateK;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterAvis(ActionEvent event) {
        alert a=new alert();
      
          if(Name.getText().isEmpty()||SubjectAvis.getText().isEmpty()||MessageAvis.getText().isEmpty()) 
                    {     
			
			
			a.showalertwarning("please fill all the fields");
                     
                    }
          else {
                     if(!(Name.getText().matches("[a-z]*")||!Name.getText().matches("[A-Z]*") )|| !(rate.getText().matches("[0-5]*")))
                     {   
                    	a.showalertwarning("incorrect , Please write the correct fields");}
          else{
           
           try {
            String subject = SubjectAvis.getText();
            String message = MessageAvis.getText();
            int Rate = Integer.parseInt(rate.getText());
            String name = Name.getText();
           
      // a.showalertinformation("Your view has been added");
            
            
               ServiceAvis av = new ServiceAvis();
           
               Avis av1 = new Avis(0, Rate, subject, message, name);
            av.ajouterAvis(av1);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAvis.fxml"));
            
           //try {
              //  Parent root = loader.load();
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
    }
          
        
    

   /* @FXML
    private void afficher(ActionEvent event) throws SQLException, IOException {
     FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficherAvis.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                AfficherAvisController ctc=loader.getController();
               //ctc.setid(Integer.parseInt(id.getText()));
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
    }*/

    @FXML
    private void menu(ActionEvent event) throws IOException {
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

    @FXML
    private void NameKey(KeyEvent event) {
         String PATTERN="^[a-zA-Z0-9]{0,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(Name.getText());
        if(!match.matches())
        {
            nameKey.setText(" Name is incorrect");
        }
        else
        {
            nameKey.setText(null);
        }
              
              
    }

    @FXML
    private void SubjectKey(KeyEvent event) {
    }

    @FXML
    private void MessageKey(KeyEvent event) {
    }

    @FXML
    private void rateKey(KeyEvent event) {
         String PATTERN="^[a-zA-Z]{0,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(rate.getText());
        if(!match.matches())
        {
            rateK.setText("choose a number between 0-5");
        }
        else
        {
            rateK.setText(null);
        }
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
