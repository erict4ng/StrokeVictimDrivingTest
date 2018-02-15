package com.example.eric.strokevictimdrivingtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SignTest extends AppCompatActivity {

    public Integer[] SignsAnswerGrid = new Integer[16];

    public Integer[] SignsCorrectGrid = new Integer[16];


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




                //updates the grid
                imageAdapter.notifyDataSetChanged();
            }
        });
    }
}
