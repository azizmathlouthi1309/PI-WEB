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
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceDriver;
import com.mycompany.entities.Driver;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Aziz
 */
public class listdrivers extends Form{
    public listdrivers(Resources theme)
    {
        Home home=new Home(theme);
        setUIID("LoginForm");
        ServiceDriver sd=new ServiceDriver();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> home.showBack());
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //SpanLabel sp = new SpanLabel(ServiceDriver.getInstance().getAllTasks().toString(),"WelcomeWhite");
        List<Driver> drivers=new ArrayList();
        drivers=sd.getAllTasks();
        for (Driver d:drivers)
        {
            Container c =new Container(BoxLayout.x());
            Container c1 =new Container(BoxLayout.y());
            Container c2 =new Container(BoxLayout.y());
            Container c3=new Container(BoxLayout.x());
            Label divide=new Label("--------------------------");
            Label firstname=new Label("first name");
            firstname.setUIID("CreateNewAccountButton");
            Label firstnamevalue=new Label(d.getFirstname());
            firstnamevalue.setUIID("CreateNewAccountButton");
            Label lastname=new Label("last name");
            lastname.setUIID("CreateNewAccountButton");
            Label lastnamevalue=new Label(d.getLastname());
            lastnamevalue.setUIID("CreateNewAccountButton");
            Label age=new Label("age");
            age.setUIID("CreateNewAccountButton");
            divide.setUIID("CreateNewAccountButton");
            Label agevalue=new Label(Integer.toString(d.getAge()));
            agevalue.setUIID("CreateNewAccountButton");
            Label phone=new Label("phone number");
            phone.setUIID("CreateNewAccountButton");
            Label phonevalue=new Label(Integer.toString(d.getPhonenumber()));
            phonevalue.setUIID("CreateNewAccountButton");
            Label adress=new Label("Adress");
            adress.setUIID("CreateNewAccountButton");
            Label adressvalue=new Label(d.getAdress());
            adressvalue.setUIID("CreateNewAccountButton");
            c1.addAll(firstname,lastname,age,phone,adress,divide);
            c2.addAll(firstnamevalue,lastnamevalue,agevalue,phonevalue,adressvalue);
            c.addAll(c1,c2);
            this.add(c);
        }
    }
}
