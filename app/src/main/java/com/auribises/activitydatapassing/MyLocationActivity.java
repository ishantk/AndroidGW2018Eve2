package com.auribises.activitydatapassing;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyLocationActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    TextView txtLocation;
    Button btnFetch;

    LocationManager locationManager;

    void initViews() {
        txtLocation = findViewById(R.id.textViewLocation);
        btnFetch = findViewById(R.id.buttonFetch);

        btnFetch.setOnClickListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonFetch) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this,"Please Grant Permissions for Location in Settings",Toast.LENGTH_LONG).show();
            }else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 30, this);
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        float speed = location.getSpeed();

        txtLocation.setText("Location: "+latitude+" : "+longitude+" : "+speed);

        if(speed >= 80){
            SmsManager smsManager = SmsManager.getDefault();
            String number = "+91 99155 71177";
            String msg = "Over speeding vehicle is dangerous !!";
            smsManager.sendTextMessage(number,null,msg,null,null);

            // Remove the Location Updates as soon we fetch the 1st location
            locationManager.removeUpdates(this);

        }

        // Remove the Location Updates as soon we fetch the 1st location
        locationManager.removeUpdates(this);

        // Reverse Geo-coding
        try{

            StringBuilder builder = new StringBuilder();
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocation(latitude,longitude,5);
            if(addresses !=null && addresses.size()>0){

                Address adrs = addresses.get(0);


                for(int i=0;i<=adrs.getMaxAddressLineIndex();i++){
                    builder.append(adrs.getAddressLine(i)+"\n");
                }

            }

            txtLocation.setText(builder.toString());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
