package com.oekonav.oekonav.resources;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.oekonav.oekonav.R;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
public class FriendlistAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ParseObject> mArrReqData;

    public FriendlistAdapter(Context context, ArrayList<ParseObject> mArrReqData) {
        super();
        this.mContext = context;
        this.mArrReqData = mArrReqData;

    }

    public int getCount() {
        // return the number of records
        return mArrReqData.size();
    }

    // getView method is called for each item of ListView
    public View getView(int position, View view, ViewGroup parent) {
        // inflate the layout for each item of listView
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.row_item_friendlist, parent, false);


        // get the reference of textView and button

        TextView txtFName = (TextView) view.findViewById(R.id.txt_FriendName);
        TextView txtFScore = (TextView) view.findViewById(R.id.txt_FriendScore);

        Button btnDeny = (Button) view.findViewById(R.id.btn_friendlistProfile);
        ParseUser sendingUser = mArrReqData.get(position).getParseUser("User");
        String username = sendingUser.getUsername();

        // Set the title and button name
        txtFName.setText(username);
        txtFScore.setText(Integer.toString(sendingUser.getInt("Score")));

        // Click listener of button
        btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseUser.getCurrentUser().getRelation("Friends").getQuery();
                query.whereMatches("User", sendingUser.getObjectId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> ReqList, ParseException e) {
                        if (e == null) {

                            for(ParseObject data : ReqList){
                                data.deleteInBackground();
                            }

                            ParseQuery<ParseObject> query1 = sendingUser.getRelation("Friends").getQuery();
                            query1.whereMatches("User", ParseUser.getCurrentUser().getObjectId());
                            query1.findInBackground(new FindCallback<ParseObject>() {
                                public void done(List<ParseObject> ReqList1, ParseException e) {
                                    for(ParseObject data1: ReqList1) {
                                        data1.deleteInBackground();
                                    }

                                    mArrReqData.remove(position);
                                    FriendlistAdapter.this.notifyDataSetChanged();
                                }
                            });

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
