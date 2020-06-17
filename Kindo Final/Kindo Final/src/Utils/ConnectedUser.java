/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import Entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class ConnectedUser {
    public static String password="";
    public static List<User> list=new ArrayList<>();
    public static void populate(User u)
    {
        list.add(u);
    }
}
