package mx.iteso.sergio.emt_ad;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        populateListView();
    }

    private void populateListView() {
        String [] myItems = {"CETSJ","Hospital Puerto Vallarta","Hospital La Barca","Hospital Autlán","Hospital Cd. Guzmán"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.simple_sergio_list,
                R.id.texto_encabezado,
                myItems);

        ListView list = (ListView) findViewById(R.id.listViewHospitales);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_profile:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                return true;
            case R.id.action_info:
                Intent intent = new Intent(this, InfoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_map:
                //Intent intent3 = new Intent(this, NewMapActivity.class);
                Intent intent3 = new Intent(this, MapsActivity.class);
                startActivity(intent3);
                return true;
            case R.id.action_about:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Zapopan CETS and move the camera
        LatLng gdl = new LatLng(20.7180, -103.3713);
        mMap.addMarker(new MarkerOptions().position(gdl).title("Centro Estatal de Transfusión Sanguínea"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gdl, 15));

        // Add a marker in Vallarta
        LatLng vllt = new LatLng(20.669692, -105.210475);
        mMap.addMarker(new MarkerOptions().position(vllt).title("Hospital regional de Puerto Vallarta"));

        // Add a marker in La Barca
        LatLng barc = new LatLng(20.281853, -102.548604);
        mMap.addMarker(new MarkerOptions().position(barc).title("Hospital regional de La Barca"));

        // Add a marker in autlan
        LatLng autlan = new LatLng(19.769113, -104.355011);
        mMap.addMarker(new MarkerOptions().position(autlan).title("Hospital regional de Autlán"));

        // Add a marker in ameca
        LatLng ameca = new LatLng(20.533831, -104.034110);
        mMap.addMarker(new MarkerOptions().position(ameca).title("Hospital regional de Ameca"));

        // Add a marker in cd guzman
        LatLng guzman = new LatLng(19.697109, -103.478166);
        mMap.addMarker(new MarkerOptions().position(guzman).title("Hospital regional de Cd Guzmán"));

        // Add a marker in lagos de moreno
        LatLng lagos = new LatLng(21.355804, -101.934184);
        mMap.addMarker(new MarkerOptions().position(lagos).title("Hospital regional de Lagos de Moreno"));

        // Add a marker in lagos de moreno
        LatLng tepa = new LatLng(20.811547, -102.779403);
        mMap.addMarker(new MarkerOptions().position(tepa).title("Hospital regional de Tepatitlán"));


        //mMap.setMyLocationEnabled(true);

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                LatLng latlng = marker.getPosition();
                float lat = (float) latlng.latitude;
                float lng = (float) latlng.longitude;

                String uri = "waze://?ll="+lat+", "+lng+"&navigate=yes";
                startActivity(new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(uri)));

                return false;
            }
        });

    }
}
