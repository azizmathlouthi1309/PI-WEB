/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Ahmed
 */
public class Reclamation {
    private int id;
    private String subject;
    private String message;
    private int teacher_id;
    private String type;
    private int parent_id;
    private int status;
    private String response;

    public Reclamation(int id, String subject, String message, int teacher_id, String type, int parent_id, int status, String response) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.teacher_id = teacher_id;
        this.type = type;
        this.parent_id = parent_id;
        this.status = status;
        this.response = response;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.subject);
        hash = 89 * hash + Objects.hashCode(this.message);
        hash = 89 * hash + this.teacher_id;
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + this.parent_id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.teacher_id != other.teacher_id) {
            return false;
        }
        if (this.parent_id != other.parent_id) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", subject=" + subject + ", message=" + message + ", teacher_id=" + teacher_id + ", type=" + type + ", parent_id=" + parent_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public Reclamation(String response) {
        this.response = response;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String getResponse() {
        return response;
    }

    public void setType(String type) {
        this.type = type;
    }
    public int getStatus()
    {
        return status;
    }
    public Reclamation(int id, String subject, String message, int teacher_id, String type, int parent_id) {
        this.id = id;
        this.subject = subject;
        this.message = message;
        this.teacher_id = teacher_id;
        this.type = type;
        this.parent_id = parent_id;
    }
    
}
