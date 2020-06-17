/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import Entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IReclamation {
     public List<Reclamation> getReclamation() throws SQLException;
    public void ajouterReclamation(Reclamation r) throws SQLException;
    public Reclamation getById(int id) throws SQLException;

    public boolean supprimerReclamation(int id) throws SQLException;


    public boolean updateReclamation(int id,String subject,String type,String message) throws SQLException;
    
    public void afficherReclamation();
    public Boolean exist(int id)throws SQLException;
    public boolean Reponse(String rep,int id)throws SQLException;
    //public boolean mailReclamation();
}
