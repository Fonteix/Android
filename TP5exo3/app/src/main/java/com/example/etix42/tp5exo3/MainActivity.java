package com.example.etix42.tp5exo3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<contactList> liste = new ArrayList<>();
    EditText varName, varLastName, varMail, varPhone;
    File dossier = new File(Environment.getExternalStorageDirectory()+ File.separator + "contactDossier/");
    File fichier = new File(Environment.getExternalStorageDirectory()+ File.separator + "contactDossier/" + "contactDossier.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!dossier.exists()) {
            dossier.mkdirs();
        }

        if (!fichier.exists()) {
            try {
                fichier.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        varName = (EditText) findViewById(R.id.nameInput);
        varLastName = (EditText) findViewById(R.id.lastNameInput);
        varMail = (EditText) findViewById(R.id.mailInput);
        varPhone = (EditText) findViewById(R.id.phoneInput);


        try {
            FileInputStream fis = new FileInputStream(fichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            liste = (ArrayList<contactList>) ois.readObject();
            ois.close();
            fis.close();
        } catch(Exception E) {
            E.printStackTrace();
        }
    }

    //vérification des champs
    public void buttonAdd(View view) throws IOException {
    if(varName.getText().toString().length() == 0) {
        varName.setError("First name is required!");
    }
    else if(varLastName.getText().toString().length() == 0) {
        varLastName.setError("Last name is required!");
    }
    else if(varMail.getText().toString().length() == 0) {
        varMail.setError("Mail is required!");
    }
    else if(varPhone.getText().toString().length() == 0) {
        varPhone.setError("Phone is required!");
    }
    else { //transfert des input à l'autre vue
        Intent myIntent = new Intent(this, activityList.class);
        contactList contact = new contactList(
                varName.getText().toString(),
                varLastName.getText().toString(),
                varMail.getText().toString(),
                varPhone.getText().toString()
        );
        liste.add(contact);

        FileOutputStream fos = new FileOutputStream(fichier, false);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(liste);
        oos.close();
        fos.close();
        startActivity(myIntent);
    }
    }

    public void buttonContact(View view) {
        startActivity(new Intent(MainActivity.this, activityList.class));
    }
}
