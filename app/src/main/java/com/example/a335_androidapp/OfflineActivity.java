package com.example.a335_androidapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class OfflineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline);
        setTitle("Badi-App");
        final Button button = findViewById(R.id.button);
        // sets the try again clickable and defines action
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // opens new activity/intend
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
