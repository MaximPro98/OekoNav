package com.example.oekonav;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class CreateChallengeActivity extends AppCompatActivity {
    //create a list of items for the spinner.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_challenge);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Spinner dropdown = findViewById(R.id.spinner3);
        String[] items = new String[]{"Easy", "Medium", "Hard"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        Button createChallange = (Button) findViewById(R.id.btn_CreateChallange);
        EditText textDesc = (EditText) findViewById(R.id.EditText_ChallangeDisc) ;
        EditText textName = (EditText) findViewById(R.id.EditText_NameChallenge) ;
        createChallange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                ParseObject newChallange = new ParseObject("challenges");
                newChallange.put("ChallengeDisc", textDesc.getText().toString());
                newChallange.put("Name", textName.getText().toString());
                newChallange.put("Difficulty", dropdown.getSelectedItem().toString().toLowerCase());
                newChallange.put("CreatedBy", ParseUser.getCurrentUser().getUsername().toLowerCase());
                newChallange.put("Type", Boolean.TRUE);
                newChallange.put("Score", (dropdown.getSelectedItemPosition()+1)*200);
                newChallange.saveInBackground();
                Toast.makeText(CreateChallengeActivity.this, "Challenge Created!", Toast.LENGTH_SHORT);

            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
