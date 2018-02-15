package com.example.eric.strokevictimdrivingtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void start_test(View view) {
        EditText pass_box = findViewById(R.id.txtbxPass);
        TextView warning = findViewById(R.id.txtWarning);

        if(pass_box.getText().toString().equals("123")) {
            Bundle bundle = new Bundle();
            bundle.putString("id", pass_box.getText().toString());
            Intent intent = new Intent(this, DotCancelTest.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            warning.setVisibility(View.VISIBLE);
        }
    }
}
