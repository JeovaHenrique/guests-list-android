package com.exemplo.guests.db;

import static com.exemplo.guests.constants.DataBaseConstants.GUEST.TABLE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.exemplo.guests.constants.DataBaseConstants;
import com.exemplo.guests.constants.DataBaseConstants.GUEST.COLUMNS;

public class GuestDataBaseHelper extends SQLiteOpenHelper {

    private static final String NAME = "Guest.db";
    private static final Integer VERSION = 1;

    private static final String CREATE_TABLE_GUEST = "create table " +  TABLE_NAME + " ("
            + COLUMNS.ID + " integer primary key autoincrement, "
            + COLUMNS.NAME + " text, "
            + COLUMNS.PRESENCE + " integer);";


    public GuestDataBaseHelper(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_GUEST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String s = "";
    }
}
