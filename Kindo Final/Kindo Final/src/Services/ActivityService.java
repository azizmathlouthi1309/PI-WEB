/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import Entities.Classe;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import IService.IActivityService;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author maiss
 */
public class ActivityService implements IActivityService<Activity> {
       private Connection con;
        private Statement ste;

    public ActivityService() {
        con = DataBase.getInstance().getConnection();
    
}

    @Override
    public void add(Activity t) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ste = con.createStatement();
        String requeteInsert ="Insert Into activity (`name`,`description`) values('"+t.getName()+"','"+t.getDescription()+"')";
        ste.executeUpdate(requeteInsert);
    }
    
    @Override
    public boolean delete(String id) throws SQLException {
        if (exist(id))
     {  
        ste = con.createStatement();
        String requetedelete="delete from activity where `name`='"+id+"';";
        ste.execute(requetedelete);
        return true;
     }
     else 
     {
      System.out.println("not here");
      return false ;
     }
         
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean update(String name,String description) throws SQLException {
        if (exist(name))
           {
        ste = con.createStatement();
        String requeteUpadte="update activity set name='"+name+"',description='"+description+"' where name='"+name+"'";
        ste.executeUpdate(requeteUpadte);
        return true ;}
         else 
             return false ;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> readAll() throws SQLException {
     List<Activity> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from activity");
     while (rs.next()) {                
               String name=rs.getString("name");
               String description=rs.getString("description");
               Activity c=new Activity(name,description);
     arr.add(c);
     }
    return arr;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean exist(String id) throws SQLException {
    if(getById(id)!=null)
        {
            return true;
        }
        return false;    
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Activity getById(String id)throws SQLException{
        ste=con.createStatement();
        String query="select * from activity where name='"+id+"'";
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Activity a=new Activity(rs.getString("name"),rs.getString("description"));
            return a;
        }
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Activity> search(String s) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(readAll().stream().filter(e->e.getName().startsWith(s)).collect(Collectors.toList()).isEmpty())
          {
            System.out.println("Class does not exist");
          }
            return readAll().stream().filter(e->e.getName().startsWith(s)).collect(Collectors.toList());
          }

    @Override
    public Set<Activity> tree(Activity t) throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      Set<Activity> set=new TreeSet<>((a,b)->a.getName().compareTo(b.getName()));
        set.addAll(readAll());
        return set ;
    }

      
    }
    
    

