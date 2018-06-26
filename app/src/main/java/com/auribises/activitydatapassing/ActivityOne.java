package com.auribises.activitydatapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
            pRef.name = eTxtName.getText().toString();
            pRef.email = eTxtEmail.getText().toString();

            Toast.makeText(this,"Person Details: "+pRef,Toast.LENGTH_LONG).show();

            // Navigate from One Activity to the Other
            Intent intent = new Intent(ActivityOne.this,ActivityTwo.class);

            //1. Put the data directly into Intent
            //intent.putExtra("keyName",pRef.name);
            //intent.putExtra("keyEmail",pRef.email);

            //2. Put the data into Bundle
            /*Bundle bundle = new Bundle();
            bundle.putString("keyName",pRef.name);
            bundle.putString("keyEmail",pRef.email);

            intent.putExtra("keyBundle",bundle);*/

            //3. Pass User Defined Object
            intent.putExtra("keyPerson",pRef);

            startActivity(intent);

        }

    }
}
