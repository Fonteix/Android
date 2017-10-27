package com.example.etix42.miniprojet4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    float longitude;
    float latitude;
    String varTitle, varDescription, varUpdated, varCoord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        TextView textViewTitle = (TextView)findViewById(R.id.TVTitle);
        TextView textViewDescription = (TextView)findViewById(R.id.TVDescription);
        TextView textViewUpdated = (TextView)findViewById(R.id.TVUpdated);
        TextView textViewCoord = (TextView)findViewById(R.id.TVCoord);

        Bundle bundle = getIntent().getExtras();

        varTitle = bundle.getString("varTransferTitle");
        textViewTitle.setText(varTitle); //on peut mettre "Title " + varTitle

        varDescription = bundle.getString("varTransferDescription");
        textViewDescription.setText(varDescription);

        varUpdated = bundle.getString("varTransferUpdated");
        textViewUpdated.setText(varUpdated);

        varCoord = bundle.getString("varTransferCoord");
        textViewCoord.setText(varCoord);


        String[] decoupe = varCoord.split(" ");
        longitude = Float.valueOf(decoupe[0]);
        latitude = Float.valueOf(decoupe[1]);
    }

    public void onClickMaps(View view) {
        //Passe à maps

        Intent intent = new Intent(Details.this, MapsActivity.class);
        intent.putExtra("Title", varTitle);
        intent.putExtra("Longitude", longitude);
        intent.putExtra("Latitude", latitude);
        startActivity(intent);

    }

}
