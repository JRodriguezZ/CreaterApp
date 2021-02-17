package com.example.createrapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentRegistroUsuarioBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;

import java.util.UUID;


public class RegistroUsuarioFragment extends Fragment {

    public static class SignUpViewModel extends ViewModel {
        Uri imagenSeleccionada;
    }
    private FragmentRegistroUsuarioBinding binding;
    private SignUpViewModel viewModel;
    private FirebaseAuth mAuth;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentRegistroUsuarioBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();

        binding.registerButton.setOnClickListener(v -> {
            String name = binding.nombreRegistro.getText().toString();
            String username = binding.usuarioRegistro.getText().toString();
            String email = binding.emailRegistro.getText().toString();
            String password = binding.passwordRegistro.getText().toString();

            boolean valid = true;

            if (name.isEmpty()) {
                binding.nombreRegistro.setError("Required");
                valid = false;
            }
            if (username.isEmpty()) {
                binding.usuarioRegistro.setError("Required");
                valid = false;
            }
            if (email.isEmpty()) {
                binding.emailRegistro.setError("Required");
                valid = false;
            }
            if (password.isEmpty()) {
                binding.passwordRegistro.setError("Required");
                valid = false;
            }

            if (viewModel.imagenSeleccionada == null) {
                Toast.makeText(requireContext(), "Seleccione una foto", Toast.LENGTH_SHORT).show();
                valid = false;
            }

            if (valid) {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            FirebaseStorage.getInstance()
                                    .getReference("avatars/" + UUID.randomUUID())
                                    .putFile(viewModel.imagenSeleccionada)
                                    .continueWithTask(task2 -> task2.getResult().getStorage().getDownloadUrl())
                                    .addOnSuccessListener(url -> {
                                        mAuth.getCurrentUser().updateProfile(
                                                new UserProfileChangeRequest.Builder()
                                                        .setDisplayName(name)
                                                        .setPhotoUri(url)
                                                        .build()
                                        );

                                        navController.navigate(R.id.action_registroUsuarioFragment_to_inicioFragment);
                                    });

                        } else {
                            Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            }
        });

        binding.imagenRegistro.setOnClickListener(v -> {
            lanzadorGaleria.launch("image/*");
        });


        if(viewModel.imagenSeleccionada != null) Glide.with(this).load(viewModel.imagenSeleccionada).circleCrop().into(binding.imagenRegistro);

    }

    private final ActivityResultLauncher<String> lanzadorGaleria = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
        viewModel.imagenSeleccionada = uri;
        Glide.with(this).load(uri).circleCrop().into(binding.imagenRegistro);
    });
}