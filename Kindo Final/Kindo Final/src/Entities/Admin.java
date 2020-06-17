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
public class Admin extends User{
    private int cin;

    public Admin(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, String last_login, String confirmation_token, String password_requested_at, String roles, String account_type,int cin) {
        super(id, username, username_canonical, email, email_canonical, enabled, salt, password, confirmation_token, roles, account_type);
        this.cin=cin;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "Admin{" +super.toString()+ "cin=" + cin + '}';
    }
    
}
