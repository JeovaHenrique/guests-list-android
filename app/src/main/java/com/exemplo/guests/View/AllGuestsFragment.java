package com.exemplo.guests.View;

import static com.exemplo.guests.constants.GuestConstants.CONFIRMATION.NOT_CONFIRMED;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exemplo.guests.R;
import com.exemplo.guests.View.adapter.GuestAdapter;
import com.exemplo.guests.View.listener.OnListClick;
import com.exemplo.guests.ViewModel.AllGuestsViewModel;
import com.exemplo.guests.constants.GuestConstants;
import com.exemplo.guests.databinding.FragmentAllGuestsBinding;
import com.exemplo.guests.model.FeedBack;

public class AllGuestsFragment extends Fragment {

    private FragmentAllGuestsBinding binding;
    private AllGuestsViewModel allGuestsViewModel;
    private ViewHolder viewHolder = new ViewHolder();
    private GuestAdapter adapter = new GuestAdapter();
    private String filter = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        allGuestsViewModel =
                new ViewModelProvider(this).get(AllGuestsViewModel.class);

        binding = FragmentAllGuestsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.viewHolder.recyclerView = root.findViewById(R.id.recycler_list);
        this.viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.viewHolder.recyclerView.setAdapter(adapter);

        OnListClick listener = new OnListClick() {
            @Override
            public void onClick(int id) {

                Bundle bundle = new Bundle();
                bundle.putInt(GuestConstants.GUEST_ID, id);
                Intent intent = new Intent(getContext(), GuestActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }

            @Override
            public void onDelete(int id) {
                allGuestsViewModel.delete(id);
                allGuestsViewModel.getList(filter);
            }


        };

        this.adapter.attachListener(listener);

        if(getArguments() != null) {
            this.filter = getArguments().getString(GuestConstants.FILTER);
        }

        this.observers();
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.allGuestsViewModel.getList(this.filter);
    }

    private void observers() {
        this.allGuestsViewModel.guestList.observe(getViewLifecycleOwner(), list -> {
            adapter.attachList(list);
        });

        this.allGuestsViewModel.feedBack.observe(getViewLifecycleOwner(), feedBack -> {
                Toast.makeText(getContext(),feedBack.getMessage(),Toast.LENGTH_SHORT).show();
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