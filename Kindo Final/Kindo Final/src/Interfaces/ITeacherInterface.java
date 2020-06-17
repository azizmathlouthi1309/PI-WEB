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
public interface ITeacherInterface {
    public int addTeacher(Teacher t) throws SQLException;
    public List<Teacher> getTeachers() throws SQLException;
    public Teacher getById(int id) throws SQLException;
    public Boolean exist(int id)throws SQLException;
    public Boolean deleteTeacher(int id) throws SQLException;
    public Boolean updateTeacher(int id,String username,String password,String email) throws SQLException;
    public String CryptingPassword(String password);
    public String decrypt(String password);
//public Boolean ChooseType(int id,String type)throws SQLException;
    //public Boolean Login(String login,String password)throws SQLException;
}
