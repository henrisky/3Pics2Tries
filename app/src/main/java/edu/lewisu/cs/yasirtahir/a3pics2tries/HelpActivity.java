package edu.lewisu.cs.yasirtahir.a3pics2tries;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HelpActivity extends android.app.Activity{
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(view -> {
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        });
    }
}
