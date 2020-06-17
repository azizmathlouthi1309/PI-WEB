/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Activity;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import Entities.Classe;
import Entities.Teacher;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import IService.IClasseService;
import java.sql.PreparedStatement;

/**
 *
 * @author maiss
 */
public class ClassService implements IClasseService <Classe>{
     
    private Connection con;
    private Statement ste;

    public ClassService() {
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void add (Classe t) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        ste = con.createStatement();
        String requeteInsert ="Insert Into class (`id`, `name`, `nb_child`,`level`,`teacher_name`) values(Null,'"+t.getNom()+"','"+t.getNb_child()+"','"+t.getLevel()+"','"+t.getTeacher_name()+"')";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(int id) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if (exist(id))
     
     {  ste = con.createStatement();
        String requetedelete="delete from class where id='"+id+"' ";
        ste.executeUpdate(requetedelete);
         return true;
     }
     else 
         return false ;
    }

    @Override
    public boolean update(int id,String name,int nb_child,int level,String teacher_name ) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         if (exist(id))
           {
        ste = con.createStatement();
        String requeteUpadte="update class set name='"+name+"' ,nb_child='"+nb_child+"' ,level='"+level+"',teacher_name='"+teacher_name+"' where id='"+id+"' ";
     
        ste.executeUpdate(requeteUpadte);
       // ste.executeUpdate(requeteUpadte);
        return true ;}
         else 
             return false ;
    }

    @Override
    public List<Classe> readAll() throws SQLException {
     
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       List<Classe> arr=new ArrayList<>();
    ste=con.createStatement();
   
    ResultSet rs=ste.executeQuery("SELECT * FROM `class`");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("name");
               int nb_child=rs.getInt("nb_child");
               int level=rs.getInt("level");
               String teacher=rs.getString("teacher_name");
               Classe c=new Classe(id,nom, nb_child,level,teacher);
     arr.add(c);
     }
    return arr;
    }
      public List<Classe> readAllbyTeacher(String n) throws SQLException {
     
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       List<Classe> arr=new ArrayList<>();
    ste=con.createStatement();
    PreparedStatement pre=con.prepareStatement("select * from class where teacher_name=?");
    pre.setString(1,n);
    ResultSet rs=pre.executeQuery();
     while (rs.next()) {                
               int ide=rs.getInt(1);
               String nom=rs.getString("name");
               int nb_child=rs.getInt("nb_child");
               int level=rs.getInt("level");
               String teacher=rs.getString("teacher_name");
               Classe c=new Classe(ide,nom, nb_child,level,teacher);
     arr.add(c);
     }
    return arr;
    }

    @Override
    public Boolean exist(int id) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     if(getById(id)!=null)
        {
            return true;
        }
        return false;
    }
    @Override
    public Classe getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from class where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Classe classe=new Classe(rs.getInt("id"),rs.getString("name"),rs.getInt("nb_child"),rs.getInt("level"),rs.getString("teacher_name"));
            return classe;
        }
        return null;
    }
    
  /* public List<Activity> getActivity(String tbl_name) throws SQLException {
            ste=con.createStatement();
            String query = "select * from " + tbl_name;
            ResultSet rs=ste.executeQuery(query);
                List<Activity> list = new ArrayList<>();
                while (rs.next()) {
                    Activity activity = new Activity();
                    
                    activity.setName(rs.getString("particular"));
                   

                    list.add(activity);
                }

                return list;*/

    @Override
    public List<Classe> search(String s) throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       if(readAllbyTeacher(s).stream().filter(e->e.getNom().startsWith(s)).collect(Collectors.toList()).isEmpty())
          {
            System.out.println("Class does not exist");
          }
            return readAllbyTeacher(s).stream().filter(e->e.getNom().startsWith(s)).collect(Collectors.toList());
          }

    @Override
    public void treeClass() throws SQLException {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       Set<Classe> set=new TreeSet<>((a,b)->a.getNom().compareTo(b.getNom()));
        set.addAll(readAll());
        set.stream().forEach(e->System.out.println(e));
    }
    public int getidbyname(String nom) throws SQLException
    {
        for(Classe c:readAll())
        {
            if(c.getNom().equals(nom))
            {
                return c.getId();
            }
        }
        return -1;
            
    }
    
    
    }


    

