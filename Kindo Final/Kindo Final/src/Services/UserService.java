/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Admin;
import Entities.Parent;
import Entities.Teacher;
import Utils.Connect;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.Mail;
import Utils.alert;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author Administrator
 */
public class UserService implements Interfaces.IUserInterface{

    private Connection con;
    private Statement ste;
    
    public UserService()
    {
        con = Connect.getInstance().getConnection();
    }
    
    public boolean emailexist(String mail) throws SQLException
    {
        return getbymail(mail)!=null;
    }
    public User getbymail(String mail) throws SQLException
    {
        for(User u : getUsers())
        {
            if(u.getEmail().equals(mail))
            {
                return u;
            }
            
        }
       return null;
    }
    public User getbyusernameandpassword(String username,String password) throws SQLException
    {
       List<User> list =getUsers();
        for(User u:list)
        {
            
            if((u.getUsername().equals(username))&&(BCrypt.checkpw(password,u.getPassword())))
            {
                return u;
            }
        }
        return null;
    }
    @Override
    public int addUser(User u) throws SQLException {
        PreparedStatement pre=con.prepareStatement("INSERT INTO `kindo`.`user` (`id`,`username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `confirmation_token`, `roles`, `account_type`) VALUES ( ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?);");
        pre.setInt(1, u.getId());
        pre.setString(2, u.getUsername());
        pre.setString(3, u.getUsername_canonical());
        pre.setString(4, u.getEmail());
        pre.setString(5, u.getEmail_canonical());
        pre.setInt(6, u.getEnabled());
        pre.setString(7, u.getSalt());
        pre.setString(8,BCrypt.hashpw(u.getPassword(),BCrypt.gensalt()));
//        pre.setString(9, u.getLast_login());
        pre.setString(9, u.getConfirmation_token());
        //pre.setString(11, u.getPassword_requested_at());
        pre.setString(10, u.getRoles());
        pre.setString(11,null);
        if(existusername(u.getUsername()))
            {
            alert a=new alert();
                   a.showalertwarning("Username exist");
            return 0;
            }
        else if(emailexist(u.getEmail()))
        {
            alert a=new alert();
                   a.showalertwarning("User email exist");
            return 0;
        }
        else
            {
        return pre.executeUpdate();
            }
    }
    public int getAdminnumber() throws SQLException{
        int c=0;
        for(User u:getUsers())
        {
            if(u.getAccount_type().equals("Admin"))
            {
                c++;
            }
        }
        return c;
    }
        public int getteachersnumber() throws SQLException{
        int c=0;
        for(User u:getUsers())
        {
            if(u.getAccount_type().equals("Teacher"))
            {
                c++;
            }
        }
        return c;
    }
            public int getparentsnumber() throws SQLException{
        int c=0;
        for(User u:getUsers())
        {
            if(u.getAccount_type().equals("Parent"))
            {
                c++;
            }
        }
        return c;
    }
    @Override
    public List<User> getUsers() throws SQLException {
        List<User> list=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from user");
     while (rs.next()) {                
               User user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getString("confirmation_token"),rs.getString("roles"),rs.getString("account_type"));
     list.add(user);
     }
    return list;
    }

    @Override
    public User getById(int id) throws SQLException {
        ste=con.createStatement();
        String query="select * from user where id="+id;
        ResultSet rs=ste.executeQuery(query);
        while(rs.next())
        {
            User user=new User(rs.getInt("id"),rs.getString("username"),rs.getString("username_canonical"),rs.getString("email"),rs.getString("email_canonical"),rs.getInt("enabled"),rs.getString("salt"),rs.getString("password"),rs.getString("confirmation_token"),rs.getString("roles"),rs.getString("account_type"));
            return user;
        }
        return null;
    }
    public Boolean existusername(String username) throws SQLException
    {
        for(User u:getUsers())
        {
            if(u.getUsername().equals(username))
            {
                return true;
            }
        }
        return false;
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
    public Boolean deleteUser(int id) throws SQLException {
        ste=con.createStatement();
        String query="delete from user where id="+id;
        if(exist(id))
        {
            ste.execute(query);
            return true;
        }
        return false;
    }
   

    @Override
    public Boolean updateUser(int id,String username,String password,String email) throws SQLException {
        String crypt=BCrypt.hashpw(password,BCrypt.gensalt());
        if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE user set username='"+username+"',password='"+crypt+"',email='"+email+"'where id='"+id+"'";
         ste.execute(query);
         return true;
        }
        System.out.println("User does not exist");
        return false;
    }
    
    @Override
    public Boolean ChooseType(int id, String type) throws SQLException {
        if(exist(id))
        {   
        ste=con.createStatement();
         String query="UPDATE user set account_type='"+type+"'where id='"+id+"'";
         ste.execute(query);
         return true;
        }
        System.out.println("User does not exist");
        return false;
    }

    @Override
    public Boolean Login(String username,String password) throws SQLException {
        List<User> list =getUsers();
        for(User u:list)
        {
            
            if((u.getUsername().equals(username))&&(BCrypt.checkpw(password,u.getPassword())))
            {
                if(getstatus(u.getId())==1)
                {
                    return true;
                }
                else
                {
                    alert a =new alert();
                    a.showalertwarning("your Account is not active");
                            
                    return false;
                }
                
            }
        }
        alert a =new alert();
        a.showalertwarning("invalid informations");
        return false;
    }
    public int GetUserId(String username,String password) throws SQLException
    {
        List<User> list =getUsers();
        for(User u:list)
        {
            if((u.getUsername().equals(username))&&(BCrypt.checkpw(password,u.getPassword())))
            {
                return u.getId();
            }
        }
        return -1;
    }

    @Override
    public Boolean ChooseType_affect(int id, String type,String subject,int cin) throws SQLException {
        if(exist(id))
                {
                    User u=getById(id);
                    Teacher t=new Teacher(id,u.getUsername(),u.getUsername_canonical(),u.getEmail(),u.getEmail_canonical(),u.getEnabled(),u.getSalt(),u.getPassword(),
                    u.getLast_login(),u.getConfirmation_token(),u.getPassword_requested_at(),u.getRoles(),"Teacher",subject);
                    Admin a=new Admin(id,u.getUsername(),u.getUsername_canonical(),u.getEmail(),u.getEmail_canonical(),u.getEnabled(),u.getSalt(),u.getPassword(),
                    u.getLast_login(),u.getConfirmation_token(),u.getPassword_requested_at(),u.getRoles(),"Admin",cin);
                    Parent p=new Parent(id,u.getUsername(),u.getUsername_canonical(),u.getEmail(),u.getEmail_canonical(),u.getEnabled(),u.getSalt(),u.getPassword(),
                    u.getLast_login(),u.getConfirmation_token(),u.getPassword_requested_at(),u.getRoles(),"Parent",0);
                    AdminService as=new AdminService();
                    TeacherService ts=new TeacherService();
                    ParentService ps=new ParentService();
                    if(u.getAccount_type()!=null)
                    {
                        System.out.println("Account type already changed and cant be changed again");
                        alert alert=new alert();
                   alert.showalertinfo("Account type already changed and cant be changed again");
                    }
                    else
                    {
                        if(type.equals("Teacher"))
                            {
                            ChooseType(id,type);
                            ts.addTeacher(t);
                            System.out.println("ok done pour teacher");
                            }
                        else if(type.equals("Parent"))
                                {
                                    ChooseType(id,type);
                                    ps.addParent(p);
                                    System.out.println("ok done pour parent");
                                }
                        else if(type.equals("Admin"))
                                {
                                    ChooseType(id,type);
                                    as.addAdmin(a);
                                    System.out.println("ok done pour admin");
                                }
                        else{
                            System.out.println("type not reconized");
                        }
                    }
                    
                }
        else{
            System.out.println("user does not exist");
        }
        return true;
       
    }
    public String getUserType(int id) throws SQLException
    {
        User u=getById(id);
        return u.getAccount_type();
   
    }
    @Override
    public void UpdateAll(int id,String username,String password,String email)throws SQLException
    {
        updateUser(id,username,password,email);
        switch (getUserType(id)) {
            case "Teacher":
                TeacherService ts=new TeacherService();
                ts.updateTeacher(id,username, password,email);
                break;
            case "Parent":
                ParentService ps=new ParentService();
                ps.updateParent(id,username, password,email);
                break;
            case "Admin":
                AdminService as=new AdminService();
                as.updateAdmin(id,username, password,email);
                break;
            case "":
                System.out.println("User didnt specified account type yet");
            default:
                System.out.println("an error occured");
                break;
        }
    }

    
   
    @Override
    public List<User> findby(String s) throws SQLException {
       
           return getUsers().stream().filter(e->e.getUsername().startsWith(s)).collect(Collectors.toList()); 
    }

    @Override
    public void deleteall(int id)throws SQLException
    {
        Boolean a;
        TeacherService ts=new TeacherService();
        ParentService ps=new ParentService();
        AdminService as=new AdminService();
        //Boolean a=deleteUser(id);
        if(getById(id).getAccount_type().equals("Teacher"))
        {
            ts.deleteTeacher(id);
            a=deleteUser(id);
        }
        else if(getById(id).getAccount_type().equals("Parent"))
        {
            ps.deleteParent(id);
            a=deleteUser(id);
        }
        else if(getById(id).getAccount_type().equals("Admin"))
        {
            as.deleteAdmin(id);
            a=deleteUser(id);
        }
        a=deleteUser(id);
    }

    public void SendactivationByMail(String mail, int act_code) {
        try {
            Mail mail1 =new Mail("aziz13mth@gmail.com","abdou15121963bab",mail,"","Your Activation code is:<h1>"+act_code+"</h1>");
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    public int Random6Digits() {
        Random generator = new Random();
            generator.setSeed(System.currentTimeMillis());
                int num = generator.nextInt(99999) + 99999;
                    if (num < 100000 || num > 999999) {
                        num = generator.nextInt(99999) + 99999;
                            if (num < 100000 || num > 999999) {
                            try {
                                throw new Exception("Unable to generate PIN at this time..");
                            } catch (Exception ex) {
                                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                                              }
                                                        }
                                return num;
    }
    public boolean activateaccount(int id) throws SQLException
    {
        if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE user set enabled='"+1+"'where id='"+id+"'";
         ste.execute(query);
            return true;
        }
        else
        {
            System.out.println("User does not exist");
        return false;
        }
    }
    public Boolean activateall(int id)throws SQLException
    {
        activateaccount(id);
        if(getById(id).getAccount_type().equals("Teacher"))
        {
            TeacherService ts=new TeacherService();
            ts.activate(id);
            return true;
        }
        else if(getById(id).getAccount_type().equals("Parent"))
        {
            ParentService ps=new ParentService();
            ps.activate(id);
            return true;
        }
        return false;
    }
    public Boolean desactivateall(int id) throws SQLException
    {
        desactivateaccount(id);
        if(getById(id).getAccount_type().equals("Teacher"))
        {
            TeacherService ts=new TeacherService();
            ts.desactivate(id);
            return true;
        }
        else if(getById(id).getAccount_type().equals("Parent"))
        {
            ParentService ps=new ParentService();
            ps.desactivate(id);
            return true;
        }
        return false;
    }
    public boolean desactivateaccount(int id) throws SQLException
    {
         if(exist(id))
        {
        ste=con.createStatement();
         String query="UPDATE user set enabled='"+0+"'where id='"+id+"'";
         ste.execute(query);
            return true;
        }
        
        System.out.println("User does not exist");
        return false;
    }
    public int getstatus(int id) throws SQLException
    {
        return getById(id).getEnabled();
    }
    public User getByEmail(String email) throws SQLException
    {
        List<User>list=getUsers();
        for(User u:list)
        {
            if(u.getEmail().equals(email))
            {
                return u;
            }
        }
        return null;
    }



}