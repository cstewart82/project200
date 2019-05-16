package com.example.a40126571.project200;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SoundExperiment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_experiment);
    }

    // This method happens upon the user pressing the "Guitar Strings" button on the activity_sound_experiment.xml page.
    public void OpenStrings(View view) {
        // This triggers the activity_strings.xml page.
        startActivity(new Intent(this, Strings.class));
    }
}


