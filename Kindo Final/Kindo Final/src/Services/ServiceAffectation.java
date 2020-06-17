package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Entities.Classe;
import Entities.Enfant;
import Utils.DataBase;
import Services.ServiceEnfant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ahmed
 */
public class ServiceAffectation{
        private Connection con;
    private Statement ste;
    
    public ServiceAffectation()
    {
          con = DataBase.getInstance().getConnection();
    }
       
      public  void AffecterEnfant(String Classname , int id) throws SQLException, Exception {
             ste=con.createStatement();
      
             
             
             
             
             
             
             String req ="UPDATE Class Inner Join Child   Set Class.nb_child=Class.nb_child-1 where Child.class_id = Class.id and Child.id="+id;
          String req1="Update Child Inner Join Class   Set Child.class_id = Class.id Where Class.name = '"+Classname+"'"+"and Child.id="+id;
          //String query="UPDATE Class set nb_child = nb_child+1 where id ="+Classname;
           String req2 ="UPDATE Class  Set nb_child=nb_child+1 where name='"+Classname+"'";
          ste.execute(req2);
          ste.execute(req);
                            //ste.execute(query);

          System.out.println(id);
      ste.execute(req1);  
    }
       
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


    public Boolean exist(int id) throws SQLException {
         if(getById(id)!=null)
        {
            return true;
        }
        return false;
    
}
    public String getTeacherName(int childid) throws SQLException
    {
        ServiceEnfant se=new ServiceEnfant();
         ClassService c=new ClassService();
         Classe c1=c.getById(getclassId(childid));
         return c1.getTeacher_name();
         
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
