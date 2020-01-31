package com.example.oekonav.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.oekonav.AddFriendActivity;
import com.example.oekonav.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mArrFriendData;

    public SearchAdapter(Context context, ArrayList mArrFriendData) {
        super();
        this.mContext = context;
        this.mArrFriendData = mArrFriendData;
    }

    public int getCount() {
        // return the number of records
        return mArrFriendData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.row_item_add_friends, parent, false);


        // get the reference of textView and button
        TextView txtSchoolTitle = (TextView) view.findViewById(R.id.txtUsernameSearch);
        Button btnAction = (Button) view.findViewById(R.id.btnAddFriendSearch);

        String username = mArrFriendData.get(position);

        // Set the title and button name
        txtSchoolTitle.setText(username);
        btnAction.setText("Add Friend");

        // Click listener of button
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
                query.whereMatches("username", username);
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> scoreList, ParseException e) {
                        if (e == null) {


                        } else {


                        }
                    }
                });


            }
        });

        return view;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}
