package com.example.tracking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {
private CardView ADD_bus,TRACK_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        getSupportActionBar().hide();
        ADD_bus=(CardView) findViewById(R.id.add_bus);
        TRACK_bus=(CardView) findViewById(R.id.Track_bus);
        getWindow().setStatusBarColor(ContextCompat.getColor(admin.this,R.color.green));
        ADD_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin.this,bus_details.class);
                startActivity(intent);
            }
        });
        TRACK_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin.this,track_bus.class);
                startActivity(intent);
            }
        });



    }

}