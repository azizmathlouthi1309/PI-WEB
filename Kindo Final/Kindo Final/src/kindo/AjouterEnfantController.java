/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Enfant;
import java.util.regex.*;
import Services.ServiceEnfant;
import Utilsahmed.alert;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AjouterEnfantController implements Initializable {

    @FXML
    private TextField FirstName;
    @FXML
    private TextField LastName;
    @FXML
    private TextField ChildAge;
    @FXML
    private Button AjouterEnfant;
    @FXML
    private TextField ChildLevel;
    @FXML
    private Button btnSingleFileChooser;
    @FXML
    private Label labSingleFile;
 List<String> lstFile=new ArrayList<>();  
    @FXML
    private Button returnM;
    @FXML
    private Label elab;
    @FXML
    private Label dabdab;
    @FXML
    private Label adab;
    @FXML
    private Label nlab;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         lstFile.add("*.png");
        lstFile.add("*.jpg");
        lstFile.add("*.IMAGE");
    }    

    @FXML
    private void ajouterEnfant(ActionEvent event) {
        alert a=new alert();
      
          if(ChildAge.getText().isEmpty()||ChildLevel.getText().isEmpty()||FirstName.getText().isEmpty()||LastName.getText().isEmpty()) 
                    {     
			
			
			a.showalertwarning("please fill all the fields");
                     
                    }
          else if(!(FirstName.getText().matches("[a-z]*")||!LastName.getText().matches("[a-z]*") ))
                     {   
                    	a.showalertwarning("incorrect , Please write the correct fields");}
          
                     else if(!(ChildAge.getText().matches("[2-5]*"))){
                         a.showalertwarning("incorrect, Age must be between 2-5");}
          
                     else{
           try {
            String nom = LastName.getText();
            String prenom = FirstName.getText();
            String labSingleFile1=labSingleFile.getText();
            ServiceEnfant enf = new ServiceEnfant();
            int Niveau=Integer.parseInt(ChildLevel.getText());
            int Age=Integer.parseInt(ChildAge.getText());
            Enfant e = new Enfant(0,nom,prenom,Niveau,Age,2,labSingleFile1);
            enf.ajouterEnfant(e);
           
      
       //Reset();
                 //FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEnfant.fxml"));
            
//           try {
//                Parent root = loader.load();
//                Scene homescene=new Scene(root);
//                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setScene(homescene);
//                    window.show();
//                AfficherEnfantController apc = loader.getController();
//               System.out.println(LastName);
//                apc.setResNom(LastName);
//                apc.setResPrenom(FirstName);
//                
//                FirstName.getScene().setRoot(root);
//            } catch (IOException ex) {
//                System.out.println();
//                }
        } catch (SQLException ex) {
            System.out.println("");
        }
                     }
        }
        
        
    

    @FXML
    private void SingleFileChooser(ActionEvent event) {
           
        FileChooser fc=new FileChooser();
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Word Files",lstFile));
       File f=fc.showOpenDialog(null);
        if(f!=null)
        {
            labSingleFile.setText(f.getAbsolutePath());
        }
        
    }

    @FXML
    private void reurnM(ActionEvent event) throws IOException {
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
    private void NameKeyReleased(KeyEvent event) {
        String PATTERN="^[a-zA-Z]{0,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(FirstName.getText());
        if(!match.matches())
        {
            nlab.setText("First Name is incorrect");
        }
        else
        {
            nlab.setText(null);
        }
    }

    @FXML
    private void AgeKeyReleased(KeyEvent event) {
         String PATTERN="^[0-9]{0,12}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(ChildAge.getText());
        if(!match.matches())
        {
            adab.setText("Age is incorrect press number");
        }
        else
        {
            adab.setText(null);
        }
        
    }

    @FXML
    private void NiveauKeyReleased(KeyEvent event) {
         String PATTERN="^[0-9]{0,12}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(ChildLevel.getText());
        if(!match.matches())
        {
            dabdab.setText("Level is incorrect press number");
        }
        else
        {
            dabdab.setText(null);
        }
    }

    @FXML
    private void LastNameKeyReleased(KeyEvent event) {
         String PATTERN="^[a-z-Z]{0,30}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(LastName.getText());
        if(!match.matches())
        {
            elab.setText("Last Name is incorrect");
        }
        else
        {
            elab.setText(null);
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
        private void Reset() {
               // tfid.setText("");
                ChildAge.setText("");
	   	LastName.setText("");
	   	FirstName.setText("");
                ChildLevel.setText("");
                labSingleFile.setVisible(false);
	       // btnAdd.setDisable(false);
    }
    }
    

