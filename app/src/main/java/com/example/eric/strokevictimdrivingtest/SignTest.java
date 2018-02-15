package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class SignTest extends AppCompatActivity {

    public Integer[] SignsAnswerGrid = new Integer[13];

    public Integer[] SignsCorrectGrid = new Integer[13];

    List<Integer> sign_list = new ArrayList<Integer>();
    int heldCardNo = 0;
    int nextCardNo = 1;

    Boolean firstClick = true;

    long signScore;

    long time_limit = 180000;

    boolean timeup = false;

    //declare gridview variables
    GridView androidGridView;
    SignAdapter imageAdapter = new SignAdapter(this, SignsAnswerGrid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_test);

        Arrays.fill(SignsAnswerGrid, R.drawable.clear);

        sign_list.add(R.drawable.level_crossing);
        sign_list.add(R.drawable.children_sign);
        sign_list.add(R.drawable.closed_lane);
        sign_list.add(R.drawable.crossroads);
        sign_list.add(R.drawable.double_bend);
        sign_list.add(R.drawable.dual_carriageway_ends);
        sign_list.add(R.drawable.give_way);
        sign_list.add(R.drawable.height_restriction);
        sign_list.add(R.drawable.lane_crossover);
        sign_list.add(R.drawable.no_left_turn);
        sign_list.add(R.drawable.no_overtaking);
        sign_list.add(R.drawable.no_waiting);
        sign_list.add(R.drawable.road_works);
        sign_list.add(R.drawable.roundabout);



        SignsCorrectGrid[0] = R.drawable.level_crossing;
        SignsCorrectGrid[1] = R.drawable.children_sign;
        SignsCorrectGrid[2] = R.drawable.double_bend;
        SignsCorrectGrid[3] = R.drawable.no_left_turn;
        SignsCorrectGrid[4] = R.drawable.dual_carriageway_ends;
        SignsCorrectGrid[5] = R.drawable.crossroads;
        SignsCorrectGrid[6] = R.drawable.give_way;
        SignsCorrectGrid[7] = R.drawable.height_restriction;
        SignsCorrectGrid[8] = R.drawable.lane_crossover;
        SignsCorrectGrid[9] = R.drawable.no_overtaking;
        SignsCorrectGrid[10] = R.drawable.closed_lane;
        SignsCorrectGrid[11] = R.drawable.road_works;


        androidGridView = findViewById(R.id.gridSignsBoard);
        androidGridView.setAdapter(imageAdapter);

                androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        ImageView nextCard = findViewById(R.id.imgNextCard);
                        ImageView currentCard = findViewById(R.id.imgHeldCard);

                        if (!sign_list.isEmpty()) {
                            if (SignsAnswerGrid[position] != R.drawable.clear)
                            {
                                Integer temp = SignsAnswerGrid[position];
                                SignsAnswerGrid[position] = sign_list.get(heldCardNo);
                                sign_list.set(heldCardNo, temp);
                                currentCard.setImageResource(sign_list.get(heldCardNo));
                            }
                            else
                            {
                                SignsAnswerGrid[position] = sign_list.get(heldCardNo);
                                sign_list.remove(heldCardNo);

                                if (firstClick) {
                                    androidGridView.setEnabled(false);
                                    firstClick = false;
                                }

                                if (heldCardNo == sign_list.size() - 1)
                                {
                                    heldCardNo = 0;
                                    nextCardNo = 1;
                                }


                                if (sign_list.size() == 1)
                                {
                                    heldCardNo = 0;
                                    nextCardNo = 0;
                                    currentCard.setImageResource(sign_list.get(heldCardNo));
                                    nextCard.setImageResource(R.drawable.clear);
                                }
                                else if (!sign_list.isEmpty())
                                {
                                    currentCard.setImageResource(sign_list.get(heldCardNo));
                                    nextCard.setImageResource(sign_list.get(nextCardNo));
                                }
                                else
                                {
                                    currentCard.setImageResource(R.drawable.clear);
                                    nextCard.setImageResource(R.drawable.clear);
                                }
                            }
                        }

                        imageAdapter.notifyDataSetChanged();
                    }
                });


        androidGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView nextCard = findViewById(R.id.imgNextCard);
                ImageView currentCard = findViewById(R.id.imgHeldCard);

                if (SignsAnswerGrid[position] != R.drawable.clear) {
                    sign_list.add(SignsAnswerGrid[position]);

                    if (sign_list.size() == 1) {
                        currentCard.setImageResource(sign_list.get(0));
                        nextCard.setImageResource(R.drawable.clear);
                        heldCardNo = 0;
                        nextCardNo = 0;
                    }

                    if (sign_list.size() == 2) {
                        nextCard.setImageResource(sign_list.get(1));
                        heldCardNo = 0;
                        nextCardNo = 1;
                    }

                    SignsAnswerGrid[position] = R.drawable.clear;
                    imageAdapter.notifyDataSetChanged();
                }

                return true;
            }
        });
    }

    public void getNextCard(View view){
        ImageView nextCard = findViewById(R.id.imgNextCard);
        ImageView currentCard = findViewById(R.id.imgHeldCard);

        if (!sign_list.isEmpty()) {
            if (sign_list.size() == 1) {
                heldCardNo = 0;
                currentCard.setImageResource(sign_list.get(heldCardNo));
                nextCard.setImageResource(R.drawable.clear);
            } else {

                if (heldCardNo == sign_list.size() - 1) {
                    heldCardNo = 0;
                    nextCardNo = 1;
                } else if (nextCardNo == sign_list.size() - 1) {
                    heldCardNo += 1;
                    nextCardNo = 0;
                } else {
                    heldCardNo += 1;
                    nextCardNo = heldCardNo + 1;
                }

                currentCard.setImageResource(sign_list.get(heldCardNo));
                nextCard.setImageResource(sign_list.get(nextCardNo));
            }

        }
    }

    public void startTest(View view){
        if(firstClick){
            Toast.makeText(SignTest.this, "Please place the example image in the right slot!" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            new CountDownTimer(time_limit, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
//                    textWarning.setText("That’s fine, you have done enough now and can stop.");
                    //records the scores
                    timeup = true;
                    for (int i=0; i <SignsAnswerGrid.length - 1; i++){
                        //records the correct dots that the user missed
                        if (SignsAnswerGrid[i].equals(R.drawable.clear) && (SignsCorrectGrid[i].equals(R.drawable.cross))){
                            signScore += 1;
                        }
                    }
                }
            }.start();

            androidGridView.setEnabled(true);


            Button startTest = findViewById(R.id.btnStartTest);

            TextView instructionsTxt = (TextView) findViewById(R.id.instructionsTxt);
            Button finishTest = (Button) findViewById(R.id.btnFinish);

            finishTest.setVisibility(VISIBLE);
            startTest.setVisibility(INVISIBLE);
            instructionsTxt.setVisibility(INVISIBLE);
            androidGridView.setEnabled(true);
        }
    }

    public void confirm(View view){
        Toast.makeText(SignTest.this, "Are you sure you wish to finish the test?" , Toast.LENGTH_SHORT).show();
        Button finishTests = (Button) findViewById(R.id.confirmBtn);
        finishTests.setVisibility(VISIBLE);
    }

    public void finishTests(View view){

        if(!timeup){
            for (int i=0; i <SignsAnswerGrid.length - 1; i++){
                //records the correct dots that the user missed
                if (SignsAnswerGrid[i].equals(SignsCorrectGrid[i])){
                    signScore += 1;
                }
            }
        }

        Bundle bundle = getIntent().getExtras();
        bundle.putLong("Sign_Score", signScore);

        Intent intent = new Intent(this, endPage.class);
        intent.putExtras(bundle);

    startActivity(intent);
    }


    @Override
    public void onBackPressed() {

    }
}
