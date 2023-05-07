package com.example.shuffs_to_buy;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DataBaseHelper extends SQLiteOpenHelper {
    public static String DB_name = "Stuffs";
    public DataBaseHelper(Context context) {
        super(context, DB_name, null, 2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Items(id primary key,name)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if exist
       // db.execSQL("DROP TABLE IF EXISTS Expense" );
        // Create tables again
        onCreate(db);
    }
}
