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

    MutableLiveData<Desafio> desafioSeleccionado = new MutableLiveData<>();
    MutableLiveData<String> terminoBusqueda = new MutableLiveData<>();
    MutableLiveData<Uri> imagenSeleccionada = new MutableLiveData<>();

    public DesafiosViewModel(@NonNull Application app) {
        super(app);
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
