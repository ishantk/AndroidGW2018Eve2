package com.auribises.activitydatapassing.view;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.auribises.activitydatapassing.R;
import com.auribises.activitydatapassing.Util;
import com.auribises.activitydatapassing.model.User;

import java.util.ArrayList;

public class AllUsersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView listView;
    ContentResolver resolver;
    ArrayList<User> users;
    ArrayList<String> userNames;

    ArrayAdapter<String> adapter;

    User user;

    int pos;


    void initViews(){
        listView = findViewById(R.id.listView);
        resolver = getContentResolver();
        users = new ArrayList<>();
        userNames = new ArrayList<>();
    }

    void queryUsers(){
        String[] projection = {Util.COL_ID,Util.COL_NAME,Util.COL_EMAIL,Util.COL_PHONE};

        Cursor cursor = resolver.query(Util.USER_URI,projection,null,null,null);

        if(cursor!=null && cursor.getCount()>0){



            while(cursor.moveToNext()){
                User user = new User();
                user.id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                user.name = cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                user.phone = cursor.getString(cursor.getColumnIndex(Util.COL_PHONE));
                user.email = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));

                users.add(user);

                userNames.add(user.name+"\n"+user.phone);
            }

            adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,userNames);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);

        }else{
            Toast.makeText(this,"No Data Found !!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);
        initViews();
        queryUsers();
    }

    void showUser(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Details of: "+user.name);
        builder.setMessage(user.id+"\n"+user.name+"\n"+user.email+"\n"+user.phone+"\n");
        builder.setPositiveButton("Done",null);
        builder.create().show();
    }

    void showOptions(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String[] items = {"View","Update","Delete"};
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (which){
                    case 0:

                        showUser();

                        break;

                    case 1:

                        Intent intent = new Intent(AllUsersActivity.this,AddUserActivity.class);
                        intent.putExtra("keyUser",user);
                        startActivity(intent);

                        break;

                    case 2:
                        askForDeletion();
                        break;
                }

            }
        });
        builder.create().show();
    }

    void askForDeletion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+user.name);
        builder.setMessage("Do You Wanna Delete ?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteUser();
            }
        });
        builder.setNegativeButton("Cancel",null);
        builder.create().show();
    }

    void deleteUser(){
        String where = Util.COL_ID+" = "+user.id;
        int i = resolver.delete(Util.USER_URI,where,null);
        Toast.makeText(this,user.name+" deleted ",Toast.LENGTH_LONG).show();

        users.remove(pos);
        userNames.remove(pos);

        adapter.notifyDataSetChanged(); // refresh the listView
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        user = users.get(position);

        pos = position;

        showOptions();



    }
}
