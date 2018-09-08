package com.conchify.maps.android.utils.demo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.conchify.modelo.Queja;
import com.conchify.modelo.dao.servicios.QuejaService;
import com.conchify.modelo.enums.Estado;
import com.conchify.modelo.enums.Tipo_Queja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundWorker extends AsyncTask<String, Void, String> {

    Context context;
    AlertDialog alertDialog;

    BackgroundWorker(Context ctx) {
        context = ctx;
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String form_url = "https://contru.net/formas.php";

        if (type.equals("form")) {
//            try {
                String etNumero_placa_id = params[1];
                String etNumero_carnet_id = params[2];
                String etNumero_franja_id = params[3];
                String etComentario = params[4];
                String etTipo_Queja = params[5];
                String etEsatado = params[6];
                String etComentario_cierre = params[7];
                String etAccion_diciplinaria = params[8];

            QuejaService quejaService = new QuejaService();

            Tipo_Queja tipo_queja = Tipo_Queja.valueOf(etTipo_Queja);
            Estado estado = Estado.valueOf(etEsatado);
            Queja queja = new Queja(Integer.valueOf(etNumero_placa_id), Integer.valueOf(etNumero_carnet_id), Integer.valueOf(etNumero_franja_id), etComentario, tipo_queja, estado, etComentario_cierre);

            quejaService.insert(queja);

//            Toast.makeText(context,"Se mando diq",Toast.LENGTH_SHORT).show();
//                URL url = new URL(form_url);
//                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//                httpURLConnection.setRequestMethod("POST");
//                httpURLConnection.setDoOutput(true);
//                httpURLConnection.setDoInput(true);
//                OutputStream outputStream = httpURLConnection.getOutputStream();
//                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                String post_data = URLEncoder.encode("numero_placa_id", "UTF-8") + "=" + URLEncoder.encode(etNumero_placa_id, "UTF-8") + "&"
//                        + URLEncoder.encode("numero_carnet_id", "UTF-8") + "=" + URLEncoder.encode(etNumero_carnet_id, "UTF-8") + "&"
//                        + URLEncoder.encode("numero_franja_id", "UTF-8") + "=" + URLEncoder.encode(etNumero_franja_id, "UTF-8") + "&"
//                        + URLEncoder.encode("comentario", "UTF-8") + "=" + URLEncoder.encode(etComentario, "UTF-8") + "&"
//                        + URLEncoder.encode("tipo_queja", "UTF-8") + "=" + URLEncoder.encode(etTipo_Queja, "UTF-8") + "&"
//                        + URLEncoder.encode("estado", "UTF-8") + "=" + URLEncoder.encode(etEsatado, "UTF-8") + "&"
//                        + URLEncoder.encode("comentario_cierre", "UTF-8") + "=" + URLEncoder.encode(etComentario_cierre, "UTF-8") + "&"
//                        + URLEncoder.encode("accion_diciplinaria", "UTF-8") + "=" + URLEncoder.encode(etAccion_diciplinaria, "UTF-8");
//                bufferedWriter.write(post_data);
//                bufferedWriter.flush();
//                bufferedWriter.close();
//                outputStream.close();
//                InputStream inputStream = httpURLConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
//                String result = "";
//                String line = "";
//                while ((line = bufferedReader.readLine()) != null) {
//                    result += line;
//                }
//                bufferedReader.close();
//                inputStream.close();
//                httpURLConnection.disconnect();
//                return result;

//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Form Status");
        }

    @Override
    protected void onPostExecute(String result) {
        alertDialog.setMessage(result);
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
