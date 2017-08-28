package com.example.siddh.advancedsqlite;

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

            SQLiteDatabase userDatabase =  this.openOrCreateDatabase("Users", MODE_PRIVATE, null);

            userDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers  (name VARCHAR, age INTEGER(3), id INTEGER PRIMARY KEY)");
            //created users twice
            //userDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Siddharth', 19)");
            //userDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES ('Arpit', 20)");

            userDatabase.execSQL("DELETE FROM newUsers WHERE id = 4");

            Cursor c = userDatabase.rawQuery("SELECT * FROM newUsers", null);

            int nameIndex = c.getColumnIndex("name");
            int ageIndex = c.getColumnIndex("age");
            int idIndex = c.getColumnIndex("id");

            c.moveToFirst();

            while (c != null) {

                Log.i("User - name", c.getString(nameIndex));
                Log.i("User - age", Integer.toString(c.getInt(ageIndex)));
                Log.i("User - id", Integer.toString(c.getInt(idIndex)));

                c.moveToNext();

            }

        }
        catch (Exception e) {

            e.printStackTrace();

        }

    }
}
