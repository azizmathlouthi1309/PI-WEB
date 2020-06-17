/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.entities;

/**
 *
 * @author Aziz
 */
public class View {
    private String sendername;
    private String subject;
    private String message;
    private int nbstars;
    public View()
    {
        //qsdq
    }
    public View(String sender,String subj,String message,int nbstars)
    {
        this.sendername=sender;
        this.subject=subj;
        this.nbstars=nbstars;
        this.message=message;        
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNbstars() {
        return nbstars;
    }

    public void setNbstars(int nbstars) {
        this.nbstars = nbstars;
    }
    
    
}
