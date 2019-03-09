package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.location.Address;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.w3c.dom.Text;

public class NewPatient extends AppCompatActivity {
    Place place;
    String email;
    PatientDatabase mydb;
    float longi,lati;
String address;
public static int PLACE_AUTOCOMPLETE_REQUEST_CODE=101;
    private void callPlaceAutocompleteActivityIntent() {
        try {
            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .build(this);
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            //PLACE_AUTOCOMPLETE_REQUEST_CODE is integer for request code
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //autocompleteFragment.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                 place = PlaceAutocomplete.getPlace(this, data);
                Log.e( "Place:", place.toString());
                address = place.getAddress().toString();
                lati = (float) place.getLatLng().latitude;
                longi = (float) place.getLatLng().longitude;
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
        setContentView(R.layout.activity_new_patient);
        mydb=new PatientDatabase(this);
        Button button=(Button)findViewById(R.id.submit_button1);
        Button button1=(Button)findViewById(R.id.map_but1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPlaceAutocompleteActivityIntent();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText=(EditText) findViewById(R.id.name_pat_new);
                String new_name=editText.getText().toString();

                EditText Gender=(EditText)findViewById(R.id.Gender);
                String gender=Gender.getText().toString();

                EditText Age=(EditText)findViewById(R.id.age);
                String age=Age.getText().toString();
                int age1=Integer.parseInt(age);

                address=place.getAddress().toString();
                TextView msit = (TextView) findViewById(R.id.location);
                msit.setText(address);

                EditText NUM=(EditText)findViewById(R.id.Pho_new);
                String num=NUM.getText().toString();

                EditText mail=(EditText)findViewById(R.id.email);
                String email=mail.getText().toString();

                if(TextUtils.isEmpty(new_name)||TextUtils.isEmpty(address) ||TextUtils.isEmpty(email)  || TextUtils.isEmpty(age) || TextUtils.isEmpty(num)|| TextUtils.isEmpty(gender))
                {
                    Toast.makeText(NewPatient.this,"Fill all details before submitting", Toast.LENGTH_SHORT).show();

                }

                else {
                   //boolean check=
                           mydb.InsertCommand(new_name,gender,age1,address,num,lati,longi,email);
                   //if(check==true) {
                        Toast.makeText(NewPatient.this, "Your Registration was successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewPatient.this, LoginasPatient.class);
                        startActivity(intent);
                   //}
                }
            }
        });
    }
}
