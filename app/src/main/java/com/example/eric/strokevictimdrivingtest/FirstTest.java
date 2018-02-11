package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FirstTest extends AppCompatActivity {

    long time_left;
    GridView androidGridView;
    Integer[] matrix_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_test);
        final TextView timerText = findViewById(R.id.timerTxt);


        new CountDownTimer(900000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("seconds remaining: " + millisUntilFinished / 1000);
                time_left = millisUntilFinished / 1000;
            }
            public void onFinish() {
                timerText.setText("done!");
            }
        }.start();

        androidGridView = findViewById(R.id.gridCrossGrid);
        androidGridView.setAdapter(new ImageAdapter(this));

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(FirstTest.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });
//        compass.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent event) {
//                switch (event.getActionMasked()) {
//                    case MotionEvent.ACTION_DOWN:
//                        xCoOrdinate = view.getX() - event.getRawX();
//                        yCoOrdinate = view.getY() - event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        xText.setText(String.valueOf(view.getX()));
//                        yText.setText(String.valueOf(view.getY()));
//                        break;
//                    default:
//                        return false;
//                }
//
//                return true;
//            }
//        });
    }

    public void startNextTest(View view){
        Bundle bundle = getIntent().getExtras();
        bundle.putString("test1_score", "8");
        bundle.putString("test1_time",String.valueOf(time_left));

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }
}

