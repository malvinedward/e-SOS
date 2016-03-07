package com.example.malvinedward.e_sos;

import android.app.admin.DeviceAdminInfo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Malvin Edward on 30/11/2015.
 */
public class DBSource {

    private int counter;
    private long id;
    private String nama;
    private String nomerhp;
    // Inisialisasi database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    // Ambil konstanta
    private String[] allColumns = { DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NAMA, DatabaseHelper.COLUMN_NOMERHP};

    // Menggunakan DBMapsHelper yang diiinisialisasi pada konstruktor
    public DBSource(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Mengambil sebuah database yang bisa digunakan
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public DBContact createContact(String nama, String nomerhp) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAMA, nama);
        values.put(DatabaseHelper.COLUMN_NOMERHP, nomerhp);

        long insertId = database.insert(DatabaseHelper.TABLE_NAME, null,
                values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                allColumns, DatabaseHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        DBContact newcontact = cursortocontact(cursor);
        cursor.close();

        return newcontact;
    }


    // Method yang berfungsi untuk membuat sebuah objek lokasi baru yang nantinya akan dimasukkan ke dalam database
    private DBContact cursortocontact(Cursor cursor) {
        cursor.moveToFirst();
        DBContact contact = new DBContact();
        //Log.v("info", "The getLONG "+cursor.getLong(0));
        //Log.v("info", "The setLatLng " + cursor.getString(1) + "," + cursor.getString(2) + cursor.getString(3) + cursor.getString(4) + cursor.getString(5) + cursor.getString(6) + cursor.getString(7));

        contact.setId(cursor.getInt(0));
        contact.setNama(cursor.getString(1));
        contact.setNomerhp(cursor.getString(2));

        return contact;
    }

    public DBContact getcontact(int id ){
         database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, new String[]{DatabaseHelper.COLUMN_ID, DatabaseHelper.COLUMN_NAMA, DatabaseHelper.COLUMN_NOMERHP}, DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor !=null)
            cursor.moveToFirst();

        DBContact contact = new DBContact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2));
        return contact;
    }


        public List<DBContact> getallcontact(){
            List<DBContact> contactlist = new ArrayList<DBContact>();
            String selectQuery = "SELECT * FROM " + DatabaseHelper.TABLE_NAME;
             SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery,null);

            if(cursor.moveToFirst()){
                do {
                    DBContact contact = new DBContact();
                    contact.setId(Integer.parseInt(cursor.getString(0)));
                    contact.setNama(cursor.getString(1));
                    contact.setNomerhp(cursor.getString(2));

                    contactlist.add(contact);

                }
                while (cursor.moveToNext());
            }
            return contactlist;
        }

    public void deleteallcontact()
    {
        database.execSQL("DELETE FROM "+ DatabaseHelper.TABLE_NAME);
    }
    public void deleteContact(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
        }






}
