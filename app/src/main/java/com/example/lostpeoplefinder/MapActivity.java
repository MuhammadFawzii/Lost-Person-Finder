package com.example.lostpeoplefinder;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    GoogleMap gMap;
    FrameLayout map;
    SearchView mapSearchView;
    Button showInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapSearchView = findViewById(R.id.mapSearch);
        map = findViewById(R.id.map);
        showInfoButton = findViewById(R.id.showInfoButton);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String location = mapSearchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null) {
                    Geocoder geocoder = new Geocoder(MapActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (addressList != null && !addressList.isEmpty()) {
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        placeMarker(latLng);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        showInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (gMap != null && gMap.getCameraPosition().target != null) {
                    LatLng selectedLocation = gMap.getCameraPosition().target;
                    Geocoder geocoder = new Geocoder(MapActivity.this, Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(selectedLocation.latitude, selectedLocation.longitude, 1);
                        if (addresses != null && !addresses.isEmpty()) {
                            Address address = addresses.get(0);
                            String cityName = address.getLocality();
                            if (cityName == null || cityName.isEmpty()) {
                                cityName = address.getAdminArea(); // If city name is not available, use administrative area
                            }
                            String streetName = address.getAddressLine(0);
                            double latitude = selectedLocation.latitude;
                            double longitude = selectedLocation.longitude;
                            String message = "City: " + cityName + "\nStreet: " + streetName + "\nLatitude: " + latitude + "\nLongitude: " + longitude;
                            Toast.makeText(MapActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent resultIntent =new Intent(getApplicationContext(),DetailsActivity3.class);
                            resultIntent.putExtra("cityName", cityName);
                            resultIntent.putExtra("latitude", latitude);
                            resultIntent.putExtra("longitude", longitude);
                            setResult(RESULT_OK, resultIntent);
                            finish();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.gMap = googleMap;
        LatLng cairoLatLng = new LatLng(30.0444, 31.2357);
        gMap.addMarker(new MarkerOptions().position(cairoLatLng).title("Cairo"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cairoLatLng, 10));
        gMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        placeMarker(latLng);
    }

    private void placeMarker(LatLng latLng) {
        gMap.clear();
        gMap.addMarker(new MarkerOptions().position(latLng));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
    }
}
