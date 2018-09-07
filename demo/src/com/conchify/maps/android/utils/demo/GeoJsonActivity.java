package com.conchify.maps.android.utils.demo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonLineStringStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GeoJsonActivity extends BaseActivity implements OnMapReadyCallback {

    private final static String mLogTag = "GeoJson";


    protected int getLayoutId() {
        return R.layout.geojson;
    }

    @Override
    protected void startApp() {
        retrieveFileFromResource();
        //getLocationPermission();
    }

    private void retrieveFileFromUrl() {
        new DownloadGeoJsonFile().execute(getString(R.string.geojson_url));
    }

    public void retrieveFileFromResource() {
        try {
            GeoJsonLayer layerAll = new GeoJsonLayer(getMap(), R.raw.routes_all_test, this);
            addGeoJsonLayerToMap(layerAll);
        } catch (IOException e) {
            Log.e(mLogTag, "Archivo GeoJSON no pudo ser leido");
        } catch (JSONException e) {
            Log.e(mLogTag, "Archivo GeoJSON no pudo ser convertido a JSONObject");
        }
    }

    private void addColorsToLayers(GeoJsonLayer layer) {
        for (GeoJsonFeature feature : layer.getFeatures()) {
            if (feature.getProperty("title") != null && feature.hasProperty("hex")) {
                GeoJsonLineStringStyle layerStyle = new GeoJsonLineStringStyle();
                layerStyle.setColor(Color.parseColor(feature.getProperty("hex")));
                feature.setLineStringStyle(layerStyle);
            }
        }
    }


    private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonLayer> {

        @Override
        protected GeoJsonLayer doInBackground(String... params) {
            try {
                InputStream stream = new URL(params[0]).openStream();

                String line;
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }

                reader.close();
                stream.close();

                return new GeoJsonLayer(getMap(), new JSONObject(result.toString()));
            } catch (IOException e) {
                Log.e(mLogTag, "Archivo GeoJSON no pudo ser leido");
            } catch (JSONException e) {
                Log.e(mLogTag, "Archivo GeoJSON no pudo ser convertido a JSONObject");
            }
            return null;
        }

        @Override
        protected void onPostExecute(GeoJsonLayer layer) {
            if (layer != null) {
                addGeoJsonLayerToMap(layer);
            }
        }

    }

    private void addGeoJsonLayerToMap(GeoJsonLayer layer) {

        addColorsToLayers(layer);
        layer.addLayerToMap();
        getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(19.457258, -70.6888)));
        getMap().animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);
        layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                Toast.makeText(GeoJsonActivity.this,
                        "Ruta seleccionada: " + feature.getProperty("title"),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }
}

