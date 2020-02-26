package com.example.oekonav;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;

public class myChallangesAdapter extends ArrayAdapter<ParseObject> {

    public myChallangesAdapter(Context context, ArrayList<ParseObject> c) {
        super(context, 0, c);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ParseObject Challange = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_challanges_list_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.txt_challange_result_name);
        TextView creator = (TextView) convertView.findViewById(R.id.txt_challange_creator_text);
        if (Challange.getBoolean("Type")) {
            String createdBy = Challange.getString("CreatedBy");
            creator.setText("Created By: " + createdBy);
        } else {
            creator.setText("Official Challange");
        }
        Button showInfo = (Button) convertView.findViewById(R.id.txt_Challange_Info);
        showInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(getContext(), ChallengeProfile.class);
                intent.putExtra("ChallangeInfo", Challange);
                intent.putExtra("ChallangeInfoReq", false);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }
        });
        // Populate the data into the template view using the data object
        tvName.setText(Challange.get("Name").toString());

        // Return the completed view to render on screen
        return convertView;

    }
}
