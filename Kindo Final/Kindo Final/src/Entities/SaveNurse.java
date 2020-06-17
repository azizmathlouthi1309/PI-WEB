/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author nada
 */
public class SaveNurse {
    private int id;
    private String date_begin;
    private String date_end;

    public SaveNurse(int id, String date_begin, String date_end) {
        this.id = id;
        this.date_begin = date_begin;
        this.date_end = date_end;
    }

  
    public int getId() {
        return id;
    }

    public String getDate_begin() {
        return date_begin;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate_begin(String date_begin) {
        this.date_begin = date_begin;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    @Override
    public String toString() {
        return "SaveNurse{" + "id=" + id + ", date_begin=" + date_begin + ", date_end=" + date_end + '}';
    }
    
}
