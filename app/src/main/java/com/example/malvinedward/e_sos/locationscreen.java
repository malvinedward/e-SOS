package com.example.malvinedward.e_sos;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;

public class locationscreen extends Activity implements LocationListener {

    private Button smsButton;
    private DBSource datasource;


    TextView tvLat;
    TextView tvStatus;
    TextView tvLng;




    String smsnumber = "087771244173";
    double lat;
    double lng;
    long minTime;
    float minDistance;
    String locProvider;
    LocationManager locMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locationscreen);
        datasource = new DBSource(this);
        datasource.open();

        smsButton = (Button) findViewById(R.id.send);
        tvStatus = (TextView) findViewById(R.id.statusloc);
        tvLat = (TextView) findViewById(R.id.statustime);
        tvLng = (TextView) findViewById(R.id.latitudeloc);

        locMgr = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        tvStatus.setText("Ambil Lokasi Terakhir");
        locProvider = LocationManager.NETWORK_PROVIDER;
        Location lastKnownLocation = locMgr.getLastKnownLocation(locProvider);
        lat = lastKnownLocation.getLatitude();
        lng = lastKnownLocation.getLongitude();
        tvLat.setText(String.valueOf(lat));
        tvLng.setText(String.valueOf(lng));

        Criteria cr = new Criteria();
        cr.setAccuracy(Criteria.ACCURACY_COARSE);

        locProvider = locMgr.getBestProvider(cr,false);

        minTime = 5*1000;

        minDistance=1;

        smsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendsmsbymanager();
            }
        });



    }

    public void onLocationChanged(Location location){

        lat = location.getLatitude();
        lng = location.getLongitude();
        tvLat.setText(String.valueOf(lat));
        tvLng.setText(String.valueOf(lng));


        tvStatus.setText("Direfresh berdasarkan:     "+locProvider);
    }


    public void sendsmsbymanager(){

            DBContact c = datasource.getcontact(1);
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(smsnumber,null,"Anda berada pada lokasi dengan kordinat lat: "+lat +"lng "+lng+" Nama Korban: "
                    + c.getNama()+" dengan Nomer hp darurat: "+c.getNomerhp(),null,null);



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
