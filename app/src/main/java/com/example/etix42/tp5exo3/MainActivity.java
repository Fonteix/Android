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

    ListView varListView;
    EditText varName, varPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        varName = (EditText) findViewById(R.id.nameInput);
        varPhone = (EditText) findViewById(R.id.phoneInput);

    }


    public void buttonAdd(View view) {
    if(varName.getText().toString().length() == 0)
        varName.setError("Name is required!");
    else if(varPhone.getText().toString().length() == 0) {
        varPhone.setError("Phone is required!");
    }
    else {
        Intent myIntent = new Intent(this, activityList.class);
        myIntent.putExtra("varTransferName", varName.getText().toString());
        myIntent.putExtra("varTransferPhone", varName.getText().toString());
        startActivity(myIntent);
    }
    }

    public void buttonContact(View view) {
        startActivity(new Intent(MainActivity.this, activityList.class));
    }
}
