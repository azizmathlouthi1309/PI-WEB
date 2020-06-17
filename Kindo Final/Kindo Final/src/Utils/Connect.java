/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author moham
 */
public class Connect {

    final static String URL = "jdbc:mysql://127.0.0.1:3306/kindoo";
    final static String LOGIN = "root";
    final static String PWD = "";
    public static Connect instance = null;
    private Connection cnx;
    private Connect() {
        
        try {
            cnx = DriverManager.getConnection(URL, LOGIN, PWD);
        } catch (SQLException e) {
             System.out.println("Error !");    
        }
    }

  public static Connect getInstance() {
        if (instance == null)
        {
            instance = new Connect();
        }
        return instance;
    }
    public  Connection getConnection(){
        return cnx;
    }
}


