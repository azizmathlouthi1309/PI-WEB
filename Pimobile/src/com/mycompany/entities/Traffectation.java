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
public class Traffectation {
    private int id;
    private String grade;
    private String driver;
    private String vehicule;

    public Traffectation() {
    }

    public Traffectation(int id, String grade, String driver, String vehicule) {
        this.id = id;
        this.grade = grade;
        this.driver = driver;
        this.vehicule = vehicule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getVehicule() {
        return vehicule;
    }

    public void setVehicule(String vehicule) {
        this.vehicule = vehicule;
    }

    @Override
    public String toString() {
        return "Traffectation{" + "id=" + id + ", grade=" + grade + ", driver=" + driver + ", vehicule=" + vehicule + '}';
    }

    
}
