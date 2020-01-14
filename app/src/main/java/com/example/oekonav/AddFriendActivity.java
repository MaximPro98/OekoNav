package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

public class AddFriendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        final Button btnSearch = findViewById(R.id.searchFriendbutton);
        final EditText txtSearch = findViewById(R.id.searchFriendEditText);


    }
}
