package com.example.apple;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String EXTRA_MESSAGE = "com.example.apple.MESSAGE";

    Button btnPlay,btnAbout,btnRecords;
    private Context context;
    EditText etPlayerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnRecords = (Button) findViewById(R.id.btnRecords);
        etPlayerName = (EditText) findViewById(R.id.etPlayerName) ;
        btnPlay.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnRecords.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay:
                Intent intent1 = new Intent(this, GameActivity.class);
                intent1.putExtra("PlayerName", etPlayerName.getText().toString());
                startActivity(intent1);
                break;
            case R.id.btnAbout:
                Intent intent2 = new Intent(this, AboutActivity.class);
                startActivity(intent2);
                break;
            case R.id.btnRecords:
                Intent intent3 = new Intent(this, RecordsActivity.class);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }



      /*  View.OnClickListener oclPlay = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(this, GameActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

        };
        btnPlay.setOnClickListener(oclPlay);
    }*/
}
