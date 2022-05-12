package com.mis.acmebe;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.mis.acmebe.resttypes.WeatherResponse;
import com.mis.acmebe.resttypes.WeatherRetrofitInterface;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int PERMISSION_REQUEST_CODE_LOCATION = 0X123;
    Double latitude,longitude;
    Retrofit retrofit;
    TextView textViewApi;
    private GoogleMap googleMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        textViewApi =  (TextView) findViewById(R.id.textViewAPI);
        retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/").addConverterFactory(GsonConverterFactory.create()).build();


        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Snackbar.make(textViewApi, R.string.location_rationale, Snackbar.LENGTH_LONG).setAction(R.string.location_rationale_ok, view -> {
                    ActivityCompat.requestPermissions(MapActivity.this, permissions, PERMISSION_REQUEST_CODE_LOCATION);
                }).show();
            } else {
                ActivityCompat.requestPermissions(MapActivity.this, permissions, PERMISSION_REQUEST_CODE_LOCATION);
            }
        }else{
            startLocationService();
        }
    }

    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationService();
                } else {
                    Toast.makeText(this, R.string.location_cancel, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void startLocationService() {
        FusedLocationProviderClient locationServices = LocationServices.getFusedLocationProviderClient(this);
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationServices.requestLocationUpdates(locationRequest,locationCallback,null);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this::onMapReady);
    }
    LocationCallback locationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if (locationResult == null || locationResult.getLastLocation() == null || !locationResult.getLastLocation().hasAccuracy()){
                return;
            }
          Location location = locationResult.getLastLocation();
            goToLocation(location.getLatitude(),location.getLongitude());
          latitude = location.getLatitude();
          longitude = location.getLongitude();

          WeatherRetrofitInterface service = retrofit.create(WeatherRetrofitInterface.class);
          Call<WeatherResponse> response = service.getCurrentWeather((latitude.floatValue()), longitude.floatValue(), getString(R.string.open_weather_map_api_key));
          response.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                    if (response.isSuccessful() && response.body() != null){
                        //Log.i("MasterITS","REST: La termpreatura en"+ response.body().getName() +" es "+ response.body().getMain().getTemp());
                       textViewApi.setText("La temperatura en "+ response.body().getName() +" es "+ response.body().getMain().getTemp());
                    }
                }
                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    Log.i("MasterITS","REST: error en la llamada"+ t.getMessage());
                }
          });
        }
    };
    private void goToLocation(double latitude, double longitude) {
        LatLng ubication = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().title("Ubicación actual").position(ubication));
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(ubication));
    }
}

