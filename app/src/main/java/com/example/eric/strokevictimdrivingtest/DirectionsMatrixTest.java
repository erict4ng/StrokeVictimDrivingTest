package com.example.eric.strokevictimdrivingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    long time_left;
    GridView androidGridView;
    boolean[] matrix_test = new boolean[15];
    public Integer[] mThumbIds = new Integer[16];

    DirectionsMatrixAdapter adapter = new DirectionsMatrixAdapter(this, mThumbIds);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directions_matrix_test);

        //ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
        //ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

        //nextCard.setImageResource(R.drawable.bothAway);
        //currentCard.setImageResource(R.drawable.test2Example);

        Arrays.fill(mThumbIds, R.drawable.car_east_southeast);
        Arrays.fill(matrix_test, false);


        imageList.add(R.drawable.both_away);
        imageList.add(R.drawable.both_front);
        imageList.add(R.drawable.both_right);
        imageList.add(R.drawable.left_lorry_right_car);
        imageList.add(R.drawable.lorry_away_car_forward);
        imageList.add(R.drawable.lorry_away_car_left);
        imageList.add(R.drawable.lorry_away_car_right);
        imageList.add(R.drawable.lorry_forward_car_away);
        imageList.add(R.drawable.lorry_forward_car_left);
        imageList.add(R.drawable.lorry_forward_car_right);
        imageList.add(R.drawable.lorry_left_car_away);
        imageList.add(R.drawable.lorry_left_car_forward);
        imageList.add(R.drawable.both_left);
        imageList.add(R.drawable.lorry_right_car_forward);
        imageList.add(R.drawable.lorry_right_car_left);
        imageList.add(R.drawable.directions_example);


        androidGridView = findViewById(R.id.directionsBoardGrid);
        androidGridView.setAdapter(adapter);


        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (!matrix_test[position]) {
                    matrix_test[position] = true;
                    mThumbIds[position] = heldCardNo;
                    Toast.makeText(DirectionsMatrixTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                }
                else if(matrix_test[position]) {
                    matrix_test[position] = false;
                    mThumbIds[position] = R.drawable.clear;
                    Toast.makeText(DirectionsMatrixTest.this, "" + matrix_test[position], Toast.LENGTH_SHORT).show();
                }

                adapter.notifyDataSetChanged();
            }
        });

    }

    //onClick to add to the stack of card images
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
