package com.example.etix42.miniprojet4;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView varListView;
    ArrayList<seisme> seismeListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestWifiData();


        Intent intent = getIntent();
        seismeListe = (ArrayList<seisme>) intent.getSerializableExtra("ListeSeisme");

        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);

        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;



        //début du refresh--------------------------------------------------------------------------
        for (seisme seisme : seismeListe) {
            //Remplissage de la liste
            map = new HashMap<String, String>();
            map.put("Title", seisme.getTitle());
            map.put("Description", seisme.getDescription());
            listItem.add(map);
        }


        //affichage de la liste
        final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.list_layout,
                new String[] {"Title", "Description"},
                new int[]{R.id.title, R.id.description}
        );
        varListView.setAdapter(adapter);
        //fin du refresh----------------------------------------------------------------------------


        //pression sur la liste, on va dans détails
        varListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Details.class);

                intent.putExtra("varTransferTitle", listItem.get(i).get("Title").toString());
                intent.putExtra("varTransferDescription", listItem.get(i).get("Description").toString());

                startActivity(intent);
            }
        });
    }


    public boolean TestWifiData() {
        boolean isConnected = false, isWiFi = false;

        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            assert activeNetwork != null;
            isWiFi = activeNetwork.getType() == ConnectivityManager.TYPE_WIFI;

        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "You are offline", Toast.LENGTH_LONG).show();
        }

        if (isConnected) {
            if (isWiFi) {
                Toast.makeText(MainActivity.this, "Connected to Wifi", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Connected to 3G", Toast.LENGTH_LONG).show();
            }
        }
        return isConnected;
    }



    public void onClick(View view) {
        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);

        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;



        for (seisme seisme : seismeListe) {
            //Remplissage de la liste
            map = new HashMap<String, String>();
            map.put("Title", seisme.getTitle());
            map.put("Description", seisme.getDescription());
            listItem.add(map);
        }


        //affichage de la liste
        final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.list_layout,
                new String[] {"Title", "Description"},
                new int[]{R.id.title, R.id.description}
        );
        varListView.setAdapter(adapter);


    }

}
