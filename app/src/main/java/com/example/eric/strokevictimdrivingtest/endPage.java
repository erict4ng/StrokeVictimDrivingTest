package com.example.eric.strokevictimdrivingtest;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class endPage extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_page);

        TextView resultsText = findViewById(R.id.txtResults);

        Bundle scores;
        scores = getIntent().getExtras();
        String id = scores.getString("id");
        long dotTime = scores.getLong("Dot_time");
        long dotNotCrossed = scores.getLong("Dot_missedCrosses");
        long dotWrongCrossed = scores.getLong("Dot_wrongCrosses");
        long directionsScore = scores.getLong(("Directions_score"));
        long compassScore = scores.getLong(("Compass_score"));
        long signScore = scores.getLong(("Sign_score"));

        double passScore = (dotTime * 0.012) + (dotWrongCrossed * 0.216) + (compassScore * 0.409) + (signScore * 1.168) - 13.79;
        double failScore = (dotTime * 0.017) + (dotWrongCrossed * 0.034) + (compassScore * 0.185) + (signScore * 0.813) - 10.042;

        resultsText.setText("Dear " + id + "\n" +
        "\n Here are the results of your recent test for the stroke drivers assessment screening." + "\n" +
        "\n Pass margin : " + passScore + "\n" +
        "\n Fail margin : " + failScore + "\n" +
        "\n How do I interpret the results?\n" +
        "\n Generally speaking, a higher pass margin indicates that you should be eligible to drive. On the other hand a higher fail margin than pass margin suggests that you may not be capable of safe driving and therefore may not be eligible to drive, however, the final decision on this matter will be up to your medical professional’s discretion. \n \n As such, after these results, please contact your medical professional for further information");
    }
}
