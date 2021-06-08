package com.example.accountbook;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton addbutton;
    Button intentbutton;

    String name;
    String type;
    int year;
    int month;
    int day;
    int money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addbutton = (ImageButton)findViewById(R.id.AddButton);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddDialog addDialog = new AddDialog(MainActivity.this);
                addDialog.show();
            }
        });

        intentbutton = (Button)findViewById(R.id.intentButton);
        intentbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    IncomeDBManager incomeDBManager = new IncomeDBManager(MainActivity.this);
                    SQLiteDatabase database1 = incomeDBManager.getReadableDatabase();
                    Cursor cursor = database1.rawQuery("SELECT * FROM Income order by year desc, month desc, day desc, money desc", null);
                    while (cursor.moveToNext()) {
                        year = cursor.getInt(cursor.getColumnIndex("year"));
                        month = cursor.getInt(cursor.getColumnIndex("month"));
                        day = cursor.getInt(cursor.getColumnIndex("day"));
                        money = cursor.getInt(cursor.getColumnIndex("money"));
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        System.out.println("year:" + year + "/month:" + month + "/day:" + day + "/money:" + money + "/name:" + name);
                    }
                    OutcomeDBManager outcomeDBManager = new OutcomeDBManager(MainActivity.this);
                    SQLiteDatabase database2 = outcomeDBManager.getReadableDatabase();
                    cursor = database2.rawQuery("SELECT * FROM Outcome order by year desc, month desc, day desc, money desc", null);
                    while (cursor.moveToNext()) {
                        year = cursor.getInt(cursor.getColumnIndex("year"));
                        month = cursor.getInt(cursor.getColumnIndex("month"));
                        day = cursor.getInt(cursor.getColumnIndex("day"));
                        money = cursor.getInt(cursor.getColumnIndex("money"));
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        type = cursor.getString(cursor.getColumnIndex("type"));
                        System.out.println("year:" + year + "/month:" + month + "/day:" + day + "/money:" + money + "/name:" + name+"/type:"+type);
                    }

                }catch (SQLException e){

                }
            }
        });

    }
}
