package com.example.etix42.tp5exo3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


public class activityList extends AppCompatActivity {

    ListView varListView;
    File fichier = new File(Environment.getExternalStorageDirectory()+ File.separator + "contactDossier/" + "contactDossier.txt");
    ArrayList<contactList> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Lecture de la liste
        try {
            FileInputStream fis = new FileInputStream(fichier);
            ObjectInputStream ois = new ObjectInputStream(fis);
            liste = (ArrayList<contactList>) ois.readObject();
            ois.close();
            fis.close();
        } catch(Exception E) {
            E.printStackTrace();
        }


        //Création de la liste
        varListView = (ListView) findViewById(R.id.listView);
        final ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String,String>>();
        HashMap<String, String>map;

        for (contactList contact : liste) {
            //Récupération des arguments de l'autre vue et on met dans la liste
            map = new HashMap<String, String>();
            map.put("Name", contact.getName());
            map.put("LastName", contact.getLastName());
            map.put("Mail", contact.getMail());
            map.put("Phone", contact.getPhone());
            listItem.add(map);
        }


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
                final int index = i;

                //on supprime le contact au rang i dans les listes
                listItem.remove(i);
                liste.remove(i);

                //on écrase la liste pour supprimer le contact au rang i
                try {
                    FileOutputStream fos = new FileOutputStream(fichier, false);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(liste);
                    oos.close();
                    fos.close();
                } catch(Exception E) {
                    E.printStackTrace();
                }

                //refresh la liste vue
                adapter.notifyDataSetChanged();

                return false;
            }
        });
    }


    //Bouton retour
    public void buttonPress(View view) {
        startActivity(new Intent(activityList.this, MainActivity.class));
    }
}
