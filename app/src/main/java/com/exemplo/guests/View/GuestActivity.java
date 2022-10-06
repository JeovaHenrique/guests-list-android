package com.exemplo.guests.View;

import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.ABSENT;
import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.NOT_CONFIRMED;
import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.PRESENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.exemplo.guests.R;
import com.exemplo.guests.ViewModel.GuestViewModel;
import com.exemplo.guests.constants.GuestConstants;
import com.exemplo.guests.model.GuestModel;

public class GuestActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private GuestViewModel mGuestViewModel;
    private int mGuestId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_form);

        this.mGuestViewModel = new ViewModelProvider(this).get(GuestViewModel.class);

        this.mViewHolder.editName = findViewById(R.id.guest_name);
        this.mViewHolder.btnPresent = findViewById(R.id.btn_present);
        this.mViewHolder.btnAbsent = findViewById(R.id.btn_absent);
        this.mViewHolder.btnNotConfirmed = findViewById(R.id.btn_no_confirmed);
        this.mViewHolder.btnSave = findViewById(R.id.btn_done);

        this.setListener();
        this.setObservers();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mGuestId = bundle.getInt(GuestConstants.GUEST_ID);
            this.mGuestViewModel.get(mGuestId);
        }
    }

    private void setObservers() {
        this.mGuestViewModel.guest.observe(this, guestModel -> {
            mViewHolder.editName.setText(guestModel.getName());

            mViewHolder.btnNotConfirmed.setChecked(guestModel.getConfirmation().equals(NOT_CONFIRMED));
            mViewHolder.btnPresent.setChecked(guestModel.getConfirmation().equals(PRESENT));
            mViewHolder.btnAbsent.setChecked(guestModel.getConfirmation().equals(ABSENT));
        });

        this.mGuestViewModel.feedBack.observe(this, feedBack -> {

            Toast.makeText(this, feedBack.getMessage(), Toast.LENGTH_SHORT).show();

            if (feedBack.getSuccess()) finish();
        });
    }


    private void setListener() {
        this.mViewHolder.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_done) this.handleSave();
    }

    private void handleSave() {
        String name = this.mViewHolder.editName.getText().toString();
        String confirmation = NOT_CONFIRMED;

        if (this.mViewHolder.btnPresent.isChecked()) confirmation = PRESENT;
        if (this.mViewHolder.btnAbsent.isChecked()) confirmation = ABSENT;

        GuestModel guest = new GuestModel(mGuestId, name, confirmation);

        this.mGuestViewModel.save(guest);

    }

    private static class ViewHolder {
        EditText editName;
        RadioButton btnNotConfirmed;
        RadioButton btnPresent;
        RadioButton btnAbsent;
        Button btnSave;

    }
}