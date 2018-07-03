package com.auribises.activitydatapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity implements View.OnClickListener{

    // Declare References to Views
    EditText eTxtName, eTxtEmail;
    Button btnSubmit;

    Person pRef; // HAS-A Relation

    // User defined function, which can be anyname of your choice
    void initViews(){

        getSupportActionBar().setTitle("Welcome");

        pRef = new Person();

        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_one);

        // call initViews after setContentView
        initViews();

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonSubmit){

            // Extracting the data from UI and setting it into Model
            //pRef.name = eTxtName.getText().toString();
            //pRef.email = eTxtEmail.getText().toString();

            //Toast.makeText(this,"Person Details: "+pRef,Toast.LENGTH_LONG).show();

            // Navigate from One Activity to the Other
            //Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

            //1. Put the data directly into Intent
            //intent.putExtra("keyName",pRef.name);
            //intent.putExtra("keyEmail",pRef.email);

            //2. Put the data into Bundle
            /*Bundle bundle = new Bundle();
            bundle.putString("keyName",pRef.name);
            bundle.putString("keyEmail",pRef.email);

            intent.putExtra("keyBundle",bundle);*/

            //3. Pass User Defined Object
            //intent.putExtra("keyPerson",pRef);
            //startActivity(intent);

            Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);
            startActivityForResult(intent,101);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode == 201){
            String name = data.getStringExtra("keyName");
            String email = data.getStringExtra("keyEmail");

            eTxtName.setText(name);
            eTxtEmail.setText(email);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Explicit Menu Creation
        menu.add(1,101,1,"All Songs");
        menu.add(1,102,1,"Artists");
        menu.add(1,103,1,"Favourites");
        menu.add(1,104,1,"Albums");
        menu.add(1,105,1,"Recently Played");

        // Implicit Menu Creation
        //getMenuInflater().inflate(R.menu.mymenu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case 101:
                Toast.makeText(this,"You Clicked All Songs",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(ActivityOne.this,AllSongsActivity.class);
                startActivity(intent);

                break;

            case 102:
                Intent intent1 = new Intent(ActivityOne.this,NewsActivity.class);
                startActivity(intent1);
                break;

            case 103:

                break;

            case 104:

                break;

            case 105:

                break;

            case R.id.addNumbers:
                Toast.makeText(this,"You Clicked Add Numbers",Toast.LENGTH_LONG).show();
                break;
            case R.id.mulNumbers:
                Toast.makeText(this,"You Clicked Multiply Numbers",Toast.LENGTH_LONG).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
