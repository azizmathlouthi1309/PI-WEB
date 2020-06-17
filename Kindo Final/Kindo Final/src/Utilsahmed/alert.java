/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilsahmed;

import javafx.scene.control.Alert;

/**
 *
 * @author Ahmed
 */
public class alert {
    public void showalertwarning(String msg)
    {
        Alert alert1 = new Alert(Alert.AlertType.WARNING);
                 alert1.setTitle("Fail");
                 alert1.setHeaderText("Error");
                 alert1.setContentText(msg);
                 alert1.showAndWait();
    }
    public void showalertinformation(String msg)
    {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                 alert1.setTitle("succes");
                 alert1.setHeaderText("Success");
                 alert1.setContentText(msg);
                 alert1.showAndWait();
    }
}
