/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface IAdminInterface {
    public int addAdmin(Admin t) throws SQLException;
    public List<Admin> getAdmins() throws SQLException;
    public Admin getById(int id) throws SQLException;
    public Boolean exist(int id)throws SQLException;
    public Boolean deleteAdmin(int id) throws SQLException;
    public Boolean updateAdmin(int id,String username,String password,String email) throws SQLException;
    public String CryptingPassword(String password);
    public String decrypt(String password);
}
