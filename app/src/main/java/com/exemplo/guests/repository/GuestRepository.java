package com.exemplo.guests.repository;

import static com.exemplo.guests.constants.DataBaseConstants.*;
import static com.exemplo.guests.constants.DataBaseConstants.GUEST.COLUMNS.ID;
import static com.exemplo.guests.constants.DataBaseConstants.GUEST.COLUMNS.NAME;
import static com.exemplo.guests.constants.DataBaseConstants.GUEST.COLUMNS.PRESENCE;
import static com.exemplo.guests.constants.DataBaseConstants.GUEST.TABLE_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.exemplo.guests.db.GuestDataBaseHelper;
import com.exemplo.guests.model.GuestModel;

import java.util.ArrayList;
import java.util.List;

public class GuestRepository {

    private static GuestRepository INSTANCE;
    private GuestDataBaseHelper mDataBase;

    private GuestRepository(Context context) {
        this.mDataBase = new GuestDataBaseHelper(context);
    }

    public static GuestRepository getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = new GuestRepository(context);
        }

        return INSTANCE;
    }

    public List<GuestModel> getList() {
        return new ArrayList<>();
    }

    public GuestModel get(Integer id) {
        
        GuestModel guest = null;

        try {
            SQLiteDatabase db = this.mDataBase.getReadableDatabase();

            String[] columns = {ID,NAME, PRESENCE};

            String selection = ID + "= ?";
            String[] selectionArgs = {String.valueOf(id)};

            Cursor cursor =  db.query(TABLE_NAME,columns,selection,selectionArgs,null,null,null);
            if(cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();

                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex(NAME));
                @SuppressLint("Range")
                String presence = cursor.getString(cursor.getColumnIndex(GUEST.COLUMNS.PRESENCE));

                guest =  new GuestModel(id,name,presence);
            }
            if(cursor != null) {
                cursor.close();
            }
            return guest;
        }catch (Exception e) {
            return null;
        }


    }

    public boolean post(GuestModel guest) {

        try {
            SQLiteDatabase db = this.mDataBase.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(NAME,guest.getName());
            values.put(PRESENCE, String.valueOf(guest.getConfirmation()));

            db.insert(TABLE_NAME,null,null);

            return true;

        }catch (Exception e) {
            return false;
        }

    }

    public boolean update(GuestModel guest) {

        try {
            SQLiteDatabase db = this.mDataBase.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(NAME,guest.getName());
            values.put(PRESENCE, String.valueOf(guest.getConfirmation()));

            String where = TABLE_NAME + "= ?";
            String[] args = {String.valueOf(guest.getId())};

            db.update(TABLE_NAME,values,where,args);
            db.close();

            return true;

        }catch (Exception e) {
            return false;
        }

    }

    public boolean delete(Integer id) {

        try {
            SQLiteDatabase db = this.mDataBase.getWritableDatabase();

            String where = TABLE_NAME + "= ?";
            String[] args = {String.valueOf(id)};

            db.delete(TABLE_NAME,where,args);
            db.close();

            return true;

        }catch (Exception e) {
            return false;
        }

    }
}
