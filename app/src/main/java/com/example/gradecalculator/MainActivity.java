package com.example.gradecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView grade = findViewById(R.id.grade);
        TextView maxGradeText = findViewById(R.id.maxGradeText);
        TextView minGradeText = findViewById(R.id.minGradeText);
        Button roll = findViewById(R.id.rollButton);
        SeekBar maxSeekBar = findViewById(R.id.maxGrade);
        SeekBar minSeekBar = findViewById(R.id.minGrade);

        maxSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int tempMaxNum = maxSeekBar.getProgress()+1;
                maxGradeText.setText(Integer.toString(tempMaxNum));
                if (tempMaxNum < minSeekBar.getProgress()){
                    minSeekBar.setProgress(tempMaxNum-1);
                    minGradeText.setText(Integer.toString(tempMaxNum-1));
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int tempMaxNum = maxSeekBar.getProgress()+1;
                maxGradeText.setText(Integer.toString(tempMaxNum));
                if (tempMaxNum < minSeekBar.getProgress()) {
                    minSeekBar.setProgress(tempMaxNum-1);
                    minGradeText.setText(Integer.toString(tempMaxNum-1));
                }
            }
        });
        minSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int tempMinNum = minSeekBar.getProgress()+1;
                minGradeText.setText(Integer.toString(tempMinNum));
                if (tempMinNum > maxSeekBar.getProgress()) {
                    maxSeekBar.setProgress(tempMinNum+1);
                    minGradeText.setText(Integer.toString(tempMinNum+1));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int tempMinNum = minSeekBar.getProgress()+1;
                minGradeText.setText(Integer.toString(tempMinNum));
                if (tempMinNum > maxSeekBar.getProgress()) {
                    maxSeekBar.setProgress(tempMinNum+1);
                    minGradeText.setText(Integer.toString(tempMinNum+1));
                }
            }
        });


roll.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Random r = new Random();
        int min = minSeekBar.getProgress()+1;
        int max = maxSeekBar.getProgress()+1;

        int randomNumber = r.nextInt(max-min)+min+1;
        if (randomNumber > max) randomNumber = max;

        grade.setText(Integer.toString(randomNumber));
    }
});



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}