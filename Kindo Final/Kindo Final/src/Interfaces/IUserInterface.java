/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public interface IUserInterface {
    public int addUser(User p) throws SQLException;
    public List<User> getUsers() throws SQLException;
    public User getById(int id) throws SQLException;
    public Boolean exist(int id)throws SQLException;
    public Boolean deleteUser(int id) throws SQLException;
    public Boolean updateUser(int id,String username,String password,String email) throws SQLException;
    public Boolean ChooseType(int id,String type)throws SQLException;
    public Boolean Login(String login,String password)throws SQLException;
    public Boolean ChooseType_affect(int id,String type,String s,int cin)throws SQLException;
    public String getUserType(int id) throws SQLException;
    public void UpdateAll(int id,String username,String password,String email)throws SQLException;
    public List<User> findby(String s)throws SQLException;
    public void deleteall(int id)throws SQLException;
}
