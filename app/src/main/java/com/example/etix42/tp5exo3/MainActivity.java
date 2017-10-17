package com.example.etix42.tp5exo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {

    EditText varName, varLastName, varMail, varPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        varName = (EditText) findViewById(R.id.nameInput);
        varLastName = (EditText) findViewById(R.id.lastNameInput);
        varMail = (EditText) findViewById(R.id.mailInput);
        varPhone = (EditText) findViewById(R.id.phoneInput);
    }

    //vérification des champs
    public void buttonAdd(View view) {
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
        myIntent.putExtra("varTransferName", varName.getText().toString());
        myIntent.putExtra("varTransferLastName", varLastName.getText().toString());
        myIntent.putExtra("varTransferMail", varMail.getText().toString());
        myIntent.putExtra("varTransferPhone", varPhone.getText().toString());
        startActivity(myIntent);
    }
    }

    public void buttonContact(View view) {
        startActivity(new Intent(MainActivity.this, activityList.class));
    }
}
