package com.conchify.maps.android.utils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends Activity {

    private final static String mLogTag = "GeoJsonDemo";

    EditText UsernameEt, PasswordEt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        UsernameEt = (EditText) findViewById(R.id.etUserName);
        PasswordEt = (EditText) findViewById(R.id.etPassword);
    }

    public void OnLogin(View view) {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";


        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password);
    }

}
