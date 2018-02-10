package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.support.v4.app.BundleCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle bundle = getIntent().getExtras(); // gets the previously created intent

        String value = bundle.getString("id");

//        String firstKeyName = myIntent.getStringExtra("test1_time"); // will return "FirstKeyValue"
//        String secondKeyName= myIntent.getStringExtra("test1_score"); // will return "SecondKeyValue"

        final TextView name = (TextView)findViewById(R.id.textName);
        final TextView age = (TextView)findViewById(R.id.textAge);

        name.setText(value);
//        age.setText(secondKeyName);

    }
}
