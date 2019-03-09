package com.example.srishtishikhar.appointmentmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginasDoctor extends AppCompatActivity {
EditText ed,e2;
public DatabaseHelper mydb;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginas_doctor);
        ed=(EditText)findViewById(R.id.Id);
        e2=(EditText)findViewById(R.id.password);
        Button button=(Button)findViewById(R.id.doc_submit);
        Button button2=(Button)findViewById(R.id.newb);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginasDoctor.this, NewDoctor.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=ed.getText().toString();
                s2=e2.getText().toString();
                mydb=new DatabaseHelper(LoginasDoctor.this);
                Cursor res=mydb.queryCommand();
                  if(res.getCount()==0)
                      return;
                while (res.moveToNext()){
                    String ID=res.getString(0);
                    String NAME=res.getString(1);
                    if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2))
                    {
                        Toast.makeText(LoginasDoctor.this,"FILL ALL DETAILS",Toast.LENGTH_SHORT).show();

                    }
                     else
                    {
                         if(s1.equalsIgnoreCase(ID)&& s2.equalsIgnoreCase(NAME)) {
                          Intent i= new Intent(LoginasDoctor.this, DoctorHomepage.class);
                          Bundle bundle=new Bundle();
                          bundle.putString("id",s1);
                          i.putExtras(bundle);
                          startActivity(i);
                      }

                      else
                          {
                          Toast.makeText(LoginasDoctor.this,"INVALID CREDENTIALS",Toast.LENGTH_SHORT).show();
                      }
                    }
                }

            }
        });



    }
}
