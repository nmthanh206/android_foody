package hcmute.spkt.foody_g7;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DBName="Foody_Group7.db";
    public Database(Context context) {
        super(context, "Foody_Group7.db", null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL(sql);
    }
    public Cursor getData(String sql){
        SQLiteDatabase db=getWritableDatabase();
        return  db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(20), password VARCHAR(20), firstName VARCHAR(30) DEFAULT Null, lastName VARCHAR(30) DEFAULT Null, phone VARCHAR(12) , address VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
    }
}
