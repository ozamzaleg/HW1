package com.example.oz_tal_application_project.utils;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import callbacks.CallBack_Location;


public class getLocation {

    private static getLocation instance;
    private Context context;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private LocationSettingsRequest locationSettingsRequest;


    public static getLocation getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new getLocation(context);
        }
    }

    private getLocation(Context context) {
        this.locationRequest = new LocationRequest();
        this.locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        this.context = context;
    }

    public void getCurrentLocation(CallBack_Location callBack_Location) {
        LocationManager locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            callBack_Location.onLocationFailure("you must turn on your location");
            return;
        }
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(this.locationRequest);
        this.locationSettingsRequest = builder.build();
        this.locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                Location location=locationResult.getLastLocation();
                if(location!=null) {
                    callBack_Location.onLocationSuccess(location.getLatitude(), location.getLongitude());
                }
                else {
                    callBack_Location.onLocationFailure("can not get your location");
                }
            }
        };

        this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        this.mFusedLocationClient.requestLocationUpdates(this.locationRequest,
                this.locationCallback, Looper.myLooper());
    }

}
