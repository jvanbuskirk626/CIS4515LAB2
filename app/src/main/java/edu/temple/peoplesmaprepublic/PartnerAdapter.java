package edu.temple.peoplesmaprepublic;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class PartnerAdapter extends RecyclerView.Adapter<PartnerAdapter.ViewHolder> {

    private ArrayList<Partners>parts;

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
}
