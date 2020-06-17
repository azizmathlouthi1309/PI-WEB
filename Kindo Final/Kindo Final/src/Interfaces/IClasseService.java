/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.Activity;
import Entities.Classe;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author maiss
 */
public interface IClasseService<T> {
   public void add(T t) throws SQLException;
   public boolean delete(int id) throws SQLException;
   public boolean update(int id,String name,int nb_child,int lev,String teacher_name) throws SQLException;
   public List<T> readAll() throws SQLException;
   public Boolean exist (int id) throws SQLException;
   public Classe getById(int id) throws SQLException;
   public List<T> search (String s) throws SQLException;
   public void treeClass () throws SQLException;
   
           
 
    
}
