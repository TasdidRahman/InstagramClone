package com.tasdid.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.util.List;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button btnsave;
    private EditText edtName, edtPunchSpeed, edtPunchPower, edtKickSpeed, edtKickPower;
    private TextView txtGetData;

    private Button btnGetAllData;
    private String allKickBoxers;

    private Button btnTransition;


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
        txtGetData= findViewById(R.id.txtGetData);
        btnGetAllData= findViewById(R.id.btGetAllData);
        btnTransition= findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("kickBoxer");
                parseQuery.getInBackground("2ICPOhfa3N", new GetCallback<ParseObject>(){

                    @Override
                    public void done(ParseObject object, ParseException e) {

                        if(object !=null && e==null){

                            txtGetData.setText(object.get("name")+"");
                        }

                    }
                });


            }
        });

        btnGetAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                allKickBoxers="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("kickBoxer");
                queryAll.whereGreaterThan("punch_power",2000);

                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){

                            if(objects.size()>0){

                                for(ParseObject kickBoxers :objects){

                                    allKickBoxers = allKickBoxers + kickBoxers.get("name") +"\n";
                                }


                                FancyToast.makeText(SignUp.this, allKickBoxers, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, false).show();

                            }else {
                                FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

                            }
                        }
                    }
                });
            }
        });


        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }


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
