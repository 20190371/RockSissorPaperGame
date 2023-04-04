package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int totalGames = 0;
    private int totalWins = 0;
    private double winningRatio = 0.4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton gamestart = findViewById(R.id.GameStart);
        ImageButton gameover = findViewById(R.id.GameOver);


        gameover.setOnClickListener(this);
        gamestart.setOnClickListener(this);
    }
        private int num1;
        private int num2;
        @Override
        public void onClick(View v) {
            ImageView firstman = (findViewById(R.id.First_Man));
            ImageView secondman = (findViewById(R.id.second_Man));
            TextView result = findViewById(R.id.Result);
            TextView winrate = findViewById(R.id.WinRate);
            switch(v.getId()){
                case R.id.GameStart:
                    totalGames++;
                    Random random = new Random();
                    num1 = random.nextInt(3);
                    num2 = random.nextInt(3);
                    if (totalGames >= 10) {
                        double winRateValue = totalGames == 0 ? 0.0 : ((double) totalWins / totalGames) * 100;
                        winrate.setText(String.format("승률: %.2f%%", winRateValue));
                    }
                    if (num1 == 0) {
                        firstman.setImageResource(R.drawable.scissor);
                    }
                    else if (num1 == 1) {
                        firstman.setImageResource(R.drawable.rock);
                    }
                    else if (num1 == 2) {
                        firstman.setImageResource(R.drawable.paper);
                    }

                    if (random.nextDouble() < winningRatio) {
                        // 컴퓨터가 이기는 경우
                        num2 = (num1 + 1) % 3;
                    } else {
                        // 컴퓨터가 지는 경우
                        num2 = (num1 + 2) % 3;
                    }
                    if (num2 == 0) {
                        secondman.setImageResource(R.drawable.scissor);
                    }
                    else if (num2 == 1) {
                        secondman.setImageResource(R.drawable.rock);
                    }
                    else if (num2 == 2) {
                        secondman.setImageResource(R.drawable.paper);
                    }
                    break;
                case R.id.GameOver:
                    if (num1 == num2) {
                        result.setText("비겼습니다!");
                    }
                    else if (num1 == 0 && num2 == 2 || num1 == 1 && num2 == 0 || num1 == 2 && num2 == 1) {
                        result.setText("승리!");
                        totalWins++;
                    }
                    else {
                        result.setText("패배!");
                    }
                    break;
            }
        }
    };
