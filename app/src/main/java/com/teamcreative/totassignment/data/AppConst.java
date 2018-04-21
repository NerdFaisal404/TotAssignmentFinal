package com.teamcreative.totassignment.data;

public class AppConst {
    public static final String DATABASE_NAME = "student.db";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "student";
    public static final String KEY_SL = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_INSTITUTION = "institution";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_GENDER = "gender";

    public static int RESULT = -1;

    /**
     * student insert query
     */
    public static final String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + "("
            + KEY_SL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NAME + " TEXT, "
            + KEY_INSTITUTION + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PHONE + " TEXT, "
            + KEY_GENDER + " TEXT ) ";

    public static final String SELECT_QUERY = "SELECT * FROM " + DATABASE_TABLE;
}
