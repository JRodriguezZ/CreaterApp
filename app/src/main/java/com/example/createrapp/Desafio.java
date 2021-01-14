package com.example.createrapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Desafio {
   @PrimaryKey(autoGenerate = true)
   int id;

   String titulo;
   String fecha;
   String multimedia;
   String descripcion;
   float dificultad;
   String comentarios;
   int likes;

   public Desafio(String titulo, String descripcion, float dificultad, String multimedia) {
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.dificultad = dificultad;
      this.multimedia = multimedia;
   }

}
