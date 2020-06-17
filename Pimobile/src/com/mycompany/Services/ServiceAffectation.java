/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Traffectation;
import com.mycompany.myapp.Home;
import com.mycompany.utils.Statics;
import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceAffectation {

    public ArrayList<Traffectation> tasks;
    
    public static ServiceAffectation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceAffectation(){
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceAffectation getInstance() {
        if (instance == null) {
            instance = new ServiceAffectation();
        }
        return instance;
    }

    public ArrayList<Traffectation> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Traffectation t = new Traffectation();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setGrade(obj.get("grade").toString());
                t.setDriver(obj.get("driver").toString());
                t.setVehicule(obj.get("vehicule").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Traffectation> getAllTasks(){
        String url = Statics.BASE_URL+"/affectations/show";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    public void sendemail()
    {
        Email from = new Email("meriem.bader1@esprit.tn");
    String subject = "Candidature LITO";
    Email to = new Email("mohamedaziz.mathlouthi@esprit.tn");
    Content content = new Content("text/plain", "merci de confirmer la candidature");
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
    

    public boolean Login(String username,String password,Resources theme)
    {
        int ala=0;
        String url = Statics.BASE_URL + "/users/connect?username="+username+"&password="+password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                String response=new String(req.getResponseData());
                if(response.equals("1"))
                {
                    Dialog.show("success", "Login Successful", new Command("OK"));
                       Home home=new Home(theme);
                        home.show();
                }
                else
                {
                    Dialog.show("Alert", "Wrong Informations", new Command("OK"));
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
   
}
