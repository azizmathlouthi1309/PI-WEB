/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Aziz
 */
public class parentback extends Form {
    public parentback(Resources theme){
        Button listviews=new Button("List Views");
        listviews.addActionListener(e->{
            new listviews(theme).show();
        });
        Button listchildren=new Button("List Children");
        listchildren.addActionListener(e->{
            new listchildren(theme).show();
        });
        Container c=new Container(BoxLayout.y());
        c.addAll(listviews,listchildren);
        add(c);
    }
}
