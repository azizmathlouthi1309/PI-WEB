/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author maiss
 */
public class TimeTable {

   private int id; 
   private String activity1; 
 
   private String activity2;
   private String activity3;
   private String activity4;
   private String classe;
   private String date;
   public int jour;

    public TimeTable(int id, String activity1, String activity2, String activity3, String activity4, String classe, String date, int jour) {
        this.id = id;
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
        this.classe = classe;
        this.date = date;
        this.jour = jour;
    }
 

    public TimeTable(String activity1, String activity2, String activity3, String activity4, String classe, String date) {
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
        this.classe = classe;
        this.date = date;
    }

    public TimeTable(String activity1, String activity2, String activity3, String activity4, String classe, String date, int jour) {
        
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
        this.classe = classe;
        this.date = date;
        this.jour = jour;
    }

    public TimeTable(String activity1, String activity2, String activity3, String activity4, int jour) {
        this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
        this.jour = jour;
    }
    

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }
    

    public TimeTable() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public TimeTable(String activity1, String activity2, String activity3, String activity4) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                 this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
    }

     public TimeTable(int id,String activity1, String activity2, String activity3, String activity4) {
        this.id=id;
         this.activity1 = activity1;
        this.activity2 = activity2;
        this.activity3 = activity3;
        this.activity4 = activity4;
       
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity1() {
        return activity1;
    }

    public void setActivity1(String activity1) {
        this.activity1 = activity1;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getActivity2() {
        return activity2;
    }

    public void setActivity2(String activity2) {
        this.activity2 = activity2;
    }

    public String getActivity3() {
        return activity3;
    }

    public void setActivity3(String activity3) {
        this.activity3 = activity3;
    }

    public String getActivity4() {
        return activity4;
    }

    public void setActivity4(String activity4) {
        this.activity4 = activity4;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TimeTable{" + "id=" + id + ", activity1=" + activity1 + ", activity2=" + activity2 + ", activity3=" + activity3 + ", activity4=" + activity4 + ", classe=" + classe + ", date=" + date + ", jour=" + jour + '}';
    }

 
   

}