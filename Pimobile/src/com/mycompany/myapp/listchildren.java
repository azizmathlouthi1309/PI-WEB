/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceChild;
import com.mycompany.Services.ServiceDriver;
import com.mycompany.entities.Child;
import com.mycompany.entities.Driver;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aziz
 */
public class listchildren extends Form {
    public listchildren(Resources theme)
    {
        Home home=new Home(theme);
        setUIID("LoginForm");
        ServiceChild sd=new ServiceChild();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> home.showBack());
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //SpanLabel sp = new SpanLabel(ServiceDriver.getInstance().getAllTasks().toString(),"WelcomeWhite");
        List<Child> children=new ArrayList();
        children=sd.getAllTasks();
        for (Child ch:children)
        {
            
            Container c =new Container(BoxLayout.x());
            Container c1 =new Container(BoxLayout.y());
            Container c2 =new Container(BoxLayout.y());
            Container c3=new Container(BoxLayout.x());
            Label divide=new Label("--------------------------");
            Label firstname=new Label("first name");
            firstname.setUIID("CreateNewAccountButton");
            Label firstnamevalue=new Label(ch.getFirstname());
            firstnamevalue.setUIID("CreateNewAccountButton");
            Label lastname=new Label("last name");
            lastname.setUIID("CreateNewAccountButton");
            Label lastnamevalue=new Label(ch.getLastname());
            lastnamevalue.setUIID("CreateNewAccountButton");
            Label age=new Label("age");
            age.setUIID("CreateNewAccountButton");
            divide.setUIID("CreateNewAccountButton");
            Label agevalue=new Label(Integer.toString(ch.getAge()));
            agevalue.setUIID("CreateNewAccountButton");
            Label level=new Label("Level");
            level.setUIID("CreateNewAccountButton");
            Label levelvalue=new Label(Integer.toString(ch.getLevel()));
            levelvalue.setUIID("CreateNewAccountButton");
            c1.addAll(firstname,lastname,age,level,divide);
            c2.addAll(firstnamevalue,lastnamevalue,agevalue,levelvalue);
            c.addAll(c1,c2);
            this.add(c);
        }
    }
}
