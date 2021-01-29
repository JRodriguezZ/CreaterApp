package com.example.createrapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.createrapp.databinding.FragmentAnyadirAmigoBinding;
import com.example.createrapp.databinding.FragmentInicioBinding;


public class AnyadirAmigoFragment extends DialogFragment {
    private FragmentAnyadirAmigoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentAnyadirAmigoBinding.inflate(inflater, container, false)).getRoot();
    }
}