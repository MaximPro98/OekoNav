package com.example.oekonav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddChallenge extends AppCompatActivity {
    ArrayList<ParseObject> mArrData = new ArrayList<ParseObject>();
    ChallangeSearchAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_challenge);
        TextInputEditText cName = findViewById(R.id.txt_Challange_Search_Input);
        TextInputEditText ccName = findViewById(R.id.txt_CreatorName);
        Spinner dropdown = findViewById(R.id.spinner2);
        Button findChallanges = findViewById(R.id.btn_searchChallange);
        ListView mListview = findViewById(R.id.lstView_ChallangeResult);
        this.madapter = new ChallangeSearchAdapter(this, mArrData);

        findChallanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                // adapter.clear();
                findChallanges.setEnabled(false);
                ParseQuery query = new ParseQuery("Challenges");
                if (cName.getText().length() > 0) {
                    query.whereContains("Name", cName.getText().toString());
                }
                if (ccName.getText().length() > 0) {
                    query.whereContains("createdBy", ccName.getText().toString());
                }
                query.whereEqualTo("Difficulty", dropdown.getSelectedItem().toString().toLowerCase());
                Log.i("Drop Down Value", "Value: " + dropdown.getSelectedItem().toString().toLowerCase());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> challangeList, ParseException e) {
                        if (e == null) {
                            Log.i("Challanges Result", "Results" + challangeList);
                            mArrData.clear();
                            for (int i = 0; i < challangeList.size(); i++) {
                                mArrData.add(challangeList.get(i));
                            }
                            madapter = new ChallangeSearchAdapter(AddChallenge.this, mArrData);
                            mListview.setAdapter(madapter);

                            findChallanges.setEnabled(true);
                            madapter.notifyDataSetChanged();
                        } else {


                        }
                    }
                });
            }

        });


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
