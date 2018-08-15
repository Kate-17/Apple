package com.example.apple;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity  {
    TextView edt, edt1, edt2, edt3, edt4, edt5, edt6, edt7, edt8, tx, nameOfPlayer;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    ImageView img, img1, img2, img3, img4, img5, img6, img7, img8;
    ImageButton btnRep, btnZap;
    int count = 1;
    String playerName;
    Record rec;
    boolean flag;
    boolean flagv;

    int n = 9, k = 4;
    Random r = new Random();
    final Integer[] random_number = new Integer[k];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);


        guess();
        final DBHelper db = new DBHelper(this);
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        edt1 = (TextView) findViewById(R.id.edt1);
        edt2 = (TextView) findViewById(R.id.edt2);
        edt3 = (TextView) findViewById(R.id.edt3);
        edt4 = (TextView) findViewById(R.id.edt4);
        edt5 = (TextView) findViewById(R.id.edt5);
        edt6 = (TextView) findViewById(R.id.edt6);
        edt7 = (TextView) findViewById(R.id.edt7);
        edt8 = (TextView) findViewById(R.id.edt8);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);
        img5 = (ImageView) findViewById(R.id.img5);
        img6 = (ImageView) findViewById(R.id.img6);
        img7 = (ImageView) findViewById(R.id.img7);
        img8 = (ImageView) findViewById(R.id.img8);
        nameOfPlayer = (TextView) findViewById(R.id.nameOfPlayer);
        edt1.setFocusable(true);
flagv=false;
        final DBHelper dbHelper;

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        playerName = intent.getStringExtra("PlayerName");
        nameOfPlayer.setText(playerName);
        tx = (TextView) findViewById(R.id.tx);
        btnRep = (ImageButton) findViewById(R.id.btnRep);
        btnZap = (ImageButton) findViewById(R.id.btnZap);
        if (count == 1) {
            edt = edt1;
            img = img1;
        }
        btn0.setEnabled(false);


        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn0.setEnabled(true);
                switch (v.getId()) {
                    case R.id.btn0:
                        edt.setText(edt.getText() + "0");
                        btn0.setEnabled(false);
                        break;
                    case R.id.btn1:
                        edt.setText(edt.getText() + "1");
                        btn1.setEnabled(false);
                        break;
                    case R.id.btn2:
                        edt.setText(edt.getText() + "2");
                        btn2.setEnabled(false);
                        break;
                    case R.id.btn3:
                        edt.setText(edt.getText() + "3");
                        btn3.setEnabled(false);
                        break;
                    case R.id.btn4:
                        edt.setText(edt.getText() + "4");
                        btn4.setEnabled(false);
                        break;
                    case R.id.btn5:
                        edt.setText(edt.getText() + "5");
                        btn5.setEnabled(false);
                        break;
                    case R.id.btn6:
                        edt.setText(edt.getText() + "6");
                        btn6.setEnabled(false);
                        break;
                    case R.id.btn7:
                        edt.setText(edt.getText() + "7");
                        btn7.setEnabled(false);
                        break;
                    case R.id.btn8:
                        edt.setText(edt.getText() + "8");
                        btn8.setEnabled(false);
                        break;
                    case R.id.btn9:
                        edt.setText(edt.getText() + "9");
                        btn9.setEnabled(false);
                        break;
                }
                if (edt1.length()==1){ chronometer.setBase(SystemClock.elapsedRealtime());chronometer.start();}//запуск секундомера
                if (edt.length() == 4) {
                    btn0.setEnabled(false);
                    btn1.setEnabled(true);
                    btn2.setEnabled(true);
                    btn3.setEnabled(true);
                    btn4.setEnabled(true);
                    btn5.setEnabled(true);
                    btn6.setEnabled(true);
                    btn7.setEnabled(true);
                    btn8.setEnabled(true);
                    btn9.setEnabled(true);


                    int t = 0;
                    int p = 0;
                    int f = 0;
                    for (int i = 0; i <= 7; i++) {
                        int a = Integer.parseInt(edt.getText().toString());
                        if (a == 0)
                            System.out.println();
                        else {
                            int[] userData = new int[4];
                            userData[3] = a % 10;
                            userData[2] = (a % 100 - userData[3]) / 10;
                            userData[1] = (a % 1000 - userData[3] - userData[2]) / 100;
                            userData[0] = (a - userData[1] * 100 - userData[2] * 10 - userData[3]) / 1000;
                            if (userData[0] == random_number[0] && userData[1] == random_number[1] && userData[2] == random_number[2] && userData[3] == random_number[3]) {
                                flagv=true;
                                img.setImageResource(R.drawable.t);

                              Integer timeInSeconds= getTimeIntoInteger(chronometer);


                                String attempt = String.valueOf(count);


                                rec = new Record();
                                rec.nameOfPlayer=playerName;
                                  rec.numberOfAttempt=attempt;
                                  rec.time=String.valueOf(timeInSeconds);


                                SQLiteDatabase db2;
                                db2 = dbHelper.getWritableDatabase();

                                Cursor c = db2.query("RecordsTable", null, null, null, null, null, null);
                                int n=0;//количество записей
                                if (c != null) {
                                    if (c.moveToFirst()) {
                                        do { n++;
                                            for (String cn : c.getColumnNames()) {
                                            }
                                        } while (c.moveToNext());
                                    }
                                    c.close();
                                } Log.d("dsf", String.valueOf(n));


                                ContentValues cv = new ContentValues();
                                cv.put("nameOfPlayer",playerName);
                                cv.put("numberOfAttempt",attempt);
                                cv.put("time",String.valueOf(timeInSeconds));
                                db2.insert("RecordsTable", null, cv);



                                //последняя добавленная запись
                                final String MY_QUERY = "SELECT last_insert_rowid()";
                                Cursor cur = db2.rawQuery(MY_QUERY, null);
                                cur.moveToFirst();
                                String ID = String.valueOf(cur.getInt(0));
                                //tx.setText(ID);
                                flag=false;
                                int num=0;
                                c=db2.query("RecordsTable", null, null, null, null, null, "numberOfAttempt");
                                if (c != null) {
                                    if (c.moveToFirst()) {
                                        do {
                                            num++;
                                               if (Integer.valueOf(ID)==Integer.valueOf(c.getString(c.getColumnIndex(c.getColumnName(0))))){
                                                   flag=true;
                                                   break;
                                               }
                                        } while (c.moveToNext()&& num<10);

                                    }
                                    c.close();
                                }

                                dbHelper.close();
                                if (flag==true){
                                    Intent intent3 = new Intent(GameActivity.this, RecordsActivity.class);
                                    startActivity(intent3);
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Попал в рекорды!", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                                else{
                                allButtonEanbledFalse();
                                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                                builder.setTitle("Важное сообщение!")
                                        .setMessage("Ты выиграл приз!")
                                        .setIcon(R.drawable.emp)
                                        .setCancelable(false)
                                        .setNegativeButton("ОК, иду на кухню",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                AlertDialog alert = builder.create();
                                alert.show();}
                                chronometer.stop();
                                flag=false;


                                chronometer.stop();chronometer.setBase(SystemClock.elapsedRealtime());
                                guess();
                                edt1.setText("");
                                edt2.setText("");
                                edt3.setText("");
                                edt4.setText("");
                                edt5.setText("");
                                edt6.setText("");
                                edt7.setText("");
                                edt8.setText("");
                                count = 0;
                                edt = edt1;
                                btn0.setEnabled(false);
                                btn1.setEnabled(true);
                                btn2.setEnabled(true);
                                btn3.setEnabled(true);
                                btn4.setEnabled(true);
                                btn5.setEnabled(true);
                                btn6.setEnabled(true);
                                btn7.setEnabled(true);
                                btn8.setEnabled(true);
                                btn9.setEnabled(true);
                                edt1.setFocusable(true);
                                img1.setImageResource(R.drawable.emp);
                                img2.setImageResource(R.drawable.emp);
                                img3.setImageResource(R.drawable.emp);
                                img4.setImageResource(R.drawable.emp);
                                img5.setImageResource(R.drawable.emp);
                                img6.setImageResource(R.drawable.emp);
                                img7.setImageResource(R.drawable.emp);
                                img8.setImageResource(R.drawable.emp);
                                break;
                            }
                            if (userData[0] == random_number[0]) t++;
                            else if (userData[0] == random_number[1] || userData[0] == random_number[2] || userData[0] == random_number[3])
                                p++;
                            else f++;
                            if (userData[1] == random_number[1]) t++;
                            else if (userData[1] == random_number[0] || userData[1] == random_number[2] || userData[1] == random_number[3])
                                p++;
                            else f++;
                            if (userData[2] == random_number[2]) t++;
                            else if (userData[2] == random_number[0] || userData[2] == random_number[1] || userData[2] == random_number[3])
                                p++;
                            else f++;
                            if (userData[3] == random_number[3]) t++;
                            else if (userData[3] == random_number[0] || userData[3] == random_number[1] || userData[3] == random_number[2])
                                p++;
                            else f++;
                            int repeatUser = 0;
                            boolean[] boolRepeatUser = new boolean[4];
                            for (int j = 0; j < 4; j++) {
                                for (int l = j + 1; l < 4; l++) {
                                    if (boolRepeatUser[l] == false) {
                                        if (userData[j] == random_number[0] || userData[j] == random_number[1] || userData[j] == random_number[2] || userData[j] == random_number[3])
                                            if (userData[j] == userData[l]) {
                                                repeatUser++;
                                                boolRepeatUser[l] = true;
                                            }
                                    }
                                }
                            }
                            p = p - repeatUser;
                            f = f + repeatUser;
                            int random4 = 0 + (int) (Math.random() * 4);
                            int random6 = 0 + (int) (Math.random() * 6);
                            int random12 = 0 + (int) (Math.random() * 12);
                            int tochno1_X[] = {R.drawable.t1_1, R.drawable.t1_2, R.drawable.t1_3,R.drawable.t1_4};
                            int tochno2_X[] = {R.drawable.t2_1, R.drawable.t2_2, R.drawable.t2_3,R.drawable.t2_4,R.drawable.t2_5,R.drawable.t2_6};
                            int tochno3_X[] = {R.drawable.t3_1, R.drawable.t3_2, R.drawable.t3_3,R.drawable.t3_4};
                            int pochti1_X[] = {R.drawable.p1_1, R.drawable.p1_2, R.drawable.p1_3,R.drawable.p1_4};
                            int pochti2_X[] = {R.drawable.p2_1, R.drawable.p2_2, R.drawable.p2_3,R.drawable.p2_4,R.drawable.p2_5,R.drawable.p2_6};
                            int pochti3_X[] = {R.drawable.p3_1, R.drawable.p3_2, R.drawable.p3_3,R.drawable.p3_4};
                            int tocho1pochti3_X[]= {R.drawable.t1p3_1,R.drawable.t1p3_2,R.drawable.t1p3_3,R.drawable.t1p3_4};
                            int tocho2pochti2_X[]= {R.drawable.t2p2_1,R.drawable.t2p2_2,R.drawable.t2p2_3,R.drawable.t2p2_4,R.drawable.t2p2_5,R.drawable.t2p2_6};
                            int tocho1pochti1_X[]= {R.drawable.t1p1_1,R.drawable.t1p1_2,R.drawable.t1p1_3,R.drawable.t1p1_4,R.drawable.t1p1_5,R.drawable.t1p1_6,R.drawable.t1p1_7,R.drawable.t1p1_8,R.drawable.t1p1_9,R.drawable.t1p1_10,R.drawable.t1p1_11,R.drawable.t1p1_12};
                            int tocho1pochti2_X[]= {R.drawable.t1p2_1,R.drawable.t1p2_2,R.drawable.t1p2_3,R.drawable.t1p2_4,R.drawable.t1p2_5,R.drawable.t1p2_6,R.drawable.t1p2_7,R.drawable.t1p2_8,R.drawable.t1p2_9,R.drawable.t1p2_10,R.drawable.t1p2_11,R.drawable.t1p2_12};
                            int tocho2pochti1_X[]= {R.drawable.t2p1_1,R.drawable.t2p1_2,R.drawable.t2p1_3,R.drawable.t2p1_4,R.drawable.t2p1_5,R.drawable.t2p1_6,R.drawable.t2p1_7,R.drawable.t2p1_8,R.drawable.t2p1_9,R.drawable.t2p1_10,R.drawable.t2p1_11,R.drawable.t2p1_12};


                            //p-почти попал, f -промах промахов, t-попал в цель
                            //только промахи
                            if (p == 1 & f == 3) img.setImageResource(pochti1_X[random4]);
                            if (p == 2 & f == 2) img.setImageResource(pochti2_X[random6]);
                            if (p == 3 & f == 1) img.setImageResource(pochti3_X[random4]);

                            //только попадания
                            if (t == 1 & f == 3) img.setImageResource(tochno1_X[random4]);
                            if (t == 2 & f == 2) img.setImageResource(tochno2_X[random6]);
                            if (t == 3 & f == 1) img.setImageResource(tochno3_X[random4]);

                            //попадания и почти попадания
                            if (t == 1 & p == 3) img.setImageResource(tocho1pochti3_X[random4]);
                            if (t == 2 & p == 2) img.setImageResource(tocho2pochti2_X[random6]);

                            //попадания, почти попадания, промахи
                            if (t == 1 & p == 1) img.setImageResource(tocho1pochti1_X[random12]);
                            if (t == 1 & p == 2) img.setImageResource(tocho1pochti2_X[random12]);
                            if (t == 2 & p == 1) img.setImageResource(tocho2pochti1_X[random12]);

                            if (p == 4) img.setImageResource(R.drawable.p4);
                            t = 0;
                            p = 0;
                            f = 0;

                        }
                    }


                    edt.setFocusable(false);
                    count++;

                        switch (count) {
                            case 1:
                                edt = edt1;
                                img = img1;
                                break;
                            case 2:
                                edt = edt2;
                                img = img2;
                                break;
                            case 3:
                                edt = edt3;
                                img = img3;
                                break;
                            case 4:
                                edt = edt4;
                                img = img4;
                                break;
                            case 5:
                                edt = edt5;
                                img = img5;
                                break;
                            case 6:
                                edt = edt6;
                                img = img6;
                                break;
                            case 7:
                                edt = edt7;
                                img = img7;
                                break;
                            case 8:
                                edt = edt8;
                                img = img8;
                                break;
                            case 9:
                                edt.setEnabled(false);
                               allButtonEanbledFalse();
                                if (flagv==false && edt8.getText()!=random_number[0] + "" + random_number[1] + "" + random_number[2] + "" + random_number[3]){
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(GameActivity.this);
                                builder2.setTitle("Важное сообщение!")
                                        .setMessage("Ты проиграл, использовав все попытки! Ответ был "+String.valueOf(random_number[0]) + String.valueOf(random_number[1]) + String.valueOf(random_number[2]) + String.valueOf(random_number[3]))
                                        .setIcon(R.drawable.emp)
                                        .setCancelable(false)
                                        .setNegativeButton("ОК, спасибо",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int id) {
                                                        dialog.cancel();
                                                    }
                                                });
                                AlertDialog alert = builder2.create();
                                alert.show();}
                                chronometer.stop();

                                break;
                        }
                        edt.setFocusable(true);

                }
            }
        };
        btn0.setOnClickListener(oclBtnOk);
        btn1.setOnClickListener(oclBtnOk);
        btn2.setOnClickListener(oclBtnOk);
        btn3.setOnClickListener(oclBtnOk);
        btn4.setOnClickListener(oclBtnOk);
        btn5.setOnClickListener(oclBtnOk);
        btn6.setOnClickListener(oclBtnOk);
        btn7.setOnClickListener(oclBtnOk);
        btn8.setOnClickListener(oclBtnOk);
        btn9.setOnClickListener(oclBtnOk);


        View.OnClickListener oclBtnZap = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tx.setText(String.valueOf(random_number[0]) + String.valueOf(random_number[1]) + String.valueOf(random_number[2]) + String.valueOf(random_number[3]));
            }
        };
        btnZap.setOnClickListener(oclBtnZap);

        View.OnClickListener oclBtnRep = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.stop();chronometer.setBase(SystemClock.elapsedRealtime());
                guess();
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
                edt4.setText("");
                edt5.setText("");
                edt6.setText("");
                edt7.setText("");
                edt8.setText("");
                count = 1;
                edt = edt1;
                btn0.setEnabled(false);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                edt1.setFocusable(true);
                img1.setImageResource(R.drawable.emp);
                img2.setImageResource(R.drawable.emp);
                img3.setImageResource(R.drawable.emp);
                img4.setImageResource(R.drawable.emp);
                img5.setImageResource(R.drawable.emp);
                img6.setImageResource(R.drawable.emp);
                img7.setImageResource(R.drawable.emp);
                img8.setImageResource(R.drawable.emp);

            }
        };
        btnRep.setOnClickListener(oclBtnRep);
    }


    public void guess() {
        flagv=false;
        edt = edt1;
        img = img1;
        count = 1;
        for (int i = 0; i < k; i++)
            random_number[i] = i + 1;

        for (int i = k; i < n; i++) {
            int j = r.nextInt(i + 1);
            if (j < k)
                random_number[j] = i + 1;
        }
        if (0 + (int) (Math.random() * 9) == 0) random_number[1 + (int) (Math.random() * 3)] = 0;
    }


    public Integer getTimeIntoInteger(Chronometer chronometer){
    int stoppedMilliseconds = 0;

    String chronoText = chronometer.getText().toString();
    String array[] = chronoText.split(":");
                                if (array.length == 2) {
        stoppedMilliseconds = Integer.parseInt(array[0]) * 60
                + Integer.parseInt(array[1]);
    }

    return stoppedMilliseconds;
    }

    public  void allButtonEanbledFalse(){
        btn0.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
    }
}
