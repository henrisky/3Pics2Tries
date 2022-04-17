package edu.lewisu.cs.yasirtahir.a3pics2tries;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private int highScore;
    private TextView highScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentById(R.id.rating_container);
//
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        boolean darkTheme = sharedPreferences.getBoolean(getString(R.string.pref_theme_key), false);
//        if(darkTheme){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//        }else{
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        }

        highScoreTextView = findViewById(R.id.highScoreTextView);
        String scoreString  = getResources().getString(R.string.high_score_string, highScore);
        highScoreTextView.setText(scoreString);
        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();

                            int gameScore = 0;
                            if (intent != null) {
                                gameScore = intent.getIntExtra("score", 0);
                            }

                            //update high score and related TextView
                            if (gameScore > highScore){
                                highScore = gameScore;
                            }

                            String scoreString = getResources().getString(R.string.high_score_string, highScore);
                            highScoreTextView.setText(scoreString);
                        }
                    }
                });


        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(view -> {
            Intent gameIntent = new Intent(getApplicationContext(), GameActivity.class);
            gameIntent.putExtra("High Score", highScore);
            startForResult.launch(gameIntent);
        });

    }
}