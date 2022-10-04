package com.exemplo.guests.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AbsentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AbsentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Absent");
    }

    public LiveData<String> getText() {
        return mText;
    }
}