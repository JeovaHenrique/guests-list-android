package com.exemplo.guests.View.viewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.guests.R;
import com.exemplo.guests.View.listener.OnListClick;
import com.exemplo.guests.model.GuestModel;

public class GuestViewHolder extends RecyclerView.ViewHolder {

    private TextView textName;

    public GuestViewHolder(@NonNull View itemView) {
        super(itemView);

        this.textName = itemView.findViewById(R.id.text_name);
    }

    public void bind(GuestModel guestModel, final OnListClick listClick) {
        this.textName.setText(guestModel.getName());

        this.textName.setOnClickListener(V -> {
            listClick.onClick(guestModel.getId());
        });

    }
}
