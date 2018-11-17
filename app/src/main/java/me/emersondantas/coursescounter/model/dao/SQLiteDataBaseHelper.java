package me.emersondantas.coursescounter.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class SQLiteDataBaseHelper<T> extends SQLiteOpenHelper {
    private static final String BASE_NAME = "CoursesCounter";
    private static final int BASE_VERSION = 1;

    public SQLiteDataBaseHelper(@Nullable Context context) {
        super(context, BASE_NAME, null, BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlDrop = "DROP TABLE " + getTableName();
        db.execSQL(sqlDrop);
        onCreate(db);
    }

    protected void executeSql(String SQL){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(SQL);
        db.close();
    }

    public void insertInTo(T obg){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv = prepareContentValues(cv, obg);
        db.insert(getTableName(), null, cv);
        db.close();
    }

    public List<T> selectAllFromTable(){
        List<T> registers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + getTableName();
        Cursor cr = db.rawQuery(sql, null);
        if(cr.moveToFirst()) {
            do {
                registers.add(createRegisterObject(cr));
            } while (cr.moveToNext());
        }
        db.close();
        return registers;
    }

    public boolean RegisterExists(T obg){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM " +getTableName() + " WHERE " + getComparableCondition(obg);
        Cursor cr = db.rawQuery(sql, null);
        cr.moveToFirst();
        int countValue = cr.getInt(0);
        db.close();
        return (countValue > 0) ? true:false;
    }

    protected void createTable(){
        executeSql("CREATE TABLE IF NOT EXISTS " + getTableName() + " ( " + getCreateTableDescriptions() + " )");
    }

    protected abstract String getComparableCondition(T obg);

    protected abstract T createRegisterObject(Cursor cr);

    protected abstract String getCreateTableDescriptions();

    protected abstract String getTableName();

    protected abstract ContentValues prepareContentValues(ContentValues cv, T obg);
}
