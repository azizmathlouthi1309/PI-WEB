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
public class Parent extends User{
    private int nbr_child;

    public Parent(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, String last_login, String confirmation_token, String password_requested_at, String roles, String account_type,int nb_child) {
        super(id, username,  username_canonical,  email,  email_canonical,  enabled,  salt,  password,  confirmation_token,  roles,  account_type);
        this.nbr_child=nb_child;
    }

    public int getNbr_child() {
        return nbr_child;
    }

    public void setNbr_child(int nbr_child) {
        this.nbr_child = nbr_child;
    }

    @Override
    public String toString() {
        return "Parent{" + super.toString() + "nbr_child=" + nbr_child + '}';
    }
    
    
}
