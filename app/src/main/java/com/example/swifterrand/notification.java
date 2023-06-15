package com.example.swifterrand;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class notification extends AppCompatActivity {
    private ListView notificationListView;
    private ArrayAdapter<String> notificationAdapter;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notificationListView = findViewById(R.id.notificationListView);
        databaseHelper = new DatabaseHelper(this);

        List<String> notifications = new ArrayList<>();
        notifications.add("Notification 1");
        notifications.add("Notification 2");
        notifications.add("Notification 3");

        notificationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notifications);
        notificationListView.setAdapter(notificationAdapter);

        retrieveNotifications();
    }

    private void retrieveNotifications() {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String TABLE_NAME_NOTIFICATIONS = "notificationsText";
        String[] columns = {"text"};

        Cursor cursor = db.query(TABLE_NAME_NOTIFICATIONS, columns, null, null, null, null, null);


        if (cursor != null && cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndexOrThrow("text"));


                notificationAdapter.add(title);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
    }
}



