package com.example.eric.strokevictimdrivingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignTest extends AppCompatActivity {

    public Integer[] SignsAnswerGrid = new Integer[16];

    public Integer[] SignsCorrectGrid = new Integer[16];

    List<Integer> image_list = new ArrayList<Integer>();
    int heldCardNo = 0;
    int nextCardNo = 1;

    //declare gridview variables
    GridView androidGridView;
    SignAdapter imageAdapter = new SignAdapter(this, SignsAnswerGrid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_test);

        Arrays.fill(SignsAnswerGrid, R.drawable.clear);

        androidGridView = findViewById(R.id.gridSignsBoard);
        androidGridView.setAdapter(imageAdapter);

                androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        ImageView nextCard = findViewById(R.id.nextCard);
                        ImageView currentCard = findViewById(R.id.heldCard);

                        if (!image_list.isEmpty()) {
                            if (SignsAnswerGrid[position] != R.drawable.square) {
                                Integer temp = SignsAnswerGrid[position];
                                SignsAnswerGrid[position] = image_list.get(heldCardNo);
                                image_list.set(heldCardNo, temp);
                                currentCard.setImageResource(image_list.get(heldCardNo));
                            } else {
                                SignsAnswerGrid[position] = image_list.get(heldCardNo);
                                image_list.remove(heldCardNo);


                                if (heldCardNo == image_list.size() - 1) {
                                    heldCardNo = 0;
                                    nextCardNo = 1;
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

                        imageAdapter.notifyDataSetChanged();
                    }
                });


        androidGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignTest.this, "butts" , Toast.LENGTH_SHORT).show();

                ImageView nextCard = findViewById(R.id.nextCard);
                ImageView currentCard = findViewById(R.id.heldCard);

                if (SignsAnswerGrid[position] != R.drawable.square) {
                    image_list.add(SignsAnswerGrid[position]);

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

                    SignsAnswerGrid[position] = R.drawable.square;
                    imageAdapter.notifyDataSetChanged();
                }

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
