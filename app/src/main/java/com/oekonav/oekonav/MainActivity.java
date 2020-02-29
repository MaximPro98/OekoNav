package com.oekonav.oekonav;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseUser;
import com.parse.ParseException;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.LogInCallback;

import static com.parse.Parse.getApplicationContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListener();
    }

    private void initListener() {
        final TextInputEditText textInputUsername = findViewById(R.id.textInputUsername);
        final TextInputEditText textInputPassword = findViewById(R.id.textInputPassword);
        final Button buttonLogin = findViewById(R.id.buttonLogin);
        final TextView textViewRegister = findViewById(R.id.textViewRegister);


        buttonLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(final View view) {

                        ParseUser.logInInBackground(textInputUsername.getText().toString(), textInputPassword.getText().toString(), new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) {
                                  goToMap(view);

                                } else {
                                    textInputUsername.setText("");
                                    textInputPassword.setText("");
                                    Context context = getApplicationContext();
                                    CharSequence text = "Wrong username/password!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });

                    }
                }
        );
        textViewRegister.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    goToRegister(view);
                    }
                }
        );

    }


    // Wechselt bei aufruf zum ausgewählten TrainProg
    public void goToRegister (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    public void goToMap (View view) {
        Intent intent = new Intent(this, Navdrawmenu.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        this.moveTaskToBack(true);
    }
}
