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
   Float dificultad;
   String comentarios;
   String likes;

   public Desafio(String titulo, String descripcion, float dificultad) {
      this.titulo = titulo;
      this.descripcion = descripcion;
      this.dificultad = dificultad;
   }

}
