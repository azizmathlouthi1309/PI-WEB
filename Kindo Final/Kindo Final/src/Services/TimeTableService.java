/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import Entities.TimeTable;
import Entities.publication;
import IService.ITimeTableService;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 */
public class TimeTableService implements ITimeTableService{

    private Connection con;
        private Statement ste;

    public TimeTableService() {
        con = DataBase.getInstance().getConnection();
    
}
    
    @Override
    public void add(TimeTable t) throws SQLException {
        ste = con.createStatement();
        String requeteInsert ="Insert into time_table (`activity1`,`activity2`,`activity3`,`activity4`,`class`,`date`,`jour`) values('"+t.getActivity1()+"','"+t.getActivity2()+"','"+t.getActivity3()+"','"+t.getActivity4()+"','"+t.getClasse()+"','"+t.getDate()+"','"+t.getJour()+"')";
        ste.executeUpdate(requeteInsert);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    @Override
    public boolean delete(int id) throws SQLException {
        if (exist(id))
     {  
        ste = con.createStatement();
        String requetedelete="delete from time_table where id="+id;
        ste.execute(requetedelete);
        return true;
     }
     else 
     {
      System.out.println("not here");
      return false ;
     }
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public boolean update(int id,String activity1, String activity2, String activity3, String activity4) throws SQLException {
        
         if (exist(id))
           {
        ste = con.createStatement();
        String requeteUpadte="update time_table set activity1='"+activity1+"' ,activity2='"+activity2+"',activity3='"+activity3+"',activity4='"+activity4+"' where id='"+id+"' ";
        ste.executeUpdate(requeteUpadte);
       
             return true ;}
         else 
             return false ;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TimeTable> readAll() throws SQLException {
    List<TimeTable> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from time_table");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String activity1=rs.getString("activity1");              
               String activity2=rs.getString("activity2");
               String activity3=rs.getString("activity3");
               String activity4=rs.getString("activity4");
               String classe=rs.getString("class");
               String date=rs.getString("date");
               int jour=rs.getInt("jour");
               TimeTable t=new TimeTable(id, activity1, activity2, activity3,activity4 ,classe,date,jour);
     arr.add(t);
     }
    return arr;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean exist(int id) throws SQLException {
            if(getById(id)!=null)
        {
            return true;
        }
        return false;
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeTable getById(int id) throws SQLException {
        
            ste=con.createStatement();
        String query="select * from time_table where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            TimeTable a=new TimeTable(rs.getInt("id"),rs.getString("activity1"),rs.getString("activity2"),rs.getString("activity3"),rs.getString("activity4"));
            return a;
        }
        return null;
        

    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       

    @Override
    public boolean delete(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
  
    public List<TimeTable> getTimetable(String classe,String date) throws SQLException{
        return readAll().stream().filter(t->t.getDate().equals(date)).filter(t->t.getClasse().equals(classe)).collect(Collectors.toList());
        
    }
    public List<TimeTable> getTimetablefinal(String classe,String date,int jour) throws SQLException{
        return readAll().stream().filter(t->t.getDate().equals(date)).filter(t->t.getClasse().equals(classe)).filter(t->t.getJour()==jour).collect(Collectors.toList());
        
    }
    public int updatefinal(int jour,String nomclass,String date,String activity1, String activity2, String activity3, String activity4) throws SQLException
    {   for(TimeTable t :getTimetablefinal(nomclass, date, jour))
    {
        ste = con.createStatement();
        String requeteUpadte="update time_table set activity1='"+activity1+"' ,activity2='"+activity2+"',activity3='"+activity3+"',activity4='"+activity4+"' where jour='"+jour+"'and date='"+date+"'and class='"+nomclass+"' ";
       // System.out.println(t.getActivity1());
        return ste.executeUpdate(requeteUpadte);
               
    }
    return 0;
    }
}
