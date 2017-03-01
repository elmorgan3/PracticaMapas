package com.example.morga.practicamapas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by morga on 28/02/2017.
 */

public class MiDataSource {

    // Declaro dos objetos de la clase
    // SQLiteDatabase, una para leer y otro para escribir
    // y otro de mi clase MySQLiteHelper
    private SQLiteDatabase dbW, dbR;
    private MySQLiteHelper dbHelper;

    public static final String TABLE_RESPUESTAS = "respuestas";
    public static final String RESPUESTA_MONUMENTO = "monumento";
    public static final String RESPUESTA_RESPUESTA = "respuesta";


    //Constructor
    public MiDataSource(Context context) {
        //En el contructor directamente abro la comunicacion con la bbdd
        dbHelper = new MySQLiteHelper(context);

        //tambien construimos dos bbdd una para leer y la otra para modificarla
        Open();
    }

    //Abro las bbdd
    private void Open() {
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }

    //Destructor
    public void Close()
    {
        dbW.close();
        dbR.close();
    }


    //*********
    //Funcion para actualizar un Articulo
    //*********
    //Creamos un nuevo articulo y devolbemos el id si lo necesitamos
    public void Update(String monumento,String respuesta)
    {
        // Modifiquem els valors de las tasca amb clau primària "id"
        ContentValues values = new ContentValues();

        values.put(RESPUESTA_RESPUESTA, respuesta);


        dbW.update(TABLE_RESPUESTAS,values, RESPUESTA_MONUMENTO + " = ?", new String[] { String.valueOf(monumento) });
    }

    //**********
    // Funciion que retorna solo el id pedido
    //**********
    public Cursor Respuesta(String monumeto)
    {
        // Retorna un cursor només amb el id indicat
        // Retornem les tasques que el camp DONE = 1
        return dbR.query(TABLE_RESPUESTAS, new String[] { RESPUESTA_MONUMENTO, RESPUESTA_RESPUESTA},
                RESPUESTA_MONUMENTO+ "=?", new String[]{String.valueOf(monumeto)},
                null,null,null
                );

    }
}
