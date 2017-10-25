package com.example.etix42.miniprojet4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView textViewTitle = (TextView)findViewById(R.id.TVTitle);
        TextView textViewDescription = (TextView)findViewById(R.id.TVDescription);

        Bundle bundle = getIntent().getExtras();

        String varTitle = bundle.getString("varTransferTitle");
        textViewTitle.setText("Title : " + varTitle);

        String varDescription = bundle.getString("varTransferDescription");
        textViewDescription.setText("Geographic position : " + varDescription);
    }
}
