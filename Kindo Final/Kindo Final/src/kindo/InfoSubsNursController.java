/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Services.SaveNurseService;
import Entities.SaveNurse;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class InfoSubsNursController implements Initializable {

    @FXML
    private TableView<SaveNurse> TableSubsData;
    @FXML
    private TableColumn<SaveNurse, Date> startC;
    @FXML
    private TableColumn<SaveNurse, Date> endC;
       ObservableList<SaveNurse> listS = FXCollections.observableArrayList();
    @FXML
    private Button notbtn;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         SaveNurseService SM=new SaveNurseService();
        try {
            listS.addAll(SM.readAll());
            System.out.println(listS.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        startC.setCellValueFactory(new PropertyValueFactory<SaveNurse, Date>("date_begin"));
        endC.setCellValueFactory(new PropertyValueFactory<SaveNurse, Date>("date_end"));
        
        TableSubsData.setItems(listS);
       
        if(endC.getCellData(1)==Calendar.getInstance().getTime())
        {
            System.out.println("ALERT , YOUR ACCOUNT IS ABOUT TO EXPIRE!!!!!");       
            JOptionPane.showMessageDialog(null, "Your subscription date is expired!!! ");
 
        }
        
    }    

    @FXML
    private void notifying(MouseEvent event) {
         String firstDate1 = "2020-02-28";
 
        if(TableSubsData.getItems().get(0).getDate_end().equals(firstDate1))
        {
           JOptionPane.showMessageDialog(null, "Your subscription date is expired!!! ");
        }   
         {
                       JOptionPane.showMessageDialog(null, "You still have enough time to renew your subscription");

        }
    }

    @FXML
    private void returnTo(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("TestGarderie.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                /*HomeController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
               ctc.setpassword(password_label.getText());*/
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    
}
