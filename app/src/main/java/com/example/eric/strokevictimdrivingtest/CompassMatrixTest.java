package com.example.eric.strokevictimdrivingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    Boolean firstClick = true;

    GridView androidGridView;
    boolean[] matrix_test = new boolean[16];
    public Integer[] compassAnswerGrid = new Integer[16];

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, compassAnswerGrid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass_matrix_test);

        Arrays.fill(compassAnswerGrid, R.drawable.car_east_southeast);
        Arrays.fill(matrix_test, false);


        image_list.add(R.drawable.car_east_northeast);
        image_list.add(R.drawable.car_east_northwest);
        image_list.add(R.drawable.car_east_southeast);
        image_list.add(R.drawable.car_east_southwest);
        image_list.add(R.drawable.car_eastwest);

        image_list.add(R.drawable.car_north_northeast);
        image_list.add(R.drawable.car_north_northwest);
        image_list.add(R.drawable.car_north_southeast);
        image_list.add(R.drawable.car_north_southwest);
        image_list.add(R.drawable.car_north_west);

        image_list.add(R.drawable.car_northeast);
        image_list.add(R.drawable.car_northeast_northwest);
        image_list.add(R.drawable.car_northeast_southwest);
        image_list.add(R.drawable.car_northeast_southeast);
        image_list.add(R.drawable.car_northsouth);

        image_list.add(R.drawable.car_northwest_southeast);
        image_list.add(R.drawable.car_northwest_southwest);

        image_list.add(R.drawable.car_south_east);
        image_list.add(R.drawable.car_south_northeast);
        image_list.add(R.drawable.car_south_northwest);
        image_list.add(R.drawable.car_south_southeast);
        image_list.add(R.drawable.car_south_southwest);

        image_list.add(R.drawable.car_southeast_southwest);
        image_list.add(R.drawable.car_southwest);

        image_list.add(R.drawable.car_west_northeast);
        image_list.add(R.drawable.car_west_northwest);
        image_list.add(R.drawable.car_west_southeast);
        image_list.add(R.drawable.car_west_southwest);
        image_list.add(R.drawable.directions_example);


        androidGridView = findViewById(R.id.compassGrid);
        androidGridView.setAdapter(adapter);




        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (!image_list.isEmpty()) {
                    if (compassAnswerGrid[position] != R.drawable.clear) {
                        Integer temp = compassAnswerGrid[position];
                        compassAnswerGrid[position] = image_list.get(heldCardNo);
                        image_list.set(heldCardNo, temp);
                        currentCard.setImageResource(image_list.get(heldCardNo));
                    } else {
                        compassAnswerGrid[position] = image_list.get(heldCardNo);
                        image_list.remove(heldCardNo);


                        if (heldCardNo == image_list.size() - 1) {
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
                        } else if (!image_list.isEmpty()) {
                            currentCard.setImageResource(image_list.get(heldCardNo));
                            nextCard.setImageResource(image_list.get(nextCardNo));
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
                Toast.makeText(CompassMatrixTest.this, "butts" , Toast.LENGTH_SHORT).show();

                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                image_list.add(compassAnswerGrid[position]);

                if (image_list.size() == 1){
                    currentCard.setImageResource(image_list.get(0));
                    nextCard.setImageResource(R.drawable.clear);
                    heldCardNo = 0;
                    nextCardNo = 0;
                }

                if (image_list.size() == 2){
                    nextCard.setImageResource(image_list.get(1));
                    heldCardNo = 0;
                    nextCardNo = 1;
                }

                compassAnswerGrid[position] = R.drawable.clear;
                adapter.notifyDataSetChanged();

                return true;
            }
        });
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


//            if (heldCardNo < (image_list.size() - 1)) {
//                heldCardNo = nextCardNo;
//            } else {
//                heldCardNo = 0;
//            }
//
//            if (nextCardNo == (image_list.size() - 1)) {
//                nextCardNo = 0;
//            } else {
//                nextCardNo = heldCardNo + 1;
//            }
//
//            if (image_list.size() == 2) {
//                heldCardNo = 0;
//                nextCardNo = 1;
//            }

                currentCard.setImageResource(image_list.get(heldCardNo));
                nextCard.setImageResource(image_list.get(nextCardNo));
            }

        }
    }


}
