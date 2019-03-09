package com.example.srishtishikhar.appointmentmanager;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

public class dialogClass extends AppCompatDialogFragment {

    public  dialogClass(){
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.dialogbox,null);
        Bundle bundle = this.getArguments();
        String doc_name = bundle.getString("doctor_name");
        String doc_ID = bundle.getString("doctor_id");
        String pat_name = bundle.getString("patient_name");
        String patient_num = bundle.getString("patient_num");
        int patient_age = bundle.getInt("patient_age");


        builder.setView(view).setTitle("CONFIRMATION").setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                

            }
        });
        return builder.create();
    }
    public interface work{
        void func();
    }
}
