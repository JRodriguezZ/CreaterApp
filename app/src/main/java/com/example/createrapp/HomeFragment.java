package com.example.createrapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.createrapp.databinding.FragmentHomeBinding;
import com.example.createrapp.databinding.ViewholderDesafioBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    DesafiosViewModel desafiosViewModel;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentHomeBinding.inflate(inflater, container, false)).getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        desafiosViewModel = new ViewModelProvider(requireActivity()).get(DesafiosViewModel.class);

        DesafiosAdapter desafiosAdapter = new DesafiosAdapter();

        binding.recyclerView.setAdapter(desafiosAdapter);

        binding.recyclerView.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        obtenerDesafios().observe(getViewLifecycleOwner(), desafios -> {
            if(desafios.size() == 0){
                binding.emptyListText.setVisibility(View.VISIBLE);
            } else {
                binding.emptyListText.setVisibility(View.GONE);
            }
            desafiosAdapter.establecerLista(desafios);
        });

    }

    static class DesafioViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderDesafioBinding binding;

        public DesafioViewHolder(ViewholderDesafioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    LiveData<List<Desafio>> obtenerDesafios(){
        return desafiosViewModel.obtener();
    }

    class DesafiosAdapter extends RecyclerView.Adapter<DesafioViewHolder> {

        List<Desafio> desafios;

        @NonNull
        @Override
        public DesafioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DesafioViewHolder(ViewholderDesafioBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DesafioViewHolder holder, int position) {
            Desafio desafio = desafios.get(position);



            Glide.with(holder.itemView).load(R.drawable.profile_picture_example).circleCrop().into(holder.binding.profilePictureDesafio);
            holder.binding.tituloDesafio.setText(desafio.titulo);
            Glide.with(holder.itemView).load(desafio.multimedia).into(holder.binding.imagenDesafio);
            holder.binding.descripcionDesafio.setText(desafio.descripcion);
            holder.binding.dificultadDesafio.setRating(desafio.dificultad);

//            holder.binding.likeDesafio.setOnTouchListener((v, event) -> {
//                if(event.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
//                    holder.binding.likeDesafio.setBackgroundColor(Color.GREEN);
//                } else if(event.getAction() == MotionEvent.ACTION_BUTTON_RELEASE) {
//                    holder.binding.likeDesafio.setBackgroundColor(Color.BLUE);
//                }
//                return false;
//            });
        }

        @Override
        public int getItemCount() {
            return desafios != null ? desafios.size() : 0;
        }

        public void establecerLista(List<Desafio> desafios){
            this.desafios = desafios;
            notifyDataSetChanged();
        }
    }
}