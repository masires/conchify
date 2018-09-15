package com.conchify.maps.android.utils.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button trayectoriaButton = findViewById(R.id.trayectoriaButton);
        Button quejasButton = findViewById(R.id.quejasButton);
        Button preguntasButton = findViewById(R.id.preguntasButton);
        trayectoriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), GeoJsonActivity.class));
            }
        });

        quejasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FormActivity.class));
            }
        });
        preguntasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FAQActivity.class));
            }
        });
    }
}
