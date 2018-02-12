package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class DotCancelTest extends AppCompatActivity {

    long time_left;
    GridView androidGridView;
    boolean[] matrix_test = new boolean[432];
    public Integer[] mThumbIds = new Integer[432];

    DotImageAdapter adapter = new DotImageAdapter(this, mThumbIds);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_cancel_test);
        final TextView timerText = findViewById(R.id.timerTxt);

        Arrays.fill(mThumbIds, R.drawable.clear);
        Arrays.fill(matrix_test, false);

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
        androidGridView.setAdapter(adapter);

        /*androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View imgView, int position, long id) {
                //ImageView imageView = (ImageView) imgView;
                //imageView.setImageResource(R.drawable.cross);
                matrix_test[position] = true;
                mThumbIds[position] = R.drawable.cross;
                androidGridView.deferNotifyDataSetChanged();
                adapter.notifyDataSetChanged();
            }
        });*/





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

        Intent intent = new Intent(this, DirectionsMatrixTest.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }




}

