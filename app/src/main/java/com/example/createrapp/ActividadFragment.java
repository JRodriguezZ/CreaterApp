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
import com.example.createrapp.databinding.ActivityMainBinding;
import com.example.createrapp.databinding.FragmentActividadBinding;
import com.example.createrapp.databinding.FragmentComunidadesBinding;


public class ActividadFragment extends Fragment {

    private FragmentActividadBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = com.example.createrapp.databinding.FragmentActividadBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        Glide.with(this).load(R.drawable.profile_picture_example).circleCrop().into(binding.notificacionPicture1);
        Glide.with(this).load(R.drawable.profile_picture_example).circleCrop().into(binding.notificacionPicture2);
        Glide.with(this).load(R.drawable.profile_picture_example).circleCrop().into(binding.notificacionPicture3);
        Glide.with(this).load(R.drawable.eric_koston_background).circleCrop().into(binding.notificacionPicture4);
        Glide.with(this).load(R.drawable.community_logo_example).circleCrop().into(binding.notificacionPicture5);


        binding.buttonNotificacion1.setOnClickListener(v -> {
            navController.navigate(R.id.action_actividadFragment_to_profileFragment);
        });
        binding.buttonNotificacion2.setOnClickListener(v -> {
            navController.navigate(R.id.action_actividadFragment_to_friendsFragment);
        });
        binding.buttonNotificacion3.setOnClickListener(v -> {
            navController.navigate(R.id.action_actividadFragment_to_friendsFragment);
        });
        binding.buttonNotificacion4.setOnClickListener(v -> {
            navController.navigate(R.id.action_actividadFragment_to_homeFragment);
        });
        binding.buttonNotificacion5.setOnClickListener(v -> {
            navController.navigate(R.id.action_actividadFragment_to_miComunidadFragment);
        });


    }
}