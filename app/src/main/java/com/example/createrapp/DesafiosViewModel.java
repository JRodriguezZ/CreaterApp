package com.example.createrapp;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class DesafiosViewModel extends AndroidViewModel {

    DesafiosAdministrador desafiosAdministrador;

    MutableLiveData<Desafio> desafioSeleccionado = new MutableLiveData<>();

    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();

    LiveData<List<Desafio>> resultadoBusqueda = Transformations.switchMap(terminoBusqueda, new Function<String, LiveData<List<Desafio>>>() {
        @Override
        public LiveData<List<Desafio>> apply(String input) {
            return desafiosAdministrador.buscar(input);
        }
    });

    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    public DesafiosViewModel(@NonNull Application app) {
        super(app);
        desafiosAdministrador = new DesafiosAdministrador(app);
    }

    LiveData<List<Desafio>> obtener() {
        return desafiosAdministrador.obtener();
    }

    LiveData<List<Desafio>> masDificiles() {
        return desafiosAdministrador.masDificiles();
    }

    LiveData<List<Desafio>> masFaciles() {
        return desafiosAdministrador.masFaciles();
    }

    LiveData<List<Desafio>> buscar(String s) {
        return desafiosAdministrador.buscar(s);
    }



    void insertar(Desafio desafio){
        desafiosAdministrador.insertar(desafio);
    }

    void eliminar(Desafio desafio){
        desafiosAdministrador.eliminar(desafio);
    }

    void actualizar(Desafio desafio, float dificultad){
        desafiosAdministrador.actualizar(desafio, dificultad);
    }


    void seleccionar(Desafio desafio){
        desafioSeleccionado.setValue(desafio);
    }

    MutableLiveData<Desafio> seleccionado(){
        return desafioSeleccionado;
    }


    void establecerTerminoBusqueda(String s){
        terminoBusqueda.setValue(s);
    }

    void establecerImagenSeleccionada(Uri uri){ imagenSeleccionada.setValue(uri); }
}
