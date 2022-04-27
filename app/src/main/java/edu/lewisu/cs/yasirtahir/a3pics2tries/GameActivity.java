package edu.lewisu.cs.yasirtahir.a3pics2tries;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends android.app.Activity{
    private TextView gameScoreTextView;
    private TextView lifetimeScoreTextView;
    private Button nextButton;
    private Button gameOverButton;
    private TextView questionTextView;
    private TextView answerTextView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private EditText userText;
    private Button checkButton;
    private Question[] questions;

    private int currentIndex = 0;
    private int score;
    private int lifetimeScore;
    private int userTries = 0;
    private Map<String, Object> tableImgData;
    private Map<String, Object> tableHintsData;
    private Map<String, Object> tableAnswersData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        questions = new Question[]{
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", "")
        };

        // Database Portion
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myImages = database.getReference("imgs");
        // GETTING IMAGES FROM DATABASE
        myImages.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // We want to execute the code when we receive the data.
                // The data is received as a snapshot


                tableImgData = (HashMap<String,Object>) snapshot.getValue();
                String imgStr1 = (String) tableImgData.get("img1");
                String imgStr2 = (String) tableImgData.get("img2");
                String imgStr3 = (String) tableImgData.get("img3");
                questions[0].setImageId1(imgStr1);
                questions[0].setImageId2(imgStr2);
                questions[0].setImageId3(imgStr3);
                Picasso.get().load(questions[0].getImageId1()).into(imageView1);
                Picasso.get().load(questions[0].getImageId2()).into(imageView2);
                Picasso.get().load(questions[0].getImageId3()).into(imageView3);

                String imgStr4 = (String) tableImgData.get("img2");
                String imgStr5 = (String) tableImgData.get("img3");
                String imgStr6 = (String) tableImgData.get("img1");
                String imgStr7 = (String) tableImgData.get("img3");
                String imgStr8 = (String) tableImgData.get("img2");
                String imgStr9 = (String) tableImgData.get("img1");

                questions[1].setImageId1(imgStr4);
                questions[1].setImageId2(imgStr5);
                questions[1].setImageId3(imgStr6);
                questions[2].setImageId1(imgStr7);
                questions[2].setImageId2(imgStr8);
                questions[2].setImageId3(imgStr9);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // GETTING HINTS FROM DATABASE
        DatabaseReference myHints = database.getReference("hints");

        myHints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // We want to execute the code when we receive the data.
                // The data is received as a snapshot

                tableHintsData = (HashMap<String,Object>) snapshot.getValue();
                String hint1 = (String) tableHintsData.get("hint1");
                String hint2 = (String) tableHintsData.get("hint2");
                String hint3 = (String) tableHintsData.get("hint3");
                questions[0].setHint(hint1);
                questions[1].setHint(hint2);
                questions[2].setHint(hint3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // GETTING ANSWERS FROM DATABASE
        DatabaseReference myAnswers = database.getReference("answers");

        myAnswers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // We want to execute the code when we receive the data.
                // The data is received as a snapshot


                tableAnswersData = (HashMap<String,Object>) snapshot.getValue();
                String answer1 = (String) tableAnswersData.get("answer1");
                String answer2 = (String) tableAnswersData.get("answer2");
                String answer3 = (String) tableAnswersData.get("answer3");
                questions[0].setAnswer(answer1);
                questions[1].setAnswer(answer2);
                questions[2].setAnswer(answer3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // End Of Database Portion

        gameScoreTextView = findViewById(R.id.gameScoreTextView);
        lifetimeScoreTextView = findViewById(R.id.lifetimeScoreTextView);

        Intent sender = getIntent();
        lifetimeScore = sender.getIntExtra("High Score",0);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new nextButtonClickListener());

        questionTextView = findViewById(R.id.questionTextView);
        answerTextView = findViewById(R.id.answerTextView);

        gameOverButton = findViewById(R.id.gameOverButton);

        imageView1 = findViewById((R.id.imageView1));
        imageView2 = findViewById((R.id.imageView2));
        imageView3 = findViewById((R.id.imageView3));

        userText = findViewById(R.id.userTextView);

        checkButton = findViewById(R.id.checkAnswerButton);
        checkButton.setOnClickListener(new checkButtonClickListener());

        gameOverButton = findViewById(R.id.gameOverButton);
        gameOverButton.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("newScore", score);
            setResult(RESULT_OK, returnIntent);
            finish();
        });

        updateScore();

    }

    private void updateQuestion(){
        Log.d("updateQuestion>", "ran");
        questionTextView.setText(getResources().getString(R.string.what_word_best_describes_the_images_below));
        String hint = questions[currentIndex].getHint();
        String image1 = questions[currentIndex].getImageId1();
        String image2 = questions[currentIndex].getImageId2();
        String image3 = questions[currentIndex].getImageId3();

        Picasso.get().load(image1).into(imageView1);
        Picasso.get().load(image2).into(imageView2);
        Picasso.get().load(image3).into(imageView3);

        userTries = 0;
    }

    private void updateScore(){
        String scoreString = getResources().getString(R.string.game_score_string, score);
        gameScoreTextView.setText(scoreString);
        scoreString = getResources().getString(R.string.high_score_string, lifetimeScore);
        lifetimeScoreTextView.setText(scoreString);
    }

    private void checkAnswer(String userGuess){
        String correctAnswer = questions[currentIndex].getAnswer();
        Log.d("check>", correctAnswer);
        // Retrieve the radio button user clicked and check for right answer.


        if(correctAnswer.equals(userGuess)){
            answerTextView.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.green_700));
            answerTextView.setText(getResources().getText(R.string.correct));
            score ++;
            lifetimeScore++;
            userTries = 2;

        }else{
            answerTextView.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.red_500));
            answerTextView.setText(getResources().getText(R.string.incorrect));
            userTries +=1;
            questionTextView.setText("Hint: "+ questions[currentIndex].getHint() + ". Try again.");
        }

        updateScore();
        answerTextView.setVisibility(View.VISIBLE);

        if(correctAnswer.equals(userGuess) || userTries == 2){
            currentIndex++;
            if(currentIndex < questions.length) {
                checkButton.setVisibility(View.GONE);
                nextButton.setVisibility(View.VISIBLE);
            }else{
                checkButton.setVisibility(View.GONE);
                gameOverButton.setVisibility(View.VISIBLE);
            }
        }
    }

    private class checkButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            String userGuess = userText.getText().toString();
            checkAnswer(userGuess);
        }
    }

    private class nextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            updateQuestion();
            nextButton.setVisibility(View.GONE);
            answerTextView.setVisibility(View.GONE);
            checkButton.setVisibility(View.VISIBLE);
            userText.setText("");
        }
    }
}
