package com.example.harith.shak.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.harith.shak.MainActivity.locat;
import static com.example.harith.shak.MainActivity.status;
import static com.example.harith.shak.MainActivity.v;
import static com.example.harith.shak.MainActivity.h;
import static com.example.harith.shak.MainActivity.topic;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_LEC);
        db.execSQL(CREATE_TABLE_SHEKH);
        db.execSQL(CREATE_TABLE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    // Data Base Version
    private static final int DATABASE_VERSION = 1;

    // Data Base Name
    private static final String DATABASE_NAME = "myLec";

    // Table Names
    private static final String TABLE_LEC = "lec";
    private static final String TABLE_SHEKH = "shekh";
    private static final String TABLE_USERS = "users";

    // LEC Table field
    private static final String L_ID = "ID";
    private static final String TOPIC = "topic";
    private static final String TIME = "time";
    private static final String LOCAT = "locat";
    private static final String SH_ID = "sh_id";
    private static final String STATUS = "status";

    // SHEKH Table field
    private static final String S_ID = "ID";
    private static final String SHEKH_COLUMN_F_NAME = "f_name";
    private static final String SHEKH_COLUMN_L_NAME = "l_name";
    private static final String SHEKH_COLUMN_U_NAME = "user_name";
    private static final String SHEKH_COLUMN_PASSWORD = "password";

    // USERS Table field
    private static final String USERS_ID = "ID";
    private static final String USERS_COLUMN_NAME = "uname";
    private static final String USERS_COLUMN_PASS = "pass";
    private static final String USERS_COLUMN_PHONE = "phone";
    private static final String USERS_COLUMN_FOLLOW_ID = "follow_id";

    // SQL Create Lec Table
    private String CREATE_TABLE_LEC = "CREATE TABLE "
            + TABLE_LEC + "(" + L_ID + " INTEGER PRIMARY KEY,"
            + TOPIC + " TEXT NOT NULL,"
            + TIME + " TEXT NOT NULL,"
            + LOCAT + " INTEGER,"
            + SH_ID + " INTEGER,"
            + STATUS + " INTEGER"
            + ")";
    // SQL Create SHEKH Table
    private String CREATE_TABLE_SHEKH = "CREATE TABLE "
            + TABLE_SHEKH + "(" + S_ID + " INTEGER PRIMARY KEY,"
            + SHEKH_COLUMN_F_NAME + " TEXT NOT NULL,"
            + SHEKH_COLUMN_L_NAME + " TEXT NOT NULL,"
            + SHEKH_COLUMN_U_NAME + " TEXT,"
            + SHEKH_COLUMN_PASSWORD + " INTEGER"
            + ")";
    // SQL Create USERS Table
    private String CREATE_TABLE_USERS = "CREATE TABLE "
            + TABLE_USERS + "("
            + USERS_ID + " INTEGER PRIMARY KEY,"
            + USERS_COLUMN_NAME + " TEXT NOT NULL,"
            + USERS_COLUMN_PASS + " INTEGER NOT NULL,"
            + USERS_COLUMN_PHONE + " INTEGER,"
            + USERS_COLUMN_FOLLOW_ID + " INTEGER"
            + ")";


    //
    public void insertUser() {
        ContentValues values = new ContentValues();
        // for (int i = 1; i < JsonArray.length; i++) {
        values.put(USERS_COLUMN_NAME, topic);  // JSONString[i].getString("uname") == Mogtba
        values.put(USERS_COLUMN_PASS, status);      //  ..........................
        values.put(USERS_COLUMN_PHONE, status);
        values.put(USERS_COLUMN_FOLLOW_ID, status);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        //}
    }

    public ArrayList<Users> getAllUser() {

        Cursor cursor;
        ArrayList<Users> users = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            // get all column indexes
            int columnIndexID = cursor.getColumnIndex(USERS_ID);
            int columnIndexName = cursor.getColumnIndex(USERS_COLUMN_NAME);
            int columnPassIndex = cursor.getColumnIndex(USERS_COLUMN_PASS);
            int columnPhoneIndex = cursor.getColumnIndex(USERS_COLUMN_PHONE);
            int columnFollowindex = cursor.getColumnIndex(USERS_COLUMN_FOLLOW_ID);


            // get value from table using the index of the field
            String Name = cursor.getString(columnIndexName);
            int ID = cursor.getInt(columnIndexID);
            int Phone = cursor.getInt(columnPhoneIndex);
            int PassWord = cursor.getInt(columnPassIndex);
            int FollowId = cursor.getInt(columnFollowindex);

            users.add(new Users(ID, Name, PassWord, Phone, FollowId));

            cursor.moveToNext();
        }

        // users.add(new Users("1","Kalid",1324,123456,1));
        db.close();
        return users;
    }
}