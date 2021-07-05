package com.example.sqliteoperations;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Data extends AppCompatActivity {
    ListView dataView;
    myDbAdapter helper;
    ArrayList<String> listaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        dataView= (ListView) findViewById(R.id.ListViewData);
        helper = new myDbAdapter(this);

        configureBackButton();
        loadData();

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDatos);
        dataView.setAdapter(adapter);

        onClickData();
    }

    private void loadData(){
        listaDatos = new ArrayList<String>();
        Cursor cursor = helper.getDataInCursor();
        while (cursor.moveToNext()){
            listaDatos.add(cursor.getString(1));
        }
    }

    private void configureBackButton(){
        Button btn = (Button) findViewById(R.id.BackBtn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void onClickData(){
        dataView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Data.this, listaDatos.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}