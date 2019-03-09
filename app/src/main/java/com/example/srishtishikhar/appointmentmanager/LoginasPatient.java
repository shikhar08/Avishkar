package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginasPatient extends AppCompatActivity {
String name,phoneno;
PatientDatabase mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginas_patient);

        Button button=(Button) findViewById(R.id.pat_submit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText x=(EditText)findViewById(R.id.Pat_login);
                name=x.getText().toString();
                EditText y=(EditText)findViewById(R.id.pat_password);
                phoneno=y.getText().toString();

                mydb= new PatientDatabase(LoginasPatient.this);
                Cursor res=mydb.queryCommand();
                if(res.getCount()==0)
                    return;
                while (res.moveToNext()){
                    String n=res.getString(1);
                    String p=res.getString(5);
                    String age = res.getString(3);
                    if(name.equalsIgnoreCase(n)&& phoneno.equalsIgnoreCase(p)) {
                        Intent i= new Intent(LoginasPatient.this, afterSignUp.class);
                        Bundle bundle=new Bundle();
                        bundle.putString("patN",n);
                        bundle.putString("patA",age);
                        bundle.putString("patM",p);
                        i.putExtras(bundle);
                        startActivity(i);
                    }

                    else if(!(name.equalsIgnoreCase(n)&& phoneno.equalsIgnoreCase(p))) {
                        Toast.makeText(LoginasPatient.this,"INVALID CREDENTIALS",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Button button1=(Button)findViewById(R.id.button3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginasPatient.this,NewPatient.class);
                startActivity(intent);
            }
        });
    }
}
