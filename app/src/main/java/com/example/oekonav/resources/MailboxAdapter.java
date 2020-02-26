package com.example.oekonav.resources;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.oekonav.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;
public class MailboxAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ParseObject> mArrReqData;

    public MailboxAdapter(Context context, ArrayList<ParseObject> mArrReqData) {
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
        view = inflater.inflate(R.layout.row_item_mailbox, parent, false);


        // get the reference of textView and button

        TextView txtInviteName = (TextView) view.findViewById(R.id.txt_inviteName);
        TextView txtInviteExtra = (TextView) view.findViewById(R.id.txt_InviteExtra);

        Button btnAccept = (Button) view.findViewById(R.id.btn_AcceptRequest);
        ImageButton btnDeny = (ImageButton) view.findViewById(R.id.btn_DeleteRequest);
        ParseUser sendingUser = mArrReqData.get(position).getParseUser("SendingUser");
        String username = sendingUser.getUsername();

        // Set the title and button name
        txtInviteName.setText("Friend Request:");
        txtInviteExtra.setText(username);
        btnAccept.setText("Accept");

        // Click listener of button
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("FriendRequests");
                query.whereMatches("TargetUser", ParseUser.getCurrentUser().getObjectId());
                query.whereMatches("SendingUser",  mArrReqData.get(position).getParseUser("SendingUser").getObjectId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> ReqList, ParseException e) {
                        if (e == null) {
                            ParseObject friend = new ParseObject("Friends");
                            friend.put("User", mArrReqData.get(position).getParseUser("SendingUser"));
                            friend.put("User2", ParseUser.getCurrentUser());
                            friend.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    // TODO Auto-generated method stub
                                    ParseObject friend2 = new ParseObject("Friends");
                                    friend2.put("User2", mArrReqData.get(position).getParseUser("SendingUser"));
                                    friend2.put("User", ParseUser.getCurrentUser());
                                    friend2.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            // TODO Auto-generated method stub
                                            ParseQuery pushQuery = ParseInstallation.getQuery();
                                            pushQuery.whereMatches("user", mArrReqData.get(position).getParseUser("SendingUser").getObjectId());
                                            ParsePush push = new ParsePush();
                                            push.setQuery(pushQuery); // Set our Installation query
                                            push.setMessage(mArrReqData.get(position).getParseUser("TargetUser").getUsername() + " Accepted your Friend Request");
                                            push.sendInBackground();
                                            mArrReqData.get(position).deleteInBackground();
                                            mArrReqData.remove(position);
                                            MailboxAdapter.this.notifyDataSetChanged();

                                        }

                                    });

                                }

                            });

                        } else {


                        }
                    }

                });
            }
        });
        btnDeny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("FriendRequests");
                query.whereMatches("TargetUser", ParseUser.getCurrentUser().getObjectId());
                query.whereMatches("SendingUser", mArrReqData.get(position).getObjectId());
                query.findInBackground(new FindCallback<ParseObject>() {
                    public void done(List<ParseObject> ReqList, ParseException e) {
                        if (e == null) {

                            mArrReqData.get(position).deleteInBackground();
                            mArrReqData.remove(position);
                            MailboxAdapter.this.notifyDataSetChanged();

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
