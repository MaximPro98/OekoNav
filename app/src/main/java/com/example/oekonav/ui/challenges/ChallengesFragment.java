package com.example.oekonav.ui.challenges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.oekonav.AddChallenge;
import com.example.oekonav.AddFriendActivity;
import com.example.oekonav.ChallangeSearchAdapter;
import com.example.oekonav.CreateChallengeActivity;
import com.example.oekonav.R;
import com.example.oekonav.myChallangesAdapter;
import com.example.oekonav.resources.SearchAdapter;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ChallengesFragment extends Fragment {

    private ChallengesViewModel challengesViewModel;

    private Button buttonCreateChallenge;
    ArrayList<ParseObject> mArrData = new ArrayList<ParseObject>();
    myChallangesAdapter madapter;
    ListView myList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        challengesViewModel =
                ViewModelProviders.of(this).get(ChallengesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_challenges, container, false);

        buttonCreateChallenge = root.findViewById(R.id.create_challange_btn);

        buttonCreateChallenge.setOnClickListener(new View.OnClickListener() {

                                                     @Override
                                                     public void onClick(View view) {
                                                         goToCreateChallenge(view);
                                                     }
                                                 }

        );

        buttonCreateChallenge = root.findViewById(R.id.challengelistButton2);


        buttonCreateChallenge.setOnClickListener(new View.OnClickListener() {

                                                     @Override
                                                     public void onClick(View view) {
                                                         goToAddChallenge(view);
                                                     }
                                                 }

        );
        myList = (ListView) root.findViewById(R.id.lstView_MyChallanges);
        ParseUser current = ParseUser.getCurrentUser();
        ArrayList<ParseObject> challangeList = new ArrayList<ParseObject>();
        if (current.getList("myChallanges") != null) {
            for (int i = 0; i < current.getList("myChallanges").size(); i++) {
                ParseQuery q = new ParseQuery("Challenges");
                q.whereEqualTo("objectId", current.getList("myChallanges").get(i));
                try {
                    List<ParseObject> results = q.find();
                    for (ParseObject result : results) {
                        System.out.println("Object found " + result.getObjectId());
                        mArrData.add(result);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            madapter = new myChallangesAdapter(this.getActivity(), mArrData);
            myList.setAdapter(madapter);
            madapter.notifyDataSetChanged();
        }


        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    // Wechselt bei aufruf zur CreateChallengeActivity
    public void goToCreateChallenge(View view) {
        Intent i = new Intent(getActivity(), CreateChallengeActivity.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }

    // Wechselt bei aufruf zur CreateChallengeActivity
    public void goToAddChallenge(View view) {
        Intent i = new Intent(getActivity(), AddChallenge.class);
        startActivity(i);
        ((Activity) getActivity()).overridePendingTransition(0, 0);
    }
}