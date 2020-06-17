/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kindo;

import Entities.Admin;
import Entities.User;
import Services.AdminService;
import Services.UserService;
import Utils.ConnectedUser;
import Utils.TrayIconDemo;
import java.awt.AWTException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 *
 * @author House
 */
public class Main {
    
    public static void main(String[] args) throws SQLException, AWTException, InterruptedException {
     //UserService us=new UserService();
        //TrayIconDemo.main(1);
        //us.activateaccount(1);
        //us.desactivateaccount(1);
        //us.getstatus(1);
        /*
        TeacherService ts=new TeacherService();
        ParentService ps=new ParentService();
        *///AdminService as=new AdminService();
        //User user=new User(0,"kindo","kindo","kindo@gmail.com","email_canonical",1,"salt","kindo",null,"confirmation_token",null,"roles","Admin");
        /*Teacher teacher=new Teacher(0,"username","username_canonical","email","email_canonical",0,"salt","password",null,"confirmation_token",null,"roles","","Math");
        *///Parent parent=new Parent(0,"username","username_canonical","email","email_canonical",0,"salt","password",null,"confirmation_token",null,"roles","",0);
        //Admin admin=new Admin(0,"aziz","username_canonical","mohamedaziz.mathlouthi@esprit.tn","email_canonical",1,"salt","password",null,"confirmation_token",null,"roles","",0);
        //////////////       CRUD USER   ////////////////////////////
        //us.addUser(user);
        //us.getUsers().stream().forEach(e->System.out.println(e));                        ///////afficher les users
        //us.updateUser(0, "hahyhay","salut");
        //us.getUsers().stream().forEach(e->System.out.println(e));
        //us.deleteUser(0);                                   ////////////////delete user
        //us.getUsers().stream().forEach(e->System.out.println(e));
        /////////////////  CRUD PARENT   ////////////////////////////
        //ps.addParent(parent);
        //ps.getParents().stream().forEach(e->System.out.println(e));
        //ps.updateParent(0,"qdqsdqd","chakchouka");
        //ps.getParents().stream().forEach(e->System.out.println(e));
        //ps.deleteParent(0);
        //ps.getParents().stream().forEach(e->System.out.println(e));
        /////////////////       CRUD TEACHER    /////////////////////
        //ts.addTeacher(teacher);
        //ts.updateTeacher(0,"aziz","11");
        //ts.deleteTeacher(0);
        //ts.getTeachers().stream().forEach(e->System.out.println(e));
        ///////////////////      CRUD ADMIN ///////////////////
        //as.addAdmin(admin);
        //as.updateAdmin(0,"heyn", "passyordddd");
        //as.deleteAdmin(0);
        //as.getAdmins().stream().forEach(e->System.out.println(e));
        //////////////   METIERS ///////////////////
        //us.ChooseType_affect(17,"Admin","Maths",5442121);
        //us.findby("m").stream().forEach(e->System.out.println(e));            ///////////////recherche avancée
        //us.Login("aziz","chakchouka");
        //us.deleteall(0); /////////////delete all
        //us.SendPasswordByMail(us.getById(0));
        //us.trierUsers().stream().forEach(e->System.out.println(e));                       /////TRI
        //us.UpdateAll(0,"aziz1309","chakchoukaa");                                       /////////UPDATEALL
        //Pdf pdf=new Pdf();
        // pdf.GeneratePdf("list__users");                                                   ////////PDF
        //String a=us.CryptingPassword("aziz");
        //String b=us.decrypt(a);
        // System.out.println(a);
        // System.out.println(b);
        //}
        //String a=BCrypt.hashpw("hey",BCrypt.gensalt());
        //if (BCrypt.checkpw("heyy",a))
        //{
          //  System.out.println("match");
        //}
        //System.out.println(ConnectedUser.list);
        //System.out.println( "959".matches("[1-100]*"));
              
        
}

}
