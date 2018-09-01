package com.conchify.maps.android.utils.demo;

import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonLayer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GeoJsonActivity extends BaseActivity implements OnMapReadyCallback {

    private final static String mLogTag = "GeoJsonDemo";



    protected int getLayoutId() {
        return R.layout.geojson;
    }

    @Override
    protected void startDemo() {
        getMap().setMinZoomPreference(12);
        retrieveFileFromResource();
        //getLocationPermission();
        //retrieveFileFromResource();
    }

    private void retrieveFileFromUrl() {
        new DownloadGeoJsonFile().execute(getString(R.string.geojson_url));
    }

    public void retrieveFileFromResource() {
        try {
            // Hay que crear metodo para colorear caya capa de geojson.
            // https://github.com/googlemaps/android-maps-utils/blob/3b8520ed07199074e5e7124872b52b0c4f85ff90/library/tests/src/com/google/maps/android/data/geojson/GeoJsonLayerTest.java#L61
            GeoJsonLayer layerM = new GeoJsonLayer(getMap(), R.raw.route_m, this);
            layerM.getDefaultLineStringStyle().setColor(Color.parseColor("#9a12b3"));
            layerM.getDefaultLineStringStyle().setWidth(15);
          //  assertEquals(Color.BLUE, layerM.getDefaultLineStringStyle().getColor());

            GeoJsonLayer layerK = new GeoJsonLayer(getMap(), R.raw.route_k, this);
            layerK.getDefaultLineStringStyle().setColor(Color.parseColor("#2b390e"));
            layerK.getDefaultLineStringStyle().setWidth(15);
          //  assertEquals(Color.BLUE, layerM.getDefaultLineStringStyle().getColor());

            addGeoJsonLayerToMap(layerM);
            addGeoJsonLayerToMap(layerK);

        } catch (IOException e) {
            Log.e(mLogTag, "GeoJSON file could not be read");
        } catch (JSONException e) {
            Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
        }
    }

//    GeoJsonLayer mLayer;
//
//    public void DefaultLineStringStyle() throws Exception {
//        mLayer.getDefaultLineStringStyle().setColor(Color.BLUE);
//        assertEquals(Color.BLUE, mLayer.getDefaultLineStringStyle().getColor());
//    }


    private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonLayer> {

        @Override
        protected GeoJsonLayer doInBackground(String... params) {
            try {
                // Open a stream from the URL
                InputStream stream = new URL(params[0]).openStream();

                String line;
                StringBuilder result = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

                while ((line = reader.readLine()) != null) {
                    // Read and save each line of the stream
                    result.append(line);
                }

                // Close the stream
                reader.close();
                stream.close();

                return new GeoJsonLayer(getMap(), new JSONObject(result.toString()));
            } catch (IOException e) {
                Log.e(mLogTag, "GeoJSON file could not be read");
            } catch (JSONException e) {
                Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
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

        layer.addLayerToMap();
        getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(19.457258, -70.6888)));
        // Demonstrate receiving features via GeoJsonLayer clicks.
        layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
            @Override
            public void onFeatureClick(Feature feature) {
                Toast.makeText(GeoJsonActivity.this,
                        "Feature clicked: " + feature.getProperty("title"),
                        Toast.LENGTH_SHORT).show();
            }

        });

    }


}


