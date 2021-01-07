package com.buslayout.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.buslayout.R;
import com.buslayout.home.MainActivity;
import com.buslayout.home.model.SeatMap;
import com.buslayout.home.view.ExpandableHeightGridView;

import java.util.ArrayList;
import java.util.Arrays;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MyViewHolder> {
    private MainActivity context;
    private ArrayList<String> seatMap;

    public BusAdapter(MainActivity context, ArrayList<String> seatMap) {
        this.context = context;
        this.seatMap = seatMap;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        viewHolder(holder,position);
    }

    @Override
    public int getItemCount() {
        return seatMap.size();
    }

    public void viewHolder(@NonNull MyViewHolder holder, int position){
        ArrayList<String> list = new ArrayList<>();
        list = new ArrayList<String>(Arrays.asList(seatMap.get(position).split(",")));
        holder.rowRecyclerView.setLayoutManager(new GridLayoutManager(context,5));
        SeatAdapter adapter = new SeatAdapter(context,list);
        holder.rowRecyclerView.setAdapter(adapter);
        holder.rowRecyclerView.setExpanded(true);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ExpandableHeightGridView rowRecyclerView;
        public MyViewHolder(View view) {
            super(view);
            rowRecyclerView = (ExpandableHeightGridView) view.findViewById(R.id.rowRecyclerView);
        }

    }
}
