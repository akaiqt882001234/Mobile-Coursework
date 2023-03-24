package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mobilecoursework.database.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DetailMainActivity extends AppCompatActivity {

    RecyclerView recyclerViewDetail;
    FloatingActionButton add_expense_button;

    MyDatabaseHelper myDBH;
    ArrayList<String> _id,text_type, text_amount, text_time;
    CustomAdapterExpenses customAdapterExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_main);

        recyclerViewDetail =findViewById(R.id.recyclerViewDetail);
        add_expense_button = findViewById(R.id.add_expense_button);
        add_expense_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailMainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDBH = new MyDatabaseHelper(DetailMainActivity.this);
        _id = new ArrayList<>();
        text_type = new ArrayList<>();
        text_amount = new ArrayList<>();
        text_time = new ArrayList<>();

        storeDataInArray2();

        customAdapterExpenses = new CustomAdapterExpenses(DetailMainActivity.this,_id, text_type, text_amount, text_time);
        recyclerViewDetail.setAdapter(customAdapterExpenses);
        recyclerViewDetail.setLayoutManager(new LinearLayoutManager(DetailMainActivity.this));
    }

    void storeDataInArray2(){
        Cursor cursor2 =myDBH.readAllData();
        if( cursor2.getCount()==0 ){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor2.moveToNext()){
                _id.add(cursor2.getString(0));
                text_type.add(cursor2.getString(1));
                text_amount.add(cursor2.getString(2));
                text_time.add(cursor2.getString(3));
            }
        }
    }
}
