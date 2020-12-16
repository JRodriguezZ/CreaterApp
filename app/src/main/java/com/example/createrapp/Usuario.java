package com.example.createrapp;

import android.widget.ImageView;

import java.time.LocalDate;

public class Usuario {

    String nombre;
    String usuario;

    String profilePic;

    int nivel;
    String localidad;
    LocalDate fecha;
    Stance stance;
    String trucoFavorito;

    public enum Stance {
        GOOFY, REGULAR;
    }


    public Usuario(String nombre, String usuario) {
        this.nombre = nombre;
        this.usuario = usuario;
    }
}
