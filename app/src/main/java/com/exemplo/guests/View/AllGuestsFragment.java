package com.exemplo.guests.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.guests.R;
import com.exemplo.guests.View.adapter.GuestAdapter;
import com.exemplo.guests.ViewModel.AllGuestsViewModel;
import com.exemplo.guests.databinding.FragmentAllGuestsBinding;
import com.exemplo.guests.model.GuestModel;

import java.util.List;

public class AllGuestsFragment extends Fragment {

    private FragmentAllGuestsBinding binding;
    private AllGuestsViewModel allGuestsViewModel;
    private ViewHolder viewHolder = new ViewHolder();
    private GuestAdapter adapter = new GuestAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allGuestsViewModel =
                new ViewModelProvider(this).get(AllGuestsViewModel.class);

        binding = FragmentAllGuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.viewHolder.recyclerView = root.findViewById(R.id.recycler_list);
        this.viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.viewHolder.recyclerView.setAdapter(adapter);

        this.observers();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.allGuestsViewModel.getlist();
    }

    private void observers() {
        this.allGuestsViewModel.guestList.observe(getViewLifecycleOwner(), list -> {
            adapter.attachList(list);
        });
    }

    private static class ViewHolder {
        RecyclerView recyclerView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}