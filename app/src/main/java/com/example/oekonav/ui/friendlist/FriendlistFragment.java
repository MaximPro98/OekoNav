package com.example.oekonav.ui.friendlist;

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

import com.example.oekonav.AddFriendActivity;
import com.example.oekonav.R;

public class FriendlistFragment extends Fragment {

    private FriendlistViewModel friendlistViewModel;
    private Button addFriendButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendlistViewModel =
                ViewModelProviders.of(this).get(FriendlistViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friendlist, container, false);


        addFriendButton = root.findViewById(R.id.friendlistButton2);


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