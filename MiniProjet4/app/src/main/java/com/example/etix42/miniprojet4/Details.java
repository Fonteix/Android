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
        TextView textViewUpdated = (TextView)findViewById(R.id.TVUpdated);
        TextView textViewCoord = (TextView)findViewById(R.id.TVCoord);

        Bundle bundle = getIntent().getExtras();

        String varTitle = bundle.getString("varTransferTitle");
        textViewTitle.setText("Title : " + varTitle);

        String varDescription = bundle.getString("varTransferDescription");
        textViewDescription.setText("Summary : " + varDescription);

        String varUpdated = bundle.getString("varTransferUpdated");
        textViewUpdated.setText("Last updated : " + varUpdated);

        String varCoord = bundle.getString("varTransferCoord");
        textViewCoord.setText("Geographic position : " + varCoord);
    }
}
