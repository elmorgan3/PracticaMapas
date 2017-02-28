package com.example.morga.practicamapas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by morga on 28/02/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    //Ponemos el nombre de la base de datos
    private static final String DATABASE_NAME = "MonumentosDB";

    // La version de la base de datos
    private static final int DATABASE_VERSION = 1;

    // Campos de la base de datos de RESPUESTAS
    String sqlCreateRespuesta = "CREATE TABLE respuestas ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "respuesta TEXT )";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    //*********
    //Funcion para hacer el CREATE de la base de datos
    //*********
    @Override
    public void onCreate(SQLiteDatabase db) {

        // //query de creacion de la base de datos con las tablas
        db.execSQL(sqlCreateRespuesta);
        

    }

    //*********
    //Funcion para hacer el UPGADE de la base de datos
    //*********
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(sqlCreateRespuesta);

    }
}
