/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entities;

/**
 *
 * @author Aziz
 */
public class Vehicule {
    private int id;
    private String brand;
    private int capacity;
    private int status;
    private String type;

    public Vehicule() {
    }

    public Vehicule(String brand, int capacity, int status, String type) {
        
        this.brand = brand;
        this.capacity = capacity;
        this.status = status;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicule{" + "id=" + id + ", brand=" + brand + ", capacity=" + capacity + ", status=" + status + ", type=" + type + '}';
    }
    
}
