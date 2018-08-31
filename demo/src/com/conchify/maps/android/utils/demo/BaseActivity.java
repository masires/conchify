/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.conchify.maps.android.utils.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.MapStyleOptions;

public abstract class BaseActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
//    private CheckBox mMyLocationButtonCheckbox;
//    private CheckBox mMyLocationLayerCheckbox;

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private Boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private final static String mLogTag = "BaseAct";
    // private FusedLocationProviderClient mFusedLocationProviderClient;
    //private static final float DEFAULT_ZOOM = 15f;
    private UiSettings mUiSettings;

    protected int getLayoutId() {
        return R.layout.geojson;
    }

    private static final String TAG = BaseActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());


        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        getLocationPermission();
        setUpMap();

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        // Log.d(m)

        mMap = map;

        mUiSettings = mMap.getUiSettings();

        // if (mLocationPermissionGranted) {
        //   getDeviceLocation();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            // ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mUiSettings = mMap.getUiSettings();



        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);
        mUiSettings.setMapToolbarEnabled(true);
        //   mUiSettings.

//        mMap.setMyLocationEnabled(true);
        // mMap.
        // mUiSettings.setScrollGesturesEnabled(true);


        try {
            boolean success = mMap.setMapStyle(

                    MapStyleOptions.loadRawResourceStyle(

                            this, R.raw.style_json));
            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        startDemo();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    protected abstract void startDemo();

    protected GoogleMap getMap() {
        return mMap;
    }

    protected void getLocationPermission() {
        Log.d(mLogTag, "getLocationPermission: getting location Permissions");

        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                Toast.makeText(this, "Map Ready", Toast.LENGTH_SHORT).show();
                getMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        Log.d(mLogTag, "onRequestPermissionsResult: called");
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;

                            Log.d(mLogTag, "onRequestPermissionsResult: permission failed");

                            return;
                        }
                    }
                    Log.d(mLogTag, "onRequestPermissionResult: permission granted");

                    mLocationPermissionGranted = true;
                    //Initialize Map

                    getMap();

                }
            }
        }
    }

//    public final boolean isCompassEnabled(){
//        return mUiSettings.isCompassEnabled();
//    }

//    private void getDeviceLocation() {
//        Log.d(mLogTag, "getDeviceLocation: searching current loc");
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
//
//        try {
//            if (mLocationPermissionGranted) {
//                final Task location = mFusedLocationProviderClient.getLastLocation();
//                location.addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        if (task.isSuccessful()) {
//                            Log.d(mLogTag, "onComplete: found loc");
//                            Location currentLocation = (Location) task.getResult();
//
//                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);
//                        } else {
//                            Log.d(mLogTag, "onComplete: connection is null");
//                            Toast.makeText(BaseActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        } catch (SecurityException e) {
//            Log.e(mLogTag, "getDeviceLocation: SecurityException: " + e.getMessage());
//        }
//
//    }

//    private void moveCamera(LatLng latLng, float zoom) {
//        Log.d(mLogTag, "Moving to: " + latLng.latitude + ", lng: " + latLng.longitude);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
//    }

    public void setCompassEnabled(View v) {

        if (!checkReady()) {

            return;

        }

        // Enables/disables the compass (icon in the top-left for LTR locale or top-right for RTL

        // locale that indicates the orientation of the map).

        mUiSettings.setCompassEnabled(((CheckBox) v).isChecked());

    }

    private boolean checkReady() {

        if (mMap == null) {

            Toast.makeText(this, "LoL", Toast.LENGTH_SHORT).show();

            return false;

        }

        return true;

    }


}
