/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Avis;
import Entities.Reclamation;
import Interfaces.IAvis;
import Utils.DataBase;
import Utilsahmed.TrayIconDemo;
import Utilsahmed.alert;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public  class ServiceAvis implements IAvis{
    
    private Connection con;
    private Statement ste;

    public ServiceAvis() {
        con = DataBase.getInstance().getConnection();

    }
      public List<Avis> readAll() throws SQLException {
    List<Avis> avv=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from view");
     while (rs.next()) {                
               int id=rs.getInt(1);
               int nb_stars=rs.getInt("nb_stars");
               String subject=rs.getString("subject");
               String message=rs.getString("message");
                String name=rs.getString("SenderName");
               
               Avis av= new Avis(id, nb_stars,subject,message,name);
     avv.add(av);
     }
    return avv;
    }
    @Override
    public void ajouterAvis(Avis v) throws SQLException {
        alert a=new alert();
    if(v.getNb_stars()<=5)
    {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`view` ( `nb_stars`, `subject`, `message`, `SenderName`) VALUES ( ?, ?, ?,?);");
     try {
          a.showalertinformation("Your view has been added");

             TrayIconDemo.main(4);
         } catch (AWTException ex) {
            
         }
    pre.setInt(1, v.getNb_stars());
    pre.setString(2, v.getSubject());
    pre.setString(3, v.getMessage());
    pre.setString(4, v.getName());
    pre.executeUpdate();
    }
    else
    {
        a.showalertwarning("stars number is out of bounds");
        System.out.println("nombre des etoiles est incorrecte");
                
    }
    }
     @Override
    public void afficherAvis() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public Boolean exist(int id) throws SQLException {
        if(getById(id)!=null)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimerAvis(int id) throws SQLException {
       ste=con.createStatement();
        String query="delete from view where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        return false;    }
    @Override
    public Avis getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from view where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Avis av;
            av = new Avis(rs.getInt("id"),rs.getInt("nb_stars"),rs.getString("subject"),rs.getString("message"));
            return av;
        }
        return null;
    }

  
   

    public boolean updateAvis(int id, int nb_stars, String subject, String message) throws SQLException {
               if(exist(id)&&(nb_stars<=5))
        {
        ste=con.createStatement();
         String query="UPDATE view set nb_stars='"+ nb_stars+"',subject='"+subject+"',message='"+message+"' where id="+id;
         ste.execute(query);
         return true;
        }
        System.out.println("verifier votre avis ou corriger le nombre d'etoiles");
        return false;
    }
    public double moyenneAvis() throws SQLException{
        
        ServiceAvis av = new ServiceAvis();
        List<Avis> list=readAll();
        int nb_etoiles_total=0;
        for(Avis v:list)
        {
            nb_etoiles_total=nb_etoiles_total+v.getNb_stars();            
        }
        System.out.println(list.size());
        System.out.println(nb_etoiles_total);
        return nb_etoiles_total/list.size();
    }

  

   
    

   
 
    
    
    }


    
    

