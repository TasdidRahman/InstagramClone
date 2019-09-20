package com.tasdid.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnsave =findViewById(R.id.btnSave);
        btnsave.setOnClickListener(SignUp.this);

        edtName =findViewById(R.id.editName);
        edtPunchSpeed =findViewById(R.id.edtPunchPower);
        edtPunchPower =findViewById(R.id.edtPunchPower);
        edtKickSpeed =findViewById(R.id.edtKickSpeed);
        edtKickPower =findViewById(R.id.edtKickPower);

    }

//    public void helloWorldTapped(View view){
//
////        ParseObject boxer = new ParseObject("Boxer");
////        boxer.put("punch_speed",200);
////        boxer.saveInBackground(new SaveCallback() {
////            @Override
////            public void done(ParseException e) {
////
////                if(e==null){
////
////                    Toast.makeText(SignUp.this,"boxer object is saved successfully", Toast.LENGTH_LONG).show();
////                }
////            }
////        });
//
//
//    }

    @Override
    public void onClick(View view) {
        try {
            final ParseObject kickBoxer = new ParseObject("kickBoxer");
            kickBoxer.put("name", edtName.getText().toString());
            kickBoxer.put("punch_speed", Integer.parseInt(edtPunchSpeed.getText().toString()));
            kickBoxer.put("punch_power", Integer.parseInt(edtPunchPower.getText().toString()));
            kickBoxer.put("kick_speed", Integer.parseInt(edtKickSpeed.getText().toString()));
            kickBoxer.put("kick_power", Integer.parseInt(edtKickPower.getText().toString()));


            kickBoxer.saveInBackground(new SaveCallback() {

                @Override
                public void done(ParseException e) {

                    if (e == null) {

                        FancyToast.makeText(SignUp.this, kickBoxer.get("name") + " is saved to server", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {

                        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                    }
                }
            });

        }catch (Exception e){

            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


        }

    }
}
