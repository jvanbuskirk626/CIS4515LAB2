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

        public ViewHolder(View itemView){
            super(itemView);

            partTextView=itemView.findViewById(R.id.firstName);
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

        TextView textView=viewHolder.partTextView;
        textView.setText(p.getUser());
    }

    public int getItemCount(){
        return partList.size();
    }
}

/*public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.MyViewHolder> {

    private ArrayList<Partners> partList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;

        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public PartnerAdapter(ArrayList<Partners> p) {
        partList=p;
    }

    public PartnerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(String.valueOf(partList.get(position)));
        holder.textView.setText(partList.get(position).getUser());

    }

    public int getItemCount() {
        return partList.size();
    }
}*/

    /*private ArrayList<Partners>parts;

    public PartnerAdapter(ArrayList<Partners>p){
        parts=p;
    }


    @NonNull
    @Override
    public PartnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PartnerAdapter.ViewHolder viewHolder, int i) {

        viewHolder.mFirstName.setText("asdfa");
    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mFirstName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mFirstName=(TextView) itemView.findViewById(R.id.firstName);
        }
    }
    }*/

