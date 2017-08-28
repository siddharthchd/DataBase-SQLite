package com.example.siddh.databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase eventsDatabase = this.openOrCreateDatabase("Events", MODE_PRIVATE, null);
            eventsDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (event VARCHAR, year INT(4))");
            eventsDatabase.execSQL("INSERT INTO events (event, year) VALUES ('End of WWII', 1945)");
            eventsDatabase.execSQL("INSERT INTO events (event, year) VALUES ('Indian Independence', 1947)");

            Cursor c = eventsDatabase.rawQuery("SELECT * FROM events", null);

            int eventIndex = c.getColumnIndex("event");
            int yearIndex = c.getColumnIndex("year");

            c.moveToFirst();

            while(c != null) {

                Log.i("Results - event", c.getString(eventIndex));
                Log.i("Results - year", Integer.toString(c.getInt(yearIndex)));

                c.moveToNext();

            }


        }
        catch (Exception e) {

            Log.i("Error", "Catch exception error");
            e.printStackTrace();

        }

    }
}
