/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javafx.scene.control.Alert;

/**
 *
 * @author Administrator
 */
public class alert {
    public void showalertwarning(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
                             alert.setTitle("Error");
                                alert.setHeaderText("");
                                    alert.setContentText(msg);
                                        alert.showAndWait();
    }
    public void showalertinfo(String msg)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                             alert.setTitle("Ok");
                                alert.setHeaderText("");
                                    alert.setContentText(msg);
                                        alert.showAndWait();
    }
}
