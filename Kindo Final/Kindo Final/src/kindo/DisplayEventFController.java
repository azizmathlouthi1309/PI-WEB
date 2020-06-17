/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.awt.Insets;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Entities.event;
import Entities.participation;
import Services.ServiceEvent;
import Services.ServiceParticipation;
import Services.mailService;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaoula
 */
public class DisplayEventFController implements Initializable {

    private VBox affiche = new VBox(20);
    // ScrollPane scroll = new ScrollPane();
    Group root = new Group();
    @FXML
    private ScrollPane scoll;
    @FXML
    private Label id_label;
    @FXML
    private Label password_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            HBox hbu = null;
            HBox hbu1 = null;
            HBox hbu2 = null;
            HBox hbu3 = null;
            ServiceEvent se = new ServiceEvent();
            Button bt;
            Button bt1;
            Button bt2;
            List<event> liste = se.readAll();
            Iterator<event> iterator = liste.iterator();
            for (int i = 0; i < liste.size(); i++) {
                hbu = new HBox(22);
                hbu1 = new HBox(22);
                hbu2 = new HBox(22);
                hbu3 = new HBox(22);
                if (iterator.hasNext()) {

                    int id = liste.get(i).getId();
                    Label name = new Label();
                    Label tire = new Label("-");
                    Label hour_begin = new Label();
                    Label hour_end = new Label();
                    Label date = new Label();
                    Label desc = new Label();
                    name.setText(liste.get(i).getName());
                    desc.setText(liste.get(i).getDescription());
                    hour_begin.setText(String.valueOf(liste.get(i).getHour_begin() + "AM"));
                    hour_end.setText(String.valueOf(liste.get(i).getHour_end() + "AM"));
                    date.setText(String.valueOf(liste.get(i).getDate()));
                    bt = new Button("Participate");
                    bt1 = new Button("Cancel");
                    bt2 = new Button("more");
                    bt2.setStyle("-fx-background-color: transparant;");
                    //     hbu.setMargin(hbu, new Insets(100,50,50,100));
                    hbu.getChildren().add(name);
                    hbu1.getChildren().add(date);
                    //hbu.getChildren().add(bt2);
                    hbu2.getChildren().add(hour_begin);
                    hbu2.getChildren().add(tire);
                    hbu2.getChildren().add(hour_end);

                    hbu3.getChildren().add(bt);
                    hbu3.getChildren().add(bt1);
                    //scroll.setContent(affiche);

                    bt1.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            ServiceParticipation sp = new ServiceParticipation();
                            participation lp = new participation(Integer.parseInt(id_label.getText()), id);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("participation");
                            alert.setHeaderText("Cancel participation ?");
                            alert.setContentText("");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                try {
                                    if (sp.exist(lp) != 0) {

                                        sp.delete(lp);

                                    }
                                  
                                } catch (SQLException ex) {
                                    System.out.println(ex);
                                }

                            }
                        }
                    });

                    bt.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Label waiting = new Label("waiting");

                            ServiceParticipation sp = new ServiceParticipation();
                            participation lp = new participation(Integer.parseInt(id_label.getText()), id);

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("participation");
                            alert.setHeaderText("Do you want participate to this event??");
                            alert.setContentText("");
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                try {
                                    if (sp.exist(lp) == 0) {

                                        sp.add(lp);
                                         

                                    }
                                      else
                                    {
                                    Alert e=new Alert(Alert.AlertType.WARNING);
			e.setTitle("What??");
			e.setHeaderText(null);
			e.setContentText("You are participate!!!!");
			Optional <ButtonType> action = e.showAndWait();
                                    
                                    }
                                    
                                } catch (SQLException ex) {
                                    System.out.println(ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(DisplayEventFController.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                        }

                    });
                }
                affiche.getChildren().add(hbu);
                affiche.getChildren().add(hbu1);
                affiche.getChildren().add(hbu2);
                affiche.getChildren().add(hbu3);
                // affiche.getChildren(scroll);
                //affiche.getChildren().add(scroll);
                scoll.setContent(affiche);
                scoll.setHbarPolicy(ScrollBarPolicy.ALWAYS);
                //root.getChildren().add(affiche, scroll);

            }
        } catch (SQLException ex) {
            System.out.println(ex);
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

    @FXML
    private void goback(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("Menu.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                
                MenuController ctc=loader.getController();
               ctc.setid(Integer.parseInt(id_label.getText()));
                 ctc.setpass(password_label.getText());
              Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

}
