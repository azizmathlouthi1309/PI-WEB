/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;
import Services.MenuService;
import Entities.Menu;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author nada
 */
public class AddMenuController implements Initializable {

    @FXML
    private TextField idF;
    @FXML
    private TextField dateF;
    @FXML
    private TextField P1F;
    @FXML
    private TextField P2F;
    @FXML
    private TextField P3F;
    @FXML
    private Button addbtnAct;
    @FXML
    private Label labelid;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addON(MouseEvent event) throws SQLException{
        if(idF.getText().isEmpty()||dateF.getText().isEmpty()||P1F.getText().isEmpty()||P2F.getText().isEmpty()||P3F.getText().isEmpty())                    {
            Alert e=new Alert(Alert.AlertType.WARNING);
            e.setTitle("Error, You can't Add");
            e.setHeaderText(null);
            e.setContentText("Please complete all fields !");
            Optional <ButtonType> action = e.showAndWait();
        }
        else{
            String id1 = idF.getText();
            String date_day = dateF.getText();
            String plate1 = P1F.getText();
            String plate2 = P2F.getText();
            String plate3 = P3F.getText();
            
            int id = Integer.parseInt(id1);
            MenuService cs = new MenuService();
            Menu a = new  Menu(id,date_day,plate1,plate2,plate3);
            cs.ajouter(a);
            System.out.println("YAY");

        }
        
     
    }

    @FXML
    private void returntodisplay(MouseEvent event) throws IOException {
FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("MenuMana.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

    @FXML
    private void displaylabelerror(KeyEvent event) {
        String PATTERN="^[0-9]{0,12}$";
        Pattern patt=Pattern.compile(PATTERN);
        Matcher match=patt.matcher(idF.getText());
        if(!match.matches())
        {
            labelid.setText("ID  is incorrect , Write a number");
        }
        else
        {
            labelid.setText(null);
        }
        
    }
    
    
}
