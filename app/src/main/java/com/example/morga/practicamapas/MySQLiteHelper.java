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
    String sqlCreateRespuesta = "CREATE TABLE respuestas ( monumento TEXT PRIMARY KEY, " +
            "respuesta TEXT)";

    // Hago los insert de los monumentos que habra en la bbdd
    // Uso el nombre de la imagen para el nombre del monumento
    String sqlInsertMonumentoCasaCaridad = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('casa_caridad', null)";

    String sqlInsertMonumentoConventoAngeles = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('convento_angeles', null)";

    String sqlInsertMonumentoIglesiaLlatzer = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('iglesia_llatzer', null)";

    String sqlInsertMonumentoSantaCreu = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('santa_creu', null)";

    String sqlInsertMonumentoTeatroRaval = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('teatro_raval', null)";

    String sqlInsertMonumentoCiutatVella = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('ciutat_vella', null)";

    String sqlInsertMonumentoModernismo = "INSERT INTO respuestas (monumento, respuesta)" +
            "VALUES ('modernismo', null)";





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

        // Hago los insert de los monumentos que habra en la base de datos
        db.execSQL(sqlInsertMonumentoCasaCaridad);
        db.execSQL(sqlInsertMonumentoConventoAngeles);
        db.execSQL(sqlInsertMonumentoIglesiaLlatzer);
        db.execSQL(sqlInsertMonumentoSantaCreu);
        db.execSQL(sqlInsertMonumentoTeatroRaval);
        db.execSQL(sqlInsertMonumentoCiutatVella);
        db.execSQL(sqlInsertMonumentoModernismo);



    }

    //*********
    //Funcion para hacer el UPGADE de la base de datos
    //*********
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(sqlCreateRespuesta);

    }
}
