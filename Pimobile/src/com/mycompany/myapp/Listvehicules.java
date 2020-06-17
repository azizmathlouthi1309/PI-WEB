/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceVehicule;
import com.mycompany.entities.Driver;
import com.mycompany.entities.Vehicule;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aziz
 */
public class Listvehicules extends Form{
    public Listvehicules(Resources theme)
    {
        Home home=new Home(theme);
        setUIID("LoginForm");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> home.showBack());
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //SpanLabel sp = new SpanLabel(ServiceVehicule.getInstance().getAllTasks().toString(),"WelcomeWhite");
        //add(sp);
        ServiceVehicule sd=new ServiceVehicule();
        List<Vehicule> vehicules=new ArrayList();
        vehicules=sd.getAllTasks();
        for (Vehicule d:vehicules)
        {
            Container c =new Container(BoxLayout.x());
            Container c1 =new Container(BoxLayout.y());
            Container c2 =new Container(BoxLayout.y());
            Container c3=new Container(BoxLayout.x());
            Label divide=new Label("--------------------------");
            Label brand=new Label("Brand");
            brand.setUIID("CreateNewAccountButton");
            Label brandvalue=new Label(d.getBrand());
            brandvalue.setUIID("CreateNewAccountButton");
            Label cap=new Label("last name");
            cap.setUIID("CreateNewAccountButton");
            Label capvalue=new Label(Integer.toString(d.getCapacity()));
            capvalue.setUIID("CreateNewAccountButton");
            Label stat=new Label("age");
            stat.setUIID("CreateNewAccountButton");
            divide.setUIID("CreateNewAccountButton");
            Label statvalue=new Label(Integer.toString(d.getStatus()));
            statvalue.setUIID("CreateNewAccountButton");
            Label type=new Label("phone number");
            type.setUIID("CreateNewAccountButton");
            Label typevalue=new Label(d.getType());
            typevalue.setUIID("CreateNewAccountButton");
            c1.addAll(brand,cap,stat,type,divide);
            c2.addAll(brandvalue,capvalue,statvalue,typevalue);
            c.addAll(c1,c2);
            this.add(c);
        }
    }
}
