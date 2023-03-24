package com.example.mobilecoursework;

import androidx.appcompat.app.AppCompatActivity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobilecoursework.database.MyDatabaseHelper;

public class ExpensesActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;
    EditText text_type,text_amount,text_time;
    Button addEx_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);

    text_type = findViewById(R.id.text_type);
    text_amount = findViewById(R.id.text_amount);
    text_time = findViewById(R.id.text_time);
    addEx_button = findViewById(R.id.addEx_button);

        addEx_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Value to Database
                MyDatabaseHelper myDB = new MyDatabaseHelper(ExpensesActivity.this);
                //Check Value of text_name,text_destination,text_date
                if(TextUtils.isEmpty(text_type.getText().toString())){
                    text_type.setError("Please enter Type!");
                }else if(TextUtils.isEmpty(text_amount.getText().toString())){
                    text_amount.setError("Please enter Amount!");
                }else if(TextUtils.isEmpty(text_time.getText().toString())){
                    text_time.setError("Please enter Time of the expenses!");
                }else{
                    //Insert Value to Database
                    myDB.add_detail(text_type.getText().toString().trim(),
                            text_amount.getText().toString().trim(),
                            text_time.getText().toString().trim());
                }
            }
        });
    }
}