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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class DirectionsMatrixTest extends AppCompatActivity {
    List<Integer> imageList = new ArrayList<>();

    int heldCardNo = 0;
    int nextCardNo = 1;
    Boolean firstClick = true;
    long time_limit = 180000;
    long directionsScore = 0;
    TextView textDirectinosHelp;

    Boolean timeup = false;

    GridView androidGridView;
    Integer[] directionsAnswerGrid = new Integer[16];
    Integer[] direcitonsCorrectGrid = new Integer[16];

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, directionsAnswerGrid);

    TextView instructions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_matrix_test);
        textDirectinosHelp = findViewById(R.id.txtSignHelp);

        Arrays.fill(directionsAnswerGrid, R.drawable.clear);
        Arrays.fill(direcitonsCorrectGrid, R.drawable.clear);

        instructions = findViewById(R.id.txtInstructions);

        direcitonsCorrectGrid[0] = R.drawable.lorry_away_car_right;
        direcitonsCorrectGrid[1] = R.drawable.lorry_away_car_away;
        direcitonsCorrectGrid[2] = R.drawable.lorry_away_car_left;
        direcitonsCorrectGrid[3] = R.drawable.lorry_away_car_forward;
        direcitonsCorrectGrid[4] = R.drawable.lorry_forward_car_right;
        direcitonsCorrectGrid[5] = R.drawable.lorry_forward_car_away;
        direcitonsCorrectGrid[6] = R.drawable.lorry_forward_car_left;
        direcitonsCorrectGrid[7] = R.drawable.lorry_forward_car_forward;
        direcitonsCorrectGrid[8] = R.drawable.lorry_right_car_right;
        direcitonsCorrectGrid[9] = R.drawable.lorry_right_car_away;
        direcitonsCorrectGrid[10] = R.drawable.lorry_right_car_left;
        direcitonsCorrectGrid[11] = R.drawable.lorry_right_car_forward;
        direcitonsCorrectGrid[12] = R.drawable.lorry_left_car_right;
        direcitonsCorrectGrid[13] = R.drawable.lorry_left_car_away;
        direcitonsCorrectGrid[14] = R.drawable.lorry_left_car_left;
        direcitonsCorrectGrid[15] = R.drawable.lorry_left_car_forward;

        imageList.add(R.drawable.lorry_right_car_away);
        imageList.add(R.drawable.lorry_away_car_away);
        imageList.add(R.drawable.lorry_forward_car_forward);
        imageList.add(R.drawable.lorry_right_car_right);
        imageList.add(R.drawable.lorry_left_car_left);
        imageList.add(R.drawable.lorry_left_car_right);
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

                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (directionsAnswerGrid[position] != R.drawable.clear) {
                    imageList.add(directionsAnswerGrid[position]);

                    if (imageList.size() == 1) {
                        currentCard.setImageResource(imageList.get(0));
                        nextCard.setImageResource(R.drawable.clear);
                        heldCardNo = 0;
                        nextCardNo = 0;
                    }

                    if (imageList.size() == 2) {
                        nextCard.setImageResource(imageList.get(1));
                        heldCardNo = 0;
                        nextCardNo = 1;
                    }

                    directionsAnswerGrid[position] = R.drawable.clear;
                    adapter.notifyDataSetChanged();
                }

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
            new CountDownTimer(time_limit, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
//                    textWarning.setText("That’s fine, you have done enough now and can stop.");
                    //records the scores
                    for (int i=0; i <= directionsAnswerGrid.length-1; i++){
                        timeup = true;
                        if(!directionsAnswerGrid[i].equals(R.drawable.clear)) {
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_forward") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_forward")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_left") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_left")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_right") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_right")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_away") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_away")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_forward") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_forward")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_left") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_left")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_right") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_right")) {
                                directionsScore += 1;
                            }
                            if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_away") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_away")) {
                                directionsScore += 1;
                            }
                        }
                    }
                }
            }.start();

            androidGridView.setEnabled(true);


            Button startTest = findViewById(R.id.startButton);


            instructions.setVisibility(View.INVISIBLE);
            startTest.setVisibility(View.INVISIBLE);

            Button finishTest = (Button) findViewById(R.id.btnFinishTest);
            finishTest.setVisibility(VISIBLE);
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


    public void confirm(View view){
        Toast.makeText(DirectionsMatrixTest.this, "Are you sure you wish to finish the test?" , Toast.LENGTH_SHORT).show();
        Button finishTests = (Button) findViewById(R.id.btnConfirm);
        finishTests.setVisibility(VISIBLE);
    }

    public void startNextTest(View view){

        if(!timeup) {
            for (int i = 0; i <= directionsAnswerGrid.length - 1; i++) {
                if (!directionsAnswerGrid[i].equals(R.drawable.clear)) {
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_forward") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_forward")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_left") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_left")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_right") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_right")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("lorry_away") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("lorry_away")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_forward") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_forward")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_left") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_left")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_right") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_right")) {
                        directionsScore += 1;
                    }
                    if (getResources().getResourceName(directionsAnswerGrid[i]).contains("car_away") && getResources().getResourceName(direcitonsCorrectGrid[i]).contains("car_away")) {
                        directionsScore += 1;
                    }
                }
            }
        }

        Bundle bundle = getIntent().getExtras();
        bundle.putLong("Directions_score", directionsScore);

        Intent intent = new Intent(this, CompassMatrixTest.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void directionHelp (View view){
        Button helpButton = findViewById(R.id.btnDirectionsHelp);
        textDirectinosHelp = findViewById(R.id.txtSignHelp);
        textDirectinosHelp.setText(R.string.directionhelp);
        helpButton.setVisibility(INVISIBLE);
        new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
            }
            public void onFinish() {
                textDirectinosHelp.setText("");
            }
        }.start();
    }



    @Override
    public void onBackPressed() {

    }


}
