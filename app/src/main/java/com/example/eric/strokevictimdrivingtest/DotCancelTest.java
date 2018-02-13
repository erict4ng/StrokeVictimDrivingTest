package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Timer;

public class DotCancelTest extends AppCompatActivity {

    long time_left;
    GridView androidGridView;
    boolean[] matrix_test = new boolean[432];
    public Integer[] mThumbIds = new Integer[432];
    public TextView timerText;

    public Chronometer overtime;

    DotImageAdapter adapter = new DotImageAdapter(this, mThumbIds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_cancel_test);
        timerText = findViewById(R.id.timerTxt);
        overtime = findViewById(R.id.chrTimer);

        Arrays.fill(mThumbIds, R.drawable.clear);
        Arrays.fill(matrix_test, false);

        matrix_test[1] = true;
        mThumbIds[1] = R.drawable.cross;
        matrix_test[8] = true;
        mThumbIds[8] = R.drawable.cross;
        matrix_test[9] = true;
        mThumbIds[9] = R.drawable.cross;
        matrix_test[13] = true;
        mThumbIds[13] = R.drawable.cross;

        androidGridView = findViewById(R.id.gridCrossGrid);
        androidGridView.setAdapter(adapter);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (!matrix_test[position]) {
                    matrix_test[position] = true;
                    mThumbIds[position] = R.drawable.cross;
                    Toast.makeText(DotCancelTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                }
                else if(matrix_test[position]) {
                    matrix_test[position] = false;
                    mThumbIds[position] = R.drawable.clear;
                    Toast.makeText(DotCancelTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                }

                adapter.notifyDataSetChanged();
            }
        });

    }

    public void hideInstructions(View view){
        if ((matrix_test[1]) && (matrix_test[8]) && (matrix_test[9]) && (matrix_test[13]) &&
                (matrix_test[14]) && (matrix_test[15]) && (matrix_test[17]) && (matrix_test[22])){
            final TextView txtInstructions = findViewById(R.id.txtInstructions);
            txtInstructions.setVisibility(View.INVISIBLE);

            new CountDownTimer(90000, 1000) {
                public void onTick(long millisUntilFinished) {
                    timerText.setText("seconds remaining: " + millisUntilFinished / 1000);
                    time_left = millisUntilFinished / 1000;
                }
                public void onFinish() {
                    overtime.setBase(SystemClock.elapsedRealtime());
                    overtime.start();

                    timerText.setText("done!");
                }
            }.start();
        }
    }

    public void startNextTest(View view){
        long elapsedMillis = (SystemClock.elapsedRealtime() - overtime.getBase()) / 1000;
        timerText.setText(String.valueOf(elapsedMillis));
        overtime.stop();

        Bundle bundle = getIntent().getExtras();
        bundle.putString("test1_score", "8");
        bundle.putString("test1_time",String.valueOf(time_left));

        Intent intent = new Intent(this, DirectionsMatrixTest.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }




}

