package com.auribises.activitydatapassing.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.auribises.activitydatapassing.Util;

public class MyUserContentProvider extends ContentProvider {

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    public MyUserContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
       String tabName = uri.getLastPathSegment();
       long id = sqLiteDatabase.insert(tabName,null,values);
       Uri dummyUri = Uri.parse("someprot://somedata/"+id);
       return dummyUri;
    }

    @Override
    public boolean onCreate() {

        dbHelper = new DBHelper(getContext(), Util.DB_NAME,null,Util.DB_VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();

        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version); //1. Create the DB
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Util.CREATE_TAB_QUERY); //2. Creation of Table
            //db.execSQL(Util.CREATE_TAB_QUERY1); //2. Creation of Table
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

}
