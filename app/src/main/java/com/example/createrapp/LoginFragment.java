package com.example.createrapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentInicioBinding;
import com.example.createrapp.databinding.FragmentLoginBinding;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        binding.loginButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_inicioFragment);
        });

        binding.registerIcon.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_registroUsuarioFragment);
        });



    }
}