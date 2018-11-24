package info.greglondon.quadstats;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.Date;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DATE = "created_on";
    private static final String TAG = "DB";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //TODO add IF NOT EXISTS
        try{
            String query = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TASK + " VARCHAR(100), " +
                    COLUMN_DATE + " DATE DEFAULT CURRENT_DATE " + ");";

            db.execSQL(query);
        }catch(SQLiteException e){
            Log.d(TAG, e.toString());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i1 == i + 1) {
            //db.execSQL("ALTER TABLE " + TABLE_TASKS + " ADD COLUMN");
            //onCreate(db);
        }
    }

    public void addTask(Tasks task){

        try{
            ContentValues values = new ContentValues();
            values.put(COLUMN_TASK, task.get_task());
            SQLiteDatabase db = getWritableDatabase();
            db.insert(TABLE_TASKS, null,values);
            db.close();

        } catch (SQLiteException e){
            Log.d(TAG, e.toString());
        }
    }

    public void deleteTask(String task){
        try{
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK + " = '" + task + "';");
            db.close();
        }catch(SQLiteException e){
            Log.d(TAG, e.toString());
        }

    }
    /*
    public String getTasksByDate(Date d){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        //TODO formate date 1st
        String q = "SELECT * FROM " + TABLE_TASKS + " WHERE " + COLUMN_DATE + " = '" + d + "'";
        Cursor c = db.rawQuery(q, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex(COLUMN_DATE)) != null){
                dbString += c.getString(c.getColumnIndex(COLUMN_TASK)) + " " +
                        c.getString(c.getColumnIndex(COLUMN_DATE)) + "\n";
            }
        }
        return dbString;
    }
    */
    public String dbToString(){
        String dbString = "";
        try{
            SQLiteDatabase db = getWritableDatabase();
            String query = "SELECT * FROM " + TABLE_TASKS + ";";

            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            while(!c.isAfterLast()){
                if(c.getString(c.getColumnIndex("task")) != null){
                    dbString += c.getString(c.getColumnIndex("task")) + "\n";
                }
            }
            db.close();
        }catch(Exception e){
            Log.d(TAG, e.toString());
        }
        return dbString;
    }
}
