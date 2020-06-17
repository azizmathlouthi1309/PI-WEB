/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.logging.Logger;

/**
 *
 * @author maiss
 */
public class Classe {
    
    private int id ;
    private String nom;
    private int nb_child;
    private int level; 
    private String teacher_name;

    public Classe(int id, String nom, int nb_child, int level, String teacher_name) {
        this.id = id;
        this.nom = nom;
        this.nb_child = nb_child;
        this.level = level;
        this.teacher_name = teacher_name;
    }

    public Classe(String nom, int nb_child, int level, String teacher_name) {
        this.nom = nom;
        this.nb_child = nb_child;
        this.level = level;
        this.teacher_name = teacher_name;
    }

    public Classe() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_child() {
        return nb_child;
    }

    public void setNb_child(int nb_child) {
        this.nb_child = nb_child;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", nom=" + nom + ", nb_child=" + nb_child + ", level=" + level + ", teacher_name=" + teacher_name + '}';
    }
    

    
}