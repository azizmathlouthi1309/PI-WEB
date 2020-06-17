/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
public class AddParticipationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ServiceParticipation se = new ServiceParticipation();
    ObservableList<participation> data = FXCollections.observableArrayList();
    ArrayList<participation> liste = new ArrayList<>();
    @FXML
    private TableView<participation> table;
    @FXML
    private TableColumn pname;
    @FXML
    private TableColumn ename;
    @FXML
    private TableColumn state;
    @FXML
    private TableColumn validate;
    @FXML
    private TableColumn refuse;
    @FXML
    private Button backto;

    public void table() {

        try {
            data.addAll(se.readAll());
            System.out.println(data.size());
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        pname.setCellValueFactory(new PropertyValueFactory<event, Integer>("parent_id"));
        ename.setCellValueFactory(new PropertyValueFactory<event, Integer>("event_id"));
        state.setCellValueFactory(new PropertyValueFactory<event, String>("state"));

        table.setItems(data);
    }

    private void addButtonToTable() {
        ServiceParticipation sp = new ServiceParticipation();
        //TableColumn<participation, Void> colBtn = new TableColumn("validate");
        Callback<TableColumn<participation, Void>, TableCell<participation, Void>> cellFactory
                = new Callback<TableColumn<participation, Void>, TableCell<participation, Void>>() {
            @Override
            public TableCell<participation, Void> call(final TableColumn<participation, Void> param) {
                final TableCell<participation, Void> cell = new TableCell<participation, Void>() {
                    private final Button btn = new Button("validate");

                    {
                        btn.setStyle("-fx-background-color:#5F9EA0 ;\n"
                                + "    -fx-background-radius: 30;\n"
                                + "    -fx-background-insets: 0;\n"
                                + "    -fx-text-fill: white;");
                        btn.setOnAction(
                                (ActionEvent event) -> {
                                    participation p = getTableView().getItems().get(getIndex());
                                    int selectedIndex = table.getSelectionModel().getSelectedIndex();
                                    try {
                                          sp.validate(table.getItems().remove(selectedIndex));
                                          mailService.sendMail("khaoulamekni59@gmail.com");
                                        // sr.supprimerReclamation(p.getId());
//                                        pname.setCellValueFactory(new PropertyValueFactory<event, Integer>("parent_id"));
//                                        ename.setCellValueFactory(new PropertyValueFactory<event, Integer>("event_id"));
//                                        state.setCellValueFactory(new PropertyValueFactory<event, String>("state"));
//
////     idR.setVisible(false);
////       idRep.setVisible(false);
//                                        table.setItems(data); //refrechTable();
                                      
                                        table.refresh();

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
            }

        };
        validate.setCellFactory(cellFactory);

        table.getColumns().add(validate);

    }
private void addButtonToTable2() {

ServiceParticipation sp = new ServiceParticipation();
        //TableColumn<participation, Void> colBtn = new TableColumn("validate");
        Callback<TableColumn<participation, Void>, TableCell<participation, Void>> cellFactory
                = new Callback<TableColumn<participation, Void>, TableCell<participation, Void>>() {
            @Override
            public TableCell<participation, Void> call(final TableColumn<participation, Void> param) {
                final TableCell<participation, Void> cell = new TableCell<participation, Void>() {
                    private final Button btn1 = new Button("refuse");

                    {
                        btn1.setStyle("-fx-background-color: linear-gradient(#ff5400, #be1d00);\n"
                                        + "    -fx-background-radius: 30;\n"
                                        + "    -fx-background-insets: 0;\n"
                                        + "    -fx-text-fill: white;");
                        btn1.setOnAction(
                                (ActionEvent event) -> {
                                    participation p = getTableView().getItems().get(getIndex());
                                    int selectedIndex = table.getSelectionModel().getSelectedIndex();
                                    try {

                                         sp.delete(table.getItems().remove(selectedIndex));
                                        // ObservableList<participation> data = FXCollections.observableArrayList();
//                                        pname.setCellValueFactory(new PropertyValueFactory<event, Integer>("parent_id"));
//                                        ename.setCellValueFactory(new PropertyValueFactory<event, Integer>("event_id"));
//                                        state.setCellValueFactory(new PropertyValueFactory<event, String>("state"));
//
////     idR.setVisible(false);
////       idRep.setVisible(false);
//                                        table.setItems(data); //refrechTable();
table.refresh();

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
                            setGraphic(btn1);
                        }
                    }
                };
                return cell;
            }

        };
        refuse.setCellFactory(cellFactory);

        table.getColumns().add(refuse);
 }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        table();
        addButtonToTable();
        addButtonToTable2();

    }

    @FXML
    private void backto(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("DisplayEvent.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = loader.load();
                Scene homescene=new Scene(root);
                    Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
                    window.setScene(homescene);
                    window.show();
    }

}
