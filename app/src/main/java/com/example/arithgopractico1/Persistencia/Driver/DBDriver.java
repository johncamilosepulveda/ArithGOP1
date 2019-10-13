package com.example.arithgopractico1.Persistencia.Driver;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBDriver extends SQLiteOpenHelper {

    private static DBDriver instance;

    public final static String DBNAME = "puntaje";
    public final static int DBVERSION = 1;

    //TABLE PUNTAJE
    public final static String TABLE_PUNTAJE = "puntaje";
    public final static String TASKLIST_SCORE = "score";

    private DBDriver(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_PUNTAJE = "CREATE TABLE $TABLE($SCORE INTEGER)";

        CREATE_TABLE_PUNTAJE = CREATE_TABLE_PUNTAJE
                .replace("$TABLE", TABLE_PUNTAJE)
                .replace("$SCORE", TASKLIST_SCORE);

        db.execSQL(CREATE_TABLE_PUNTAJE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_PUNTAJE);
        onCreate(db);
    }

    public static synchronized DBDriver getInstance(Context context){
        if(instance == null){
            instance = new DBDriver(context);
        }
        return instance;
    }


}
