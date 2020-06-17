/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kindo;

import Entities.Classe;
import Entities.Enfant;
import Services.ClassService;
import Services.ServiceEnfant;
import Services.ServiceAffectation;
import Utilsahmed.alert;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ahmed
 */
public class AffectationController implements Initializable {

    @FXML
    private Button Validate;
    @FXML
    private TextField IdChild;
    @FXML
    private TextField ChildName;
    @FXML
    private TextField TeacherName;
    @FXML
    private ComboBox<String> DestinationClass;
    @FXML
    private TextField FirstName;
    @FXML
    private TextField Age;
    @FXML
    private TextField Level;
    @FXML
    private ImageView tajrba;
    @FXML
    private TextField ActualClass;
    @FXML
    private Button meni;
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
    private void Validate(ActionEvent event) throws Exception {
          ServiceEnfant enf = new ServiceEnfant();
          ServiceAffectation sa=new ServiceAffectation();
          
         if(DestinationClass.getValue() != " " && ChildName.getText() != " " && TeacherName.getText() != " "&&Age.getText()!=" "&&Level.getText()!=" ")
        {
           try {
            String nom = ChildName.getText();
            String Destinationclass = DestinationClass.getValue();
            String teacherName=TeacherName.getText();
            String prenom=FirstName.getText();
           int level=Integer.parseInt(Level.getText());
           String classe=ActualClass.getText();
            
            
              
            
            
            
           // Enfant e = new Enfant(0,nom,prenom,level,Age,2,labSingleFile1);
            sa.AffecterEnfant(Destinationclass, Integer.parseInt(IdChild.getText()));
            System.out.println(Destinationclass);

                 FXMLLoader loader = new FXMLLoader(getClass().getResource("Affectation.fxml"));
                 
            alert a=new alert();
       a.showalertinformation("child changed check mail");
//                Parent root = loader.load();
//                Scene homescene=new Scene(root);
//                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
//                    window.setScene(homescene);
//                    window.show();
//                AfficherEnfantController apc = loader.getController();
               //System.out.println(LastName);
               //             apc.setResNom(LastName);
               //        apc.setResPrenom(FirstName);
               // FirstName.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println("");
        }
        }
        else
        {
            alert a=new alert();
       a.showalertwarning("please check your information");
         
        }
    }
        
    


    @FXML
    private void Show(MouseEvent event) throws SQLException {
    ServiceEnfant se=new ServiceEnfant();
         ClassService cs=new ClassService();
         ServiceAffectation sa=new ServiceAffectation();
              Classe cc=cs.getById(Integer.parseInt(IdChild.getText()));

        //ObservableList<Classe> obser=FXCollections.observableArrayList();
        Enfant enf=se.getById(Integer.parseInt(IdChild.getText()));
        ChildName.setText(enf.getNom());
        FirstName.setText(enf.getPrenom());
        Age.setText(""+enf.getAge());
        Level.setText(""+enf.getNiveau());
         File file;
               int k = Integer.parseInt(IdChild.getText());
               file = new File(se.getById(k).getPhoto());
               Image image = new Image(file.toURI().toString());
               // if (image.isError()) {
               tajrba.setImage(image);
              TeacherName.setText(sa.getTeacherName(Integer.parseInt(IdChild.getText())));
              ActualClass.setText(sa.getClassName(Integer.parseInt(IdChild.getText())));

        //System.out.println(enf.getNiveau());
        Classe classe_n=cs.getById(1);
        //System.out.println(classe_n.getTeachername());
        
        
        List<Classe> list=cs.readAll().stream().filter(e->(e.getNb_child()<25)&&(e.getLevel()==enf.getNiveau())).collect(Collectors.toList());
        DestinationClass.getItems().removeAll(DestinationClass.getItems());
        for(Classe c:list){
            System.out.println(c);
                    
        DestinationClass.getItems().addAll(c.getNom());
            //System.out.println(c.getLevel());
        //Classe cc_n=cs.getById(c.getId());
        //TeacherName.setText(cc_n.getTeachername());
        }
        
        
        //System.out.println(list);
    }

    @FXML
    private void Meni(ActionEvent event) throws IOException {
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


