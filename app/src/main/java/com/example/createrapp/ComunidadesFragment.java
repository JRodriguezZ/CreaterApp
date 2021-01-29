package com.example.createrapp;

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
import com.example.createrapp.databinding.FragmentComunidadesBinding;
import com.example.createrapp.databinding.FragmentNuevoDesafioBinding;


public class ComunidadesFragment extends Fragment {
    private NavController navController;
    private FragmentComunidadesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = com.example.createrapp.databinding.FragmentComunidadesBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.comunidadPicture1); /* Imagen de perfil redondeada */
        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.comunidadPicture2);
        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.comunidadPicture3);
        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.comunidadPicture4);


        binding.constraint1Comunidades.setOnClickListener(v -> {
            navController.navigate(R.id.action_comunidadesFragment_to_miComunidadFragment);
        });



    }
}