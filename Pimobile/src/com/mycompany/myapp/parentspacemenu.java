/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Services.ServiceChild;

/**
 *
 * @author Aziz
 */
public class parentspacemenu extends Form {
    public parentspacemenu(Resources theme){
        setTitle("Parent's space");
        setLayout(BoxLayout.y());
        setUIID("LoginForm");
        Button addview = new Button("add view");
        Button addchild = new Button("add child");
        addchild.addActionListener(e->{
           addchild addch=new addchild(theme);
           addch.show();
        });
        addview.addActionListener(e->{
            new addview(theme).show();
        });
     Container c=new Container(BoxLayout.y());
     c.addAll(addview,addchild);
        add(c);
    }
}
