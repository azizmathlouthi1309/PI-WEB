/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Activity;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author maiss
 */
public interface IActivityService<T> {
   public void add(T t) throws SQLException;
   public boolean delete(String id) throws SQLException;
   public boolean update(String name,String description) throws SQLException;
   public List<T> readAll() throws SQLException;
   public Boolean exist (String id) throws SQLException;
   public Activity getById(String id) throws SQLException;
   public List<T> search (String s) throws SQLException;
   public Set<T> tree (T t) throws SQLException;
}
