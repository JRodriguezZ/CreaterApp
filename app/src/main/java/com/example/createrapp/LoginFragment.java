package com.example.createrapp;

import android.content.Intent;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.createrapp.databinding.FragmentLoginBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;
    private NavController navController;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);
        mAuth = FirebaseAuth.getInstance();

        binding.loginButton.setOnClickListener(v -> {
            String email = binding.correoLogin.getText().toString();
            String password = binding.passwordLogin.getText().toString();

            boolean valid = true;

            if (email.isEmpty()) {
                binding.correoLogin.setError("Required");
                valid = false;
            }
            if (password.isEmpty()) {
                binding.passwordLogin.setError("Required");
                valid = false;
            }

            if (valid) {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                navController.navigate(R.id.action_loginFragment_to_inicioFragment);
                            } else {
                                Toast.makeText(requireContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        binding.registerIcon.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_registroUsuarioFragment);
        });

        binding.googleIcon.setOnClickListener(v -> {
            loginClient.launch(GoogleSignIn.getClient(requireContext(), new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).build()).getSignInIntent());
        });


    }

    ActivityResultLauncher<Intent> loginClient = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        try {
            FirebaseAuth.getInstance().signInWithCredential(GoogleAuthProvider.getCredential(GoogleSignIn.getSignedInAccountFromIntent(result.getData()).getResult(ApiException.class).getIdToken(), null));
            navController.navigate(R.id.action_loginFragment_to_inicioFragment);
        } catch (ApiException e) {}
    });
}