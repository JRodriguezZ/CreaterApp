package com.example.createrapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.createrapp.databinding.FragmentNuevoDesafioBinding;


public class NuevoDesafioFragment extends Fragment {

    private FragmentNuevoDesafioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = com.example.createrapp.databinding.FragmentNuevoDesafioBinding.inflate(inflater, container, false)).getRoot();
    }
}