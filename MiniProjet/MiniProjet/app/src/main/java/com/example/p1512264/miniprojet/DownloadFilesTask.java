package com.example.p1512264.miniprojet;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

//la version du downloader qui est sensé s'executer en se faisant appeler par le main

public class DownloadFilesTask extends AppCompatActivity {

    public TextView tv;
    ArrayList<seisme> seismeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_downloader);
        //on peut mettre un setContentView pour lier à un activite.xml

        }




    public class DownloadTask extends AsyncTask<Object, Void, String> {
        @Override
        protected String doInBackground(Object... params) {

            String result = "";
            String descriptions = "";

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

                    int eventType = xpp.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {

                        if ((eventType == XmlPullParser.START_TAG)) {
                            if (xpp.getName().equals("title")) {
                                titre = xpp.nextText();
                                //Log.d("titre",titre);
                                result += titre + '\n';
                            } else if (xpp.getName().equals("point")) {
                                description = xpp.nextText();
                                //Log.d("description",description);
                                descriptions += description + '\n';
                            }

                        }

                        seisme seisme = new seisme(titre, description);
                        seismeList.add(seisme);

                        eventType = xpp.next();
                    }
                    urlConnection.disconnect();
                }

                return result;

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
            Log.d("state", result);

            Intent intent = new Intent(DownloadFilesTask.this, MainActivity.class);
            //le bug est ici
            //intent.putExtra("ListeSeisme", seismeList);
            startActivity(intent);

            finish();
        }
    }
}
