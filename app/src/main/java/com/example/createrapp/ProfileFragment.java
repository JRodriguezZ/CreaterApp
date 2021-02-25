package com.example.createrapp;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {


    private NavController navController;
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = com.example.createrapp.databinding.FragmentProfileBinding.inflate(inflater, container, false)).getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();

        navController = Navigation.findNavController(view);

        Glide.with(this).load(auth.getCurrentUser().getPhotoUrl()).circleCrop().into(binding.profilePicturePerfil); /* Imagen de perfil redondeada */

        binding.nombrePerfil.setText(auth.getCurrentUser().getDisplayName());

        binding.backPerfil.setOnClickListener(v ->
                navController.popBackStack()
        );

    }
}