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
import com.mycompany.Services.ServiceView;
import com.mycompany.entities.Child;
import com.mycompany.entities.View;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aziz
 */
public class listviews extends Form{
    public listviews(Resources theme)
    {
        Home home=new Home(theme);
        setUIID("LoginForm");
        ServiceView sd=new ServiceView();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> home.showBack());
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        //SpanLabel sp = new SpanLabel(ServiceDriver.getInstance().getAllTasks().toString(),"WelcomeWhite");
        List<View> children=new ArrayList();
        children=sd.getAllTasks();
        for (View ch:children)
        {
            
            Container c =new Container(BoxLayout.x());
            Container c1 =new Container(BoxLayout.y());
            Container c2 =new Container(BoxLayout.y());
            Container c3=new Container(BoxLayout.x());
            Label divide=new Label("--------------------------");
            Label sender=new Label("sendername");
            sender.setUIID("CreateNewAccountButton");
            Label sendervalue=new Label(ch.getSendername());
            sendervalue.setUIID("CreateNewAccountButton");
            Label subject=new Label("subject");
            subject.setUIID("CreateNewAccountButton");
            Label subjectvalue=new Label(ch.getSubject());
            subjectvalue.setUIID("CreateNewAccountButton");
            Label nbstars=new Label("nbstars");
            nbstars.setUIID("CreateNewAccountButton");
            divide.setUIID("CreateNewAccountButton");
            Label nbstarsvalue=new Label(Integer.toString(ch.getNbstars()));
            nbstarsvalue.setUIID("CreateNewAccountButton");
            Label message=new Label("message");
            message.setUIID("CreateNewAccountButton");
            Label messagevalue=new Label(ch.getMessage());
            messagevalue.setUIID("CreateNewAccountButton");
            c1.addAll(sender,subject,nbstars,message,divide);
            c2.addAll(sendervalue,subjectvalue,nbstarsvalue,messagevalue);
            c.addAll(c1,c2);
            this.add(c);
        }
    }
}
