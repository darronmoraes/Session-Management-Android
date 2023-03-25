package com.example.loginsessionmanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedPreferences;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Initialize
        EditText etEmail = findViewById(R.id.id_et_email);
        EditText etPassword = findViewById(R.id.id_et_password);
        Button btnLogin = findViewById(R.id.id_btn_login);

        // getting data stored in sp
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        //
        email = sharedPreferences.getString(EMAIL_KEY, null);
        password = sharedPreferences.getString(PASSWORD_KEY, null);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etEmail.getText().toString()) && TextUtils.isEmpty(etPassword.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(EMAIL_KEY, etEmail.getText().toString());
                    editor.putString(PASSWORD_KEY, etPassword.getText().toString());

                    editor.apply();

                    startHomeActivity();
                }
            }
        });

    }

    public void startHomeActivity() {
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (email != null && password != null) {
            Intent i = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(i);
        }
    }
}