package com.exemplo.guests.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.exemplo.guests.model.GuestModel;
import com.exemplo.guests.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {

    private GuestRepository guestRepository;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.guestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guest) {
        this.guestRepository.post(guest);
    }
}
