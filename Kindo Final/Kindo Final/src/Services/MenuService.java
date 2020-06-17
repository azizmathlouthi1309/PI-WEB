/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.MenuInterface;
import Entities.Menu;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nada
 */
public class MenuService implements MenuInterface<Menu> {

    private Connection con;
    private Statement ste;

    public MenuService() {
        con = DataBase.getInstance().getConnection();
}
  @Override
    public Menu getById(int id) throws SQLException {
        
        ste=con.createStatement();
        String query="select * from menu where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Menu m;
            m = new Menu(rs.getInt("id"),rs.getString("date_day"),rs.getString("plate1"),rs.getString("plate2"),rs.getString("plate3"));
            return m;
        }
        return null;
    }    
    
    @Override
    public void ajouter(Menu m) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `kindo`.`menu` (`date_day`, `plate1`, `plate2`, `plate3`) VALUES ( '" + m.getDate_day()+ "', '" + m.getPlate1() + "', '" + m.getPlate2() + "', '" + m.getPlate3() + "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from menu where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        System.out.println("doesnt exist!!!!");
        return false; 
        
    }
    
    
    
    @Override
    public boolean update(int id, String date_day, String plate1, String plate2, String plate3) throws SQLException {
  if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE menu set date_day='"+date_day+"',plate1='"+plate1+"',plate2='"+plate2+"',plate3='"+plate3+"' where id="+id;
         ste.execute(query);
         return true;
        }
        System.out.println("User does not exist");
        return false;
    }

    @Override
    public List<Menu> readAll() throws SQLException {
    List<Menu> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from menu");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String date_day=rs.getString("date_day");
               String plate1=rs.getString("plate1");
               String plate2=rs.getString("plate2");
               String plate3=rs.getString("plate3");

        
               Menu m=new Menu(id, date_day, plate1, plate2, plate3);
     arr.add(m);
     }
    return arr;
    }

    @Override
    public Boolean exist(int id) throws SQLException {
        if(getById(id)!=null)
        {
            return true;
        }
        return false;

    }

    public void update(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

  



}
  
    

