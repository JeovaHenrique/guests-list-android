package com.exemplo.guests.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.exemplo.guests.ViewModel.PresentViewModel;
import com.exemplo.guests.databinding.FragmentPresentBinding;

public class PresentFragment extends Fragment {

    private FragmentPresentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        PresentViewModel galleryViewModel =
                new ViewModelProvider(this).get(PresentViewModel.class);

        binding = FragmentPresentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}