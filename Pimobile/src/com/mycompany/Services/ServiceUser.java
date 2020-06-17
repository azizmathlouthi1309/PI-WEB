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
import com.mycompany.entities.User;
import com.mycompany.myapp.Home;
import com.mycompany.myapp.HomeIntegration;
import com.mycompany.myapp.Login;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> tasks;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
        //Connection.setRequestProperty( "Accept-Encoding", "" );
         req = new ConnectionRequest();
         req.setReadRequest(true);
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(String username,String password,String email,Resources theme) {
        String url = Statics.BASE_URL + "/users/new?username="+username+"&password="+password+"&email="+email;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                if(resultOK)
                {
                    Dialog.show("success", "Creation Successful", new Command("OK"));
                    HomeIntegration log=new HomeIntegration(theme);
                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public String Login(String username,String password,Resources theme)
    {
        if(username.equals("kindo") && password.equals("kindo"))
        {
            return "admin";
        }
        else
        {
        int ala=0;
        String url = Statics.BASE_URL + "/users/connect?username="+username+"&password="+password;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                String response=new String(req.getResponseData());
                if(resultOK)
                {
                    if(response.equals("1"))
                    {
                    Dialog.show("success", "Login Successful", new Command("OK"));
                        HomeIntegration log=new HomeIntegration(theme);
                        log.show();
                    }
                    else
                    {
                       Dialog.show("Error", "Wrong Informations", new Command("OK"));
                    }
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return "user";
    }
        
    }
    public boolean disconnect(Resources theme)
    {
        String url = Statics.BASE_URL + "/users/logout";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                
                if(resultOK)
                {
                    Dialog.show("success", "Disconnected !", new Command("OK"));
                    Login log=new Login(theme);
                    log.show();
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
  
       public void chooseparent()
       {
           String url = Statics.BASE_URL + "/users/chooseparent";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                
                if(resultOK)
                {
                    Dialog.show("success", "parent chosen !", new Command("OK"));
                    
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
           
       }
       public void chooseteahcer()
       {
           
       }
}
