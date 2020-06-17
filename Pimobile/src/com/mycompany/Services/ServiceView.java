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
import com.mycompany.entities.View;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class ServiceView {
    public ArrayList<View> tasks;
    
    public static ServiceView instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceView() {
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceView getInstance() {
        if (instance == null) {
            instance = new ServiceView();
        }
        return instance;
    }

    public boolean addview(String sender,String subject,int nbstars,String msg,Resources theme) {
        String url = Statics.BASE_URL +"ahmed/addview?sender="+sender+"&subject="+subject+"&nbstars="+nbstars+"&message="+msg;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if(resultOK)
                {
                    Dialog.show("success", "Creation Successful", new Command("OK"));
//                    Transportback log=new Transportback(theme);
//                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         public ArrayList<View> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                View t = new View();
                t.setSendername(obj.get("senderName").toString());
                t.setSubject(obj.get("subject").toString());
                float nbstars = Float.parseFloat(obj.get("nbStars").toString());
                t.setNbstars((int)nbstars);
                t.setMessage(obj.get("message").toString());
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<View> getAllTasks(){
        String url = Statics.BASE_URL+"ahmed/viewallviews";
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
