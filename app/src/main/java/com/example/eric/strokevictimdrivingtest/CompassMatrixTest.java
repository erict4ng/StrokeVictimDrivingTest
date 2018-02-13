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
    List<Integer> imageList = new ArrayList<Integer>();
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


        imageList.add(R.drawable.car_east_northeast);
        imageList.add(R.drawable.car_east_northwest);
        imageList.add(R.drawable.car_east_southeast);
        imageList.add(R.drawable.car_east_southwest);
        imageList.add(R.drawable.car_eastwest);

        imageList.add(R.drawable.car_north_northeast);
        imageList.add(R.drawable.car_north_northwest);
        imageList.add(R.drawable.car_north_southeast);
        imageList.add(R.drawable.car_north_southwest);
        imageList.add(R.drawable.car_north_west);

        imageList.add(R.drawable.car_northeast);
        imageList.add(R.drawable.car_northeast_northwest);
        imageList.add(R.drawable.car_northeast_southwest);
        imageList.add(R.drawable.car_northeast_southeast);
        imageList.add(R.drawable.car_northsouth);

        imageList.add(R.drawable.car_northwest_southeast);
        imageList.add(R.drawable.car_northwest_southwest);

        imageList.add(R.drawable.car_south_east);
        imageList.add(R.drawable.car_south_northeast);
        imageList.add(R.drawable.car_south_northwest);
        imageList.add(R.drawable.car_south_southeast);
        imageList.add(R.drawable.car_south_southwest);

        imageList.add(R.drawable.car_southeast_southwest);
        imageList.add(R.drawable.car_southwest);

        imageList.add(R.drawable.car_west_northeast);
        imageList.add(R.drawable.car_west_northwest);
        imageList.add(R.drawable.car_west_southeast);
        imageList.add(R.drawable.car_west_southwest);
        //imageList.add(R.drawable.directions_example);


        androidGridView = findViewById(R.id.compassGrid);
        androidGridView.setAdapter(adapter);




        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (firstClick){
                    compassAnswerGrid[position] = R.drawable.directions_example;
                    ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
                    ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

                    currentCard.setImageResource(imageList.get(heldCardNo));
                    nextCard.setImageResource(imageList.get(nextCardNo));
                    firstClick = false;

                } else if(!firstClick) {
                    if (!matrix_test[position]) {
                        //matrix_test[position] = true;
                        compassAnswerGrid[position] = imageList.get(heldCardNo);
                        Toast.makeText(CompassMatrixTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                    } else if (matrix_test[position]) {
                        //matrix_test[position] = false;
                        compassAnswerGrid[position] = R.drawable.clear;
                        Toast.makeText(CompassMatrixTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void getNextCard(View view){
        ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
        ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

        if (heldCardNo < (imageList.size() - 1))
        {
            heldCardNo = nextCardNo;
        }
        else
        {
            heldCardNo = 0;
        }


        if (heldCardNo == (imageList.size() - 1))
        {
            nextCardNo = 0;
        }
        else
        {
            nextCardNo = heldCardNo + 1;
        }


        currentCard.setImageResource(imageList.get(heldCardNo));
        nextCard.setImageResource(imageList.get(nextCardNo));
    }


}
