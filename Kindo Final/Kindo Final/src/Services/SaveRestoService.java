/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Interfaces.SaveRestoInterface;
import Utils.DataBase;
import Entities.SaveResto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author nada
 */
public class SaveRestoService implements SaveRestoInterface<SaveResto>  {
     private Connection con;
    private Statement ste;

    public SaveRestoService() {
        con = DataBase.getInstance().getConnection();
}

    @Override
    public SaveResto getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from save_resto where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            SaveResto s;
            s = new SaveResto(rs.getInt("id"),rs.getString("date_begin"),rs.getString("date_end"));
            return s;
        }
        return null;    
    }

    
    @Override
    public Boolean exist(int id) throws SQLException {
        return getById(id)!=null;
    }

    @Override
    public void ajouter(SaveResto s) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `kindo`.`save_resto` (`date_begin`, `date_end`) VALUES ( '" + s.getDate_begin()+ "', '" + s.getDate_end() + "');";
        
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from save_resto where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        
    return false;
    }

    @Override
    public boolean update(int id, String date_begin, String date_end) throws SQLException {
         if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE save_resto set date_begin='"+date_begin+"',date_end='"+date_end+"' where id="+id;
         ste.execute(query);
         return true;
        }
        System.out.println("User does not exist");
        return false;
    }

    @Override
    public List<SaveResto> readAll() throws SQLException {
 List<SaveResto> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from save_resto");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String date_begin=rs.getString("date_begin");
               String date_end=rs.getString("date_end");
               SaveResto s=new SaveResto(id, date_begin, date_end);
                arr.add(s);
                 Collections.sort(arr, new Comparator<SaveResto>(){
         @Override
         public int compare(SaveResto o1,SaveResto o2){
             return o1.getDate_end().compareTo(o2.getDate_end());
         }});
     }        
    return arr;
    }
}

