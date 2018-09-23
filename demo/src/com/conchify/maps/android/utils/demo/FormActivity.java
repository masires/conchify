package com.conchify.maps.android.utils.demo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class FormActivity extends Activity {

    private final static String mLogTag = "FormDemo";
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    TextView tvDisplayDate;

    EditText etCorreo_Electronico_id, etNumero_placa_id, etNumero_carnet_id, etNumero_franja_id, etComentario;

    String str_etCorreo_Electronico_id, str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id, str_etComentario, str_fecha;

    Button btnSendFormInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        tvDisplayDate = (TextView) findViewById(R.id.tvDate);
        etCorreo_Electronico_id = findViewById(R.id.etCorreo_Electronico_id);
        etNumero_placa_id = findViewById(R.id.etNumero_placa_id);
        etNumero_carnet_id = findViewById(R.id.etNumero_carnet_id);
        etNumero_franja_id = findViewById(R.id.etNumero_franja_id);
        etComentario = findViewById(R.id.etComentario);

        tvDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        FormActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d(mLogTag, "onDateSet: Date: " + year + "/" + month + "/" + dayOfMonth);

                String date = month + "/" + dayOfMonth + "/" + year;
                tvDisplayDate.setText(date);
            }
        };


    }

    public void OnFormInfo(View view) {
        str_etCorreo_Electronico_id = etCorreo_Electronico_id.getText().toString();
        str_etNumero_place_id = etNumero_placa_id.getText().toString();
        str_etNumero_carnet_id = etNumero_carnet_id.getText().toString();
        str_etNumero_franja_id = etNumero_franja_id.getText().toString();
        str_etComentario = etComentario.getText().toString();
        str_fecha = tvDisplayDate.getText().toString();

        if (str_etNumero_place_id.equals("") && str_etNumero_carnet_id.equals("") && str_etNumero_franja_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Debe especificar una franja, placa o carnet para enviar una queja.", Toast.LENGTH_SHORT).show();
        } else {
            String type = "form";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, str_etCorreo_Electronico_id, str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id,
                    str_fecha, str_etComentario);
            finish();
        }
    }


}

