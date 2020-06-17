/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Administrator
 */
public class Teacher extends User{
    private String subject;

    public Teacher(int id, String username, String username_canonical, String email, String email_canonical,
                   int enabled, String salt, String password, String last_login, String confirmation_token,
                   String password_requested_at, String roles, String account_type,String subject) {
                   super(id, username,  username_canonical,  email,  email_canonical,  enabled,  salt,  password,  confirmation_token,  roles,  account_type);
        this.subject=subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Teacher{" + super.toString()+ "subject=" + subject + '}';
    }
    
}
