package info.greglondon.quadstats;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tasks.db";
    public static final String TABLE_TASKS = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_DATE = "created_on";

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TASKS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TASK + " VARCHAR(100), " +
                COLUMN_DATE + " DATE DEFAULT CURRENT_DATE " + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i1 == i + 1) {
            //db.execSQL("ALTER TABLE " + TABLE_TASKS + " ADD COLUMN");
            //onCreate(db);
        }
    }

    public void addTask(Tasks tasks){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, tasks.get_task());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_TASKS, null,values);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TASKS + " WHERE " + COLUMN_TASK + " = '" + task + "'");
    }

    public String dbToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_TASKS;

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            if(c.getString(c.getColumnIndex("task")) != null){
                dbString += c.getString(c.getColumnIndex("task")) + "\n";
            }
        }
        db.close();
        return dbString;
    }
}
