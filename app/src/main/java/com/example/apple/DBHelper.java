package com.example.apple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "RecordsBD";
    // Contacts table name
    private static final String TABLE_RECORDS = "RecordsTable";
    // Shops Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "nameOfPlayer";
    public static final String KEY_ATTEMPTS = "numberOfAttempt";
    public static final String KEY_TIME = "time";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORDS_TABLE = "CREATE TABLE " + TABLE_RECORDS + " ("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_ATTEMPTS + " TEXT, "
                + KEY_TIME + " TEXT" + ");";
        db.execSQL(CREATE_RECORDS_TABLE);
    }


    public void onOrder(SQLiteDatabase db) {
        String s = "SELECT * FROM "+ TABLE_RECORDS +
                " ORDER BY numberOfAttempt";
        db.execSQL(s);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECORDS);
// Creating tables again
        onCreate(db);
    }

    public void addRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        Log.d("Insert: ", "Inserting ..");
        //  values.put(KEY_ID, record.getNumberOfAttempt());
        values.put(KEY_NAME, record.nameOfPlayer);
        values.put(KEY_ATTEMPTS, record.numberOfAttempt);
        values.put(KEY_TIME, record.time);
// Inserting Row

        //  db.insert(TABLE_RECORDS,null, values);
        long rowID = db.insert(TABLE_RECORDS, null, values);
        Log.d("row inserted, ID = " + record.time, String.valueOf(rowID));
        db.close(); // Closing database connection
    }

    // Getting one shop
    public Record getRecord(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECORDS, new String[]{KEY_ID,
                        KEY_NAME, KEY_ATTEMPTS, KEY_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Record contact = new Record();
       /* Record contact = new Record(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3));*/
// return shop
        return contact;
    }

    // Getting All Shops
    public List<Record> getAllRecords() {
        List<Record> recordList = new ArrayList<Record>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_RECORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Record record = new Record();
                record.id = Integer.parseInt(cursor.getString(0));
                record.nameOfPlayer = cursor.getString(1);
                record.numberOfAttempt = cursor.getString(2);
                record.time = cursor.getString(3);
// Adding contact to list
                recordList.add(record);
            } while (cursor.moveToNext());
        }
// return contact list
        return recordList;
    }
    // Getting shops Count

    public int getRecordsCount() {
        String countQuery = "SELECT * FROM " + TABLE_RECORDS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
// return count
        return cursor.getCount();
    }

    public void deleteRecord(Record record) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECORDS, KEY_ID + " = ?",
                new String[]{String.valueOf(record.getId())});
        db.close();
    }



}

