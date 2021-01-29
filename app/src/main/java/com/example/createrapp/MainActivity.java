package com.example.createrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);

        Glide.with(this).load(R.drawable.profile_picture_example).circleCrop().into(binding.profilePicture); /* Imagen de perfil redondeada */

        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            if (destination.getId() == R.id.nuevoDesafioFragment
                    || destination.getId() == R.id.actividadFragment
                    || destination.getId() == R.id.inicioFragment
                    || destination.getId() == R.id.registroUsuarioFragment
                    || destination.getId() == R.id.loginFragment
                    || destination.getId() == R.id.profileFragment
                    || destination.getId() == R.id.friendsFragment
                    || destination.getId() == R.id.miComunidadFragment) {
                binding.searchView.setVisibility(View.GONE);
            } else {
                binding.searchView.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.profileFragment
                    || destination.getId() == R.id.inicioFragment
                    || destination.getId() == R.id.registroUsuarioFragment
                    || destination.getId() == R.id.loginFragment
                    || destination.getId() == R.id.friendsFragment) {
                binding.profilePicture.setVisibility(View.GONE);
            } else {
                binding.profilePicture.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.profileFragment
                    || destination.getId() == R.id.inicioFragment
                    || destination.getId() == R.id.registroUsuarioFragment
                    || destination.getId() == R.id.loginFragment
                    || destination.getId() == R.id.friendsFragment) {
                binding.friendTab.setVisibility(View.GONE);
            } else {
                binding.friendTab.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.profileFragment
                    || destination.getId() == R.id.inicioFragment
                    || destination.getId() == R.id.registroUsuarioFragment
                    || destination.getId() == R.id.loginFragment
                    || destination.getId() == R.id.friendsFragment) {
                binding.bottomNavView.setVisibility(View.GONE);
            } else {
                binding.bottomNavView.setVisibility(View.VISIBLE);
            }

        });

        binding.profilePicture.setOnClickListener(v -> {
            navController.navigate(R.id.profileFragment);
        });


        binding.friendTab.setOnClickListener(v -> {
            navController.navigate(R.id.friendsFragment);
        });

    }
}