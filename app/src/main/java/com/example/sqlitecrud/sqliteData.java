package com.example.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class sqliteData extends SQLiteOpenHelper {
    static final String DATABASE_NAME="company.db";
    static final int DATABASE_VERSION=7;

    public sqliteData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry="create table employee_table(id integer primary key,name text,contact integer,birthdate integer)";
        String qry1="create table user_table(id integer primary key,name text,contact integer,birthdate integer)";
        String qry2="create table my_table(id integer primary key,name text,contact integer,birthdate integer)";

        sqLiteDatabase.execSQL(qry);
        sqLiteDatabase.execSQL(qry1);
        sqLiteDatabase.execSQL(qry2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //       case 1:
        //           sqLiteDatabase.execSQL("ALTER TABLE employee_table ADD COLUMN age text");
        if (i1 == 3) {
            sqLiteDatabase.execSQL("ALTER TABLE employee_table ADD COLUMN address text");
        }
        if(i1==5)
        {
            sqLiteDatabase.execSQL("create table user_table(id integer primary key,name text,contact integer,birthdate integer)");
        }
        if(i1==6)
        {
            sqLiteDatabase.execSQL("create table my_table(id integer primary key,name text,contact integer,birthdate integer)");
        }
        if(i1==7)
        {
            sqLiteDatabase.execSQL("ALTER TABLE my_table ADD COLUMN address text");
        }

//        switch (i1)
//        {
//            case 1:
//                sqLiteDatabase.execSQL("ALTER TABLE employee_table ADD COLUMN address text");
//            case 2:
//            case 3:
//            case 4:
//            case 5:
//                sqLiteDatabase.execSQL("create table user_table(id integer primary key,name text,contact integer,birthdate integer)");
//            case 6:
//                sqLiteDatabase.execSQL("create table my_table(id integer primary key,name text,contact integer,birthdate integer)");
//            case 7:
//                sqLiteDatabase.execSQL("ALTER TABLE my_table ADD COLUMN address text");
//                break;
//        }
    }

    public boolean insert(int id,String name,int contact,int bdate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("birthdate",bdate);

        long result=db.insert("employee_table",null,cv);
        db.insert("my_table",null,cv);
        if(result==-1)
        {
            return false;
        }
       else
        {
            return true;
        }
    }

    public boolean update(int id,String name,int contact,int bdate)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("id",id);
        cv.put("name",name);
        cv.put("contact",contact);
        cv.put("birthdate",bdate);

//        Cursor cursor1=db.rawQuery("DELETE FROM employee_table where id=?",new String[]{String.valueOf(id)});
//        if(cursor1.getCount()>0)
//        {
//            db.delete("employee_table","id=?", new String[]{String.valueOf(id)});
//
            Cursor cursor=db.rawQuery("Select * from employee_table where id=?",new String[]{String.valueOf(id)});
            if(cursor.getCount()>0)
            {
                long result=db.update("employee_table",cv,"id=?", new String[]{String.valueOf(id)});
                if(result==-1)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
    }
    public boolean delete(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        //ContentValues cv=new ContentValues();
        Cursor cursor= db.rawQuery("select * from employee_table where name=?",new String[]{name});
        if(cursor.getCount()>0)
        {
            long result=  db.delete("employee_table","name=?",new String[]{name});
            if(result==-1)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }

    }
    public Cursor viewdata(int id)
    {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor res = db1.rawQuery("select * from employee_table where id=?",new String[]{String.valueOf(id)});
        return res;
    }

}
