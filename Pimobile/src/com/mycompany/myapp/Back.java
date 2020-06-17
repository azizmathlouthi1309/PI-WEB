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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aziz
 */
public class Back extends Form{
    public Back(Resources theme)
    {
        Login log=new Login(theme);
       Button transport =new Button("Transport");
       Button espaceparent =new Button("Parent's Space");
       espaceparent.addActionListener(e->{
           new parentback(theme).show();
       });
        Container c=new Container(new FlowLayout(CENTER, CENTER));
        transport.addActionListener(e->{
            Transportback tb=new Transportback(theme);
            tb.show();
        });
       c.addAll(transport,espaceparent);
       this.add(c);
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> log.showBack());
    }
}
