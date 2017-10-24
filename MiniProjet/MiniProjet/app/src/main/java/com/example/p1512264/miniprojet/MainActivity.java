package com.example.p1512264.miniprojet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    EditText title,link,description;
    Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.title);

        //title = (EditText) findViewById(R.id.textView);
        // link = (EditText) findViewById(R.id.editText2);
        // description = (EditText) findViewById(R.id.editText3);

        //    b1=(Button)findViewById(R.id.btnDateHeure);
        // b2=(Button)findViewById(R.id.button2);

        /*while (obj.parsingComplete) ;
        title.setText(obj.getTitle());
        link.setText(obj.getLink());
        description.setText(obj.getDescription());*/
    }


    public void onClick(View view) {
        DownloadFilesTask downloadFilesTask = new DownloadFilesTask();
        downloadFilesTask.execute("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom",tv);
    }


}
