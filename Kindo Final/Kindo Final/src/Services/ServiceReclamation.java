/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Reclamation;
import Interfaces.IReclamation;
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
import java.util.List;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 *
 * @author Ahmed
 */
public class ServiceReclamation implements IReclamation {
    private Connection con;
    private Statement ste;

    public ServiceReclamation() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public List<Reclamation> getReclamation() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean supprimerReclamation(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from complaint where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        return false;    }

    @Override
    public boolean updateReclamation(int id,String subject,String type,String message) throws SQLException {
        if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE complaint set subject='"+subject+"',type='"+type+"',message='"+message+"' where id="+id;
         ste.execute(query);
         return true;
        }
        System.out.println("votre reclamation n'existe pas");
        return false;
    }
    @Override
    public void afficherReclamation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Reclamation> readAll() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from complaint");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String type=rs.getString("type");
               String subject=rs.getString("subject");
               String message=rs.getString(6);
               int teacher_id =rs.getInt("teacher_id");
               int parent_id =rs.getInt("parent_id");
               int status=rs.getInt("status");
               String res=rs.getString("response");
               Reclamation r=new  Reclamation(id, subject, message,  teacher_id,  type,parent_id,status,res);
     arr.add(r);
     }
    return arr;
    }

 
    @Override
    public void ajouterReclamation(Reclamation r) throws SQLException {
         {
             
    PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`complaint` (`subject`, `type`, `message`) VALUES ( ?, ?, ?);");
    
    //pre.setInt(1, r.getId());
    pre.setString(1, r.getSubject());
    pre.setString(2, r.getType());
    pre.setString(3, r.getMessage());
    //pre.setInt(3, r.getId());
    //pre.setInt(4, r.getId());
   
     try {
             TrayIconDemo.main(3);
         } catch (AWTException ex) {
            
         }
    pre.executeUpdate();
    alert a =new alert();
    a.showalertinformation("Complaint sent");
         }
    
    }
 
    @Override
    public Reclamation getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from complaint where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Reclamation rec;
            rec = new Reclamation(rs.getInt("id"),rs.getString("subject"),rs.getString("Message"),rs.getInt("teacher_Id"),rs.getString("type"),rs.getInt("parent_id"));
            return rec;
        }
        return null;
    }   
       public Boolean exist(int id) throws SQLException {
        return getById(id)!=null;
    }

    @Override
    public boolean Reponse(String rep,int id) throws SQLException {
        alert a=new alert();
        if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE complaint set status=1 , response='"+rep+"' where id="+id;
         
         ste.execute(query);
         return true;
        }
        a.showalertwarning("Please write your response");
        return false;
    }
}
    




