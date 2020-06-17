/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;


/**
 *
 * @author nada
 */
public class Menu {
    
    private int id;
    private String date_day;
    private String plate1;
    private String plate2;
    private String plate3;

    public Menu(int id, String date_day, String plate1, String plate2, String plate3) {
        this.id = id;
        this.date_day = date_day;
        this.plate1 = plate1;
        this.plate2 = plate2;
        this.plate3 = plate3;
    }

    public int getId() {
        return id;
    }

    public String getDate_day() {
        return date_day;
    }

    public String getPlate1() {
        return plate1;
    }

    public String getPlate2() {
        return plate2;
    }

    public String getPlate3() {
        return plate3;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_day(String date_day) {
        this.date_day = date_day;
    }

    public void setPlate1(String plate1) {
        this.plate1 = plate1;
    }

    public void setPlate2(String plate2) {
        this.plate2 = plate2;
    }

    public void setPlate3(String plate3) {
        this.plate3 = plate3;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", date_day=" + date_day + ", plate1=" + plate1 + ", plate2=" + plate2 + ", plate3=" + plate3 + '}';
    }

}
