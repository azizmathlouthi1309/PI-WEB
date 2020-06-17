/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author maiss
 */
public class publication {
    private int id;
    private String subject;
    private String content;
    private String class_name;
    private int teacher_id;
    public publication(){
        
    }
    public publication(int id, String subject, String content, String class_name, int teacher_id) {
        this.id = id;
        this.subject = subject;
        this.content = content;
        this.class_name = class_name;
        this.teacher_id = teacher_id;
    }
public publication( String subject, String content, String class_name, int teacher_id) {
        this.subject = subject;
        this.content = content;
        this.class_name = class_name;
        this.teacher_id = teacher_id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "publication{" + "id=" + id + ", subject=" + subject + ", content=" + content + ", class_name=" + class_name + ", teacher_id=" + teacher_id + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final publication other = (publication) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.teacher_id != other.teacher_id) {
            return false;
        }
        if (!Objects.equals(this.subject, other.subject)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.class_name, other.class_name)) {
            return false;
        }
        return true;
    }

}
