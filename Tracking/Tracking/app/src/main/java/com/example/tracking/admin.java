package com.example.tracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class admin extends AppCompatActivity {
Button add_bus,track_bus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add_bus=findViewById(R.id.add_bus);
        track_bus=findViewById(R.id.Track_bus);
        add_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin.this,bus_details.class);
                startActivity(intent);
            }
        });
        track_bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(admin.this,track_bus.class);
                startActivity(intent);
            }
        });
    }
}