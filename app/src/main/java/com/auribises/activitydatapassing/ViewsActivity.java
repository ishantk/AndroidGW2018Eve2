package com.auribises.activitydatapassing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ViewsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    // Declare References to the Views
    CheckBox cbCpp, cbJava, cbPython;
    RadioButton rbMale, rbFemale;

    Spinner spCity;
    ArrayAdapter<String> adapter;

    EditText eTxtName;
    Button btnSubmit;

    void initViews(){
        cbCpp = findViewById(R.id.checkBoxCpp);
        cbJava = findViewById(R.id.checkBoxJava);
        cbPython = findViewById(R.id.checkBoxPython);

        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);

        spCity = findViewById(R.id.spinner);

        eTxtName = findViewById(R.id.editTextName);

        btnSubmit = findViewById(R.id.buttonSubmit);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item);
        adapter.add("==Select City==");
        adapter.add("Ludhiana");
        adapter.add("Chandigarh");
        adapter.add("Delhi");
        adapter.add("Bengaluru");
        adapter.add("Hyderabad");

        spCity.setAdapter(adapter);


        cbCpp.setOnCheckedChangeListener(this);
        cbJava.setOnCheckedChangeListener(this);
        cbPython.setOnCheckedChangeListener(this);
        rbMale.setOnCheckedChangeListener(this);
        rbFemale.setOnCheckedChangeListener(this);


        // Event Handling using Anonymous Class
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String city = adapter.getItem(position);
                Toast.makeText(ViewsActivity.this,"You Selected: "+city,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        initViews();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int id = buttonView.getId();

        switch (id){
            case R.id.checkBoxCpp:

                if(isChecked){
                    Toast.makeText(this,"You Checked C++",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"You UnChecked C++",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.radioButtonMale:

                if(isChecked){
                    Toast.makeText(this,"You Selected Male",Toast.LENGTH_LONG).show();
                }

                break;

        }

    }
}
