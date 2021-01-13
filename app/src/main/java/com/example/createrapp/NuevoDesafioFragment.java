package com.example.createrapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DesafiosViewModel elementosViewModel = new ViewModelProvider(requireActivity()).get(DesafiosViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.botonPublicarNuevoDesafio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.tituloNuevoDesafio.getText().toString();
                String descripcion = binding.descripcionNuevoDesafio.getText().toString();
                Float dificultad = binding.dificultadNuevoDesafio.getRating();

                elementosViewModel.insertar(new Desafio(nombre, descripcion, dificultad));

                navController.popBackStack();
            }
        });
    }

}