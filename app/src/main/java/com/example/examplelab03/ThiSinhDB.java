package com.example.examplelab03;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ThiSinhDB extends SQLiteOpenHelper {
    public static final String TableName= "ThiSinhTable";
    public static final String  SBD = "SBD";
    public static final String HoTen = "HoTen";
    public static final String Toan = "Toan";
    public static final String Ly = "Ly";
    public static final String Hoa = "Hoa";

    public static final String TongDiem = "TongDiem";

    public ThiSinhDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tao cau sql de tao bang StudentTable
        String sqlCreate = "Create table if not exists "+ TableName + "("
                + SBD + " Text, "
                + HoTen + " Text, "
                + Toan + " Text, "
                + Ly + " Text, "
                + Hoa + " Text, "
                + TongDiem + " Text)";
        //chay cau lenh truy van de tao bang
        db.execSQL(sqlCreate);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //xoa bang da co
        db.execSQL("Drop table if exists " + TableName);
        //tao lai
        onCreate(db);
    }

    public void addThiSinh(ThiSinh ts){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(SBD, ts.getSbd());
        value.put(HoTen, ts.getHoTen());
        value.put(Toan, String.valueOf(ts.getToan()));
        value.put(Ly, String.valueOf(ts.getLy()));
        value.put(Hoa, String.valueOf(ts.getLy()));
        value.put(TongDiem, String.valueOf(ts.tongDiem()));
        db.insert(TableName, null, value);
        db.close();
    }

    public ArrayList<ThiSinh> getAllStudent(){
        ArrayList<ThiSinh> list = new ArrayList<>();
        //cau lenh truy van
        String sql = "Select * from " + TableName;
        //lay doi tuowng csdl sqlite
        SQLiteDatabase db = this.getReadableDatabase();
        //chay cau lenh truy van tra ve dang Cursor
        Cursor cursor = db.rawQuery(sql, null);
        //tao Arraylist de tra ve
        if(cursor != null){
            while (cursor.moveToNext()){
                ThiSinh ts = new ThiSinh(cursor.getString(0),
                        cursor.getString(1), cursor.getFloat(2),
                        cursor.getFloat(3), cursor.getFloat(4));

                list.add(ts);

            }
        }
        return list;
    }
}
