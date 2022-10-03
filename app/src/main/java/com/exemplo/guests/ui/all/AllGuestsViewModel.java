package com.exemplo.guests.ui.all;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllGuestsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AllGuestsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This are All Guests");
    }

    public LiveData<String> getText() {
        return mText;
    }
}