package com.example.eric.strokevictimdrivingtest;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import java.util.Arrays;

public class DotCancelTest extends AppCompatActivity {

    //declare timer variables
    long time_left;
    long time_taken;
    long timelimit = 90000;
    CountDownTimer timer;

    public Integer[] DotAnswerGrid = new Integer[432];
    public Integer[] DotCorrectGrid = new Integer[432];

    //declare gridview variables
    GridView androidGridView;
    DotImageAdapter adapter = new DotImageAdapter(this, DotAnswerGrid);

    //declare misc variables
    public Button nextTest;
    public TextView textWarning;
    public Button askConfirm;

    //declare scores
    public int notCrossed;
    public int wrongCrossed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dot_cancel_test);
        //sets up next test button and warning view so that it can be manipulated later
        nextTest = findViewById(R.id.btnNexttest);
        textWarning = findViewById(R.id.txtWarning);
        askConfirm = findViewById(R.id.btnAskConfirm);

        //loads in the array of correct crosses
        Arrays.fill(DotCorrectGrid, R.drawable.clear);
        DotCorrectGrid[26] = R.drawable.cross;
        DotCorrectGrid[31] = R.drawable.cross;
        DotCorrectGrid[33] = R.drawable.cross;
        DotCorrectGrid[34] = R.drawable.cross;
        DotCorrectGrid[35] = R.drawable.cross;
        DotCorrectGrid[39] = R.drawable.cross;
        DotCorrectGrid[40] = R.drawable.cross;
        DotCorrectGrid[47] = R.drawable.cross;
        DotCorrectGrid[48] = R.drawable.cross;
        DotCorrectGrid[49] = R.drawable.cross;
        DotCorrectGrid[53] = R.drawable.cross;
        DotCorrectGrid[61] = R.drawable.cross;
        DotCorrectGrid[65] = R.drawable.cross;
        DotCorrectGrid[70] = R.drawable.cross;
        DotCorrectGrid[75] = R.drawable.cross;
        DotCorrectGrid[76] = R.drawable.cross;
        DotCorrectGrid[78] = R.drawable.cross;
        DotCorrectGrid[82] = R.drawable.cross;
        DotCorrectGrid[84] = R.drawable.cross;
        DotCorrectGrid[90] = R.drawable.cross;
        DotCorrectGrid[91] = R.drawable.cross;
        DotCorrectGrid[94] = R.drawable.cross;
        DotCorrectGrid[96] = R.drawable.cross;
        DotCorrectGrid[98] = R.drawable.cross;
        DotCorrectGrid[102] = R.drawable.cross;
        DotCorrectGrid[107] = R.drawable.cross;
        DotCorrectGrid[114] = R.drawable.cross;
        DotCorrectGrid[115] = R.drawable.cross;
        DotCorrectGrid[119] = R.drawable.cross;
        DotCorrectGrid[122] = R.drawable.cross;
        DotCorrectGrid[125] = R.drawable.cross;
        DotCorrectGrid[126] = R.drawable.cross;
        DotCorrectGrid[132] = R.drawable.cross;
        DotCorrectGrid[134] = R.drawable.cross;
        DotCorrectGrid[140] = R.drawable.cross;
        DotCorrectGrid[141] = R.drawable.cross;
        DotCorrectGrid[145] = R.drawable.cross;
        DotCorrectGrid[152] = R.drawable.cross;
        DotCorrectGrid[153] = R.drawable.cross;
        DotCorrectGrid[157] = R.drawable.cross;
        DotCorrectGrid[158] = R.drawable.cross;
        DotCorrectGrid[159] = R.drawable.cross;
        DotCorrectGrid[161] = R.drawable.cross;
        DotCorrectGrid[166] = R.drawable.cross;
        DotCorrectGrid[168] = R.drawable.cross;
        DotCorrectGrid[170] = R.drawable.cross;
        DotCorrectGrid[174] = R.drawable.cross;
        DotCorrectGrid[181] = R.drawable.cross;
        DotCorrectGrid[182] = R.drawable.cross;
        DotCorrectGrid[185] = R.drawable.cross;
        DotCorrectGrid[191] = R.drawable.cross;
        DotCorrectGrid[194] = R.drawable.cross;
        DotCorrectGrid[199] = R.drawable.cross;
        DotCorrectGrid[201] = R.drawable.cross;
        DotCorrectGrid[202] = R.drawable.cross;
        DotCorrectGrid[203] = R.drawable.cross;
        DotCorrectGrid[207] = R.drawable.cross;
        DotCorrectGrid[215] = R.drawable.cross;
        DotCorrectGrid[216] = R.drawable.cross;
        DotCorrectGrid[217] = R.drawable.cross;
        DotCorrectGrid[222] = R.drawable.cross;
        DotCorrectGrid[223] = R.drawable.cross;
        DotCorrectGrid[226] = R.drawable.cross;
        DotCorrectGrid[227] = R.drawable.cross;
        DotCorrectGrid[234] = R.drawable.cross;
        DotCorrectGrid[241] = R.drawable.cross;
        DotCorrectGrid[248] = R.drawable.cross;
        DotCorrectGrid[249] = R.drawable.cross;
        DotCorrectGrid[253] = R.drawable.cross;
        DotCorrectGrid[255] = R.drawable.cross;
        DotCorrectGrid[257] = R.drawable.cross;
        DotCorrectGrid[262] = R.drawable.cross;
        DotCorrectGrid[266] = R.drawable.cross;
        DotCorrectGrid[271] = R.drawable.cross;
        DotCorrectGrid[273] = R.drawable.cross;
        DotCorrectGrid[274] = R.drawable.cross;
        DotCorrectGrid[275] = R.drawable.cross;
        DotCorrectGrid[279] = R.drawable.cross;
        DotCorrectGrid[380] = R.drawable.cross;
        DotCorrectGrid[287] = R.drawable.cross;
        DotCorrectGrid[288] = R.drawable.cross;
        DotCorrectGrid[289] = R.drawable.cross;
        DotCorrectGrid[293] = R.drawable.cross;
        DotCorrectGrid[294] = R.drawable.cross;
        DotCorrectGrid[301] = R.drawable.cross;
        DotCorrectGrid[305] = R.drawable.cross;
        DotCorrectGrid[310] = R.drawable.cross;
        DotCorrectGrid[315] = R.drawable.cross;
        DotCorrectGrid[316] = R.drawable.cross;
        DotCorrectGrid[318] = R.drawable.cross;
        DotCorrectGrid[322] = R.drawable.cross;
        DotCorrectGrid[324] = R.drawable.cross;
        DotCorrectGrid[330] = R.drawable.cross;
        DotCorrectGrid[331] = R.drawable.cross;
        DotCorrectGrid[334] = R.drawable.cross;
        DotCorrectGrid[336] = R.drawable.cross;
        DotCorrectGrid[338] = R.drawable.cross;
        DotCorrectGrid[342] = R.drawable.cross;
        DotCorrectGrid[354] = R.drawable.cross;
        DotCorrectGrid[355] = R.drawable.cross;
        DotCorrectGrid[359] = R.drawable.cross;
        DotCorrectGrid[362] = R.drawable.cross;
        DotCorrectGrid[365] = R.drawable.cross;
        DotCorrectGrid[366] = R.drawable.cross;
        DotCorrectGrid[372] = R.drawable.cross;
        DotCorrectGrid[374] = R.drawable.cross;
        DotCorrectGrid[378] = R.drawable.cross;
        DotCorrectGrid[380] = R.drawable.cross;
        DotCorrectGrid[381] = R.drawable.cross;
        DotCorrectGrid[385] = R.drawable.cross;
        DotCorrectGrid[392] = R.drawable.cross;
        DotCorrectGrid[393] = R.drawable.cross;
        DotCorrectGrid[397] = R.drawable.cross;
        DotCorrectGrid[398] = R.drawable.cross;
        DotCorrectGrid[401] = R.drawable.cross;
        DotCorrectGrid[406] = R.drawable.cross;
        DotCorrectGrid[408] = R.drawable.cross;
        DotCorrectGrid[410] = R.drawable.cross;
        DotCorrectGrid[414] = R.drawable.cross;
        DotCorrectGrid[421] = R.drawable.cross;

        //fills answer array with clear images
        Arrays.fill(DotAnswerGrid, R.drawable.clear);

        //setting example crosses up
        DotAnswerGrid[1] = R.drawable.cross;
        DotAnswerGrid[8] = R.drawable.cross;
        DotAnswerGrid[9] = R.drawable.cross;
        DotAnswerGrid[13] = R.drawable.cross;

        //sets up adapter
        androidGridView = findViewById(R.id.gridCrossGrid);
        androidGridView.setAdapter(adapter);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if (DotAnswerGrid[position] == R.drawable.clear) {
                    DotAnswerGrid[position] = R.drawable.cross;
                }
                else if(DotAnswerGrid[position] != R.drawable.clear) {
                    DotAnswerGrid[position] = R.drawable.clear;
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void hideInstructions(View view){
        if ((DotAnswerGrid[1] == R.drawable.cross) && (DotAnswerGrid[8] == R.drawable.cross) && (DotAnswerGrid[9] == R.drawable.cross) && (DotAnswerGrid[13] == R.drawable.cross) &&
                (DotAnswerGrid[14] == R.drawable.cross) && (DotAnswerGrid[15] == R.drawable.cross) && (DotAnswerGrid[17] == R.drawable.cross) && (DotAnswerGrid[22] == R.drawable.cross)){
            final TextView txtInstructions = findViewById(R.id.txtDialog);
            txtInstructions.setVisibility(View.INVISIBLE);
            askConfirm.setVisibility(View.VISIBLE);

            timer = new CountDownTimer(timelimit, 1000) {
                public void onTick(long millisUntilFinished) {
                    time_left = millisUntilFinished / 1000;
                }
                public void onFinish() {
                    time_left = 0;

                    textWarning.setText("That’s fine, you have done enough now and can stop.");


                    for (int i=23; i <DotAnswerGrid.length; i++){
                        if (DotAnswerGrid[i].equals(R.drawable.clear) && (DotCorrectGrid[i].equals(R.drawable.cross))){
                            notCrossed += 1;
                        }
                        if (DotAnswerGrid[i].equals(R.drawable.cross) && (DotCorrectGrid[i].equals(R.drawable.clear))){
                            wrongCrossed += 1;
                        }
                    }
                }
            }.start();
            textWarning.setText("");
        }else{
            textWarning.setText(R.string.instructionswrong);
        }
    }

    public void startNextTest(View view){
        time_taken = (timelimit / 1000) - time_left;
        timer.cancel();

        if(time_taken * 1000 != timelimit){
            for (int i=23; i <DotAnswerGrid.length; i++){
                if (DotAnswerGrid[i].equals(R.drawable.clear) && (DotCorrectGrid[i].equals(R.drawable.cross))){
                    notCrossed += 1;
                }
                if (DotAnswerGrid[i].equals(R.drawable.cross) && (DotCorrectGrid[i].equals(R.drawable.clear))){
                    wrongCrossed += 1;
                }
            }
        }

        Bundle bundle = getIntent().getExtras();
        bundle.putLong("Dot_time", time_taken);
        bundle.putLong("Dot_missedCrosses", notCrossed);
        bundle.putLong("Dot_wrongCrosses", wrongCrossed);

        Intent intent = new Intent(this, DirectionsMatrixTest.class);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void askForConfirm(View view){
        nextTest.setVisibility(View.VISIBLE);
        textWarning.setText("are you sure you wish to go to the next test?");
    }

    public void removeMe(View view){
        Intent intent = new Intent(this, DirectionsMatrixTest.class);
        startActivity(intent);
    }

}

