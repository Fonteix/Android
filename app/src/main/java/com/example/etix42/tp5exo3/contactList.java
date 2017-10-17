package com.example.etix42.tp5exo3;

import java.io.Serializable;

public class contactList implements Serializable {

    private String name;
    private String lastName;
    private String mail;
    private String phone;


    transient private Thread myThread;

    public contactList(String name, String lastName, String mail, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.myThread = new Thread();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact : name=" + name + ", phone=" + phone + "";
    }


}
