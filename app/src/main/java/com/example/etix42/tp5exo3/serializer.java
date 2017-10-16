package com.example.etix42.tp5exo3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class serializer {

    static contactList c;

    //public static void main(String[] args) throws IOException, ClassNotFoundException {
    public  static  void serializer1(String arg1, String arg2) throws IOException, ClassNotFoundException {
        String filename = "time.ser";
        c = new contactList(arg1, arg2);

        // save the object to file
        FileOutputStream fos =  new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(c);
        out.close();

        // read the object from file
        // save the object to file
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fis);
        c = (contactList) in.readObject();
        in.close();

        System.out.println(c);
    }
}