package com.example.mobilecoursework;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList _id, text_name, text_destination, text_date, text_risk;
    int position;

    CustomAdapter(Context context,
                  ArrayList<String> _id,
                  ArrayList<String> text_name,
                  ArrayList<String> text_destination,
                  ArrayList<String> text_date,
                  ArrayList<String> text_risk){

        this.context = context;
        this._id = _id;
        this.text_name = text_name;
        this.text_destination = text_destination;
        this.text_date = text_date;
        this.text_risk = text_risk;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        this.position = position;
        holder._id_txt.setText(String.valueOf(_id.get(position)));
        holder.name_txt.setText(String.valueOf(text_name.get(position)));
        holder.destination_txt.setText(String.valueOf(text_destination.get(position)));
        holder.date_txt.setText(String.valueOf(text_date.get(position)));
        holder.risk_txt.setText(String.valueOf(text_risk.get(position)));
        //UpdateFunction
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(_id.get(position)));
                intent.putExtra("name",String.valueOf(text_name.get(position)));
                intent.putExtra("destination",String.valueOf(text_destination.get(position)));
                intent.putExtra("date",String.valueOf(text_date.get(position)));
                intent.putExtra("risk",String.valueOf(text_risk.get(position)));
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return _id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView _id_txt, name_txt, destination_txt, date_txt, risk_txt;
        LinearLayout mainLayout;

         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            _id_txt =itemView.findViewById(R.id._id_txt);
            name_txt =itemView.findViewById(R.id.name_txt);
            destination_txt =itemView.findViewById(R.id.destination_txt);
            date_txt =itemView.findViewById(R.id.date_txt);
            risk_txt =itemView.findViewById(R.id.risk_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}

