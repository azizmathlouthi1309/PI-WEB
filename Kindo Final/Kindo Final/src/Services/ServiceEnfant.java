/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import Interfaces.IEnfant;
import Utils.DataBase;
import Utilsahmed.Mail;
import Utilsahmed.Notification;
import Utilsahmed.TrayIconDemo;
import Utilsahmed.alert;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;

/**
 *
 * @author Ahmed
 */
public class ServiceEnfant implements IEnfant {
     private Connection con;
    private Statement ste;

    public ServiceEnfant() {
        con = DataBase.getInstance().getConnection();

    }

     @Override
    public int displaynbrchild(int level) throws SQLException{
     int i = 0 ;
       String req="select * from class where nb_child < 25 and level = "+level+" LIMIT 1";
        try {
            Statement stm = con.createStatement();
            ResultSet rs=stm.executeQuery(req);
       
     while(rs.next())
    
       
        i = rs.getInt("id");
       
      
  
        } catch (SQLException ex) {
          
        }
        return i ;
    }

   
    public  void ajouterEnfant(Enfant f) throws SQLException {
        alert a=new alert();
          ServiceEnfant enf= new ServiceEnfant(); 
         
     PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`child` ( `lastname`, `firstname`, `level`, `age`, `class_id`, `photo`) VALUES ( ?, ?, ?, ?,? ,?);");
             ste=con.createStatement();
         
         String query="UPDATE class set nb_child = nb_child+1 where id ="+displaynbrchild(f.getNiveau());
         
       
    pre.setString(1, f.getNom());
    pre.setString(2, f.getPrenom());
    pre.setInt(3, f.getNiveau());
    pre.setInt(4, f.getAge());
    pre.setInt(5, displaynbrchild(f.getNiveau()));
      pre.setString(6, f.getPhoto());
 if(displaynbrchild(f.getNiveau()) > 0)
        {
     try {
         Notification note=new Notification();
             note.shownotification(1);
             ste.execute(query);
              pre.executeUpdate();
              a.showalertinformation("child added check mail");
         } 
     catch (AWTException ex) {
            System.out.println(ex);
         }
     ClassService cs=new ClassService();
              try {
                  Mail m=new Mail("ahmedrais988@gmail.com", "mnhsairj","ahmedrais20200@gmail.com", "Inscription Kindo", "<h2>félicitation !votre enfant a eté affecté au classe  </h3>"+cs.getById(displaynbrchild(f.getNiveau())).getNom());
                   }
              catch (Exception ex) {
                  Logger.getLogger(ServiceEnfant.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
            else 
                {
                        a.showalertwarning("oops all Classes are full");
                }
    }
    
    public boolean updateEnfant(int id,String nom, String prenom, int niveau, int age,int class_id) throws SQLException {
                if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE child set lastname='"+nom+"',firstname='"+prenom+"',level='"+niveau+"',age='"+age+"',class_id='"+class_id+"' where id="+id;
         ste.execute(query);
         return true;
        }
        System.out.println("l' Enfant n'existe pas");
        return false;
    }

    @Override
    public boolean supprimerEnfant(int id) throws SQLException {
          alert a=new alert();
   ste=con.createStatement();
        String query="delete from child where id="+id;
        String query1="UPDATE class inner join child set nb_child = nb_child-1 where class.id = child.class_id";
     
        if(exist(id))
        {
            ste.execute(query);
             ste.execute(query1);
                 try {
             TrayIconDemo.main(0);
         } catch (AWTException ex) {
            
         }
             a.showalertinformation("child deleted");
            return true;
        }
        a.showalertwarning("error child doesn't exist");
        return false;    }

    public List<Enfant> readAll() throws SQLException {
    List<Enfant> en=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from child");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("lastname");
               String prenom=rs.getString("firstname");
               String photo=rs.getString("photo");
               int niveau=rs.getInt("level");
               int age=rs.getInt("age");
               int class_id=rs.getInt("class_id");
               Enfant enf= new Enfant(id,nom,prenom,niveau,age,class_id,photo);
     en.add(enf);
     }
    return en;
    }

public List<Enfant> getenfant(int id) throws SQLException {
    List<Enfant> en=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from child where id="+id);
     while (rs.next()) {                
              
               String nom=rs.getString("lastname");
               String prenom=rs.getString("firstname");
               String photo=rs.getString("photo");
               int niveau=rs.getInt("level");
               int age=rs.getInt("age");
               int class_id=rs.getInt("class_id");
               Enfant enf= new Enfant(id,nom,prenom,niveau,age,class_id,photo);
     en.add(enf);
     }
    return en;
    }
    @Override
    public Enfant getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from child where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Enfant enf;
            enf = new Enfant(rs.getInt("id"),rs.getString("lastname"),rs.getString("firstname"),rs.getInt("level"),rs.getInt("age"),rs.getInt("class_id"),rs.getString("photo"));
            return enf;
        }
        return null;
    }


    @Override
    public Boolean exist(int id) throws SQLException {
         if(getById(id)!=null)
        {
            return true;
        }
        return false;
    
}

    @Override
    public void trierEnfants() throws SQLException {
        Set<Enfant> set=new TreeSet<>((a,b)->a.getNom().compareTo(b.getNom()));
        List list=readAll();
        set.addAll(list);
        set.stream().forEach(e->System.out.println(e));
    }

    @Override
    public void rechercherEnfants(String enf) throws SQLException {
        readAll().stream().filter(e->e.getNom().startsWith(enf)).forEach(e->System.out.println(e));
    }
     public int getclassId(int childid) throws SQLException
     {
         ServiceEnfant se=new ServiceEnfant();
         ClassService c=new ClassService();
         for(Enfant e:se.readAll())
         {
             if(e.getId()==childid)
             {
                 return e.getClass_id();
             }
         }
          return -1;
     }
     public String getClassName(int childid) throws SQLException
    {
        ServiceEnfant se=new ServiceEnfant();
         ClassService c=new ClassService();
         Classe c1=c.getById(getclassId(childid));
         return c1.getNom();
    }



  
     
   



    
}
