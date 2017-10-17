package com.example.etix42.tp5exo3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import static android.R.string.ok;
import static com.example.etix42.tp5exo3.serializer.c;
import static com.example.etix42.tp5exo3.serializer.serializer1;

public class activityList extends AppCompatActivity {

    ListView varListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);
        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String,String>>();
        final HashMap<String, String>map;


        //Récupération des arguments de l'autre vue et on met dans la liste
        map = new HashMap<String, String>();
        map.put("Name", getIntent().getStringExtra("varTransferName"));
        map.put("LastName", getIntent().getStringExtra("varTransferLastName"));
        map.put("Mail", getIntent().getStringExtra("varTransferMail"));
        map.put("Phone", getIntent().getStringExtra("varTransferPhone"));
        listItem.add(map);


        //Affichage de la liste
        final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.affichageitem,
                new String[] {"Name", "Phone"},
                new int[]{R.id.name, R.id.phone}
        );
        varListView.setAdapter(adapter);


        //pression sur la liste
        varListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(
                        activityList.this,
                        listItem.get(i).get("Name") + "\n"+
                        listItem.get(i).get("LastName") + "\n"+
                        listItem.get(i).get("Mail") + "\n"+
                        listItem.get(i).get("Phone").toString(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });


        //pression longue sur la liste : suppression
        varListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activityList.this, "Delete " + listItem.get(i).get("Name"), Toast.LENGTH_SHORT).show();
                map.remove(listItem.get(i));
                return false;
            }
        });

    }



    //Sauvegarde de la hashmap
    public void buttonSave(View view) throws IOException, ClassNotFoundException {

        serializer1("test", "test", "test@gmail.com", "06");

        Toast.makeText(activityList.this, "Liste : " + c, Toast.LENGTH_SHORT).show();


    }

    //affichage de la hashmap
    public void buttonShow(View view) {
        Toast.makeText(activityList.this, "Liste : " + c, Toast.LENGTH_SHORT).show();
    }





    //Bouton retour
    public void buttonPress(View view) {
        startActivity(new Intent(activityList.this, MainActivity.class));
    }
}
