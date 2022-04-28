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
import java.util.Locale;
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
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
                new Question("", "",
                        "", "", ""),
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

                String imgStr4 = (String) tableImgData.get("img4");
                String imgStr5 = (String) tableImgData.get("img5");
                String imgStr6 = (String) tableImgData.get("img6");
                String imgStr7 = (String) tableImgData.get("img7");
                String imgStr8 = (String) tableImgData.get("img8");
                String imgStr9 = (String) tableImgData.get("img9");
                String imgStr10 = (String) tableImgData.get("img10");
                String imgStr11 = (String) tableImgData.get("img11");
                String imgStr12 = (String) tableImgData.get("img12");
                String imgStr13 = (String) tableImgData.get("img13");
                String imgStr14 = (String) tableImgData.get("img14");
                String imgStr15 = (String) tableImgData.get("img15");
                String imgStr16 = (String) tableImgData.get("img16");
                String imgStr17 = (String) tableImgData.get("img17");
                String imgStr18 = (String) tableImgData.get("img18");
                String imgStr19 = (String) tableImgData.get("img19");
                String imgStr20 = (String) tableImgData.get("img20");
                String imgStr21 = (String) tableImgData.get("img21");
                String imgStr22 = (String) tableImgData.get("img22");
                String imgStr23 = (String) tableImgData.get("img23");
                String imgStr24 = (String) tableImgData.get("img24");
                String imgStr25 = (String) tableImgData.get("img25");
                String imgStr26 = (String) tableImgData.get("img26");
                String imgStr27 = (String) tableImgData.get("img27");
                String imgStr28 = (String) tableImgData.get("img28");
                String imgStr29 = (String) tableImgData.get("img29");
                String imgStr30 = (String) tableImgData.get("img30");

                questions[1].setImageId1(imgStr4);
                questions[1].setImageId2(imgStr5);
                questions[1].setImageId3(imgStr6);
                questions[2].setImageId1(imgStr7);
                questions[2].setImageId2(imgStr8);
                questions[2].setImageId3(imgStr9);
                questions[3].setImageId1(imgStr10);
                questions[3].setImageId2(imgStr11);
                questions[3].setImageId3(imgStr12);
                questions[4].setImageId1(imgStr13);
                questions[4].setImageId2(imgStr14);
                questions[4].setImageId3(imgStr15);
                questions[5].setImageId1(imgStr16);
                questions[5].setImageId2(imgStr17);
                questions[5].setImageId3(imgStr18);
                questions[6].setImageId1(imgStr19);
                questions[6].setImageId2(imgStr20);
                questions[6].setImageId3(imgStr21);
                questions[7].setImageId1(imgStr22);
                questions[7].setImageId2(imgStr23);
                questions[7].setImageId3(imgStr24);
                questions[8].setImageId1(imgStr25);
                questions[8].setImageId2(imgStr26);
                questions[8].setImageId3(imgStr27);
                questions[9].setImageId1(imgStr28);
                questions[9].setImageId2(imgStr29);
                questions[9].setImageId3(imgStr30);
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
                String hint4 = (String) tableHintsData.get("hint4");
                String hint5 = (String) tableHintsData.get("hint5");
                String hint6 = (String) tableHintsData.get("hint6");
                String hint7 = (String) tableHintsData.get("hint7");
                String hint8 = (String) tableHintsData.get("hint8");
                String hint9 = (String) tableHintsData.get("hint9");
                String hint10 = (String) tableHintsData.get("hint10");
                questions[3].setHint(hint4);
                questions[4].setHint(hint5);
                questions[5].setHint(hint6);
                questions[6].setHint(hint7);
                questions[7].setHint(hint8);
                questions[8].setHint(hint9);
                questions[9].setHint(hint10);
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
                String answer4 = (String) tableAnswersData.get("answer4");
                String answer5 = (String) tableAnswersData.get("answer5");
                String answer6 = (String) tableAnswersData.get("answer6");
                String answer7 = (String) tableAnswersData.get("answer7");
                String answer8 = (String) tableAnswersData.get("answer8");
                String answer9 = (String) tableAnswersData.get("answer9");
                String answer10 = (String) tableAnswersData.get("answer10");
                questions[3].setAnswer(answer4);
                questions[4].setAnswer(answer5);
                questions[5].setAnswer(answer6);
                questions[6].setAnswer(answer7);
                questions[7].setAnswer(answer8);
                questions[8].setAnswer(answer9);
                questions[9].setAnswer(answer10);
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
        Log.d("updateQuestion", "updated current question");
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
        Log.d("correct answer-> ", correctAnswer);
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
            String userGuess = userText.getText().toString().toLowerCase(Locale.ROOT).trim();
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
