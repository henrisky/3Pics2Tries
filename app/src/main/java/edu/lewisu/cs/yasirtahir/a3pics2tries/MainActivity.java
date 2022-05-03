package edu.lewisu.cs.yasirtahir.a3pics2tries;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {
    private int highScore;
    private TextView highScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                                gameScore = intent.getIntExtra("newScore", 0);
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

        Button helpButton = findViewById(R.id.helpButton);
        helpButton.setOnClickListener(view -> {
            Intent helpIntent = new Intent(this, HelpActivity.class);
            startActivity(helpIntent);
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(view -> {
            showAlertDialog("Are you sure you want to exit the app?");
        });

    }

    private void showAlertDialog(String message){
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit?")
                .setMessage(message)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
        }

        if (id == R.id.helpButton) {
            startActivity(new Intent(MainActivity.this, HelpActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}