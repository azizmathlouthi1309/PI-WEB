/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Ahmed
 */
public class Avis {
    private int id;
    private int nb_stars;
    private String subject;
    private String message;
    private String name;
    private String reponse; 

    public Avis(int id, int nb_stars, String subject, String message, String name) {
        this.id = id;
        this.nb_stars = nb_stars;
        this.subject = subject;
        this.message = message;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", nb_stars=" + nb_stars + ", subject=" + subject + ", message=" + message + '}';
    }

    public Avis(int id, int nb_stars, String subject, String message) {
        this.id = id;
        this.nb_stars = nb_stars;
        this.subject = subject;
        this.message = message;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Avis other = (Avis) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.nb_stars != other.nb_stars) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_stars() {
        return nb_stars;
    }

    public void setNb_stars(int nb_stars) {
        this.nb_stars = nb_stars;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
 
}
