package com.example.etix42.tp6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL url = new URL(rssUrl);
            HttpURLConnection urlConnection = urlConnection = (HttpURLConnection) url.openConnection();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(urlConnection.getInputStream(), "UTF-8");
                while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                    if ((xpp.getEventType() == XmlPullParser.START_TAG){
                        if (xpp.getName().equals(« title") Log.d("GetRSS", "balise ouvrante : title");
                    }
                    xpp.next();
                }
                in.close();
                urlConnection.disconnect();
            }catch (Exception e) {
                System.out.println("bug");
            }
    }
}
