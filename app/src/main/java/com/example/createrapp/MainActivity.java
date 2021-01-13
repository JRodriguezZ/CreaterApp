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
            if (destination.getId() == R.id.nuevoDesafioFragment || destination.getId() == R.id.actividadFragment) {
                binding.searchView.setVisibility(View.GONE);
            } else {
                binding.searchView.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.profileFragment) {
                binding.profilePicture.setVisibility(View.GONE);
            } else {
                binding.profilePicture.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.profileFragment || destination.getId() == R.id.registroUsuarioFragment) {
                binding.bottomNavView.setVisibility(View.GONE);
            } else {
                binding.bottomNavView.setVisibility(View.VISIBLE);
            }

            if (destination.getId() == R.id.inicioFragment){
                binding.bottomNavView.setVisibility(View.GONE);
                binding.profilePicture.setVisibility(View.GONE);
                binding.searchView.setVisibility(View.GONE);
            } else{
                binding.bottomNavView.setVisibility(View.VISIBLE);
                binding.profilePicture.setVisibility(View.VISIBLE);
                binding.searchView.setVisibility(View.VISIBLE);
            }
        });

        binding.profilePicture.setOnClickListener(v -> {
            navController.navigate(R.id.profileFragment);
        });

    }


}