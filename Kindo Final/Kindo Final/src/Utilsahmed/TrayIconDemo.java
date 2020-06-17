/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilsahmed;

/**
 *
 * @author Ahmed
 */
import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo {

    public static void main(int i) throws AWTException {
        if (SystemTray.isSupported()) {
            TrayIconDemo td = new TrayIconDemo();
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
    trayIcon.displayMessage("Enfant Ajouté" ,"L'enfant a ete ajouté avec succés", MessageType.INFO);
    
         
 }
 if (i== 0 ){
     trayIcon.displayMessage("Enfant Supprimé" ,"L'enfant a ete suppmrimé avec succés", MessageType.INFO);
          
 }
  if (i== 3 ){
     trayIcon.displayMessage("Reclamation" ,"une reclamation a ete ajouté", MessageType.INFO);
          
 }
  if (i== 4 ){
     trayIcon.displayMessage("Avis" ,"Avis a ete ajouté", MessageType.INFO);
          
 }
       
    
    
    }
}