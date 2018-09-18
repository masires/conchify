package com.conchify.maps.android.utils.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class FormActivity extends Activity {
    private WebView myWebView;
    private String user = "conchify@contru.net";
    private String pwd = "zO&wP9qA&#Ug947CZvO**qc6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);
        myWebView = (WebView) findViewById(R.id.webView);
        String url = "https://contru.net";
        myWebView.loadUrl(url);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:{document.getElementsByName(\"email\")[0].value = '" + user + "';};");

            }
        });
//        final String js = "javascript:document.getElementById('";
//        webSettings.setDomStorageEnabled(true);
//        myWebView.setWebViewClient(new WebViewClient());

    }

    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
//    private final static String mLogTag = "FormDemo";
//
//    EditText etNumero_placa_id, etNumero_carnet_id, etNumero_franja_id, etComentario,
//            etTipo_Queja, etEstado, etComentario_cierra, etAccion_diciplinaria;
//
//    String str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id, str_etComentario, str_etTipo_Queja, str_etEstado, str_etComentario_cierra, str_etAccion_diciplinaria;
//
//    Button btnSendFormInfo;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.form);
//        etNumero_placa_id = findViewById(R.id.etNumero_placa_id);
//        etNumero_carnet_id = findViewById(R.id.etNumero_carnet_id);
//        etNumero_franja_id = findViewById(R.id.etNumero_franja_id);
//        etComentario = findViewById(R.id.etComentario);
//        etTipo_Queja = findViewById(R.id.etTipo_Queja);
//        etEstado = findViewById(R.id.etEstado);
//        etComentario_cierra = findViewById(R.id.etComentario_cierre);
//        etAccion_diciplinaria = findViewById(R.id.etAccion_diciplinaria);
//
//    }
//
//    public void OnFormInfo(View view) {
//        str_etNumero_place_id = etNumero_placa_id.getText().toString();
//        str_etNumero_carnet_id = etNumero_carnet_id.getText().toString();
//        str_etNumero_franja_id = etNumero_franja_id.getText().toString();
//        str_etComentario = etComentario.getText().toString();
//        str_etTipo_Queja = etTipo_Queja.getText().toString();
//        str_etEstado = etEstado.getText().toString();
//        str_etComentario_cierra = etComentario_cierra.getText().toString();
//        str_etAccion_diciplinaria = etAccion_diciplinaria.getText().toString();
//
//        String type = "form";
//        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
//        backgroundWorker.execute(type, str_etNumero_place_id, str_etNumero_carnet_id, str_etNumero_franja_id, str_etComentario,
//                str_etTipo_Queja, str_etEstado, str_etComentario_cierra, str_etAccion_diciplinaria);
//        finish();
//    }
//

//}

