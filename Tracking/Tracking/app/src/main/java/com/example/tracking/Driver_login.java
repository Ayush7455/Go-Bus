package com.example.tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class Driver_login extends AppCompatActivity {
EditText bus_no,password;
Button login;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);
        bus_no=findViewById(R.id.name);
        password=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference docRef = db.collection("Buses").document(bus_no.getText().toString());
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Map<String,Object> user=document.getData();
                                Toast.makeText(Driver_login.this, "DDDD"+user.get("password").toString().equals(password.getText().toString()), Toast.LENGTH_SHORT).show();
                                if(user.get("password").toString().equals(password.getText().toString()))
                                {
                                    SharedPreferences sp=getSharedPreferences("MyData",MODE_PRIVATE);
                                    SharedPreferences.Editor editor= sp.edit();
                                    editor.putString("busnumber",bus_no.getText().toString());
                                    editor.commit();
                                    Intent intent=new Intent(Driver_login.this,driver_location.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                  //  Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                              //  Toast.makeText(MainActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                          //  Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}