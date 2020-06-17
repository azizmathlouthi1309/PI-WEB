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
 * @param <SaveNurse>
 */
public interface SaveNurseInterface<SaveNurse> 
{
    public SaveNurse getById(int id)throws SQLException;
    public Boolean exist(int id) throws SQLException;
    void ajouter(SaveNurse s) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(int id, String date_begin, String date_end) throws SQLException;
    List<SaveNurse> readAll() throws SQLException;
}
