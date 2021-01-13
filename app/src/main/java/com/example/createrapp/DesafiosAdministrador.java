package com.example.createrapp;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DesafiosAdministrador {
    DesafiosDatabase.DesafiosDao desafiosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    DesafiosAdministrador(Application app) {
        desafiosDao = DesafiosDatabase.obtenerInstancia(app).obtenerDesafiosDao();
    }

    LiveData<List<Desafio>> obtener(){
        return desafiosDao.obtener();
    }

    LiveData<List<Desafio>> masDificiles(){
        return desafiosDao.masDificiles();
    }

    LiveData<List<Desafio>> masFaciles(){
        return desafiosDao.masFaciles();
    }

    LiveData<List<Desafio>> buscar(String s){
        return desafiosDao.buscar(s);
    }

    void insertar(Desafio desafio){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                desafiosDao.insertar(desafio);
            }
        });
    }

    void eliminar(Desafio desafio) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                desafiosDao.eliminar(desafio);
            }
        });
    }

    public void actualizar(Desafio desafio, float dificultad) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                desafio.dificultad = dificultad;
                desafiosDao.actualizar(desafio);
            }
        });
    }

}
