/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Set;

/**
 *
 * @author khaoula
 */
public interface IeventService<event> {
    
    void add(event e) throws SQLException;
    boolean cancel(event e) throws SQLException;
    boolean update(Date date ,String name , String description,int hour_begin, int hour_end,int capacity) throws SQLException;
    boolean delay(String name, int hour_begin, int hour_end) throws SQLException;
    List<event> readAll() throws SQLException;
    //List<event> search(int id1);
    List<event> search1(String n) throws SQLException ;
    List<event> search2(String n) throws SQLException ;
    Set<event> sort();
}
