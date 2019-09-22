package com.tasdid.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //UI Components
    private EditText edtEmail , edtUsername, edtPassword;
    private Button btnSignUp, btnLogIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Sign Up");

        edtEmail =findViewById(R.id.edtSignUpEmail);
        edtUsername=findViewById(R.id.edtSignUpUsername);
        edtPassword= findViewById(R.id.edtSignUpPassword);

        btnSignUp =findViewById(R.id.btnSignUpActivity);
        btnLogIn =findViewById(R.id.btnSignUpLoginActivity);

        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        if(ParseUser.getCurrentUser() !=null){

            ParseUser.getCurrentUser().logOut();
        }




    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnSignUpActivity:

                final ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());

                final ProgressDialog progressDialog =new ProgressDialog(this);
                progressDialog.setMessage("Signing up "+edtUsername.getText().toString());
                progressDialog.show();

                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                            FancyToast.makeText(SignUp.this, appUser.getUsername()+" is signed up successfully",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS,false).show();
                        }else{
                            FancyToast.makeText(SignUp.this, "There is an error "+e.getMessage(),
                                    Toast.LENGTH_LONG, FancyToast.ERROR,false).show();
                        }

                        progressDialog.dismiss();

                    }
                });

                break;

            case R.id.btnSignUpLoginActivity:

                Intent intent = new Intent(SignUp.this, LoginActivity.class);
                startActivity(intent);



                break;
        }

    }
}