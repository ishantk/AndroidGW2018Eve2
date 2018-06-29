package com.auribises.activitydatapassing;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class AllSongsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ArrayAdapter<String> adapter;

    void initViews(){
        listView = findViewById(R.id.listView);

        String sdCardPath = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this,"Path is: "+sdCardPath,Toast.LENGTH_LONG).show();

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1);

        /*adapter.add("Song A"); //0
        adapter.add("Song B");
        adapter.add("Song C");
        adapter.add("Song D");
        adapter.add("Song E"); //n-1*/

        // java.io
        File file = new File(sdCardPath);
        if(file.isDirectory()){
            String[] names = file.list();
            for(String nm : names){
                if(nm.endsWith(".mp3")) {
                    adapter.add(nm);
                }
            }
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_songs);
        initViews();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String songName = adapter.getItem(position);
        Toast.makeText(this,"Song Is: "+songName,Toast.LENGTH_LONG).show();
    }
}
