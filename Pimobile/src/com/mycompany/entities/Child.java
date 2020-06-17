/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Ahmed
 */
public class Child {
    private int id;
    private String lastname;
    private String firstname;
    private int level;
    private int parent_id;
    private int class_id;
    private int age;
    private String photo;
    private int teacher_id;

    @Override
    public String toString() {
        return "child{" + "id=" + id + ", lastname=" + lastname + ", firstname=" + firstname + ", level=" + level + ", parent_id=" + parent_id + ", class_id=" + class_id + ", age=" + age + ", photo=" + photo + ", teacher_id=" + teacher_id + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Child() {
    }

    public Child(int id, String lastname, String firstname, int level, int parent_id, int class_id, int age, String photo, int teacher_id) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.level = level;
        this.parent_id = parent_id;
        this.class_id = class_id;
        this.age = age;
        this.photo = photo;
        this.teacher_id = teacher_id;
    }

}

