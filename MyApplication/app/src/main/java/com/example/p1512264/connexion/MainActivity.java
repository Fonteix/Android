package com.example.p1512264.connexion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.textView);
    }


    public void onClick(View view) {
        DownloadFilesTask downloadFilesTask = new DownloadFilesTask();
        downloadFilesTask.execute("http://10.0.2.2:3402/time",tv);
    }

}
