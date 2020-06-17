/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Parent;
import Interfaces.IAdminInterface;
import Utils.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Administrator
 */
public class AdminService implements IAdminInterface{
    private Connection con;
    private Statement ste;
    
    public AdminService(){
        con = Connect.getInstance().getConnection();
    }
    @Override
    public int addAdmin(Admin u) throws SQLException {
PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`admin` ( `id`,`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `account_type`, `cin`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
        pre.setInt(1, u.getId());
        pre.setString(2, u.getUsername());
        pre.setString(3, u.getUsername_canonical());
        pre.setString(4, u.getEmail());
        pre.setString(5, u.getEmail_canonical());
        pre.setInt(6, u.getEnabled());
        pre.setString(7, u.getSalt());
        pre.setString(8, u.getPassword());
        pre.setString(9, u.getLast_login());
        pre.setString(10, u.getConfirmation_token());
        pre.setString(11, u.getPassword_requested_at());
        pre.setString(12, u.getRoles());
        pre.setString(13,"Admin");
        pre.setInt(14,u.getCin());
        if(exist(u.getId()))
            {
            System.out.println("Admin already exist");
            return 0;
            }
        else
            {
        return pre.executeUpdate();
            }
    }

    @Override
    public List<Admin> getAdmins() throws SQLException {
        List<Admin> list=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from admin");
     while (rs.next()) {                
               Admin p=new Admin(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getString("last_login"),rs.getString("confirmation_token"),rs.getString("password_requested_at"),rs.getString("roles"),rs.getString("account_type"),rs.getInt("cin"));
     list.add(p);
     }
    return list;
    }

    @Override
    public Admin getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from admin where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            Admin t=new Admin(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getString("last_login"),rs.getString("confirmation_token"),rs.getString("password_requested_at"),rs.getString("roles"),rs.getString("account_type"),rs.getInt("cin"));
            return t;
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
    public Boolean deleteAdmin(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from admin where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        System.out.println("admin does not exist");
        return false;
    }

    @Override
    public Boolean updateAdmin(int id, String username, String password,String email) throws SQLException {
        String crypt=BCrypt.hashpw(password,BCrypt.gensalt());
        if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE admin set username='"+username+"',password='"+crypt+"',email='"+email+"'where id='"+id+"'";
         ste.execute(query);
         return true;
        }
        System.out.println("Admin does not exist");
        return false;
    }

    @Override
    public String CryptingPassword(String password) {
            String crypte="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            crypte=crypte+(char)c;
        }
        return crypte;
// throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String decrypt(String password) {
        String aCrypter="";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48; 
            aCrypter=aCrypter+(char)c;
        }
        return aCrypter;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
