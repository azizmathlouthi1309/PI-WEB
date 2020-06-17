/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceAffectation;

/**
 *
 * @author Aziz
 */
public class Listaffectations extends Form{
    public Listaffectations(Resources theme)
    {
        Home home=new Home(theme);
        setUIID("LoginForm");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> home.showBack());
        //super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        SpanLabel sp = new SpanLabel(ServiceAffectation.getInstance().getAllTasks().toString(),"WelcomeWhite");
        
        add(sp);
    }
}
