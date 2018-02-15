package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DirectionsMatrixTest extends AppCompatActivity {
    List<Integer> imageList = new ArrayList<>();

    int heldCardNo = 0;
    int nextCardNo = 1;
    Boolean firstClick = true;

    GridView androidGridView;
    public Integer[] directionsAnswerGrid = new Integer[16];

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, directionsAnswerGrid);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_matrix_test);

        Arrays.fill(directionsAnswerGrid, R.drawable.clear);

        imageList.add(R.drawable.directions_example);
        imageList.add(R.drawable.both_away);
        imageList.add(R.drawable.both_front);
        imageList.add(R.drawable.both_right);
        imageList.add(R.drawable.both_left);
        imageList.add(R.drawable.left_lorry_right_car);
        imageList.add(R.drawable.lorry_away_car_forward);
        imageList.add(R.drawable.lorry_away_car_left);
        imageList.add(R.drawable.lorry_away_car_right);
        imageList.add(R.drawable.lorry_forward_car_away);
        imageList.add(R.drawable.lorry_forward_car_left);
        imageList.add(R.drawable.lorry_forward_car_right);
        imageList.add(R.drawable.lorry_left_car_away);
        imageList.add(R.drawable.lorry_left_car_forward);
        imageList.add(R.drawable.lorry_right_car_forward);
        imageList.add(R.drawable.lorry_right_car_left);

        androidGridView = findViewById(R.id.directionsBoardGrid);
        androidGridView.setAdapter(adapter);

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (!imageList.isEmpty()) {
                    if (directionsAnswerGrid[position] != R.drawable.clear) {
                        Integer temp = directionsAnswerGrid[position];
                        directionsAnswerGrid[position] = imageList.get(heldCardNo);
                        imageList.set(heldCardNo, temp);
                        currentCard.setImageResource(imageList.get(heldCardNo));
                    } else {
                        directionsAnswerGrid[position] = imageList.get(heldCardNo);
                        imageList.remove(heldCardNo);
                        //heldCardNo = nextCardNo;

                        //nextCardNo += 1;

                        if (heldCardNo == imageList.size() - 1) {
                            heldCardNo = 0;
                            nextCardNo = 1;
                        }

                        if (firstClick) {
                            androidGridView.setEnabled(false);
                            firstClick = false;
                        }

                        if (imageList.size() == 1) {
                            heldCardNo = 0;
                            nextCardNo = 0;
                            currentCard.setImageResource(imageList.get(heldCardNo));
                            nextCard.setImageResource(R.drawable.clear);
                        } else if (!imageList.isEmpty()) {
                            currentCard.setImageResource(imageList.get(heldCardNo));
                            nextCard.setImageResource(imageList.get(nextCardNo));
                        } else {
                            currentCard.setImageResource(R.drawable.clear);
                            nextCard.setImageResource(R.drawable.clear);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

        androidGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DirectionsMatrixTest.this, "butts" , Toast.LENGTH_SHORT).show();

                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                imageList.add(directionsAnswerGrid[position]);

                if (imageList.size() == 1){
                    currentCard.setImageResource(imageList.get(0));
                    nextCard.setImageResource(R.drawable.clear);
                    heldCardNo = 0;
                    nextCardNo = 0;
                }

                if (imageList.size() == 2){
                    nextCard.setImageResource(imageList.get(1));
                    heldCardNo = 0;
                    nextCardNo = 1;
                }

                directionsAnswerGrid[position] = R.drawable.clear;
                adapter.notifyDataSetChanged();

                return true;
            }
        });

    }

    public void startTest(View view){
        if(firstClick){
            Toast.makeText(DirectionsMatrixTest.this, "Please place the example image!" , Toast.LENGTH_SHORT).show();
        }
        else
        {

//            new CountDownTimer(timelimit, 1000) {
//                public void onTick(long millisUntilFinished) {
//                    //updates the time left every second
//                    time_left = millisUntilFinished / 1000;
//                }
//                public void onFinish() {
//                    time_left = 0;
//                    //tells the user that the time is up
//                    textWarning.setText("That’s fine, you have done enough now and can stop.");
//                    //records the scores
//                    for (int i=23; i <directionsAnswerGrid.length; i++){
//                        //records the correct dots that the user missed
//                        if (DotAnswerGrid[i].equals(R.drawable.clear) && (DotCorrectGrid[i].equals(R.drawable.cross))){
//                            notCrossed += 1;
//                        }
//                        //records the dots that the user crossed that were incorrect
//                        if (DotAnswerGrid[i].equals(R.drawable.cross) && (DotCorrectGrid[i].equals(R.drawable.clear))){
//                            wrongCrossed += 1;
//                        }
//                    }
//                }
//            }.start();

            androidGridView.setEnabled(true);

            TextView instructions = findViewById(R.id.instructionsTest);
            Button startTest = findViewById(R.id.startButton);

            instructions.setVisibility(View.INVISIBLE);
            startTest.setVisibility(View.INVISIBLE);
        }
    }


    //onClick to add to the stack of card images
    public void getNextCard(View view) {
        ImageView nextCard = findViewById(R.id.nextCard);
        ImageView currentCard = findViewById(R.id.heldCard);

        if (!imageList.isEmpty()) {
            if (imageList.size() == 1) {
                heldCardNo = 0;
                currentCard.setImageResource(imageList.get(heldCardNo));
                nextCard.setImageResource(R.drawable.clear);
            } else {


                if (heldCardNo == imageList.size() - 1) {
                    heldCardNo = 0;
                    nextCardNo = 1;
                } else if (nextCardNo == imageList.size() - 1) {
                    heldCardNo += 1;
                    nextCardNo = 0;
                } else {
                    heldCardNo += 1;
                    nextCardNo = heldCardNo + 1;
                }

                currentCard.setImageResource(imageList.get(heldCardNo));
                nextCard.setImageResource(imageList.get(nextCardNo));
            }

        }
    }


    public void startNextTest(View view){
        //long elapsedMillis = (SystemClock.elapsedRealtime() - overtime.getBase()) / 1000;
        //timerText.setText(String.valueOf(elapsedMillis));
        //overtime.stop();

        //Bundle bundle = getIntent().getExtras();
        //bundle.putString("test1_score", "8");
        //bundle.putString("test1_time",String.valueOf(time_left));

        Intent intent = new Intent(this, CompassMatrixTest.class);
        //intent.putExtras(bundle);

        startActivity(intent);
    }


}
