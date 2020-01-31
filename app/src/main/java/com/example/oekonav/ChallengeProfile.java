package com.example.oekonav;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oekonav.resources.Challenge;
import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

public class ChallengeProfile extends AppCompatActivity {
    public ParseObject o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_profile);
        Button addChallange = findViewById(R.id.btn_AddChallange);
        Button acceptChallange = findViewById(R.id.btn_accept);
        Button denyChallange = findViewById(R.id.btn_deny);
        Button completeChallange = findViewById(R.id.btn_complete);
        Button giveupChallange = findViewById(R.id.btn_givup);
        TextView challangeDisc = findViewById(R.id.txt_description);
        TextView challangeTitle = findViewById(R.id.txt_ChallangeNameProfile);
        TextView points = findViewById(R.id.txt_points);
        ParseUser current = ParseUser.getCurrentUser();
        ArrayList<String> challangeList = new ArrayList<String>();
        if (current.getList("myChallanges") != null) {
            for (int i = 0; i < current.getList("myChallanges").size(); i++) {
                String ch = current.getList("myChallanges").get(i).toString();
                challangeList.add(ch);
            }
        }
        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        addChallange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                challangeList.add(o.getObjectId().toString());
                current.put("myChallanges", challangeList);
                current.saveInBackground();
                addChallange.setEnabled(false);
                Toast.makeText(ChallengeProfile.this, "Challange Added to list", Toast.LENGTH_SHORT);
            }
        });

        if (b != null) {
            o = (ParseObject) b.get("ChallangeInfo");
            Boolean isReq = (boolean) b.get("ChallangeInfoReq");
            Log.i("Current INFO SCREEN PASSED", "VALUE: " + o.get("Name").toString());
            points.setText(o.getString("Score"));
            challangeDisc.setText(o.get("ChallengeDisc").toString());
            challangeTitle.setText(o.get("Name").toString());
            if (challangeList.size() > 0) {
                if (challangeList.contains(o.getObjectId().toString())) {
                    addChallange.setVisibility(View.INVISIBLE);
                    acceptChallange.setVisibility(View.VISIBLE);
                    denyChallange.setVisibility(View.VISIBLE);
                    completeChallange.setVisibility(View.VISIBLE);
                    giveupChallange.setVisibility(View.VISIBLE);
                } else {

                    acceptChallange.setVisibility(View.INVISIBLE);
                    denyChallange.setVisibility(View.INVISIBLE);
                    completeChallange.setVisibility(View.INVISIBLE);
                    giveupChallange.setVisibility(View.INVISIBLE);
                }
            } else {
                addChallange.setVisibility(View.VISIBLE);
                acceptChallange.setVisibility(View.INVISIBLE);
                denyChallange.setVisibility(View.INVISIBLE);
                completeChallange.setVisibility(View.INVISIBLE);
                giveupChallange.setVisibility(View.INVISIBLE);
            }
            if (isReq) {
                acceptChallange.setVisibility(View.VISIBLE);
                denyChallange.setVisibility(View.VISIBLE);
            } else {
                acceptChallange.setVisibility(View.INVISIBLE);
                denyChallange.setVisibility(View.INVISIBLE);
            }


        }
    }
}
