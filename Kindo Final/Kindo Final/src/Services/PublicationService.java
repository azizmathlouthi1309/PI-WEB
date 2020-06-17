

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Entities.*;
import Interfaces.IPublicationService;
import Utils.DataBase;
import java.awt.AWTException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import manage.Notification;


public class PublicationService implements IPublicationService{

    
    private Connection con;
        private Statement ste;

    public PublicationService() {
        con = DataBase.getInstance().getConnection();
    
}
    
    @Override
    public void add(publication p,int id) throws SQLException {
        
        ste = con.createStatement();
        String requeteInsert ="Insert into publication (`subject`,`content`,`class_name`,`teacher_id`) values('"+p.getSubject()+"','"+p.getContent()+"','"+p.getClass_name()+"','"+id+"')";
            try {
             Notification.main(1);
         } catch (AWTException ex) {
            
         }
        ste.executeUpdate(requeteInsert);
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(int id) throws SQLException {
            if (exist(id))
     {  
        ste = con.createStatement();
        String requetedelete="delete from publication where id="+id;
        ste.execute(requetedelete);
        return true;
     }
     else 
     {
      System.out.println("not here");
      return false ;
     }
      
//       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(int id, String subject, String content,String classame) throws SQLException {
        if (exist(id))
           {
        ste = con.createStatement();
        String requeteUpadte="update publication set subject='"+subject+"' ,content='"+content+"' , class_name='"+classame+"'where id='"+id+"' ";
        ste.executeUpdate(requeteUpadte);
        return true ;}
         else 
             return false ;
    //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<publication> readAll() throws SQLException {
        List<publication> arr=new ArrayList<>();
        Teacher T;
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from publication");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String sbj=rs.getString("subject");
               String content=rs.getString("content");
               String teachern=rs.getString("class_name");
               int teacher_id=rs.getInt("teacher_id");
               publication c=new publication(id,sbj,content,teachern,teacher_id);
     arr.add(c);
     }
    return arr;
//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public List<publication> search(String s) throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(readAll().stream().filter(e->e.getSubject().startsWith(s)).collect(Collectors.toList()).isEmpty())
          {
            System.out.println("pub does not exist");
          }
            return readAll().stream().filter(e->e.getSubject().startsWith(s)).collect(Collectors.toList());
          }

    @Override
    public publication getById(int id) throws SQLException {
            ste=con.createStatement();
        String query="select * from publication where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            publication a=new publication(rs.getInt("id"),rs.getString("subject"),rs.getString("Content"),rs.getString("class_name"),rs.getInt("teacher_id"));
            return a;
        }
        return null;
        
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
