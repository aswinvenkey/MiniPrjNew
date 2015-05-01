package com.example.aswinvenkat.miniprj;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by aswin venkat on 3/20/2015.
 */
public class Db {
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_REGNO = "regno";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASS = "pass";
    public static final String KEY_CPASS = "cpass";
    private static final String DB_NAME = "studentdb";
    private static final String DB_TABLE = "student";
    private static final int DB_VERSION = 1;
    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DB_TABLE + " ( " + KEY_ROWID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT NOT NULL, "
                            + KEY_REGNO + " TEXT NOT NULL, " + KEY_EMAIL + " TEXT NOT NULL, " + KEY_PASS + " TEXT NOT NULL, "
                            + KEY_CPASS + " TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
            onCreate(db);
        }
    }

    public Db(Context context) {
        ourContext = context;
    }

    public Db open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourHelper.close();
    }

    public long create(String name, String regnu, String spass, String scpass, String smail) {

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_REGNO, regnu);
        cv.put(KEY_PASS, spass);
        cv.put(KEY_CPASS, scpass);
        cv.put(KEY_EMAIL, smail);
        return ourDatabase.insert(DB_TABLE, null, cv);
    }


}

