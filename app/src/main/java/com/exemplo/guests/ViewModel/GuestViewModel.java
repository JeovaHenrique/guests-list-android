package com.exemplo.guests.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exemplo.guests.model.GuestModel;
import com.exemplo.guests.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {

    private GuestRepository guestRepository;

    private MutableLiveData<GuestModel> mGuest = new MutableLiveData<>();
    public LiveData<GuestModel> guest = this.mGuest;

    private MutableLiveData<Boolean> mFeedBack = new MutableLiveData<>();
    public LiveData<Boolean> feedBack = this.mFeedBack;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.guestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guest) {
        if ((guest.getId() == 0)) {
           this.mFeedBack.setValue(this.guestRepository.post(guest));
        } else {
          this.mFeedBack.setValue(this.guestRepository.update(guest));
        }
    }

    public void get(int id) {
        this.mGuest.setValue(this.guestRepository.get(id));
    }
}
