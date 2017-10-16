package com.example.etix42.tp5exo3;

import java.io.Serializable;

public class contactList implements Serializable {

    private String name;
    private String phone;

    transient private Thread myThread;

    public contactList(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.myThread = new Thread();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact : name=" + name + ", phone=" + phone + "";
    }


}
