/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import Entities.participation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author khaoula
 */
public interface IparticipationService<participation> {
    void add() throws SQLException;
    int capacity() throws SQLException ;
    void add( participation p) throws SQLException;
    List<participation> readAll() throws SQLException;
    //void rechercher(int id);
     int exist(participation p) throws SQLException;
     boolean delete(participation p) throws SQLException;
     boolean validate(participation p)throws SQLException;
     boolean refuse(participation p) throws SQLException;
}