package edu.temple.peoplesmaprepublic;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    RecyclerView partnerRecyclerView;
    RecyclerView.Adapter mAdapter;
    //RecyclerView.LayoutManager layoutManager;
    //ArrayList<Partners> parts=new ArrayList<Partners>();
    ArrayList<Partners> partnerList=new ArrayList<Partners>();
    ArrayList<Partners> tempPartnerList=new ArrayList<Partners>();
    Button button;
    Partners pat=new Partners("steve","12","12");
    int i;


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

        //Bundle extras=this.getArguments().getBundle("partnerList");
        //tempPartnerList=(ArrayList<Partners>)getArguments().getSerializable("partnerList");
        partnerList=(ArrayList<Partners>)getArguments().getSerializable("partnerList");
        Collections.sort(partnerList);
        //partnerList.add(pat);
       /* for(int i=0;i<tempPartnerList.size();i++){
            tempPartnerList.add(partnerList.get(i));
        }*/
        Toast.makeText(getContext(),String.valueOf(partnerList.size()), Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v2= inflater.inflate(R.layout.fragment_user, container, false);

        partnerRecyclerView=v2.findViewById(R.id.recycleView);
        //layoutManager=new LinearLayoutManager(getActivity());
        partnerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //partnerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter=new PartnerAdapter(partnerList);
        partnerRecyclerView.setAdapter(mAdapter);

        button=v2.findViewById(R.id.getPartnersButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(),String.valueOf(tempPartnerList.size()), Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(),partnerList.get(i).getUser(), Toast.LENGTH_LONG).show();
                i++;
            }
        });

        return v2;
    }

}
