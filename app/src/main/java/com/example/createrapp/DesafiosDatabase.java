package com.example.createrapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Desafio.class}, version = 1, exportSchema = false)

public abstract class DesafiosDatabase extends RoomDatabase {

    public abstract DesafiosDao obtenerDesafiosDao();

    private static volatile DesafiosDatabase DB;

    static DesafiosDatabase obtenerInstancia(final Context context) {
        if (DB == null) {
            synchronized (DesafiosDatabase.class) {
                if (DB == null) {
                    DB = Room.databaseBuilder(context, DesafiosDatabase.class, "desafios.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return DB;
    }

    @Dao
    interface DesafiosDao {
        @Query("SELECT * FROM Desafio")
        LiveData<List<Desafio>> obtener();

        @Insert
        void insertar(Desafio desafio);

        @Update
        void actualizar(Desafio desafio);

        @Delete
        void eliminar(Desafio elemento);

        @Query("SELECT * FROM Desafio ORDER BY dificultad DESC")
        LiveData<List<Desafio>> masDificiles();

        @Query("SELECT * FROM Desafio ORDER BY dificultad ASC")
        LiveData<List<Desafio>> masFaciles();

        @Query("SELECT * FROM Desafio WHERE titulo LIKE '%' || :d || '%'")
        LiveData<List<Desafio>> buscar(String d);
    }

}
