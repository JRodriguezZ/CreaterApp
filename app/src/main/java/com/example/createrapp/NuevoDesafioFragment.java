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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentNuevoDesafioBinding;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.time.LocalDateTime;
import java.util.UUID;


public class NuevoDesafioFragment extends Fragment {

    private FragmentNuevoDesafioBinding binding;
    private DesafiosViewModel desafiosViewModel;
    private Uri imagenSeleccionada;
    private FirebaseFirestore firestore;
    private FirebaseStorage storage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = com.example.createrapp.databinding.FragmentNuevoDesafioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firestore = FirebaseFirestore.getInstance();

        NavController navController = Navigation.findNavController(view);

        binding.botonPublicarNuevoDesafio.setOnClickListener(v -> {
            if (imagenSeleccionada != null) {
                String nombre = binding.tituloNuevoDesafio.getText().toString();
                String descripcion = binding.descripcionNuevoDesafio.getText().toString();
                float dificultad = binding.dificultadNuevoDesafio.getRating();

                firestore.collection("desafios")
                        .add(new Desafio(nombre, descripcion, dificultad, imagenSeleccionada.toString()));

                navController.popBackStack();

                navController.navigate(R.id.homeFragment);
            } else {
                Toast.makeText(requireContext(), "Seleccione una imagen", Toast.LENGTH_SHORT).show();
            }
        });

        binding.imagenNuevoDesafio.setOnClickListener(v -> {
            lanzadorGaleria.launch("image/*");
        });

        desafiosViewModel.imagenSeleccionada.observe(getViewLifecycleOwner(), uri -> {
            if (uri != null) {
                imagenSeleccionada = uri;
                Glide.with(requireView()).load(uri).into(binding.imagenNuevoDesafio);
            }
        });

    }

    private final ActivityResultLauncher<String> lanzadorGaleria = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        storage.getReference("" + UUID.randomUUID())
                .putFile(uri)
                .continueWithTask(task -> task.getResult().getStorage().getDownloadUrl())
                .addOnSuccessListener(uri1 -> {
                    String fecha = LocalDateTime.now().toString();

                    firestore.collection("desafios")
                            .add()
                })
        desafiosViewModel.establecerImagenSeleccionada(uri);
    });



}