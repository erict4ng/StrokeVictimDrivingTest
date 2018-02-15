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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CompassMatrixTest extends AppCompatActivity {
    List<Integer> image_list = new ArrayList<Integer>();
    int heldCardNo = 0;
    int nextCardNo = 1;
    long time_Limit = 300000;
    Boolean timeup = false;
    GridView androidGridView;
    boolean[] matrix_test = new boolean[16];
    Integer[] compassAnswerGrid = new Integer[16];
    Integer[] compassCorrectGrid = new Integer[16];

    boolean firstClick;

    long compassScore;

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, compassAnswerGrid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_matrix_test);

        Arrays.fill(compassAnswerGrid, R.drawable.square);
        Arrays.fill(matrix_test, false);

        image_list.add(R.drawable.car_north_sw);
        image_list.add(R.drawable.car_west_se);
        image_list.add(R.drawable.car_south_ea);
        image_list.add(R.drawable.car_ea_ne);
        image_list.add(R.drawable.car_northwest_ea);
        image_list.add(R.drawable.car_ea_se);
        image_list.add(R.drawable.car_south_se);
        image_list.add(R.drawable.car_south_sw);
        image_list.add(R.drawable.car_north_ne);
        image_list.add(R.drawable.car_north_nw);
        image_list.add(R.drawable.car_north_se);
        image_list.add(R.drawable.car_north_west);
        image_list.add(R.drawable.car_north_east);
        image_list.add(R.drawable.car_northwest_ne);
        image_list.add(R.drawable.car_ne_sw);
        image_list.add(R.drawable.car_ne_se);
        image_list.add(R.drawable.car_north_south);
        image_list.add(R.drawable.car_northwest_se);
        image_list.add(R.drawable.car_northwest_sw);
        image_list.add(R.drawable.car_south_ne);
        image_list.add(R.drawable.car_south_northwest);
        image_list.add(R.drawable.car_se_sw);
        image_list.add(R.drawable.car_south_west);
        image_list.add(R.drawable.car_west_ne);
        image_list.add(R.drawable.car_west_northwest);
        image_list.add(R.drawable.car_west_sw);

        compassCorrectGrid[0] = R.drawable.car_west_se;
        compassCorrectGrid[1] = R.drawable.car_west_ne;
        compassCorrectGrid[2] = R.drawable.car_west_sw;
        compassCorrectGrid[3] = R.drawable.car_ea_west;
        compassCorrectGrid[4] = R.drawable.car_northwest_se;
        compassCorrectGrid[5] = R.drawable.car_northwest_ne;
        compassCorrectGrid[6] = R.drawable.car_northwest_sw;
        compassCorrectGrid[7] = R.drawable.car_northwest_ea;
        compassCorrectGrid[8] = R.drawable.car_north_se;
        compassCorrectGrid[9] = R.drawable.car_north_ne;
        compassCorrectGrid[10] = R.drawable.car_north_sw;
        compassCorrectGrid[11] = R.drawable.car_north_east;
        compassCorrectGrid[12] = R.drawable.car_south_se;
        compassCorrectGrid[13] = R.drawable.car_south_ne;
        compassCorrectGrid[14] = R.drawable.car_south_sw;
        compassCorrectGrid[15] = R.drawable.car_south_ea;

        androidGridView = findViewById(R.id.compassGrid);
        androidGridView.setAdapter(adapter);




        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (!image_list.isEmpty()) {
                    if (compassAnswerGrid[position] != R.drawable.square) {
                        Integer temp = compassAnswerGrid[position];
                        compassAnswerGrid[position] = image_list.get(heldCardNo);
                        image_list.set(heldCardNo, temp);
                        currentCard.setImageResource(image_list.get(heldCardNo));
                    }
                    else
                    {
                        compassAnswerGrid[position] = image_list.get(heldCardNo);
                        image_list.remove(heldCardNo);


                        if (heldCardNo == image_list.size() - 1)
                        {
                            heldCardNo = 0;
                            nextCardNo = 1;
                        }

                        if (firstClick) {
                            androidGridView.setEnabled(false);
                            firstClick = false;
                        }

                        if (image_list.size() == 1) {
                            heldCardNo = 0;
                            nextCardNo = 0;
                            currentCard.setImageResource(image_list.get(heldCardNo));
                            nextCard.setImageResource(R.drawable.clear);
                        }
                        else if (!image_list.isEmpty())
                        {
                            currentCard.setImageResource(image_list.get(heldCardNo));
                            nextCard.setImageResource(image_list.get(nextCardNo));
                        }
                        else
                        {
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
                Toast.makeText(CompassMatrixTest.this, "butts" , Toast.LENGTH_SHORT).show();

                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (compassAnswerGrid[position] != R.drawable.square) {
                    image_list.add(compassAnswerGrid[position]);

                    if (image_list.size() == 1) {
                        currentCard.setImageResource(image_list.get(0));
                        nextCard.setImageResource(R.drawable.clear);
                        heldCardNo = 0;
                        nextCardNo = 0;
                    }

                    if (image_list.size() == 2) {
                        nextCard.setImageResource(image_list.get(1));
                        heldCardNo = 0;
                        nextCardNo = 1;
                    }

                    compassAnswerGrid[position] = R.drawable.square;
                    adapter.notifyDataSetChanged();
                }

                return true;
            }
        });
    }


    public void startTest(View view){
        if(firstClick){
            Toast.makeText(CompassMatrixTest.this, "Please place the example image!" , Toast.LENGTH_SHORT).show();
        }
        else
        {
            new CountDownTimer(time_Limit, 1000) {
                public void onTick(long millisUntilFinished) {
                }
                public void onFinish() {
//                    textWarning.setText("That’s fine, you have done enough now and can stop.");
                    //records the scores
                    for (int i=0; i <= compassAnswerGrid.length-1; i++){
                        timeup = true;
                        if(!compassAnswerGrid[i].equals(R.drawable.clear)) {
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("north") && getResources().getResourceName(compassCorrectGrid[i]).contains("north")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("northwest") && getResources().getResourceName(compassCorrectGrid[i]).contains("northwest")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("south") && getResources().getResourceName(compassCorrectGrid[i]).contains("south")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("west") && getResources().getResourceName(compassCorrectGrid[i]).contains("west")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("ne") && getResources().getResourceName(compassCorrectGrid[i]).contains("ne")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("ea") && getResources().getResourceName(compassCorrectGrid[i]).contains("ea")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("se") && getResources().getResourceName(compassCorrectGrid[i]).contains("se")) {
                                compassScore += 1;
                            }
                            if (getResources().getResourceName(compassAnswerGrid[i]).contains("sw") && getResources().getResourceName(compassCorrectGrid[i]).contains("sw")) {
                                compassScore += 1;
                            }
                        }
                    }
                }
            }.start();

            androidGridView.setEnabled(true);


            Button startTest = findViewById(R.id.startButton);


//            instructions.setVisibility(View.INVISIBLE);
//            startTest.setVisibility(View.INVISIBLE);
        }
    }

    public void getNextCard(View view){
        ImageView nextCard = findViewById(R.id.nextCard);
        ImageView currentCard = findViewById(R.id.heldCard);

        if (!image_list.isEmpty()) {
            if (image_list.size() == 1) {
                heldCardNo = 0;
                currentCard.setImageResource(image_list.get(heldCardNo));
                nextCard.setImageResource(R.drawable.clear);
            } else {


                if (heldCardNo == image_list.size() - 1) {
                    heldCardNo = 0;
                    nextCardNo = 1;
                } else if (nextCardNo == image_list.size() - 1) {
                    heldCardNo += 1;
                    nextCardNo = 0;
                } else {
                    heldCardNo += 1;
                    nextCardNo = heldCardNo + 1;
                }

                currentCard.setImageResource(image_list.get(heldCardNo));
                nextCard.setImageResource(image_list.get(nextCardNo));
            }

        }
    }

    public void startNextTest(View view){
        if(!timeup) {
            for (int i = 0; i <= compassAnswerGrid.length - 1; i++) {
                if (!compassAnswerGrid[i].equals(R.drawable.clear)) {
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("north") && getResources().getResourceName(compassCorrectGrid[i]).contains("north")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("northwest") && getResources().getResourceName(compassCorrectGrid[i]).contains("northwest")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("south") && getResources().getResourceName(compassCorrectGrid[i]).contains("south")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("west") && getResources().getResourceName(compassCorrectGrid[i]).contains("west")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("ne") && getResources().getResourceName(compassCorrectGrid[i]).contains("ne")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("ea") && getResources().getResourceName(compassCorrectGrid[i]).contains("ea")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("se") && getResources().getResourceName(compassCorrectGrid[i]).contains("se")) {
                        compassScore += 1;
                    }
                    if (getResources().getResourceName(compassAnswerGrid[i]).contains("sw") && getResources().getResourceName(compassCorrectGrid[i]).contains("sw")) {
                        compassScore += 1;
                    }
                }
            }
        }

        Button button = findViewById(R.id.button);
        button.setText(String.valueOf(compassScore));

        //Bundle bundle = getIntent().getExtras();
        //bundle.putString("test1_score", "8");
        //bundle.putString("test1_time",String.valueOf(time_left));

        Intent intent = new Intent(this, SignTest.class);
        //intent.putExtras(bundle);

        startActivity(intent);
    }


}
