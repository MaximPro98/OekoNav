package com.example.oekonav.ui.challenges;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChallengesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ChallengesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Challenges fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}