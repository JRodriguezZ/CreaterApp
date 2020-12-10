package com.example.createrapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

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

        Glide.with(this).load(R.drawable.mood).circleCrop().into(binding.perfil);
    }
}