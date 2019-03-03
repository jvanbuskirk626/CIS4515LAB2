package edu.temple.peoplesmaprepublic;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mapView;
    LocationManager lm;
    LocationListener ll;
    GoogleMap map;
    Marker lastMarker;
    View mView;
    LatLng latLng;
    ArrayList<LatLng> markers=new ArrayList<LatLng>();
    ArrayList<Partners> partnerList=new ArrayList<Partners>();
    MarkerOptions options=new MarkerOptions();
    Button update;


    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        partnerList=(ArrayList<Partners>)getArguments().getSerializable("partnerList");


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView= inflater.inflate(R.layout.fragment_map, container, false);
        update=mView.findViewById(R.id.updatePins);
        /*for(int i=0;i<markers.size();i++){
            options.position(markers.get(i));
            options.title(partnerList.get(i).getUser());
            map.addMarker(options);
        }*/

        //Toast.makeText(getContext(),String.valueOf(markers.size()), Toast.LENGTH_LONG).show();
        //Toast.makeText(getContext(),"map frag", Toast.LENGTH_LONG).show();

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView=(MapView) mView.findViewById(R.id.mapView);

        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        lm =(LocationManager)getActivity().getSystemService(LocationManager.class);

        ll=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                if(lastMarker!=null){
                    lastMarker.setPosition(latLng);
                }else{
                    MarkerOptions markerOptions=(new MarkerOptions())
                            .position(latLng)
                            .title("Marky Mark");

                    lastMarker=map.addMarker(markerOptions);
                }



                //CameraUpdate cameraUpdate=CameraUpdateFactory.newLatLngZoom(lastMarker.getPosition(), 16);
                //map.animateCamera(cameraUpdate);
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
        };

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<partnerList.size();i++){
                    markers.add(partnerList.get(i).getLatLng());
                }
                for(int i=0;i<markers.size();i++){
                    options.position(markers.get(i));
                    options.title(partnerList.get(i).getUser());
                    map.addMarker(options);
                }
                //Toast.makeText(getContext(),String.valueOf(partnerList.size()), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();

        if(getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            registerForLocationUpdates();
        }else{
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        mapView.onPause();
        lm.removeUpdates(ll);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    /*@Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            registerForLocationUpdates();
        }else{
            Toast.makeText(getActivity(),"Cannot do the thing", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void registerForLocationUpdates(){
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,ll);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0,ll);
        lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0,0,ll);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());
        map=googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }
}
