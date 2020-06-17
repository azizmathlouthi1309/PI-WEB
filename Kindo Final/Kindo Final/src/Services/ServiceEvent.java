/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.awt.AWTException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import Entities.event;
import Interfaces.IeventService;
import Utils.DataBase;
import Utils.TrayIconDemo;

/**
 *
 * @author khaoula
 */
public class ServiceEvent implements IeventService<event>{
    private Connection con;
    private Statement ste;
    ArrayList<event> events=new ArrayList<>();
    
    public List<event> getEvent()
    {
        return events;
    }

    public ServiceEvent() {
        con = DataBase.getInstance().getConnection();
    }
    @Override
    public void add(event e) throws SQLException {
     //PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`event` ( `name`, `description`, `date_begin`, `date_end`) VALUES ( ?, ?, ?, ?);");
   PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`event`  ( name, description, date, hour_begin, hour_end, capacity) VALUES ( ?, ?, ?, ?, ?, ?);"); 
   
    pre.setString(1, e.getName());
    pre.setString(2, e.getDescription());
    pre.setDate(3, e.getDate());
    pre.setInt(4, e.getHour_begin());
    pre.setInt(5, e.getHour_end());
    pre.setInt(6, e.getCapacity());
    int rowsInserted = pre.executeUpdate();
if (rowsInserted > 0) {
    System.out.println("A new event was inserted successfully!");
}
    }

    @Override
    public boolean cancel(event e) throws SQLException {
       String sql="DELETE from event where id=? ";
       PreparedStatement pre=con.prepareStatement(sql);
       pre.setInt(1, e.getId());
       int rowsDeleted = pre.executeUpdate();
     if (rowsDeleted > 0) {
    System.out.println("An event was canceled successfully!");
}

return true;
    }

    @Override
    public boolean update(Date date,String name , String description,int hour_begin, int hour_end,int capacity) throws SQLException {

String sql= "UPDATE event SET name=?, description=?, date=?, hour_begin=?, hour_end=?, capacity=? WHERE name=?";
         PreparedStatement pre=con.prepareStatement(sql);
    pre.setString(1, name);
    pre.setString(2, description);
    pre.setDate(3, date);
    pre.setInt(4, hour_begin);
    pre.setInt(5, hour_end);
    pre.setInt(6, capacity);
    pre.setString(7, name);
    pre.executeUpdate();

return true;
   //         String sql = "UPDATE event SET   name='"+e.getName()+"' , description='"+e.getDescription()+"' , date='"+e.getDate()+"' WHERE id='"+e.getId()+"' ";
//    try
//    {
//        ste = con.createStatement();
//        ste.executeUpdate(sql);
//        System.out.println("An existing event was updated successfully!");
//    }
//    catch(SQLException ex)
//            {
//                System.out.println("errr");
//            } 

    }

    @Override
    public List<event> readAll() throws SQLException {
       List<event> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from event ");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String name=rs.getString("name");
               String description=rs.getString("description");
               Date date=rs.getDate("date");
               int hour_begin=rs.getInt("hour_begin");
               int hour_end=rs.getInt("hour_end");
               int capacity=rs.getInt("capacity");
               
               event e=new event(id, name, description, date, hour_begin, hour_end, capacity);
     arr.add(e);
     }
    return arr;
    }

//    @Override
//    public List<event> search(int id1) {
//       List<event> arr=new ArrayList<>();
//        String sql="SELECT * FROM event WHERE id="+id1+"";
//        try {
//            ste=con.createStatement();
//            ResultSet rs=ste.executeQuery(sql);
//            while(rs.next()){
//               //System.out.println("Event: id of event = "+rs.getInt(1)+" , name : " + rs.getString (2)+",Descripition : " + rs.getString(3) + ",Date : "+rs.getString(4));
//               int id=rs.getInt(1);
//               String name=rs.getString("Name");
//               String description=rs.getString("Description");
//               Date date=rs.getDate("Date");
//                int hour_begin=rs.getInt("hour_begin");
//               int hour_end=rs.getInt("hour_end");
//               int capacity=rs.getInt("Capacity");
//               
//               event e=new event(id, name, description, date, hour_begin, hour_end, capacity);
//               arr.add(e);
//            }
//        } catch (SQLException ex) {
//            System.out.println("not exist ");
//        }
//
//return arr;
//    }

    @Override
    public Set<event> sort() {
        Set<event> set = new TreeSet<>((a,b)->a.getName().compareTo(b.getName()));
        set.addAll(getEvent());
    
    return set;
}


    @Override
    public boolean delay(String name, int hour_begin, int hour_end) throws SQLException {
String sql= "UPDATE event SET  hour_begin=?, hour_end=? WHERE name=?";
         PreparedStatement pre=con.prepareStatement(sql);
    pre.setInt(1, hour_begin);
    pre.setInt(2, hour_end);
    pre.setString(3, name);
    int rowsUpdated = pre.executeUpdate();
if (rowsUpdated > 0) {
    System.out.println("An existing event was updated successfully!");
}
return true;
    }

    @Override
    public List<event> search1(String n) throws SQLException {

if(readAll().stream().filter(e->e.getName().startsWith(n)).collect(Collectors.toList()).isEmpty())
{
    System.out.println("event not exist");
}
return readAll().stream().filter(e->e.getName().startsWith(n)).collect(Collectors.toList());
    }

    @Override
    public List<event> search2(String n) throws SQLException {
if(readAll().stream().filter(e->e.getDescription().startsWith(n)).collect(Collectors.toList()).isEmpty())
{
    System.out.println("event not exist");
}
return readAll().stream().filter(e->e.getDescription().startsWith(n)).collect(Collectors.toList());    }
}

//          List<event> arr=new ArrayList<>();
//        String sql="SELECT * FROM event WHERE id="+name+"";
//        try {
//            ste=con.createStatement();
//            ResultSet rs=ste.executeQuery(sql);
//            while(rs.next()){
//               //System.out.println("Event: id of event = "+rs.getInt(1)+" , name : " + rs.getString (2)+",Descripition : " + rs.getString(3) + ",Date : "+rs.getString(4));
//               int id=rs.getInt(1);
//               String Name=rs.getString("Name");
//               String description=rs.getString("Description");
//               Date date=rs.getDate("Date");
//                int hour_begin=rs.getInt("hour_begin");
//               int hour_end=rs.getInt("hour_end");
//               int capacity=rs.getInt("Capacity");
//               
//               event e=new event(id, Name, description, date, hour_begin, hour_end, capacity);
//               arr.add(e);
//            }
//        } catch (SQLException ex) {
//            System.out.println("not exist ");
//        }
//
//return arr;