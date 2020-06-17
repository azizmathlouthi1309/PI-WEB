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
public interface IPublicationService {
   public void add(publication p,int id) throws SQLException;
   public boolean delete(int id) throws SQLException;
   public boolean update(int id,String subject,String content,String teacher_name) throws SQLException;
   public List<publication> readAll() throws SQLException;
   public Boolean exist (int id) throws SQLException;
   public publication getById(int id) throws SQLException;
   
}
