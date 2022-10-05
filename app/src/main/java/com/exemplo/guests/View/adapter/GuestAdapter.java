package com.exemplo.guests.View.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.guests.R;
import com.exemplo.guests.View.listener.OnListClick;
import com.exemplo.guests.View.viewHolder.GuestViewHolder;
import com.exemplo.guests.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestViewHolder> {

    private List<GuestModel> list = new ArrayList<>();
    private OnListClick listClick;

    @NonNull
    @Override
    public GuestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_guest_row,parent,false);

        return new GuestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestViewHolder holder, int position) {
        holder.bind(this.list.get(position),this.listClick);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void attachList(List<GuestModel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public void attachListener(OnListClick listener) {
        this.listClick = listener;
    }
}
