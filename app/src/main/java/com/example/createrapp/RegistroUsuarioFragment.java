package com.example.createrapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.createrapp.databinding.FragmentRegistroUsuarioBinding;


public class RegistroUsuarioFragment extends Fragment {

    private FragmentRegistroUsuarioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRegistroUsuarioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Dropdown menu stances --
//        Spinner dropdown = binding.stanceSpinnerRegistro;
//        String[] stances = new String[]{"Regular", "Goofy"};
//        ArrayAdapter<> adapter = new ArrayAdapter<>(this, R.id.stanceSpinnerRegistro, stances);
//        dropdown.setAdapter(adapter);
//
//        binding.
    }
}