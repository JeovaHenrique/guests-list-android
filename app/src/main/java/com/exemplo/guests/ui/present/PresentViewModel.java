package com.exemplo.guests.ui.present;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PresentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public PresentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Present");
    }

    public LiveData<String> getText() {
        return mText;
    }
}