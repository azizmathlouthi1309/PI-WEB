/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceChild;

/**
 *
 * @author Aziz
 */
public class addchild extends Form{
    public addchild(Resources theme)
    {
        setUIID("LoginForm");
        TextField firstname=new TextField("","First name");
        TextField lastname=new TextField("","Last name");
        TextField level=new TextField("","Level");
        TextField age=new TextField("","Age");
        Button addchild=new Button("add child");
        addchild.addActionListener(e->{
            ServiceChild sch=new ServiceChild();
            sch.addchild(firstname.getText(),lastname.getText(),Integer.parseInt(level.getText()),27,2, Integer.parseInt(age.getText()),4);
        });
        Container c=new Container(BoxLayout.y());
        c.addAll(firstname,lastname,level,age,addchild);
            add(c); 
    }
}
