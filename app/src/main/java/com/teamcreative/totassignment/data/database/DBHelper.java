package com.teamcreative.totassignment.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.teamcreative.totassignment.data.model.StudentInfo;

import java.util.ArrayList;
import java.util.List;

import static com.teamcreative.totassignment.data.AppConst.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    // Checking table has data or not
    public boolean hasData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);

        RESULT = cursor.getCount();
        cursor.close();
        db.close();
        if (RESULT > 0) return true;
        return false;
    }

    // Checking table has the given data or not
    public boolean isInserted(String value) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_PHONE + "=\"" + value.trim() + "\";", null);

        RESULT = cursor.getCount();
        cursor.close();
        db.close();

        if (RESULT > 0) return true;

        return false;
    }


    /**
     * add new student
     */
    public long addStudent(StudentInfo info) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_SL, info.getId());
        contentValues.put(KEY_NAME, info.getName());
        contentValues.put(KEY_INSTITUTION, info.getInstitution());
        contentValues.put(KEY_EMAIL, info.getEmail());
        contentValues.put(KEY_PHONE, info.getPhone());
        contentValues.put(KEY_GENDER, info.getGender());

        long inserted = db.insert(DATABASE_TABLE, null, contentValues);
        db.close();
        return inserted;
    }

    /**
     * return all student as a list
     */
    public List<StudentInfo> getStudentList() {
        List<StudentInfo> allStudent = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(SELECT_QUERY, null);
        if (cursor.moveToFirst()) {
            do {
                StudentInfo info = new StudentInfo();
                info.setId(cursor.getString(0));
                info.setName(cursor.getString(1));
                info.setInstitution(cursor.getString(2));
                info.setEmail(cursor.getString(3));
                info.setPhone(cursor.getString(4));
                info.setGender(cursor.getString(5));

                allStudent.add(info);

            } while (cursor.moveToNext());
        }
        return allStudent;
    }

    /**
     * update student
     */
    public int update(StudentInfo info) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, info.getName());
        contentValues.put(KEY_INSTITUTION, info.getInstitution());
        contentValues.put(KEY_EMAIL, info.getEmail());
        contentValues.put(KEY_PHONE, info.getPhone());
        contentValues.put(KEY_GENDER, info.getGender());

        int updated = db.update(DATABASE_TABLE, contentValues, KEY_SL + "=?", new String[]{info.getId()});
        db.close();
        return updated;
    }

    /**
     * delete student
     */
    public void deleteStudent(String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_SL + "=?", new String[]{position});
        db.close();
    }

    /**
     * Delete all student
     */
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        // Create tables again
        onCreate(db);
    }
}
