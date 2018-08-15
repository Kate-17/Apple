package com.example.apple;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.List;

import static com.example.apple.DBHelper.KEY_ATTEMPTS;
import static com.example.apple.DBHelper.KEY_ID;
import static com.example.apple.DBHelper.KEY_NAME;
import static com.example.apple.DBHelper.KEY_TIME;


public class RecordsActivity extends AppCompatActivity {
    TextView tvPlayer1_Name, tvPlayer2_Name, tvPlayer3_Name, tvPlayer4_Name, tvPlayer5_Name, tvPlayer6_Name, tvPlayer7_Name, tvPlayer8_Name, tvPlayer9_Name, tvPlayer10_Name;
    TextView tvPlayer1_Attempt, tvPlayer2_Attempt, tvPlayer3_Attempt, tvPlayer4_Attempt, tvPlayer5_Attempt, tvPlayer6_Attempt, tvPlayer7_Attempt, tvPlayer8_Attempt, tvPlayer9_Attempt, tvPlayer10_Attempt;
    TextView tvPlayer1_Time, tvPlayer2_Time, tvPlayer3_Time, tvPlayer4_Time, tvPlayer5_Time, tvPlayer6_Time, tvPlayer7_Time, tvPlayer8_Time, tvPlayer9_Time, tvPlayer10_Time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        tvPlayer1_Name = (TextView) findViewById(R.id.tvPlayer1_Name);
        tvPlayer2_Name = (TextView) findViewById(R.id.tvPlayer2_Name);
        tvPlayer3_Name = (TextView) findViewById(R.id.tvPlayer3_Name);
        tvPlayer4_Name = (TextView) findViewById(R.id.tvPlayer4_Name);
        tvPlayer5_Name = (TextView) findViewById(R.id.tvPlayer5_Name);
        tvPlayer6_Name = (TextView) findViewById(R.id.tvPlayer6_Name);
        tvPlayer7_Name = (TextView) findViewById(R.id.tvPlayer7_Name);
        tvPlayer8_Name = (TextView) findViewById(R.id.tvPlayer8_Name);
        tvPlayer9_Name = (TextView) findViewById(R.id.tvPlayer9_Name);
        tvPlayer10_Name = (TextView) findViewById(R.id.tvPlayer10_Name);

        tvPlayer1_Attempt = (TextView) findViewById(R.id.tvPlayer1_Attempt);
        tvPlayer2_Attempt = (TextView) findViewById(R.id.tvPlayer2_Attempt);
        tvPlayer3_Attempt = (TextView) findViewById(R.id.tvPlayer3_Attempt);
        tvPlayer4_Attempt = (TextView) findViewById(R.id.tvPlayer4_Attempt);
        tvPlayer5_Attempt = (TextView) findViewById(R.id.tvPlayer5_Attempt);
        tvPlayer6_Attempt = (TextView) findViewById(R.id.tvPlayer6_Attempt);
        tvPlayer7_Attempt = (TextView) findViewById(R.id.tvPlayer7_Attempt);
        tvPlayer8_Attempt = (TextView) findViewById(R.id.tvPlayer8_Attempt);
        tvPlayer9_Attempt = (TextView) findViewById(R.id.tvPlayer9_Attempt);
        tvPlayer10_Attempt = (TextView) findViewById(R.id.tvPlayer10_Attempt);

        tvPlayer1_Time = (TextView) findViewById(R.id.tvPlayer1_Time);
        tvPlayer2_Time = (TextView) findViewById(R.id.tvPlayer2_Time);
        tvPlayer3_Time = (TextView) findViewById(R.id.tvPlayer3_Time);
        tvPlayer4_Time = (TextView) findViewById(R.id.tvPlayer4_Time);
        tvPlayer5_Time = (TextView) findViewById(R.id.tvPlayer5_Time);
        tvPlayer6_Time = (TextView) findViewById(R.id.tvPlayer6_Time);
        tvPlayer7_Time = (TextView) findViewById(R.id.tvPlayer7_Time);
        tvPlayer8_Time = (TextView) findViewById(R.id.tvPlayer8_Time);
        tvPlayer9_Time = (TextView) findViewById(R.id.tvPlayer9_Time);
        tvPlayer10_Time = (TextView) findViewById(R.id.tvPlayer10_Time);


        TextView[] tv1={tvPlayer1_Name,tvPlayer1_Attempt,tvPlayer1_Time,
                        tvPlayer2_Name,tvPlayer2_Attempt,tvPlayer2_Time,
                tvPlayer3_Name,tvPlayer3_Attempt,tvPlayer3_Time,
                tvPlayer4_Name,tvPlayer4_Attempt,tvPlayer4_Time,
                tvPlayer5_Name,tvPlayer5_Attempt,tvPlayer5_Time,
                tvPlayer6_Name,tvPlayer6_Attempt,tvPlayer6_Time,
                tvPlayer7_Name,tvPlayer7_Attempt,tvPlayer7_Time,
                tvPlayer8_Name,tvPlayer8_Attempt,tvPlayer8_Time,
                tvPlayer9_Name,tvPlayer9_Attempt,tvPlayer9_Time,
                tvPlayer10_Name,tvPlayer10_Attempt,tvPlayer10_Time,


        };
        final DBHelper dbHelper;

        dbHelper = new DBHelper(this);



        SQLiteDatabase db2;
        db2 = dbHelper.getWritableDatabase();
      /*  Cursor c = db2.query("RecordsTable", new String[] {KEY_ID,
                        KEY_NAME,KEY_ATTEMPTS,KEY_TIME},
                null, null, null, null, KEY_ATTEMPTS   + " ASC");
*/
    //c=db2.query("RecordsTable", null, null, null, null, null, "numberOfAttempt");
        Cursor  c=db2.query("RecordsTable", null, null, null, null, null, "numberOfAttempt");


        int num=0;int k=0;

        if (c != null) {
            if (c.moveToFirst()) {
                String str;
                do {
                    str = "";num++;
                    for (int i=1;i<4;i++) {
                        String cn = c.getColumnName(i);
                        if (i==3){  tv1[k].setText(getTimeIntoString(c.getString(c.getColumnIndex(cn))));}
                        else tv1[k].setText(c.getString(c.getColumnIndex(cn)));
                        k++;
                        str = str.concat(cn + " = "
                                + c.getString(c.getColumnIndex(cn)) + "; ");
                    }
                    Log.d("lfdka", str);
                } while (c.moveToNext()&& num<10);

            }
            c.close();
        }



        dbHelper.close();





    }

    public String getTimeIntoString(String timeInSeconds){
String minutesStr="",secondsStr="";
        int minutes = Integer.valueOf(timeInSeconds)/60;
        int seconds =Integer.valueOf(timeInSeconds)%60;
        minutesStr=String.valueOf(minutes);
        secondsStr=String.valueOf(seconds);
        if (String.valueOf(minutes).length()<2) minutesStr="0"+String.valueOf(minutes);
        if (String.valueOf(seconds).length()<2) secondsStr="0"+String.valueOf(seconds);
        String timeInString = minutesStr+":"+secondsStr;
        return timeInString;
    }

}
