package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,button0,button1,button2,button3;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score =0 ;
    TextView resultText,questionText,timerText;
    int noOfQuestion = 0;
    TextView scoreText;
    Button playAgainButton;
    RelativeLayout gameLayout;
    boolean gameactive = false;

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.playagain));

    }

    public void playAgain(View view){
        score = 0;
        noOfQuestion = 0;

        gameactive = true;
        timerText.setText("30s");
        scoreText.setText("0/0");
        resultText.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        generateQuestion();
        new CountDownTimer(31000,1000){
            @Override
            public void onTick(long l) {
                timerText.setText(Long.toString(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                timerText.setText("0s");
                resultText.setText("Your Score " + Integer.toString(score)+ "/" + Integer.toString(noOfQuestion));
                playAgainButton.setVisibility(View.VISIBLE);
                gameactive = false;
            }
        }.start();

    }

    public void generateQuestion(){
//        Generating the random numbers.
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);

        questionText.setText(Integer.toString(a) + " + " + Integer.toString(b));

        int inCorrectAnswer;
        locationOfCorrectAnswer = random.nextInt(4);
        answer.clear();
        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer)
                answer.add(a+b);
            else
            {
                inCorrectAnswer = random.nextInt(41);
                while (inCorrectAnswer == a+b){
                    inCorrectAnswer = random.nextInt(41);
                }
                answer.add(inCorrectAnswer);
            }
        }

        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }

    public void chooseAnswer(View view){

        if(gameactive) {
            if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultText.setText("Correct!");
            }
             else{
            resultText.setText("Wrong!");
            }
            noOfQuestion++;
            scoreText.setText(Integer.toString(score)+ "/" + Integer.toString(noOfQuestion));
            generateQuestion();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);
        startButton = findViewById(R.id.start);
        questionText = findViewById(R.id.questionText);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultText = findViewById(R.id.resultText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        playAgainButton = findViewById(R.id.playagain);

    }
}