package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
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
    List<Integer> imageList = new ArrayList<Integer>();

    int heldCardNo = 0;
    int nextCardNo = 1;
    Boolean firstClick = true;

    GridView androidGridView;
    boolean[] matrix_test = new boolean[16];
    public Integer[] directionsAnswerGrid = new Integer[16];

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, directionsAnswerGrid);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_matrix_test);

        Arrays.fill(directionsAnswerGrid, R.drawable.car_east_southeast);
        Arrays.fill(matrix_test, false);

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
                ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
                ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

                if(firstClick)
                {
                    directionsAnswerGrid[position] = R.drawable.directions_example;

                    currentCard.setImageResource(imageList.get(heldCardNo));
                    nextCard.setImageResource(imageList.get(nextCardNo));
                    firstClick = false;

                }
                else if(!firstClick)
                {
//                    if (imageList.size() == 2) {
//                        heldCardNo = 0;
//                        nextCardNo = 1;
//                    }
//
//                    if (imageList.size() == 1) {
//                        heldCardNo = 1;
//                        currentCard.setImageResource(imageList.get(heldCardNo));
//                        nextCard.setImageResource(R.drawable.clear);
//                    }

                        if (directionsAnswerGrid[position] != R.drawable.clear)
                        {
                            Integer temp = directionsAnswerGrid[position];
                            directionsAnswerGrid[position] = imageList.get(heldCardNo);
                            imageList.set(heldCardNo, temp);
                            currentCard.setImageResource(imageList.get(heldCardNo));
                        }
                        else
                        {
                            directionsAnswerGrid[position] = imageList.get(heldCardNo);
                            imageList.remove(heldCardNo);
                            heldCardNo = nextCardNo;

                            if (heldCardNo == imageList.size()){
                                heldCardNo = 0;
                                nextCardNo = 1;
                            }
//                             else if (heldCardNo == imageList.size())
//                            {
//                                nextCardNo = 1;
//                            }
                            else
                            {
                                nextCardNo = heldCardNo + 1;
                            }

                            if (imageList.size() == 2) {
                                heldCardNo = 0;
                                nextCardNo = 1;
                            }

                            if (imageList.size() == 1) {
                                heldCardNo = 0;
                                nextCardNo = 0;
                                currentCard.setImageResource(imageList.get(heldCardNo));
                                nextCard.setImageResource(R.drawable.clear);
                            }


                            if (!imageList.isEmpty()){
                                currentCard.setImageResource(imageList.get(heldCardNo));
                                nextCard.setImageResource(imageList.get(nextCardNo));
                            } else {
                                currentCard.setImageResource(R.drawable.clear);
                                nextCard.setImageResource(R.drawable.clear);
                            }
                        }


                        Toast.makeText(DirectionsMatrixTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();

                }

                adapter.notifyDataSetChanged();
            }
        });

    }

    public void startTest(View view){
        if( firstClick){
            Toast.makeText(DirectionsMatrixTest.this, "Please place the example image!" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            Arrays.fill(directionsAnswerGrid, R.drawable.clear);
            adapter.notifyDataSetChanged();

            TextView instructions = (TextView) findViewById(R.id.instructionsTest);
            Button startTest = (Button) findViewById(R.id.startButton);

            instructions.setVisibility(View.INVISIBLE);
            startTest.setVisibility(View.INVISIBLE);
        }
    }


    //onClick to add to the stack of card images
    public void getNextCard(View view){
        ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
        ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

//        if (imageList.size() == 1){
//                heldCardNo = 1;
//                currentCard.setImageResource(imageList.get(heldCardNo));
//                nextCard.setImageResource(R.drawable.clear);
//        } else {
            if (heldCardNo < (imageList.size() - 1)) {
                heldCardNo = nextCardNo;
            } else {
                heldCardNo = 0;
            }


            if (heldCardNo == (imageList.size() - 1)) {
                nextCardNo = 0;
            } else {
                nextCardNo = heldCardNo + 1;
            }

//            if (imageList.size() == 2) {
//                heldCardNo = 0;
//                nextCardNo = 1;
//            }

            currentCard.setImageResource(imageList.get(heldCardNo));
            nextCard.setImageResource(imageList.get(nextCardNo));
        }

   // }


    public void startNextTest(View view){
        //long elapsedMillis = (SystemClock.elapsedRealtime() - overtime.getBase()) / 1000;
        //timerText.setText(String.valueOf(elapsedMillis));
        //overtime.stop();

        Bundle bundle = getIntent().getExtras();
        bundle.putString("test1_score", "8");
        //bundle.putString("test1_time",String.valueOf(time_left));

        Intent intent = new Intent(this, CompassMatrixTest.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }


}
