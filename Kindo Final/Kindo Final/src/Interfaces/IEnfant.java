/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Enfant;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IEnfant {
     public int displaynbrchild(int level) throws SQLException;
     public void ajouterEnfant(Enfant f) throws SQLException;
     public boolean updateEnfant(int id,String nom, String prenom, int niveau, int age,int class_id) throws SQLException;
     public boolean supprimerEnfant(int id) throws SQLException;
     public List<Enfant> readAll() throws SQLException ;
     public Enfant getById(int id) throws SQLException;
     public Boolean exist(int id)throws SQLException;
     //public boolean MailEnfant();
     public void trierEnfants()throws SQLException;
     public void rechercherEnfants(String enf)throws SQLException;
}
