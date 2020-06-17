/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Avis;
import Entities.Reclamation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ahmed
 */
public interface IAvis {
    public void ajouterAvis(Avis v) throws SQLException;
    public Avis getById(int id) throws SQLException;
    public boolean supprimerAvis(int id) throws SQLException;
    public boolean updateAvis(int id, int nb_stars, String subject, String message) throws SQLException;
    public List<Avis> readAll() throws SQLException ;
    public void afficherAvis();
    public Boolean exist(int id)throws SQLException;
    
}
