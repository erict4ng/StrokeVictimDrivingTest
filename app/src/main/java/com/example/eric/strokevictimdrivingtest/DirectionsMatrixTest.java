package com.example.eric.strokevictimdrivingtest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DirectionsMatrixTest extends AppCompatActivity {
    List<Integer> imageList = new ArrayList<Integer>();
    int heldCardNo = 0;
    int nextCardNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras(); // gets the previously created intent

        String value = bundle.getString("id");

//        String firstKeyName = myIntent.getStringExtra("test1_time"); // will return "FirstKeyValue"
//        String secondKeyName= myIntent.getStringExtra("test1_score"); // will return "SecondKeyValue"

        final TextView name = (TextView)findViewById(R.id.textName);
        //final TextView age = (TextView)findViewById(R.id.textAge);

        name.setText(value);

        ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
        ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

        //nextCard.setImageResource(R.drawable.bothAway);
        //currentCard.setImageResource(R.drawable.test2Example);

//        imageList.add(R.drawable.bothAway);
//        imageList.add(R.drawable.bothFront);
//        imageList.add(R.drawable.bothRight);
//        imageList.add(R.drawable.leftLorryRightCar);
//        imageList.add(R.drawable.lorryAwayCarForward);
//        imageList.add(R.drawable.lorryAwayCarLeft);
//        imageList.add(R.drawable.lorryAwayCarRight);
//        imageList.add(R.drawable.lorryForwardCarAway);
//        imageList.add(R.drawable.lorryForwardCarLeft);
//        imageList.add(R.drawable.lorryForwardCarRight);
//        imageList.add(R.drawable.lorryLeftCarAway);
//        imageList.add(R.drawable.lorryLeftCarForward);
//        imageList.add(R.drawable.bothLeft);
//        imageList.add(R.drawable.lorryRightCarForward);
//        imageList.add(R.drawable.lorryRightCarLeft);
//        imageList.add(R.drawable.test2Example);



    }

    //onClick to add to the stack of card images
    public void getNextCard(View view){
        ImageView nextCard = (ImageView) findViewById(R.id.nextCard);
        ImageView currentCard = (ImageView) findViewById(R.id.heldCard);

        if (heldCardNo < 8)
        {
            heldCardNo = nextCardNo;
        }
        else
        {
            heldCardNo = 0;
        }


        if (heldCardNo == 8)
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
