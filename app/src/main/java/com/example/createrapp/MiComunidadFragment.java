package com.example.createrapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentAnyadirAmigoBinding;
import com.example.createrapp.databinding.FragmentMiComunidadBinding;

public class MiComunidadFragment extends Fragment {
    private FragmentMiComunidadBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentMiComunidadBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.comunidadMiaPicture);
    }
}