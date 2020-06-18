/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Vehicule;
import com.mycompany.Services.ServiceVehicule;

/**
 *
 * @author bhk
 */
public class Addvehicule extends Form{

    public Addvehicule(Resources theme) {
        setTitle("Add a new Vehicule");
        setLayout(BoxLayout.y());
        setUIID("LoginForm");
        TextField brand = new TextField("","Brand");
        TextField capa= new TextField("", "Capacity");
        TextField status = new TextField("","Status");
        TextField type = new TextField("","Type");
        Button btnValider = new Button("Add Vehicule");
        ServiceVehicule sv=new ServiceVehicule();
        btnValider.addActionListener(e->{
            if ((brand.getText().length()==0)||(capa.getText().length()==0)||(status.getText().length()==0)||(type.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        
                        Vehicule t = new Vehicule(brand.getText(),Integer.parseInt(capa.getText()),Integer.parseInt(status.getText()),type.getText());
                boolean addVehicule = sv.addVehicule(t.getBrand(),t.getCapacity(),t.getStatus(),t.getType(),theme);
                        
                    
                }
        });
                
                
        Home tb=new Home(theme);
        addAll(brand,capa,status,type,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> tb.showBack());
                
    }
    
    
}
