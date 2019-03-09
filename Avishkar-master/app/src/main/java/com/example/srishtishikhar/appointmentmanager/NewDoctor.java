package com.example.srishtishikhar.appointmentmanager;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;
import android.text.format.DateFormat;


import com.example.srishtishikhar.appointmentmanager.DatabaseHelper;
import com.example.srishtishikhar.appointmentmanager.LoginasDoctor;
import com.example.srishtishikhar.appointmentmanager.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class NewDoctor extends AppCompatActivity  {
    String new_name, lic, spec, qual, address, fee,phone;
    Place place;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;

    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;
    DatabaseHelper mydb;

    private void callPlaceAutocompleteActivityIntent() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            //PLACE_AUTOCOMPLETE_REQUEST_CODE is integer for request code
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autocompleteFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                place = PlaceAutocomplete.getPlace(this, data);
                Log.e("Place:", place.toString());

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("Status", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor);
        mydb = new DatabaseHelper(this);
        Button button = (Button) findViewById(R.id.submit_button);
        Button button1 = (Button) findViewById(R.id.map_but);

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.start_time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2= (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.minutes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3= (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.end_time, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        spinner4= (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.minutes, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPlaceAutocompleteActivityIntent();
            }
        });



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.name_new);
                new_name = name.getText().toString();

                EditText id = (EditText) findViewById(R.id.Lic_new);
                lic = id.getText().toString();

                EditText specs = (EditText) findViewById(R.id.spec_new);
                spec = specs.getText().toString();

                EditText qualif = (EditText) findViewById(R.id.qual_new);
                qual = qualif.getText().toString();

                EditText fees = (EditText) findViewById(R.id.Fee_new);
                fee = fees.getText().toString();
                int feee = Integer.parseInt(fee);

                EditText phn= (EditText) findViewById(R.id.editText4);
                phone = phn.getText().toString();

                final String starthour  = spinner1.getSelectedItem().toString();
                final String startminute = spinner2.getSelectedItem().toString();
                final String endhour = spinner3.getSelectedItem().toString();
                final String endminute = spinner4.getSelectedItem().toString();

                address = place.getAddress().toString();
                float latitude= (float) place.getLatLng().latitude;
                float longitude= (float) place.getLatLng().longitude;
                if ( TextUtils.isEmpty(phone)|| TextUtils.isEmpty(new_name) || TextUtils.isEmpty(lic) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(qual) || TextUtils.isEmpty(fee) || TextUtils.isEmpty(address)) {
                    Toast.makeText(NewDoctor.this, "Fill all details before submitting", Toast.LENGTH_SHORT).show();

                } else {
                    boolean check = false;
                    // if (!address.isEmpty()) {

                    check = mydb.InsertCommand(lic, new_name, spec, qual, feee, address, phone, Integer.parseInt(starthour), Integer.parseInt(startminute), Integer.parseInt(endhour), Integer.parseInt(endminute), latitude, longitude);


                    if (check ==true) {
                        Toast.makeText(NewDoctor.this, "Your Registration was successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewDoctor.this, LoginasDoctor.class);
                        startActivity(intent);
                        finish();
                    }

                    else
                    {
                        Toast.makeText(NewDoctor.this, "Your Registration was not successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }



    });
  }
}
