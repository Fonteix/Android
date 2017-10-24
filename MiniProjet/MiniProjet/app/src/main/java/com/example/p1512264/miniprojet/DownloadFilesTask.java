package com.example.p1512264.miniprojet;

import android.os.AsyncTask;
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

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by p1512264 on 23/10/2017.
 */

public class DownloadFilesTask extends AsyncTask<Object, Void, String> {

    public TextView tv;


    @Override
    protected String doInBackground(Object... params) {

        String titres="";
        String descriptions="";
        String rssUrl = (String) params[0];
        tv = (TextView) params[1];
        try {
            URL url = new URL(rssUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpsURLConnection.HTTP_OK){

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(urlConnection.getInputStream(), "UTF-8");

                int eventType = xpp.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {

                    if ((eventType == XmlPullParser.START_TAG)){
                        if (xpp.getName().equals("title")) {
                            String titre = xpp.nextText();
                            Log.d("titre",titre);
                            titres += titre +'\n';
                        }


                            /*else if ((xpp.getName().contains("georss")) && (xpp.getName().contains("point"))) {
                                String description = xpp.nextText();
                                Log.d("description",description);
                                descriptions += description +'\n';
                            }*/

                        /*else if(xpp.getName().equals("link")) {
                            String link = xpp.nextText();
                        }*/

                    }
                    eventType = xpp.next();
                }
              urlConnection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
            titres = "Problème d'acces au serveur";
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return titres;
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        tv.setText(result);
        Log.d("state", result);
    }

}
