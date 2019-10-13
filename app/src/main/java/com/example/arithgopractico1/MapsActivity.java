package com.example.arithgopractico1;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Marker miUbicacion;
    private Circle biblioteca;
    private Circle auditorios;
    private Circle edificioD;

    private Button but_operacion;
    private Button but_canje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        but_operacion = findViewById(R.id.but_oper);
        but_canje = findViewById(R.id.but_canj);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ActivityCompat.requestPermissions(this, new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
        }, 11);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 11){
            //Sucede despues de que el usuario interactue con el dialogo
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        but_operacion = findViewById(R.id.but_oper);
        but_canje = findViewById(R.id.but_canj);
        mMap.setOnMapLongClickListener(this);

        auditorios = mMap.addCircle(new CircleOptions()
                .center(new LatLng(3.342622, -76.529685))
                .radius(20).strokeColor(Color.BLACK));

        edificioD = mMap.addCircle(new CircleOptions()
                .center(new LatLng(3.340899, -76.530248))
                .radius(20).strokeColor(Color.BLACK));

        biblioteca = mMap.addCircle(new CircleOptions()
                .center(new LatLng(3.341786, -76.529960))
                .radius(20).strokeColor(Color.BLACK));

        auditorios.setVisible(true);
        edificioD.setVisible(true);
        biblioteca.setVisible(true);

        LatLng icesi = new LatLng(3.341552, -76.529784);
        miUbicacion = mMap.addMarker(new MarkerOptions().position(icesi).title("Icesi"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(icesi, 18));


        //Solicitud de ubicación
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            Log.e(">>>>>","Permisos no concedidos");
            return;
        }
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, this);
        //manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

        but_operacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, QuestionActivity.class);
                startActivityForResult(i, 11);

            }
        });

        but_canje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MapsActivity.this, ExchangeActivity.class);
                startActivityForResult(i, 11);

            }
        });

    }



    public void ubicadoEnBiblioteca(LatLng pos){
        float[] disResultado = new float[2];

        Location.distanceBetween( pos.latitude, pos.longitude,
                biblioteca.getCenter().latitude,
                biblioteca.getCenter().longitude,
                disResultado);

        if(disResultado[0] > biblioteca.getRadius()){

        } else {
            Toast.makeText(MapsActivity.this, "Acaba de ingresar a la zona de canje", Toast.LENGTH_LONG).show();
            but_canje.setEnabled(true);
        }
    }


    public void ubicadoEnElD(LatLng pos){
        float[] disResultado = new float[2];

        Location.distanceBetween( pos.latitude, pos.longitude,
                edificioD.getCenter().latitude,
                edificioD.getCenter().longitude,
                disResultado);

        if(disResultado[0] > edificioD.getRadius()){

        } else {
            Toast.makeText(MapsActivity.this, "Acaba de ingresar a una zona reactiva", Toast.LENGTH_LONG).show();
            but_canje.setEnabled(true);
        }
    }

    public void ubicadoEnAuditorios(LatLng pos){
        float[] disResultado = new float[2];

        Location.distanceBetween( pos.latitude, pos.longitude,
                auditorios.getCenter().latitude,
                auditorios.getCenter().longitude,
                disResultado);

        if(disResultado[0] > auditorios.getRadius()){

        } else {
            Toast.makeText(MapsActivity.this, "Acaba de ingresar a una zona reactiva", Toast.LENGTH_LONG).show();
            but_canje.setEnabled(true);
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.e(">>>","Accuracy: "+location.getAccuracy());
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        miUbicacion.setPosition(pos);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 18));

        ubicadoEnBiblioteca(pos);
        ubicadoEnAuditorios(pos);
        ubicadoEnElD(pos);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onMapLongClick(LatLng latLng) {

    }
}
