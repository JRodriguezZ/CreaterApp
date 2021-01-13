package com.example.createrapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.createrapp.databinding.ViewholderDesafioBinding;

import java.util.List;


public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    class DesafioViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderDesafioBinding binding;

        public DesafioViewHolder(ViewholderDesafioBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    class DesafiosAdapter extends RecyclerView.Adapter<DesafioViewHolder> {

        List<Publicacion> desafios;

        @NonNull
        @Override
        public DesafioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new DesafioViewHolder(ViewholderDesafioBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DesafioViewHolder holder, int position) {

            Publicacion desafio = desafios.get(position);

            holder.binding.tituloDesafio.setText(desafio.titulo);
            holder.binding.descripcionDesafio.setText(desafio.descripcion);
            holder.binding.dificultadDesafio.setRating(desafio.dificultad);
        }

        @Override
        public int getItemCount() {
            return desafios != null ? desafios.size() : 0;
        }

        public void establecerLista(List<Publicacion> desafios){
            this.desafios = desafios;
            notifyDataSetChanged();
        }
    }
}