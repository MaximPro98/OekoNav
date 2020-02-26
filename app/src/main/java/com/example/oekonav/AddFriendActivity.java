package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.oekonav.resources.SearchAdapter;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class AddFriendActivity extends AppCompatActivity {
    private ListView mListview;
    private ArrayList<String> mArrData;
    private SearchAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        mListview = (ListView) findViewById(R.id.ListSearchResults);
        final Button btnSearch = findViewById(R.id.searchFriendbutton);
        final TextInputEditText txtSearch = findViewById(R.id.searchFriendEditText);
        txtSearch.setText("");
        txtSearch.setHint("Username");
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String search = txtSearch.getText().toString().toLowerCase();

                ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                query.whereContains("username", search);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {
                            if (scoreList.size() > 0) {
                                mArrData = new ArrayList<String>();
                                for (int i = 0; i < scoreList.size(); i++) {
                                    mArrData.add(scoreList.get(i).getString("username"));
                                }
                                mAdapter = new SearchAdapter(AddFriendActivity.this, mArrData);
                                mListview.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();
                            }


                        } else {
                            mArrData = new ArrayList<String>();
                            mAdapter = new SearchAdapter(AddFriendActivity.this, mArrData);
                            mListview.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();

                        }
                    }
                });

            }
        });


    }
}
