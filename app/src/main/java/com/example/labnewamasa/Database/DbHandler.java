package com.example.labnewamasa.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="CourseApp.dp";
    public DbHandler( Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Query=
                "CREATE TABLE " + DatabaseMaster.User.TABLE_NAME + " ("+
                        DatabaseMaster.User._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.User.COLUMN_NAME_USERNAME + " TEXT," +
                        DatabaseMaster.User.COLUMN_NAME_PASSWORD + " TEXT," +
                        DatabaseMaster.User.COLUMN_NAME_TYPE + " TEXT)";
        db.execSQL(Query);
        String Quer2=
                "CREATE TABLE " + DatabaseMaster.Message.TABLE_NAME + " ("+
                        DatabaseMaster.Message._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        DatabaseMaster.Message.COLUMN_NAME_USERNAME + " TEXT," +
                        DatabaseMaster.Message.COLUMN_NAME_SUBJECT + " TEXT," +
                        DatabaseMaster.Message.COLUMN_NAME_MESSAGE + " TEXT)";
        db.execSQL(Quer2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long AddMyUser(String Username, String Passsword, String Type){
        SQLiteDatabase DB= getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(DatabaseMaster.User.COLUMN_NAME_USERNAME,Username);
        CV.put(DatabaseMaster.User.COLUMN_NAME_PASSWORD,Passsword);
        CV.put(DatabaseMaster.User.COLUMN_NAME_TYPE,Type);
        long exec=DB.insert(DatabaseMaster.User.TABLE_NAME,null,CV);
        return exec;
    }

    public String CheckUserLoginDetails(String UserEnterUsername,String UserEnteredPassword){
        String SelectedType="null";
        SQLiteDatabase DB= getWritableDatabase();
        String [] Projection ={
                DatabaseMaster.User.COLUMN_NAME_PASSWORD,
                DatabaseMaster.User.COLUMN_NAME_TYPE
        };
        String Selection =DatabaseMaster.User.COLUMN_NAME_USERNAME +" Like ?";
        String [] args= { UserEnterUsername };

        Cursor cursor=DB.query(
                DatabaseMaster.User.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );

        while(cursor.moveToNext()){
            String PasswordIntheDatabse=cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.User.COLUMN_NAME_PASSWORD));
            if(PasswordIntheDatabse.equals(UserEnteredPassword)){

               String MyType=cursor.getString(cursor.getColumnIndexOrThrow( DatabaseMaster.User.COLUMN_NAME_TYPE));
                SelectedType=MyType;
                return SelectedType;


            }
        }
        return SelectedType;

    }

    public long SaveMessage (String Username, String msg, String Subject){
        SQLiteDatabase DB = getWritableDatabase();
        ContentValues CV= new ContentValues();
        CV.put(DatabaseMaster.Message.COLUMN_NAME_USERNAME,Username);
        CV.put(DatabaseMaster.Message.COLUMN_NAME_MESSAGE,msg);
        CV.put(DatabaseMaster.Message.COLUMN_NAME_SUBJECT,Subject);
        long exec=DB.insert(DatabaseMaster.Message.TABLE_NAME,null,CV);
        return exec;

    }

    public List GetAllSubject(){
        SQLiteDatabase DB= getReadableDatabase();
        String [] Projection ={
                DatabaseMaster.Message.COLUMN_NAME_SUBJECT
        };

        Cursor cursor= DB.query(
                DatabaseMaster.Message.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null
        );

        List MyAllSubjectIntheDatbase= new ArrayList();

        while(cursor.moveToNext()){
            MyAllSubjectIntheDatbase.add(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COLUMN_NAME_SUBJECT)));

        }
        return MyAllSubjectIntheDatbase;

    }
    public List GetMEssageDeatils(String ID){
        SQLiteDatabase DB= getReadableDatabase();
        String [] Projection ={
                DatabaseMaster.Message.COLUMN_NAME_SUBJECT,
                DatabaseMaster.Message.COLUMN_NAME_MESSAGE

        };

        String Selection = DatabaseMaster.Message._ID + " Like ?";
        String [] args ={ ID };

        Cursor cursor= DB.query(
                DatabaseMaster.Message.TABLE_NAME,
                Projection,
                Selection,
                args,
                null,
                null,
                null
        );

        List MyMessageDetauils= new ArrayList();

        while(cursor.moveToNext()){
            MyMessageDetauils.add(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COLUMN_NAME_SUBJECT)));
            MyMessageDetauils.add(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseMaster.Message.COLUMN_NAME_MESSAGE)));

        }
        return MyMessageDetauils;

    }



}
