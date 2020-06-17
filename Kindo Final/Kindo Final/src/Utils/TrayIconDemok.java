/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author khaoula
 */
import java.awt.*;
import java.awt.TrayIcon.MessageType;
public class TrayIconDemok {
    public static void main(int i) throws AWTException, InterruptedException {
        if (SystemTray.isSupported()) {
            TrayIconDemok td = new TrayIconDemok();
            td.displayTray(i);
        } else {
            System.err.println("System tray not supported!");
        }
    }

    public void displayTray(int i) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("notify.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);
 if (i== 1 ){
    trayIcon.displayMessage("Event Ajouté" ,"L'event a ete ajouté avec succés", MessageType.INFO);
    
         
 }
 if (i== 0 ){
     trayIcon.displayMessage("Event Supprimé" ,"L'Event a ete suppmrimé avec succés", MessageType.INFO);
          
 }
          
 }
       
    
    
    }


