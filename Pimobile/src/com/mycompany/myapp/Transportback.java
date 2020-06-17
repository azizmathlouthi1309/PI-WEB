/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aziz
 */
public class Transportback extends Form {
    public Transportback(Resources theme)
    {
        Back log =new Back(theme);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> log.showBack());
        Button addveh=new Button("Add Vehicule");
        Button adddriv=new Button("Add Driver");
        Button deleteveh=new Button("Add Vehicule");
        Button deletedriv=new Button("Add Driver");
        Container c =new Container(BoxLayout.y());
        addveh.addActionListener(e->{
            Addvehicule add=new Addvehicule(theme);
            add.show();
        });
        adddriv.addActionListener(e->{
            Adddriver add=new Adddriver(theme);
            add.show();
        });
        deleteveh.addActionListener(e->{
            
        });
        deletedriv.addActionListener(e->{
            
        });
        c.addAll(adddriv,addveh);
        add(c);
    }
}
