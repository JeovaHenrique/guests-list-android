package com.exemplo.guests.ViewModel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exemplo.guests.model.FeedBack;
import com.exemplo.guests.model.GuestModel;
import com.exemplo.guests.repository.GuestRepository;

public class GuestViewModel extends AndroidViewModel {

    private GuestRepository guestRepository;

    private MutableLiveData<GuestModel> mGuest = new MutableLiveData<>();
    public LiveData<GuestModel> guest = this.mGuest;

    private MutableLiveData<FeedBack> mFeedBack = new MutableLiveData<>();
    public LiveData<FeedBack> feedBack = this.mFeedBack;

    public GuestViewModel(@NonNull Application application) {
        super(application);
        this.guestRepository = GuestRepository.getInstance(application.getApplicationContext());
    }

    public void save(GuestModel guest) {
        if ("".equals(guest.getName())) {
            this.mFeedBack.setValue(new FeedBack("Mandatory Name",false));
            return;
        } else {

            if ((guest.getId() == 0)) {
                if (this.guestRepository.post(guest)) {
                    this.mFeedBack.setValue(new FeedBack("Guest Entered Successfully"));
                } else {
                    this.mFeedBack.setValue(new FeedBack("Unexpected Error",false));
                }
            } else {
                if (this.guestRepository.update(guest)) {
                    this.mFeedBack.setValue(new FeedBack("Guest Updated Successfully"));
                } else {
                    this.mFeedBack.setValue(new FeedBack("Unexpected Error",false));
                }
            }
        }
    }

    public void get(int id) {
        this.mGuest.setValue(this.guestRepository.get(id));
    }
}
