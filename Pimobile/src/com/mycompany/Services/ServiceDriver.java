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
import com.mycompany.entities.Driver;
import com.mycompany.entities.Vehicule;
import com.mycompany.myapp.Transportback;
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
public class ServiceDriver {
    public ArrayList<Driver> tasks;
    
    public static ServiceDriver instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceDriver() {
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceDriver getInstance() {
        if (instance == null) {
            instance = new ServiceDriver();
        }
        return instance;
    }

    public boolean addDriver(String firstname,String lastname,int age,int phone,String adress,Resources theme) {
        String url = Statics.BASE_URL + "/mobile/adddriver?firstname="+firstname+"&lastname="+lastname+"&age="+age+"&phone="+phone+"&adress="+adress;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if(resultOK)
                {
                    Dialog.show("success", "Creation Successful", new Command("OK"));
                    //ServiceChild sch=new ServiceChild();
                    //sch.sendmail();
                    Transportback log=new Transportback(theme);
                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<Driver> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Driver t = new Driver();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setFirstname(obj.get("firstname").toString());
                t.setLastname(obj.get("lastname").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                t.setAge((int)age);
                float ph = Float.parseFloat(obj.get("phonenumber").toString());
                t.setAge((int)ph);
                t.setAdress(obj.get("adress").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Driver> getAllTasks(){
        String url = Statics.BASE_URL+"/mobile/drivers/show";
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
  
}

