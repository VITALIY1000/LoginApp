package com.vitalytyrenko.loginapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public final static String APP_PREFERENCES = "APP_PREFERENCES";
    public final static String LOGIN = "LOGIN";
    public final static String PASSWORD = "PASSWORD";
    public final static int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences mySharedPref =
                getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if (mySharedPref.contains(LOGIN)) {
            TextView textview = findViewById(R.id.login_text);
            String outputText = getResources().getString(R.string.login_message)
                    + getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                    .getString(LOGIN, "");

            textview.setText(outputText);
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                TextView textview = findViewById(R.id.login_text);
                String outputText = getResources().getString(R.string.login_message)
                        + getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                        .getString(LOGIN, "");

                textview.setText(outputText);
            } else {
                finish();
            }
        }
    }

    public void onExitButtonClicked(View view) {
        getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE).edit().clear().apply();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, REQUEST_CODE);
    }
}
