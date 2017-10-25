package com.example.etix42.miniprojet4;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Downloader extends AppCompatActivity {

    public TextView tv;
    ArrayList<seisme> seismeList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloader);
        //on peut mettre un setContentView pour lier à un activite.xml

        Downloader.DownloadTask task = new Downloader.DownloadTask();
        task.execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom", tv);
    }


    public class DownloadTask extends AsyncTask<Object, Void, String> {
        @Override
        protected String doInBackground(Object... params) {

            String result = "";

            String titre = "";
            String description = "";

            String rssUrl = (String) params[0];
            tv = (TextView) params[1];


            try {
                URL url = new URL(rssUrl);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK) {

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(urlConnection.getInputStream(), "UTF-8");



                    String bufferTitle ="";
                    String bufferDescription ="";
                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        if ((eventType == XmlPullParser.START_TAG)) {
                            if (xpp.getName().equals("title")) {
                                titre = xpp.nextText();
                                //Log.d("titre",titre);
                                //result += titre + '\n';
                            }
                            else if (xpp.getName().equals("point")) {
                                description = xpp.nextText();
                                //Log.d("description",description);
                                //result += description + '\n';
                            }


                            //buffer qui vérifie si le dernier est pareil que le précédent
                            if(!titre.equalsIgnoreCase(bufferTitle)) {
                                seisme seisme = new seisme(titre, description);
                                seismeList.add(seisme);
                            }
                            bufferTitle = titre;
                            bufferDescription = bufferDescription;
                        }


                        eventType = xpp.next();
                    }

                    urlConnection.disconnect();
                }


            } catch (IOException e) {
                e.printStackTrace();
                result = "Problème d'acces au serveur";
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return result;
        }




        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //tv.setText(seismeList.toString());
            //Log.d("state", result);


            Intent intent = new Intent(Downloader.this, MainActivity.class);

            //jai rajouté ca
            /*String title = result;
            String coord = result;
            seisme seisme = new seisme(title, coord);
            seismeList.add(seisme);*/


            intent.putExtra("ListeSeisme", seismeList);


            startActivity(intent);

            finish();
        }
    }
}
