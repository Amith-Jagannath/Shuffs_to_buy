package com.example.shuffs_to_buy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  ItemClick {
    SQLiteDatabase db;
    private FloatingActionButton btn;
    private ArrayList<Stuffs> stuffList;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private StuffAdapter stuffAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHelper(this).getWritableDatabase();
        stuffList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        btn = findViewById(R.id.addStuffs);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),AddStuffs.class);
                startActivity(i);

            }
        });
        setupRecyclerView();



        Cursor cursor = db.rawQuery("SELECT * FROM Items", null);
        for(int i=0;i<cursor.getCount();i++) {
            cursor.moveToNext();
            String s = "";
            String id = cursor.getString(0);
            String name = cursor.getString(1);



            stuffList.add(new Stuffs(id, name));
            stuffAdapter = new StuffAdapter(stuffList, this);
            stuffAdapter.notifyDataSetChanged();
        }
    }

    private void setupRecyclerView() {

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        stuffAdapter = new StuffAdapter(stuffList,this);
        recyclerView.setAdapter(stuffAdapter);
    }

    @Override
    public void onclick(Stuffs expense) {
        Toast.makeText(this, expense.id, Toast.LENGTH_SHORT).show();

      db.execSQL("delete from Items where name='" +expense.id+"'");
       db.close();
            Intent inten = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(inten);
        }


}