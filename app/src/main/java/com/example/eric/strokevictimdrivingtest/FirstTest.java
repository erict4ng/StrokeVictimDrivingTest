package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FirstTest extends AppCompatActivity {

    private float xCoOrdinate, yCoOrdinate;
    long timeleft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_test);
        final TextView timerText = findViewById(R.id.timerTxt);
//        final TextView xText = findViewById(R.id.txtX);
//        final TextView yText = findViewById(R.id.txtY);

        new CountDownTimer(900000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText("seconds remaining: " + millisUntilFinished / 1000);
                timeleft = millisUntilFinished / 1000;
            }

            public void onFinish() {
                timerText.setText("done!");
            }
        }.start();

        ImageView dot_cancel_grid = (ImageView)findViewById(R.id.imgDotGrid);

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
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("test1_score",String.valueOf(timeleft));
        intent.putExtra("test1_time","8");
        startActivity(intent);
    }
}

