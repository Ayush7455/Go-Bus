package com.example.tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_admin extends AppCompatActivity {
    private EditText emailedit;
    private TextView resetbtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_admin);
        getSupportActionBar().hide();
        emailedit=(EditText)findViewById(R.id.email);
        resetbtn=(TextView) findViewById(R.id.resetpass);
        mAuth=FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(ContextCompat.getColor(forgot_admin.this,R.color.blue));
        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });

    }
    private void resetpassword(){
        String email=emailedit.getText().toString();
        if(email.isEmpty()){

            emailedit.setError("Email cannot be empty");
            emailedit.requestFocus();
            return;
        }
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(forgot_admin.this,"Check your email", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(forgot_admin.this,"No Admin rights are provided to this email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}