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
public class Enfant {
    private String nom  ;
     private String  prenom ;
      private int niveau  ;
      private int parent_id ;
      private int class_id ;
      private int save_nurs_id ;
      private int save_resto_id ;
      private int  age;
      private int  id;
      private String photo;

  

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Enfant other = (Enfant) obj;
        if (this.parent_id != other.parent_id) {
            return false;
        }
        if (this.class_id != other.class_id) {
            return false;
        }
        if (this.save_nurs_id != other.save_nurs_id) {
            return false;
        }
        if (this.save_resto_id != other.save_resto_id) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.niveau, other.niveau)) {
            return false;
        }
        return true;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    

    public Enfant(int id,String nom, String prenom, int niveau, int age,int class_id,String photo) {
        this.nom = nom;
        this.prenom = prenom;
        this.niveau = niveau;
        this.class_id = class_id;
        this.age = age;
        this.id = id;
        this.photo=photo;
    }

    public String getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Enfant{" + "nom=" + nom + ", prenom=" + prenom + ", niveau=" + niveau + ", parent_id=" + parent_id + ", class_id=" + class_id + ", save_nurs_id=" + save_nurs_id + ", save_resto_id=" + save_resto_id + ", age=" + age + ", id=" + id + ", photo=" + photo + '}';
    }
 

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNiveau() {
        return niveau;
    }

   

  
    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getSave_nurs_id() {
        return save_nurs_id;
    }

    public void setSave_nurs_id(int save_nurs_id) {
        this.save_nurs_id = save_nurs_id;
    }

    public int getSave_resto_id() {
        return save_resto_id;
    }

    public void setSave_resto_id(int save_resto_id) {
        this.save_resto_id = save_resto_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
