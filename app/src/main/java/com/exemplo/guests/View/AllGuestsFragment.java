package com.exemplo.guests.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.guests.R;
import com.exemplo.guests.View.adapter.GuestAdapter;
import com.exemplo.guests.View.listener.OnListClick;
import com.exemplo.guests.ViewModel.AllGuestsViewModel;
import com.exemplo.guests.constants.GuestConstants;
import com.exemplo.guests.databinding.FragmentAllGuestsBinding;

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

        OnListClick listener = id -> {

            Bundle bundle = new Bundle();
            bundle.putInt(GuestConstants.GUESTID, id);
            Intent intent = new Intent(getContext(),GuestActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);

        };

        this.adapter.attachListener(listener);

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