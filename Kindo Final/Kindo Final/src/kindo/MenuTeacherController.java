/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author maiss
 */
public class MenuTeacherController implements Initializable {

    @FXML
    private AnchorPane AP;
    @FXML
    private Button interactivity;
    @FXML
    private Button interclass;
    @FXML
    private Button intertime;
    @FXML
    private BorderPane bp;
    private Label label_id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPage("Activities");
    }    

    @FXML
    private void interAct(MouseEvent event) {
        loadPage("MenuTeacher");
    }

    @FXML
    private void interClass(MouseEvent event) {
        loadPage("listesClasses");
    }

    @FXML
    private void interTime(MouseEvent event) {
        loadPage("timetableliste");
    }
    
    public void loadPage(String page){
        Parent root = null;
        try {
        root = FXMLLoader.load(getClass().getResource(page+".fxml"));

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE,null,ex);
        }
        bp.setCenter(root);
        }

    private void interPublication(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Publication.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                PublicationController ctc=loader.getController();
                ctc.setid(Integer.parseInt(label_id.getText()));
        //loadPage("Publication");
        Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    private void intertSend(MouseEvent event) {
        loadPage("Timetable");
    }
    public void setid(int id)
    {
        this.label_id.setText(""+id);
        this.label_id.setVisible(false);
    }



    @FXML
    private void gotobackact(MouseEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuAdmin.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                //PublicationController ctc=loader.getController();
                //ctc.setid(Integer.parseInt(label_id.getText()));
        //loadPage("Publication");
        Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    
    }

    @FXML
    private void gotobackact1(ActionEvent event) throws IOException {
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuTeacher.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
//                PublicationController ctc=loader.getController();
                //ctc.setid(Integer.parseInt(label_id.getText()));
        //loadPage("Publication");
        Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }
}
