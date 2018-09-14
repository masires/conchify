package com.conchify.maps.android.utils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends Activity {

    private final static String mLogTag = "FormDemo";

    EditText etNumero_placa_id, etNumero_carnet_id, etNumero_franja_id, etComentario,
            etTipo_Queja, etEstado, etComentario_cierra, etAccion_diciplinaria;

    String str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id, str_etComentario, str_etTipo_Queja, str_etEstado, str_etComentario_cierra, str_etAccion_diciplinaria;

    Button btnSendFormInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        etNumero_placa_id = findViewById(R.id.etNumero_placa_id);
        etNumero_carnet_id = findViewById(R.id.etNumero_carnet_id);
        etNumero_franja_id = findViewById(R.id.etNumero_franja_id);
        etComentario = findViewById(R.id.etComentario);
        etTipo_Queja = findViewById(R.id.etTipo_Queja);
        etEstado = findViewById(R.id.etEstado);
        etComentario_cierra = findViewById(R.id.etComentario_cierre);
        etAccion_diciplinaria = findViewById(R.id.etAccion_diciplinaria);


    }

    public void OnFormInfo(View view) {
        str_etNumero_place_id = etNumero_placa_id.getText().toString();
        str_etNumero_carnet_id = etNumero_carnet_id.getText().toString();
        str_etNumero_franja_id = etNumero_franja_id.getText().toString();
        str_etComentario = etComentario.getText().toString();
        str_etTipo_Queja = etTipo_Queja.getText().toString();
        str_etEstado = etEstado.getText().toString();
        str_etComentario_cierra = etComentario_cierra.getText().toString();
        str_etAccion_diciplinaria = etAccion_diciplinaria.getText().toString();

        String type = "form";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id, str_etComentario,
                str_etTipo_Queja, str_etEstado, str_etComentario_cierra, str_etAccion_diciplinaria);
        finish();
    }


}

