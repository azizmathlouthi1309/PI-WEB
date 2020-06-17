/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entities;

/**
 *
 * @author Aziz
 */
public class Classe {
    private int id;
    private String name;
    private  int nbchild;
    private  int level;
    private String teacher;

    public Classe() {
    }

    public Classe(int id, int nbchild, int level, String teacher) {
        this.id = id;
        this.nbchild = nbchild;
        this.level = level;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbchild() {
        return nbchild;
    }

    public void setNbchild(int nbchild) {
        this.nbchild = nbchild;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
}
