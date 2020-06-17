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
import com.mycompany.entities.Child;
import com.mycompany.entities.View;
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
 * @author Aziz
 */
public class ServiceChild {
    public ArrayList<Child> tasks;
    
    public static ServiceChild instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceChild() {
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceChild getInstance() {
        if (instance == null) {
            instance = new ServiceChild();
        }
        return instance;
    }

    public boolean addchild( String firstname,String lastname,int level, int parent_id, int class_id, int age, int teacher_id) {
        String url = Statics.BASE_URL +"ahmed/addchild?firstname="+firstname+"&lastname="+lastname+"&level="+level+"&parent="+parent_id+"&class="+class_id+"&age="+age;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if(resultOK)
                {
                    Dialog.show("success", "Creation Successful", new Command("OK"));
                    sendmail();
//                    Transportback log=new Transportback(theme);
//                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<Child> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Child t = new Child();
                t.setFirstname(obj.get("firstname").toString());
                t.setLastname(obj.get("lastname").toString());
                float nbstars = Float.parseFloat(obj.get("level").toString());
                t.setLevel((int)nbstars);
                float age = Float.parseFloat(obj.get("age").toString());
                t.setAge((int)age);
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Child> getAllTasks(){
        String url = Statics.BASE_URL+"ahmed/viewallchildren";
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
    public void sendmail()
    {
        Email from = new Email("mohamedaziz.mathlouthi@esprit.tn");
    String subject = "Candidature LITO";
    Email to = new Email("ahmed.rais@esprit.tn");
    Content content = new Content("text/plain", "Driver added Successfully");
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
}
