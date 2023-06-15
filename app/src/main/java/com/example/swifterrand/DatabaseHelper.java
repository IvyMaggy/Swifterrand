package com.example.swifterrand;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "swift_errands.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_CITY = "city";

    private static final String TABLE_NAME_NOTIFICATIONS = "notifications";
    private static final String COLUMN_NOTIFICATION_ID = "notification_id";
    private static final String COLUMN_NOTIFICATION_TEXT = "notification_text";

    private static final String TABLE_NAME_MESSAGES = "messages";
    private static final String COLUMN_MESSAGE_ID = "message_id";
    private static final String COLUMN_MESSAGE_TEXT = "message_text";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME+ "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " VARCHAR,"
                + COLUMN_EMAIL + " VARCHAR,"
                + COLUMN_PASSWORD + " VARCHAR,"
                + COLUMN_PHONE + " VARCHAR,"
                + COLUMN_CITY + " TEXT"
                + ")";
        db.execSQL(createTableQuery);

        // Create notifications table
        String createNotificationTableQuery = "CREATE TABLE " + TABLE_NAME_NOTIFICATIONS + "("
                + COLUMN_NOTIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTIFICATION_TEXT + " TEXT"
                + ")";
        db.execSQL(createNotificationTableQuery);

        // Create messages table
        String createMessageTableQuery = "CREATE TABLE " + TABLE_NAME_MESSAGES + "("
                + COLUMN_MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MESSAGE_TEXT + " TEXT"
                + ")";
        db.execSQL(createMessageTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_NOTIFICATIONS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MESSAGES);
            onCreate(db);
        }
    }

    public long insertUser(String user_name, String email, String password, String id_No, String phone, String city ) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, id_No);
        values.put(COLUMN_NAME, user_name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_CITY, city);

        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();

        return newRowId;
    }

    public long insertNotification(String notificationText) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTIFICATION_TEXT, notificationText);

        long newRowId = db.insert(TABLE_NAME_NOTIFICATIONS, null, values);
        db.close();

        return newRowId;
    }

    public long insertMessage(String messageText) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MESSAGE_TEXT, messageText);

        long newRowId = db.insert(TABLE_NAME_MESSAGES, null, values);
        db.close();

        return newRowId;
    }

    public boolean checkUserCredentials(String email, String password) {
        return false;
    }


}

