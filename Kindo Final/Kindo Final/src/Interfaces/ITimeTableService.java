/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entities.*;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ITimeTableService {
   public void add(TimeTable t) throws SQLException;
   public boolean delete(int id) throws SQLException;
   public boolean update(int id,String activity1, String activity2, String activity3, String activity4) throws SQLException;
   public List<TimeTable> readAll() throws SQLException;
   public Boolean exist (int id) throws SQLException;
   public TimeTable getById(int id) throws SQLException;
 //  public String getActivityName(int id) throws SQLException;
}
