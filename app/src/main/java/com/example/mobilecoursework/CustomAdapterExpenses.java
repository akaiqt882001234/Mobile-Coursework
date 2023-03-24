package com.example.mobilecoursework;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapterExpenses extends RecyclerView.Adapter {

    private final Context context2;
    private Context content2;
    private ArrayList trip_id, text_type, text_amount, text_time;

    CustomAdapterExpenses(Context context2,
                          ArrayList trip_id,
                          ArrayList text_type,
                          ArrayList text_amount,
                          ArrayList text_time){

        this.context2 = context2;
        this.trip_id = trip_id;
        this.text_type = text_type;
        this.text_amount = text_amount;
        this.text_time = text_time;
    }

    @NonNull
    @Override
    public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context2);
        View view =inflater.inflate(R.layout.my_row, parent, false);
        return new CustomAdapterExpenses.MyViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    /*@Override
    public void onBindViewHolder(@NonNull MyViewHolder2 holder, int position) {
        holder.text_type.setText(String.valueOf(text_type.get(position)));
        holder.text_amount.setText(String.valueOf(text_amount.get(position)));
        holder.text_time.setText(String.valueOf(text_time.get(position)));


    }*/


    @Override
    public int getItemCount() {
        return trip_id.size();
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder{

        TextView text_type, text_amount, text_time;
        LinearLayout expensesLayout;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            text_type =itemView.findViewById(R.id.text_type);
            text_amount =itemView.findViewById(R.id.text_amount);
            text_time =itemView.findViewById(R.id.text_time);
            expensesLayout = itemView.findViewById(R.id.expensesLayout);
        }
    }
}
