package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.location.Address;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class NewPatient extends AppCompatActivity {
    Place place;
    String email;
    PatientDatabase mydb;
    private Spinner spinner1,spinner2;
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
      //  mydb=new PatientDatabase(this);
        Button button=(Button)findViewById(R.id.submit_button1);
        Button button1=(Button)findViewById(R.id.map_but1);



        spinner1 = (Spinner) findViewById(R.id.spinner);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Specialization_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
    ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.Gender_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPlaceAutocompleteActivityIntent();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = spinner1.getSelectedItem().toString();
                final String text2 = spinner2.getSelectedItem().toString();
                EditText editText=(EditText) findViewById(R.id.name_pat_new);
                String new_name=editText.getText().toString();
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

                if(TextUtils.isEmpty(new_name)||TextUtils.isEmpty(address) ||TextUtils.isEmpty(email)  || TextUtils.isEmpty(age) || TextUtils.isEmpty(num)|| TextUtils.isEmpty(text2))
                {
                    Toast.makeText(NewPatient.this,"Fill all details before submitting", Toast.LENGTH_SHORT).show();
                }
                else {

                                                                                              }
                      Intent intent = new Intent(NewPatient.this, Temp.class);
                    Bundle bundle = new Bundle();
//                    bundle.putString("name", new_name);
//                    bundle.putString("gender", text2);
//                    bundle.putString("age", age);
//                    bundle.putString("add", address);
//                    bundle.putString("email", email);
//                    bundle.putString("phone", num);
                    bundle.putString("specialization", text);

                    intent.putExtras(bundle);

                    startActivity(intent);
                   //}

                }
            //}
        });
    }
}
