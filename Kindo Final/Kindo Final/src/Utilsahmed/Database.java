/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilsahmed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ahmed
 */
public class Database {
     String url = "jdbc:mysql://localhost:3306/kindo";
     String login = "root";
     String pwd = "";
    public  static Database db;
    public Connection con;
    private Database() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static Database getInstance()
    {if(db==null)
        db=new Database();
    return db;
    }     
    
}
