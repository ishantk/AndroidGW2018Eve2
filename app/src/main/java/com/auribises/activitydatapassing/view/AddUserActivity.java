package com.auribises.activitydatapassing.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.auribises.activitydatapassing.R;
import com.auribises.activitydatapassing.Util;
import com.auribises.activitydatapassing.model.User;

public class AddUserActivity extends AppCompatActivity {

    EditText eTxtName,eTxtPhone,eTxtEmail;

    User user;

    ContentResolver resolver;

    void initViews(){
        eTxtName = findViewById(R.id.editTextName);
        eTxtPhone = findViewById(R.id.editTextPhone);
        eTxtEmail = findViewById(R.id.editTextEmail);

        user = new User();

        resolver = getContentResolver();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initViews();
    }

    void addUser(){

        ContentValues values = new ContentValues();
        values.put(Util.COL_NAME,user.name);
        values.put(Util.COL_PHONE,user.phone);
        values.put(Util.COL_EMAIL,user.email);

        Uri uri = resolver.insert(Util.USER_URI,values);
        Toast.makeText(this,user.name+" Added "+uri.getLastPathSegment(),Toast.LENGTH_LONG).show();

    }

    public void clickHandler(View view){
        user.name = eTxtName.getText().toString();
        user.phone = eTxtPhone.getText().toString();
        user.email = eTxtEmail.getText().toString();

        addUser();
    }
}

/*
Ishants-Air:~ ishantkumar$ cd Library/Android/sdk/platform-tools/
Ishants-Air:platform-tools ishantkumar$ pwd
/Users/ishantkumar/Library/Android/sdk/platform-tools
Ishants-Air:platform-tools ishantkumar$ ./adb root
restarting adbd as root
Ishants-Air:platform-tools ishantkumar$ ./adb shell
generic_x86:/ # cd data/data/com.auribises.activitydatapassing
generic_x86:/data/data/com.auribises.activitydatapassing # ls
cache code_cache databases
generic_x86:/data/data/com.auribises.activitydatapassing # cd databases
generic_x86:/data/data/com.auribises.activitydatapassing/databases # ls
Users.db Users.db-journal
generic_x86:/data/data/com.auribises.activitydatapassing/databases # sqlite3 Users.db
SQLite version 3.19.4 2017-08-18 19:28:12
Enter ".help" for usage hints.
sqlite> .tables
User              android_metadata
sqlite> select * from User;
1|John|99999 88888|john@example.com
2|Jennie|77777 88888|jennie@example.com
sqlite>

 */
