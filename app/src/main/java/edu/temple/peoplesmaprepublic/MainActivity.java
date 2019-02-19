package edu.temple.peoplesmaprepublic;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity{

    boolean singlePane;
    FragmentManager fm;
    MapFragment mapFragment;

    LocationManager lm;
    LocationListener ll;
    LatLng latLng;
    String lat="test";
    String lng;
    Button button;
    TextView latView, longView;
    ArrayList <Partners> partners;
    //RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        partners=getPartners();

        button=findViewById(R.id.button);
        latView=findViewById(R.id.latView);
        longView=findViewById(R.id.longView);
        lm=getSystemService(LocationManager.class);

        ll=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat=String.valueOf(location.getLatitude());
                lng=String.valueOf(location.getLongitude());
                //latView.setText(String.valueOf(location.getLatitude()));
                //longView.setText(String.valueOf(location.getLongitude()));
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

        singlePane=findViewById(R.id.container2)==null;

        Bundle bundle=new Bundle();
        bundle.putSerializable("partnerList", partners);

        mapFragment=new MapFragment();
        UserFragment uf=new UserFragment();
        uf.setArguments(bundle);

        fm=getSupportFragmentManager();

        //fm.beginTransaction().replace(R.id.container1, new UserFragment()).commit();
        fm.beginTransaction().replace(R.id.container1, uf).commit();

        if(!singlePane){
            fm.beginTransaction().replace(R.id.container2, mapFragment).commit();
        }

        //getPartners();

    }

    public ArrayList<Partners> getPartners(){

        final ArrayList<Partners> parts2=new ArrayList<>();
        final String url = "https://kamorris.com/lab/get_locations.php";
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response
                //mTextView.setText(response.toString());

                // Process the JSON
                try{
                    // Loop through the array elements
                    for(int i=0;i<response.length();i++){
                        // Get current json object
                        JSONObject partner = response.getJSONObject(i);
                        //Toast.makeText(getApplicationContext(),"Getting Users", Toast.LENGTH_LONG).show();
                        // Get the current student (json object) data
                        String userName = partner.getString("username");
                        String lat = partner.getString("latitude");
                        String lng = partner.getString("longitude");
                        Partners p=new Partners(userName,lat,lng);
                        //Toast.makeText(getContext(),userName+" "+lat+" "+lng, Toast.LENGTH_LONG).show();
                        //if((userName!=null)&&(lat!=null)&&(lng!=null))
                        parts2.add(p);
                        //Toast.makeText(getContext(),String.valueOf(parts2.size()), Toast.LENGTH_LONG).show();
                        //Toast.makeText(getContext(),parts.get(i).getUser(), Toast.LENGTH_LONG).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(getApplication(),"Failure", Toast.LENGTH_LONG).show();
                    }
                }
        );

        queue.add(jsonArrayRequest);

        return parts2;

    }

    public void userSelected(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            registerForLocationUpdates();
        }else{
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(ll);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {
            registerForLocationUpdates();
        }else{
            Toast.makeText(this,"Cannot do the thing", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void registerForLocationUpdates(){
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0,ll);
        //lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,0,ll);
        //lm.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0,0,ll);
    }

}
