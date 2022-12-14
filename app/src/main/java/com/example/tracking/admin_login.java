package com.example.tracking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class admin_login extends AppCompatActivity {
    private TextView newpass;
    private TextView Login;
    private FirebaseAuth mAuth;
    private EditText Email;
    private EditText Password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        getSupportActionBar().hide();
        newpass=(TextView) findViewById(R.id.forgot);
        Email=(EditText)findViewById(R.id.email);
        Password=(EditText)findViewById(R.id.pass);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        Login=(TextView)findViewById(R.id.login);
        mAuth = FirebaseAuth.getInstance();
        getWindow().setStatusBarColor(ContextCompat.getColor(admin_login.this,R.color.blue));
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginuser();
            }
        });

        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(admin_login.this, forgot_admin.class);
                startActivity(intent);
            }
        });
    }

    private void loginuser(){
        String email=Email.getText().toString();
        String password=Password.getText().toString();
        if(TextUtils.isEmpty(email)){
            Email.setError("Email cannot be empty");
        }
        else if(TextUtils.isEmpty(password)){
            Password.setError("Password cannot be empty");
        }
        else{
//            Login.setEnabled(false);

            progressBar.setVisibility(View.VISIBLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Email.setText("");
                        Password.setText("");
                        Toast.makeText(admin_login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(admin_login.this, admin.class));
                        progressBar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                    else{
                        Toast.makeText(admin_login.this, "Log in Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    }
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        progressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}