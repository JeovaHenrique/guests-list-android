package com.exemplo.guests.ViewModel;

import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.ABSENT;
import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.NOT_CONFIRMED;
import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.PRESENT;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exemplo.guests.View.adapter.GuestAdapter;
import com.exemplo.guests.constants.GuestConstants;
import com.exemplo.guests.model.FeedBack;
import com.exemplo.guests.model.GuestModel;
import com.exemplo.guests.repository.GuestRepository;

import java.util.List;

public class AllGuestsViewModel extends AndroidViewModel {

    private GuestRepository guestRepository;

    private MutableLiveData<List<GuestModel>> mGuestList = new MutableLiveData<>();
    public LiveData<List<GuestModel>> guestList = this.mGuestList;

    private MutableLiveData<FeedBack> mFeedBack = new MutableLiveData<>();
    public LiveData<FeedBack> feedBack = this.mFeedBack;

    public AllGuestsViewModel(@NonNull Application application) {
        super(application);
        this.guestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void getList(String filter) {
        if (filter.equals(NOT_CONFIRMED)) {
            this.mGuestList.setValue(this.guestRepository.getAll());
        } if (filter.equals(PRESENT)) {
            this.mGuestList.setValue(this.guestRepository.getPresent());
        } else if (filter.equals(ABSENT)){
            this.mGuestList.setValue(this.guestRepository.getAbsent());
        }
    }

    public void delete(int id) {
        if (this.guestRepository.delete(id)) {
            this.mFeedBack.setValue(new FeedBack("Guest Successfully Removed"));
        } else {
            this.mFeedBack.setValue(new FeedBack("Unexpected Error", false));
        }
    }
}