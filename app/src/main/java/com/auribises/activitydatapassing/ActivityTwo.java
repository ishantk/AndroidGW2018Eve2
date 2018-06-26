package com.auribises.activitydatapassing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityTwo extends AppCompatActivity implements View.OnClickListener{

    // Declare References to Views
    EditText eTxtName, eTxtEmail;
    Button btnBack;

    void initViews(){

        eTxtName = findViewById(R.id.editTextName);
        eTxtEmail = findViewById(R.id.editTextEmail);

        btnBack = findViewById(R.id.buttonBack);

        btnBack.setOnClickListener(this);

        //Intent rcv = getIntent();

        /*if(rcv.hasExtra("keyName") && rcv.hasExtra("keyEmail")) {

            String name = rcv.getStringExtra("keyName");
            String email = rcv.getStringExtra("keyEmail");

            eTxtName.setText(name);
            eTxtEmail.setText(email);
        }*/

        /*if(rcv.hasExtra("keyBundle")) {

            Bundle rcvBun = rcv.getBundleExtra("keyBundle");

            String name = rcvBun.getString("keyName");
            String email = rcvBun.getString("keyEmail");

            eTxtName.setText(name);
            eTxtEmail.setText(email);
        }*/

        /*Person person = (Person)rcv.getSerializableExtra("keyPerson");
        eTxtName.setText(person.name);
        eTxtEmail.setText(person.email);*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        initViews();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonBack){

            String name = eTxtName.getText().toString();
            String email = eTxtEmail.getText().toString();

            Intent data = new Intent(); // Empty Intent, No Source No Dest.
            // This intent is used to hold only data
            data.putExtra("keyName",name);
            data.putExtra("keyEmail",email);

            setResult(201,data);

            finish();
        }
    }
}
