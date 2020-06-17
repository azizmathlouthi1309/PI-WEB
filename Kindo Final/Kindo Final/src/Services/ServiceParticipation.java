/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entities.event;
import Entities.participation;
import Interfaces.IparticipationService;
import Utils.DataBase;

/**
 *
 * @author khaoula
 */
public class ServiceParticipation implements IparticipationService<participation>{
    private Connection con;
    private Statement ste;
    private List<participation> list=new ArrayList<>();

    public ServiceParticipation() {
        con = DataBase.getInstance().getConnection();
    }

    public List<participation> getList() {
        return list;
    }
    
   
    @Override
    public List<participation> readAll() throws SQLException {
         List<participation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from participation");
     while (rs.next()) {                
               int parent_id=rs.getInt(1);
               int event_id=rs.getInt(2);
               String state=rs.getString("state");
               participation p =new participation(parent_id,event_id,state);
     arr.add(p);
     }
    return arr;
    }

    @Override
    public int capacity() throws SQLException  {
        int i=0;
       ste=con.createStatement();
    ResultSet rs=ste.executeQuery("SELECT capacity from event");
     while (rs.next()) {
     i=rs.getInt(1);
     }
     return i;
    }

    @Override
    public void add( participation p) throws SQLException {
        //PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`event` ( `name`, `description`, `date_begin`, `date_end`) VALUES ( ?, ?, ?, ?);");
   PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`participation`( parent_id,event_id) VALUES (?,?);"); 

    pre.setInt(1, p.getParent_id());
    pre.setInt(2, p.getEvent_id());
    
    int rowsInserted = pre.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("participate ");
}
    }

    @Override
    public void add() throws SQLException {
       
    }

    @Override
    public int exist(participation p) throws SQLException {
        int exist=0;
    String sql="select count(*) from participation where parent_id=? and event_id=?";
       PreparedStatement pre=con.prepareStatement(sql);
       pre.setInt(1, p.getParent_id());
        pre.setInt(2, p.getEvent_id());
        ResultSet rs=pre.executeQuery();
        
     while(rs.next())
     {
     exist=rs.getInt(1);
     }
     
     return exist;
    }

    @Override
    public boolean delete(participation p ) throws SQLException {
        String sql="DELETE from participation where event_id=? ";
       PreparedStatement pre=con.prepareStatement(sql);
       pre.setInt(1, p.getEvent_id());
       int rowsDeleted = pre.executeUpdate();
     if (rowsDeleted > 0) {
    System.out.println("cancel!");
    
    }
return true;
}

    @Override
    public boolean validate(participation p) throws SQLException {
  String sql= "UPDATE participation SET state=? WHERE state='waiting'";
         PreparedStatement pre=con.prepareStatement(sql);
    pre.setString(1, "validate");
  
    pre.executeUpdate();

return true;
}

    @Override
    public boolean refuse(participation p) throws SQLException {
     String sql= "UPDATE participation SET state=? WHERE state='validate'";
         PreparedStatement pre=con.prepareStatement(sql);
    pre.setString(1, "refuse");
  
    pre.executeUpdate();

return true;
    }
}