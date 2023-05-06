package com.example.odev;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {

    //INFO DATABASE
    public static final String DATABASE_NAME = "kisiler";
    private static final int DATABASE_VERSION = 1;
    private static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "userName";
    public static final String KEY_USER_SURNAME = "userSurname";
    public static final String KEY_USER_NUMBER = "userNumber";

    SQLiteDatabase dataBase;

    public DataBaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + "(" + KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +// Define a primary key
                KEY_USER_NAME + " VARCHAR(40)," + KEY_USER_SURNAME + " VARCHAR(40)," + KEY_USER_NUMBER + " VARCHAR(20)" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(database);
    }

    public void kisiEkle(String ad, String soyad, String numara, Context context) {
        dataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_USER_NAME, ad.trim());
        contentValues.put(KEY_USER_SURNAME, soyad.trim());
        contentValues.put(KEY_USER_NUMBER, numara.trim());

        long result = dataBase.insert(DATABASE_NAME, null, contentValues);

        if (result > -1)
            Toast.makeText(context, "Not kaydedildi", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Not kaydedilemedi", Toast.LENGTH_SHORT).show();

        dataBase.close();
    }

    public void kisiGuncelle(Long id, String ad, String soyad, String numara) {
        dataBase = getWritableDatabase();
        String sorgu = "UPDATE " + DATABASE_NAME + " SET " + KEY_USER_NAME + "=?, " + KEY_USER_SURNAME + "=?, " + KEY_USER_NUMBER + "=? WHERE id=?";
        SQLiteStatement durumlar = dataBase.compileStatement(sorgu);
        durumlar.bindString(1, ad);
        durumlar.bindString(2, soyad);
        durumlar.bindString(3, numara);
        durumlar.bindLong(4, id);
        durumlar.execute();

    }

    public void kisiSil(int id) {
        dataBase = this.getWritableDatabase();
        dataBase.delete(DATABASE_NAME, "id=?", new String[]{String.valueOf(id)});
        dataBase.close();
    }

    public ArrayList<Kisi> kisiListele() {
        ArrayList<Kisi> Kisiler = new ArrayList<>();

        dataBase = this.getReadableDatabase();

        String[] projection = {
                KEY_USER_ID,
                KEY_USER_NAME,
                KEY_USER_SURNAME,
                KEY_USER_NUMBER
        };

        Cursor cursor = dataBase.query(
                DATABASE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        int adIndex = cursor.getColumnIndex(KEY_USER_NAME);
        int soyadIndex = cursor.getColumnIndex(KEY_USER_SURNAME);
        int numaraIndex = cursor.getColumnIndex(KEY_USER_NUMBER);
        int idIndex = cursor.getColumnIndex(KEY_USER_ID);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(idIndex);
                String ad = String.valueOf(cursor.getString(adIndex));
                String soyad = String.valueOf(cursor.getString(soyadIndex));
                String numara = String.valueOf(cursor.getString(numaraIndex));
                // Verileri kullan
                Kisiler.add(new Kisi(id, ad, soyad, numara));
            } while (cursor.moveToNext());
        }
        /*
        while (cursor.moveToNext()) {
            int id = cursor.getInt(idIndex);
            String ad = cursor.getString(adIndex);
            String soyad = cursor.getString(soyadIndex);
            String numara = cursor.getString(numaraIndex);
            Kisi kisi = new Kisi(id, ad, soyad, numara);
            Kisiler.add(kisi);
        }

         */

        cursor.close();
        dataBase.close();

        return Kisiler;
    }
}

