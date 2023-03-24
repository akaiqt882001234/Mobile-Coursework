package com.example.mobilecoursework.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "My_Database.db";
    public static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_management";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "text_name";
    private static final String COLUMN_DESTINATION = "text_destination";
    private static final String COLUMN_DATE = "text_date";
    private static final String COLUMN_RISK = "text_risk";
    private static final String COLUMN_DESCRIPTION = "text_description";

    private static final String TABLE_EXPENSES_NAME = "expenses_detail";
    private static final String COLUMN_ID_EXPENSES = "_id_expenses";
    private static final String COLUMN_TRIP_ID = "trip_id";
    private static final String COLUMN_TYPE = "text_type";
    private static final String COLUMN_AMOUNT = "text_amount";
    private static final String COLUMN_TIME_EXPENSES = "text_time";

    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME + " ("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_NAME + " TEXT, "
                        + COLUMN_DESTINATION + " TEXT, "
                        + COLUMN_DATE + " TEXT, "
                        + COLUMN_RISK + " TEXT, "
                        + COLUMN_DESCRIPTION + " TEXT);";

        String query_expenses =
                "CREATE TABLE " + TABLE_EXPENSES_NAME + " ("
                        + COLUMN_ID_EXPENSES + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COLUMN_TRIP_ID + " INTEGER, "
                        + COLUMN_TYPE + " TEXT, "
                        + COLUMN_AMOUNT + " TEXT, "
                        + COLUMN_TIME_EXPENSES + " TEXT, "
                        + " FOREIGN KEY ("+COLUMN_TRIP_ID+") REFERENCES "+TABLE_NAME+"("+COLUMN_ID+"));";

        db.execSQL(query);
        db.execSQL(query_expenses);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES_NAME);
        onCreate(db);
    }
    //Create AddActivity Database
    public void add_trip(String name, String destination, String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISK, risk);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME, null, cv);
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db!= null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;
    }
// UpdateData by ID
    public boolean updateData(String id, String name, String destination, String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISK, risk);
        cv.put(COLUMN_DESCRIPTION, description);
        db.update(TABLE_NAME, cv,"_id = ?",new String[] {id});
        return true;
    }

// DeleteData by ID
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"_id = ?",new String[]{id});
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ TABLE_NAME);
    }

    //Create ExpensesActivity Database
    public void add_detail(String type, String amount, String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();

        cv2.put(COLUMN_TYPE, type);
        cv2.put(COLUMN_AMOUNT, amount);
        cv2.put(COLUMN_TIME_EXPENSES, time);
        long result = db.insert(TABLE_EXPENSES_NAME, null, cv2);
    }

    public Cursor readAllDataDetail(){
        String query_expenses = "SELECT * FROM " + TABLE_EXPENSES_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor2 = null;
        if(db!= null){
            cursor2 =db.rawQuery(query_expenses,null);
        }
        return cursor2;
    }



}

