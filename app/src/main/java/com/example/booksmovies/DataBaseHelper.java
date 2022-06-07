package com.example.booksmovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private final Context context;
    private static String name;

    public DataBaseHelper(@Nullable Context context, String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        this.name = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + name + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, author TEXT, date TEXT, comment TEXT, rating TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + name);
        onCreate(db);
    }

    Cursor readAll() {
        String query = "SELECT * FROM " + name;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void add(String name, String author, String date, String comment, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("author", author);
        cv.put("date", date);
        cv.put("comment", comment);
        cv.put("rating", rating);

        long res = db.insert(this.name, null, cv);
        if (res == -1) {
            Toast.makeText(context, "Could not add.", Toast.LENGTH_SHORT).show();
        }
    }

    void update(String id, String name, String author, String date, String comment, String rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("author", author);
        cv.put("date", date);
        cv.put("comment", comment);
        cv.put("rating", rating);

        long res = db.update(this.name, cv, "_id=?", new String[]{id});
        if (res == -1) {
            Toast.makeText(context, "Could not update.", Toast.LENGTH_SHORT).show();
        }
    }

    void delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res = db.delete(this.name, "_id=?", new String[]{id});
        if (res == -1) {
            Toast.makeText(context, "Could not delete.", Toast.LENGTH_SHORT).show();
        }
    }
}
