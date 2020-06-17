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
import com.mycompany.Services.ServiceDriver;
import com.mycompany.Services.ServiceVehicule;
import com.mycompany.entities.*;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import static java.lang.Integer.parseInt;

/**
 *
 * @author bhk
 */
public class Adddriver extends Form{

    public Adddriver(Resources theme) {
        setTitle("Add a new Driver");
        setLayout(BoxLayout.y());
        setUIID("LoginForm");
        TextField first = new TextField("","first name");
        TextField last= new TextField("", "last name");
        TextField age = new TextField("","age");
        TextField phone = new TextField("","phone number");
        TextField adress = new TextField("","address");
        Button btnValider = new Button("Add Driver");
         btnValider.setUIID("LoginButton");
        
       
        ServiceDriver sv=new ServiceDriver();
        btnValider.addActionListener(e->{
            if ((first.getText().length()==0)||(last.getText().length()==0)||(age.getText().length()==0)||(phone.getText().length()==0)||(adress.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                        
                        Driver t = new Driver(first.getText(),last.getText(),Integer.parseInt(age.getText()),Integer.parseInt(phone.getText()),adress.getText());
                boolean adddriver = sv.addDriver(t.getFirstname(),t.getLastname(),t.getAge(),t.getPhonenumber(),t.getAdress(),theme);
                         
          //  public void actionPerformed(ActionEvent evt) {
               Email from = new Email("aziz13mth@gmail.com");
    String subject = "Notification";
    Email to = new Email("mohamedaziz.mathlouthi@esprit.tn");
    Content content = new Content("text/plain", "Driver added");
    Mail mail = new Mail(from, subject, to, content);

    SendGrid sg = new SendGrid("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");
    Request request = new Request();
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sg.api(request);
      System.out.println(response.getStatusCode());
      System.out.println(response.getBody());
      System.out.println(response.getHeaders());
    } catch (IOException ex) {
    System.out.println("message non envoyé");
    }
            }
        });
                
        


                
        Home tb=new Home(theme);
        addAll(first,last,age,phone,adress,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> tb.showBack());
                
    }
    
    
}
