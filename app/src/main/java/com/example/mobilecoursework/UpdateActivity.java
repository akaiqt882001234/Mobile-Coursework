package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilecoursework.database.MyDatabaseHelper;

public class UpdateActivity extends AppCompatActivity {

    EditText text_name, text_destination, text_date, text_risk, text_description, text_ID;
    Button update_button, delete_button;
    String id, name, destination, date, risk, description;
    MyDatabaseHelper myDB = new MyDatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        text_name = findViewById(R.id.text_name2);
        text_destination = findViewById(R.id.text_destination2);
        text_date = findViewById(R.id.text_date2);
        text_risk = findViewById(R.id.text_risk2);
        text_ID = findViewById(R.id.text_ID);
        text_description = findViewById(R.id.text_description2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Value to Database
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                //Check Value of text_name,text_destination,text_date
                if (TextUtils.isEmpty(text_ID.getText().toString())) {
                    text_ID.setError("Please enter ID!");
                } else if (TextUtils.isEmpty(text_destination.getText().toString())) {
                    text_destination.setError("Please enter Destination!");
                } else if (TextUtils.isEmpty(text_date.getText().toString())) {
                    text_date.setError("Please enter Date of Trip!");
                }else if(TextUtils.isEmpty(text_name.getText().toString())){
                    text_name.setError("Please enter Name of Trip!");
                }else{
                    UpdateData();
                }
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Value to Database
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                //Check Value of text_name,text_destination,text_date
                if(TextUtils.isEmpty(text_ID.getText().toString())){
                    text_ID.setError("Please enter ID!");
                }else{
                    DeleteData();
                }
            }
        });

    }

    public void UpdateData(){
        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDB.updateData(text_ID.getText().toString(),
                        text_name.getText().toString(),
                        text_destination.getText().toString(),
                        text_date.getText().toString(),
                        text_risk.getText().toString(),
                        text_description.getText().toString()
                        );
                confirmDialog();

                /*if(isUpdate == true){
                    Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(UpdateActivity.this,"Data inserted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(UpdateActivity.this,"Data not inserted", Toast.LENGTH_LONG).show();
                }*/
            }
        });
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Edit Success !!!")
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
    }

    public void DeleteData(){
        delete_button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteOneRow = myDB.deleteData(text_ID.getText().toString());
                        if(deleteOneRow > 0)
                            Toast.makeText(UpdateActivity.this, "Delete!", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(UpdateActivity.this, "No ID Selected! ", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}