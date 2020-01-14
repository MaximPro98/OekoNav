package com.example.oekonav.ui.challenges;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.oekonav.AddChallenge;
import com.example.oekonav.CreateChallengeActivity;
import com.example.oekonav.R;

public class ChallengesFragment extends Fragment {

    private ChallengesViewModel challengesViewModel;

    private Button buttonCreateChallenge;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        challengesViewModel =
                ViewModelProviders.of(this).get(ChallengesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_challenges, container, false);

        buttonCreateChallenge = root.findViewById(R.id.button7);


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

        return root;
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