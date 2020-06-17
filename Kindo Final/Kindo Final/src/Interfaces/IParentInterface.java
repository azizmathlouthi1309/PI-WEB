/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Parent;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IParentInterface {
    public int addParent(Parent t) throws SQLException;
    public List<Parent> getParents() throws SQLException;
    public Parent getById(int id) throws SQLException;
    public Boolean exist(int id)throws SQLException;
    public Boolean deleteParent(int id) throws SQLException;
    public Boolean updateParent(int id,String username,String password,String email) throws SQLException;
    public String CryptingPassword(String password);
    public String decrypt(String password);
}
