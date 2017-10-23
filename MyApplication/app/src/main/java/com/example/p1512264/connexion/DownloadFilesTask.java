package com.example.p1512264.connexion;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by p1512264 on 19/10/2017.
 */

public class DownloadFilesTask extends AsyncTask<Object, Void, String> {

    public TextView tv;

    @Override
    protected String doInBackground(Object... params) {

        String time="";
        String strUrl = (String) params[0];
        tv = (TextView) params[1];
        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader in = new BufferedReader( new InputStreamReader(urlConnection.getInputStream() ) );
                time = in.readLine();
                in.close(); //ferme le flux
            }
        } catch (IOException e) {
            e.printStackTrace();
            time = "Problème d'acces au serveur";
        }
        return time;
    };

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        tv.setText(result);
    }


}
