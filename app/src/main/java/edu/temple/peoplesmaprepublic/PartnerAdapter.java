package edu.temple.peoplesmaprepublic;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class PartnerAdapter extends  RecyclerView.Adapter<PartnerAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView partTextView;
        public TextView locationTextView;

        public ViewHolder(View itemView){
            super(itemView);

            partTextView=itemView.findViewById(R.id.firstName);
            locationTextView=itemView.findViewById(R.id.locationBox);
        }
    }

    private ArrayList<Partners> partList;

    public PartnerAdapter(ArrayList<Partners> p){
        partList=p;
    }

    public PartnerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View partnerView=inflater.inflate(R.layout.row,parent,false);

        ViewHolder viewHolder=new ViewHolder(partnerView);
        return viewHolder;
    }

    public void onBindViewHolder(PartnerAdapter.ViewHolder viewHolder, int position){
        Partners p=partList.get(position);

        TextView userView=viewHolder.partTextView;
        userView.setText(p.getUser());

        TextView locView=viewHolder.locationTextView;
        locView.setText(p.getLatLngString());
    }

    public int getItemCount(){
        return partList.size();
    }
}
