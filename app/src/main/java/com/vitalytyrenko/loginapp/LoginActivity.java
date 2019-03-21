package com.vitalytyrenko.loginapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText editText1, editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ad;
        Context context = this;
        String title = getResources().getString(R.string.alert_dialog_title);
        String message = getResources().getString(R.string.alert_dialog_message);
        String button1String = getResources().getString(R.string.alert_dialog_button_ok);
        String button2String = getResources().getString(R.string.alert_dialog_button_cancel);

        ad = new AlertDialog.Builder(context);
        ad.setTitle(title);  // заголовок
        ad.setMessage(message); // сообщение
        ad.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        ad.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });

        ad.show();
    }

    public void onLoginButtonClicked(View view) {
        SharedPreferences mySharedPref =
                getSharedPreferences(MainActivity.APP_PREFERENCES, Context.MODE_PRIVATE);

        String login = editText1.getText().toString();
        String password = editText2.getText().toString();

        if (!(login.isEmpty() || password.isEmpty())) {
            mySharedPref.edit().putString(MainActivity.LOGIN, login).apply();
            mySharedPref.edit().putString(MainActivity.PASSWORD, password).apply();

            setResult(RESULT_OK);
            finish();
        } else {
            showToast(getResources().getString(R.string.warning_null_data));
        }
    }

    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
