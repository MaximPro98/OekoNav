package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AddChallenge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Spinner dropdown = findViewById(R.id.spinner2);
        String[] items = new String[]{"Easy", "Medium", "Hard"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);


        return super.onCreateOptionsMenu(menu);
    }
}
