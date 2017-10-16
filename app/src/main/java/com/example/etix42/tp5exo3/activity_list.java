package com.example.etix42.tp5exo3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class activity_list extends AppCompatActivity {

    ListView varListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);
        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String,String>>();
        HashMap<String, String>map;


        //Récupération des arguments de l'autre vue et on met dans la liste
        map = new HashMap<String, String>();
        map.put("Name", getIntent().getStringExtra("varTransferName"));
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



        //Sauvegarde de la hashmap






        //pression sur la list
        varListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(activity_list.this, listItem.get(i).get("Name") + "\n" + listItem.get(i).get("Phone").toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    //Bouton retour
    public void buttonPress(View view) {
        startActivity(new Intent(activity_list.this, MainActivity.class));
    }
}
