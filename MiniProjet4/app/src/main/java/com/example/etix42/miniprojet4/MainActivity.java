package com.example.etix42.miniprojet4;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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

    final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
    HashMap<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TestWifiData();


        Intent intent = getIntent();
        seismeListe = (ArrayList<seisme>) intent.getSerializableExtra("ListeSeisme");

        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);

        //final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        //HashMap<String, String> map;


        //color(varListView);



        //début du refresh--------------------------------------------------------------------------
        for (seisme seisme : seismeListe) {
            //Remplissage de la liste
            map = new HashMap<String, String>();
            map.put("Title", seisme.getTitle());
            map.put("Description", seisme.getDescription());
            map.put("Updated",seisme.getUpdated());
            map.put("Coord",seisme.getUpdated());
            listItem.add(map);
        }


        //affichage de la liste
        final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.list_layout,
                new String[] {"Title" /*on peut mettre , "autre chose"*/},
                new int[]{R.id.title}
        ) {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the current item from ListView
                View view = super.getView(position,convertView,parent);

                //varListView.toString().contains("M 5.4")
                if (position %2 == 1) {

                    view.setBackgroundColor(Color.parseColor("#99ccff"));
                }
                else {
                    view.setBackgroundColor(Color.parseColor("#ff9999"));
                }

                return view;
            }
        };


        varListView.setAdapter(adapter);
        //fin du refresh----------------------------------------------------------------------------


        //pression sur la liste, on va dans détails
        varListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Details.class);

                intent.putExtra("varTransferTitle", listItem.get(i).get("Title").toString());
                intent.putExtra("varTransferDescription", listItem.get(i).get("Description").toString());
                intent.putExtra("varTransferUpdated", listItem.get(i).get("Updated").toString());
                intent.putExtra("varTransferCoord", listItem.get(i).get("Coord").toString());

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
            map.put("Updated",seisme.getUpdated());
            map.put("Coord",seisme.getCoord());
            listItem.add(map);
        }


        //affichage de la liste
        final SimpleAdapter adapter = new SimpleAdapter (
                this.getBaseContext(),
                listItem,
                R.layout.list_layout,
                new String[] {"Title"},
                new int[]{R.id.title}
        );
        varListView.setAdapter(adapter);


    }

    /*public void color(View view) {
        varListView = (ListView) findViewById(R.id.listView);

        for(seisme seisme : seismeListe) {

            if (varListView.toString().contains("4")) {
                varListView.setBackgroundColor(Color.RED);
            }
            Log.d("varlistCOUCOUMANONview", varListView.toString());
        }
    }*/

}
