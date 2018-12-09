package com.example.fares.tpsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contactmanager";
    public static  final String TABLE_NAME ="contact";
    public static  final String KEY_ID ="ID";
    public static  final String KEY_NAME="NAME";
    public static  final String KEY_NUMBER="NUMBER";
    private SQLiteDatabase mDb;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public SQLiteDatabase open(){
        mDb = this.getWritableDatabase();
        return mDb;
    }
    public  void close(){
        mDb.close();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+TABLE_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+KEY_NAME+" TEXT," +
                ""+KEY_NUMBER+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  db.execSQL("drop table if exists "+TABLE_NAME);
  onCreate(db);
    }

    public void addContact(Contact c ){
        ContentValues v = new ContentValues();
        v.put(KEY_NAME,c.getNom());
        v.put(KEY_NUMBER,c.getNumber());
        mDb.insert(TABLE_NAME,null,v);
    }


    public List<Contact> getAllContact() {
        List<Contact> contactList = new ArrayList<Contact>();
        mDb = this.getReadableDatabase();
        Cursor c = mDb.rawQuery("select * from " + TABLE_NAME, null);
        if (c.moveToNext())
            do {
                Contact oc = new Contact();
                oc.setId(Integer.parseInt(c.getString(0)));
                oc.setNom(c.getString(1));
                oc.setNumber(c.getString(2));
                contactList.add(oc);
            } while (c.moveToNext());
            c.close();
            return contactList;
        }
    public int updateContact(Contact c ){
        mDb = this.getWritableDatabase();
        ContentValues v= new ContentValues();
        v.put(KEY_NAME,c.getNom());
        v.put(KEY_NUMBER,c.getNumber());
        return mDb.update(TABLE_NAME,v,KEY_ID+"= ?",new String[]{String.valueOf(c.getId())});
    }
    public void DeleteContact(Contact c){
        mDb = this.getWritableDatabase();
        mDb.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(c.getId())});
        mDb.close();
    }
}
