package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.database.sqlite.SQLiteDatabase;

import com.example.mobilecoursework.database.MyDatabaseHelper;

public class AddActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    EditText text_name,text_destination,text_date,text_description,text_risk;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        text_name = findViewById(R.id.text_name);
        text_destination = findViewById(R.id.text_destination);
        text_date = findViewById(R.id.text_date);
        text_risk = findViewById(R.id.text_risk);

        text_description = findViewById(R.id.text_description);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Value to Database
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                //Check Value of text_name,text_destination,text_date
                if(TextUtils.isEmpty(text_name.getText().toString())){
                    text_name.setError("Please enter Name of Trip!");
                }else if(TextUtils.isEmpty(text_destination.getText().toString())){
                    text_destination.setError("Please enter Destination!");
                }else if(TextUtils.isEmpty(text_date.getText().toString())){
                    text_date.setError("Please enter Date of Trip!");
                }else{
                    getInputs();

                    //Insert Value to Database
                    myDB.add_trip(text_name.getText().toString().trim(),
                            text_destination.getText().toString().trim(),
                            text_date.getText().toString().trim(),
                            text_risk.getText().toString().trim(),
                            text_description.getText().toString().trim());

                }
            }
        });
    }

// Get Inputs form field
    private void getInputs() {
        //Set String input Value
        String strTripName = text_name.getText().toString();
        String strDestination = text_destination.getText().toString();
        String strDate = text_date.getText().toString();
        String strRisk = text_risk.getText().toString();
        String strDescription = text_description.getText().toString();

        displayNextAlert(strTripName, strDestination, strDate, strRisk,strDescription);

    }
    // Display Alert Message and Close button
    private void displayNextAlert(String strTripName, String strDestination, String strDate, String strRisk, String strDescription) {
        new AlertDialog.Builder(this).setMessage("Your Trip Information: " +
                        "\n Trip name: " + strTripName +
                        "\n Destination: " + strDestination +
                        "\n Date: " + strDate +
                        "\n Risk Assessment: " + strRisk +
                        "\n Description: " + strDescription
                )
                .setNeutralButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).show();
    }

}