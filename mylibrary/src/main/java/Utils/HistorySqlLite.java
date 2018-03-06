package Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by zhengheng on 18/1/23.
 */
public class HistorySqlLite extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "searchhistory";
    public static final int VERSION = 1;

    private static HistorySqlLite dbhelper = null;

    public static HistorySqlLite getInstance(Context context) {
        if (dbhelper == null) {
            dbhelper = new HistorySqlLite(context);
        }
        return dbhelper;
    }

    public HistorySqlLite(Context context) {
        super(context, TABLE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "( id integer primary key autoincrement , historykey varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insert(final String key) {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//
//            }
//        }.start();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("historykey", key);
            db.insert(TABLE_NAME, null, contentValues);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
//            db.close();
        }

    }


    public ArrayList<String> query() {
//        new Thread() {
//            @Override
//            public void run() {
//                super.run();
//            }
//        }.start();
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.beginTransaction();
        Cursor cursor = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            cursor = db.query(HistorySqlLite.TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int historykey = cursor.getColumnIndex("historykey");
                String columnName = cursor.getString(historykey);
                list.add(columnName);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {

        } finally {
            db.endTransaction();
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
//                db.close();
            }
        }
        return list;
    }

    public void delete() {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
//        db.close();
    }

    public int deleteSingle(String key) {
        SQLiteDatabase db = getWritableDatabase();
        int i = 0;
        db.beginTransaction();
        try {
            String[] args = {key};
            i = db.delete(TABLE_NAME, "historykey=?", args);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            return i;
        }
    }

//    public void doInAsyncTask(){
//        new AsyncTask<Boolean,Integer,String>() {
//
//        }
//    }
}
