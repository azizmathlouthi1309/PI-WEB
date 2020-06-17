/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author khaoula
 */
public class participation {
    private int parent_id;
    private int event_id;
    private String state;

    public participation(int parent_id, int event_id) {
        this.parent_id = parent_id;
        this.event_id = event_id;
    }
            

    public participation() {
    }

    public participation(int parent_id, int event_id, String state) {
        this.parent_id = parent_id;
        this.event_id = event_id;
        this.state = state;
    }

    public int getParent_id() {
        return parent_id;
    }

    @Override
    public String toString() {
        return "participation{" + "parent_id=" + parent_id + ", event_id=" + event_id + ", state=" + state + '}';
    }

    public int getEvent_id() {
        return event_id;
    }

    public String getState() {
        return state;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setState(String state) {
        this.state = state;
    }

    
    
    
    
}
