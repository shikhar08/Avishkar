package com.example.srishtishikhar.appointmentmanager;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;


public class NewDoctor extends AppCompatActivity {
    String new_name, lic, spec, qual, address, fee, phone, start_hour,start_min, end_hour, end_min, start_ampm, end_ampm,start_time,end_time;
    Place place;
//    Spinner spinner1;
//    Spinner spinner2;
//    Spinner spinner3;
//    Spinner spinner4;

    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 100;

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
        //  mydb = new DatabaseHelper(this);
        final FirebaseDatabase fb = FirebaseDatabase.getInstance();
        final DatabaseReference db = fb.getReference("doctors");
        final FirebaseDatabase fb1 = FirebaseDatabase.getInstance();
        final DatabaseReference db1 = fb1.getReference("Specialisations");
        Button button = (Button) findViewById(R.id.submit_button);
        Button button1 = (Button) findViewById(R.id.map_but);
//        spinner1 = (Spinner) findViewById(R.id.spinner1);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.start_time, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner1.setAdapter(adapter);
//
//        spinner2 = (Spinner) findViewById(R.id.spinner2);
//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_item);
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner2.setAdapter(adapter2);
//
//        spinner3 = (Spinner) findViewById(R.id.spinner3);
//        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.end_time, android.R.layout.simple_spinner_item);
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner3.setAdapter(adapter3);
//
//        spinner4 = (Spinner) findViewById(R.id.spinner4);
//        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.minutes, android.R.layout.simple_spinner_item);
//        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner4.setAdapter(adapter4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPlaceAutocompleteActivityIntent();
            }
        });
        final EditText sth = (EditText) findViewById(R.id.start_hour);
        sth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent i= new Intent(NewDoctor.this,Time.class);
               // startActivity(i);
               // finish();
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewDoctor.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//                        EditText sth = (EditText) findViewById(R.id.start_hour);
//                        sth.setText(hourOfDay);
//                        start_hour = sth.getText().toString();
//
//                        EditText stm = (EditText) findViewById(R.id.start_min);
//                        stm.setText(minutes);
//                        start_min = stm.getText().toString();
//                        EditText sampm = (EditText) findViewById(R.id.start_ampm);
//                        sampm.setText(format);
//                        start_ampm= sampm.getText().toString();
                       // start_hour= getString(hourOfDay);
                        //start_min= getString(minutes);
                        sth.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();

                //String start_time=start_hour+":"+start_min;


            }
        });
        final EditText et = (EditText) findViewById(R.id.end_hour);
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent i= new Intent(NewDoctor.this,Time.class);
                // startActivity(i);
                // finish();
                TimePickerDialog timePickerDialog = new TimePickerDialog(NewDoctor.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
//
                        et.setText(hourOfDay + ":" + minutes);
                    }
                }, 0, 0, false);
                timePickerDialog.show();
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
               // int feee = Integer.parseInt(fee);

                EditText phn = (EditText) findViewById(R.id.editText4);
                phone = phn.getText().toString();

//                Intent i = getIntent();
//                Bundle getBundle = getIntent().getExtras();
//                final String sh = getBundle.getString("hour");
//                final String sm = getBundle.getString("minute");
//                final String format = getBundle.getString("format");

//                EditText sth = (EditText) findViewById(R.id.start_hour);
//                sth.setText(sh);
            start_hour = sth.getText().toString();
//
//                EditText stm = (EditText) findViewById(R.id.start_min);
//                stm.setText(sm);
//                start_min = stm.getText().toString();
//                EditText sampm = (EditText) findViewById(R.id.start_ampm);
//                sampm.setText(format);
//                start_ampm= sampm.getText().toString();
//
//                String start_time=start_hour+":"+start_min+start_ampm;

               // EditText sth = (EditText) findViewById(R.id.start_hour);
                       //sth.setText(start_hour);

//                       EditText stm = (EditText) findViewById(R.id.start_min);
//                       stm.setText(start_min);
//
//                Intent i1= getIntent();
//                Bundle getBundle1 = getIntent().getExtras();
//                final String eh = getBundle1.getString("hour");
//                final String em = getBundle1.getString("minute");
//                final String eformat = getBundle1.getString("format");
//
//                EditText eth = (EditText) findViewById(R.id.end_hour);
//                eth.setText(eh);
                 end_hour= et.getText().toString();
//
//                EditText etm = (EditText) findViewById(R.id.end_min);
//                etm.setText(em);
//               end_min= etm.getText().toString();
//                EditText eampm = (EditText) findViewById(R.id.end_ampm);
//                eampm.setText(eformat);
//                end_ampm= eampm.getText().toString();
//
//
//
//                String end_time=end_hour+":"+end_min+end_ampm;


//                final String starthour = spinner1.getSelectedItem().toString();
//                final String startminute = spinner2.getSelectedItem().toString();
//                final String endhour = spinner3.getSelectedItem().toString();
//                final String endminute = spinner4.getSelectedItem().toString();

                address = place.getAddress().toString();
                float latitude = (float) place.getLatLng().latitude;
                float longitude = (float) place.getLatLng().longitude;
                String lat=Float.toString(latitude);
                String lon=Float.toString(longitude);

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(new_name) || TextUtils.isEmpty(lic) || TextUtils.isEmpty(spec) || TextUtils.isEmpty(qual) || TextUtils.isEmpty(fee) || TextUtils.isEmpty(address)) {
                    Toast.makeText(NewDoctor.this, "Fill all details before submitting", Toast.LENGTH_SHORT).show();

                } else {
                    //boolean check = false;
                    if (!address.isEmpty()) {
                        //check = mydb.InsertCommand(lic, new_name, spec, qual, feee, address, phone, Integer.parseInt(starthour), Integer.parseInt(startminute), Integer.parseInt(endhour), Integer.parseInt(endminute), latitude, longitude);

                        /*db.child("Licence No.").push().setValue(lic);
                        db.child("Name").push().setValue(new_name);
                        db.child("Specialisation").push().setValue(spec);
                        db.child("Qualification").push().setValue(qual);
                        db.child("Fees").push().setValue(fee);
                        db.child("Address").push().setValue(address);
                        db.child("Contact Number").push().setValue(phone);
*/

                        //String doc= db.push();
                        db.child(lic).push();
                        String doc=lic;
                        //Doctors newdoc = new Doctors(new_name,lic,address,spec,Integer.parseInt(fee),qual,phone,Integer.parseInt(start_time),Integer.parseInt(end_time));
                        db.child(doc).child("Name").setValue(new_name);
                        db.child(doc).child("LicenceNumber").setValue(lic);
                        db.child(doc).child("Qualification").setValue(qual);
                        db.child(doc).child("Address").setValue(address);
                        db.child(doc).child("Fees").setValue(fee);
                        db.child(doc).child("Specialisation").setValue(spec);
                        db.child(doc).child("Phone").setValue(phone);
                        db.child(doc).child("Start Time").setValue(start_hour);
                        db.child(doc).child("End Time").setValue(end_hour);
                        db1.child(spec).push();
                        db1.child(spec).child(lic).setValue(new_name);



//                        db.child(doc).child("Latitude").setValue(lat);
//                        db.child(doc).child("Longitude").setValue(lon);

                      //  db.child(doc).child("Na").setValue(new_name);
                     //   db.child(doc).child("Name").setValue(new_name);
                       // db.child(doc).child("Name").setValue(new_name);



                       // if (check == true) {
                            Toast.makeText(NewDoctor.this, "Your Registration was successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewDoctor.this, LoginasDoctor.class);
                            startActivity(intent);
                            finish();
                       // }
                        //else {
                         //   Toast.makeText(NewDoctor.this, "Your Registration was not successful", Toast.LENGTH_SHORT).show();
                       // }
                    }
                }


            }




        });
    }
}