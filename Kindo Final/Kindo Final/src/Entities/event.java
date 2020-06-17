/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

/**
 *
 * @author khaoula
 */
public class event {
    private int id;
    private String name;
    private String description;
    private Date date;
    private LocalDate date1;
    private int hour_begin;
    private int hour_end;
    private int capacity;

    public event() {
    }

    public event(String name, String description, Date date, int hour_begin, int hour_end, int capacity) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.hour_begin = hour_begin;
        this.hour_end = hour_end;
        this.capacity = capacity;
    }

    public event(int id, String name, String description, Date date, int hour_begin, int hour_end, int capacity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.hour_begin = hour_begin;
        this.hour_end = hour_end;
        this.capacity = capacity;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }
    public LocalDate getDate1() {
        return date1;
    }

    public int getHour_begin() {
        return hour_begin;
    }

    public int getHour_end() {
        return hour_end;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setHour_begin(int hour_begin) {
        this.hour_begin = hour_begin;
    }

    public void setHour_end(int hour_end) {
        this.hour_end = hour_end;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "event{" + "id=" + id + ", name=" + name + ", description=" + description + ", date=" + date + ", hour_begin=" + hour_begin + ", hour_end=" + hour_end + ", capacity=" + capacity + '}';
    }

  
    
}
