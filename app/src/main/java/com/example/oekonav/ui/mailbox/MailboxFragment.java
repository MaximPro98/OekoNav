package com.example.oekonav.ui.mailbox;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.oekonav.R;
import com.example.oekonav.resources.MailboxAdapter;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class MailboxFragment extends Fragment {



    MailboxViewModel mailboxViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mailboxViewModel =
                ViewModelProviders.of(this).get(MailboxViewModel.class);
        View root = inflater.inflate(R.layout.fragment_mailbox, container, false);
        ListView mListview = (ListView) root.findViewById(R.id.lstView_Mailbox);
        ParseQuery q1 = new ParseQuery("FriendRequests");
        q1.include("TargetUser");
        q1.include("SendingUser");
        q1.whereMatches("TargetUser", ParseUser.getCurrentUser().getObjectId());

        MailboxAdapter mAdatper;
        ArrayList<ParseObject> mArrData = new ArrayList<ParseObject>();
        try {
            List<ParseObject> results = q1.find();
            for (ParseObject result : results) {
                System.out.println("Object found " + result.get("SendingUser"));
                mArrData.add(result);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mAdatper = new MailboxAdapter(this.getActivity(), mArrData );
        mListview.setAdapter(mAdatper);

        return root;
    }
}
