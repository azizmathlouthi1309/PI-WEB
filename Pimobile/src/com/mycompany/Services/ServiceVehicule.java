/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.Services;

import com.codename1.components.ToastBar;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Rest;
import com.codename1.messaging.Message;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.entities.Traffectation;
import com.mycompany.entities.User;
import com.mycompany.entities.Vehicule;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.Login;
import com.mycompany.myapp.Transportback;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class ServiceVehicule {
    public ArrayList<Vehicule> tasks;
    
    public static ServiceVehicule instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceVehicule() {
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceVehicule getInstance() {
        if (instance == null) {
            instance = new ServiceVehicule();
        }
        return instance;
    }

    public boolean addVehicule(String brand,int capa,int stat,String type,Resources theme) {
        String url = Statics.BASE_URL + "/mobile/addvehicule?brand="+brand+"&capacity="+capa+"&status="+stat+"&type="+type;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if(resultOK)
                {
                    Dialog.show("success", "Creation Successful", new Command("OK"));
                    sendsms();
                    Transportback log=new Transportback(theme);
                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public ArrayList<Vehicule> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Vehicule t = new Vehicule();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setBrand(obj.get("brand").toString());
                float capa = Float.parseFloat(obj.get("capacity").toString());
                t.setCapacity((int)capa);
                float stat = Float.parseFloat(obj.get("status").toString());
                t.setStatus((int)stat);
                t.setType(obj.get("type").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Vehicule> getAllTasks(){
        String url = Statics.BASE_URL+"/mobile/vehicules/show";
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
    public void sendsms()
    {
        com.codename1.io.rest.Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/AC6d836bce0a77691fe7146f351048e8f2/Messages.json").
        queryParam("To", "+21653146920").
        queryParam("From","+12029331218").
        queryParam("Body", "Vehicule added Successfully").
        header("Authorization", "Basic " + Base64.encodeNoNewline(("AC6d836bce0a77691fe7146f351048e8f2"+ ":" + "ea6ad83377ca7411da97e340d28c3bb1").getBytes())).
        getAsJsonMap();
        if(result.getResponseData() != null) {
        String error = (String)result.getResponseData().get("error_message");
            if(error != null) {
            ToastBar.showErrorMessage(error);
            }
        } 
        else {
        ToastBar.showErrorMessage("Error sending SMS: " + result.getResponseCode());
        }
    }
    public void deleteveh(int id)
    {
        
    }
    
}
