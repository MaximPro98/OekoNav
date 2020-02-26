package com.example.oekonav.ui.friendlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.oekonav.AddFriendActivity;
import com.example.oekonav.R;
import com.example.oekonav.resources.FriendlistAdapter;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class FriendlistFragment extends Fragment {

    private FriendlistViewModel friendlistViewModel;
    private Button addFriendButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendlistViewModel =
                ViewModelProviders.of(this).get(FriendlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friendlist, container, false);

        ListView friendList = root.findViewById(R.id.lstView_myFriends);


        addFriendButton = root.findViewById(R.id.friendlistButton2);
        FriendlistAdapter mAdatper;
        ArrayList<ParseObject> mArrData = new ArrayList<ParseObject>();
        ParseQuery<ParseObject> q1 = new ParseQuery<ParseObject>("Friends");
        q1.include("User");
        q1.include("User2");
        q1.whereNotEqualTo("User", ParseUser.getCurrentUser().getObjectId());
        q1.whereMatches("User2", ParseUser.getCurrentUser().getObjectId());
        try {
            List<ParseObject> results = q1.find();
            for (ParseObject result : results) {
                mArrData.add(result);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mAdatper = new FriendlistAdapter(this.getActivity(), mArrData );
        friendList.setAdapter(mAdatper);


        addFriendButton.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        goToAddFriend(view);
                    }
                }
        );

        return root;
    }


    // Wechselt bei aufruf zur AddFriendActivity
    public void goToAddFriend (View view) {
        Intent i = new Intent(getActivity(), AddFriendActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }


}