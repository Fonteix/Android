package com.example.etix42.miniprojet4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView varListView;
    ArrayList<seisme> seismeListe = new ArrayList<>();
    TextView tv;
    EditText title, link, description;
    Button b1, b2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.title);
    }


    public void onClick(View view) {
        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);
        seismeListe = (ArrayList<seisme>) tv.getText();
        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;


/*
        for (seisme seisme : seismeListe) {
            //Remplissage de la liste
            map = new HashMap<String, String>();
            map.put("Title", seisme.getTitle());
            map.put("Description", seisme.getDescription());
            listItem.add(map);
        }
*/

        //affichage de la liste
        /*final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.list_layout,
                new String[] {"Title", "Description"},
                new int[]{R.id.title, R.id.description}
        );
        varListView.setAdapter(adapter);*/


    }
}
