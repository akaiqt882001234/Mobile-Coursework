package com.example.mobilecoursework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mobilecoursework.database.MyDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> _id,text_name, text_destination, text_date, text_risk;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView =findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        _id = new ArrayList<>();
        text_name = new ArrayList<>();
        text_destination = new ArrayList<>();
        text_date = new ArrayList<>();
        text_risk = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(MainActivity.this,_id, text_name, text_destination, text_date, text_risk);
        recycleView.setAdapter(customAdapter);
        recycleView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArray(){
        Cursor cursor =myDB.readAllData();
        if( cursor.getCount()==0 ){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                _id.add(cursor.getString(0));
                text_name.add(cursor.getString(1));
                text_destination.add(cursor.getString(2));
                text_date.add(cursor.getString(3));
                text_risk.add(cursor.getString(4));

            }
        }
    }
//Delete Button Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this,"Delete", Toast.LENGTH_SHORT).show();
            MyDatabaseHelper myDB = new MyDatabaseHelper(this);
            myDB.deleteAllData();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}