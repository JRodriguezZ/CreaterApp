package com.example.createrapp;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentRegistroUsuarioBinding;


public class RegistroUsuarioFragment extends Fragment {

    private FragmentRegistroUsuarioBinding binding;
    private DesafiosViewModel desafiosViewModel;
    private Uri imagenSeleccionada;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRegistroUsuarioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        desafiosViewModel = new ViewModelProvider(requireActivity()).get(DesafiosViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.registerButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_registroUsuarioFragment_to_inicioFragment);
        });

        binding.imagenRegistro.setOnClickListener(v -> {
            lanzadorGaleria.launch("image/*");
        });

        desafiosViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                imagenSeleccionada = uri;
                Glide.with(requireView()).load(uri).into(binding.imagenRegistro);
            }
        });

    }

    private final ActivityResultLauncher<String> lanzadorGaleria = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        desafiosViewModel.establecerImagenSeleccionada(uri);
    });
}