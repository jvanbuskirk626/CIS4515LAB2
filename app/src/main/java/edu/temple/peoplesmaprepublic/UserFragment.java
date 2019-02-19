package edu.temple.peoplesmaprepublic;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    RecyclerView partners;
    RecyclerView.Adapter mAdapter;
    //RecyclerView.LayoutManager layoutManager;
    ArrayList<Partners> parts=new ArrayList<Partners>();
    Button button;
    Partners pat=new Partners("steve","12","12");


    Context parent;


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.parent=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //parts.add(pat);
        Bundle extras=this.getArguments().getBundle("arraylist");
        //Log.d("EXTRAS TAG JAWN", extras.toString());
        @SuppressWarnings("unchecked")
        ArrayList<Partners> parts = (ArrayList<Partners>) extras.getSerializable("partnerList");

        //parts=populateParts();
        Toast.makeText(getContext(),String.valueOf(parts.size()), Toast.LENGTH_LONG).show();

        /*final String url = "https://kamorris.com/lab/get_locations.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());

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
                        parts.add(p);
                        Toast.makeText(getContext(),String.valueOf(parts.size()), Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getContext(),"Failure", Toast.LENGTH_LONG).show();
                    }
                }
        );

        queue.add(jsonArrayRequest);*/

        //for(int i=0;i<parts.size();i++)
           //Toast.makeText(getContext(),parts.get(i).getUser(), Toast.LENGTH_LONG).show();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_user, container, false);

        partners=v.findViewById(R.id.recycleView);
        //layoutManager=new LinearLayoutManager(getActivity());
        partners.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter=new PartnerAdapter(parts);
        partners.setAdapter(mAdapter);

        button=v.findViewById(R.id.button2);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String url = "https://kamorris.com/lab/get_locations.php";
                RequestQueue queue = Volley.newRequestQueue(getContext());

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
                                parts.add(p);

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
                                Toast.makeText(getContext(),"Failure", Toast.LENGTH_LONG).show();
                            }
                        }
                );

                queue.add(jsonArrayRequest);
                Toast.makeText(getContext(),String.valueOf(parts.size()), Toast.LENGTH_LONG).show();

            }
        });/*
        //Toast.makeText(getContext(),String.valueOf(parts.size()), Toast.LENGTH_LONG).show();
       /* partners=v.findViewById(R.id.recycleView);
        layoutManager=new LinearLayoutManager(getActivity());
        partners.setLayoutManager(layoutManager);
        mAdapter=new PartnerAdapter(parts);
        partners.setAdapter(mAdapter);*/




        return v;
    }

    public ArrayList<Partners> populateParts(){

        final ArrayList<Partners> parts2=new ArrayList<>();
        final String url = "https://kamorris.com/lab/get_locations.php";
        RequestQueue queue = Volley.newRequestQueue(getContext());

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
                        Toast.makeText(getContext(),"Failure", Toast.LENGTH_LONG).show();
                    }
                }
        );

        queue.add(jsonArrayRequest);

        return parts2;
    }

}
