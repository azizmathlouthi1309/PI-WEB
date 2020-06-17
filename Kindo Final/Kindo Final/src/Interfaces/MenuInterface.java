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
 * @param <Menu>
 */
public interface MenuInterface<Menu> {
    public Menu getById(int id)throws SQLException;
    public Boolean exist(int id) throws SQLException;
    void ajouter(Menu m) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(int id, String date_day, String plate1, String plate2, String plate3) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    List<Menu> readAll() throws SQLException;
}
