package com.buslayout.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buslayout.R;
import com.buslayout.home.MainActivity;

import java.util.ArrayList;


public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.MyViewHolder> {
    private MainActivity context;
    private ArrayList<String> seatMap;

    public SeatAdapter(MainActivity context, ArrayList<String> seatMap) {
        this.context = context;
        this.seatMap = seatMap;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.set_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       viewHolder(holder,position);
    }

    private void viewHolder(MyViewHolder holder, int position) {
        String url = seatMap.get(position);
        String seat_no = url.substring(url.lastIndexOf('-') + 1);
        if(seat_no.equals("0")) {
            holder.tv_seat_no.setText("");
        }else {
            holder.tv_seat_no.setText(seat_no);
            holder.tv_seat_no.setBackgroundResource(R.drawable.round_corner_white);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_seat_no;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_seat_no = (TextView) itemView.findViewById(R.id.tv_seat_no);
        }
    }
}
