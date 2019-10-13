package com.example.arithgopractico1.Persistencia.Data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.arithgopractico1.Persistencia.App.ScoreApp;
import com.example.arithgopractico1.Persistencia.Driver.DBDriver;
import com.example.arithgopractico1.Persistencia.Entity.Score;

import java.util.ArrayList;
import java.util.Date;

public class CRUDScore {

    public static void insertScore(Score score){
        DBDriver driver = DBDriver.getInstance(ScoreApp.getContext());
        SQLiteDatabase db = driver.getWritableDatabase();

        String sql = "INSERT INTO $TABLE($SCORE) VALUES('$VSCORE')";
        sql = sql
                .replace("$TABLE", DBDriver.TABLE_PUNTAJE)
                .replace("$ID", DBDriver.TASKLIST_SCORE);

        db.execSQL(sql);
        db.close();
    }



}
