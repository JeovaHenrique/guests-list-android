package com.exemplo.guests.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exemplo.guests.View.adapter.GuestAdapter;
import com.exemplo.guests.model.GuestModel;
import com.exemplo.guests.repository.GuestRepository;

import java.util.List;

public class AllGuestsViewModel extends AndroidViewModel {

    private GuestRepository guestRepository;

    private MutableLiveData<List<GuestModel>> mGuestList = new MutableLiveData<>();
    public LiveData<List<GuestModel>> guestList = this.mGuestList;

    public AllGuestsViewModel(@NonNull Application application) {
        super(application);
        this.guestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void getlist() {
        this.mGuestList.setValue(this.guestRepository.getList());
    }
}