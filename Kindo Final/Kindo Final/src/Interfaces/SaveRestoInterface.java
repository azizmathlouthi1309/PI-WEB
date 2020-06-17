/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author nada
 * @param <SaveResto>
 */
public interface SaveRestoInterface<SaveResto> {
    public SaveResto getById(int id)throws SQLException;
    public Boolean exist(int id) throws SQLException;
    void ajouter(SaveResto s) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(int id, String date_begin, String date_end) throws SQLException;
    List<SaveResto> readAll() throws SQLException;
}
// tri par date_end
// afficher la date more likely to expire