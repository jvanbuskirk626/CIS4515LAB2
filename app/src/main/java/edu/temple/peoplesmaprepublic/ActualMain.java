package edu.temple.peoplesmaprepublic;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ActualMain extends AppCompatActivity{

    boolean singlePane;
    FragmentManager fm;
    MapFragment mapFragment;
    UserFragment uf=new UserFragment();
    Partners you;

    LocationManager lm;
    LocationListener ll;
    LatLng latLng;
    String lat;
    String lng;
    ArrayList <Partners> partners;
    //RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_main);

        Intent intent = getIntent();
        //String userName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        String userName="";

        //Toast.makeText(this,String.valueOf(partners.size()), Toast.LENGTH_LONG).show();

        //All user location stuff
        lm=getSystemService(LocationManager.class);
        ll=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat=String.valueOf(location.getLatitude());
                lng=String.valueOf(location.getLongitude());
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

        //acquire all user info
        uploadUser(userName);
        partners=getPartners();

        //determine orientation
        singlePane=findViewById(R.id.container2)==null;

        Bundle bundle=new Bundle();
        bundle.putSerializable("partnerList", partners);

        mapFragment=new MapFragment();
        uf=new UserFragment();
        uf.setArguments(bundle);

        fm=getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.containerOne, uf).commit();

        if(!singlePane)
            fm.beginTransaction().replace(R.id.container2, mapFragment).commit();
    }

    public void uploadUser(String user2){

        String url="https://kamorris.com/lab/register_location.php";
        final String u2=user2;
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        //Log.d("Error.Response", error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("user", u2);
                params.put("latitude", lat);
                params.put("longitude", lng);

                return params;
            }
        };
        queue.add(postRequest);
        you= new Partners(u2,lat,lng);

    }

    public ArrayList<Partners> getPartners(){

        final ArrayList<Partners> parts2=new ArrayList<>();
        final String url = "https://kamorris.com/lab/get_locations.php";
        RequestQueue queue2 = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Do something with response
                //mTextView.setText(response.toString());

                // Process the JSON
                try{
                    // Loop through the array elements
                    for(int i=0;i<response.length();i++){
                        JSONObject partner = response.getJSONObject(i);
                        String userName = partner.getString("username");
                        String lat = partner.getString("latitude");
                        String lng = partner.getString("longitude");
                        Partners p=new Partners(userName,lat,lng);

                        parts2.add(p);

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

        queue2.add(jsonArrayRequest);

        //Toast.makeText(this,String.valueOf(parts2.size()), Toast.LENGTH_LONG).show();

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
