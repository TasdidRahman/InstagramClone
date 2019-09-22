package com.tasdid.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText edtLoginEmail, edtLoginPassword;
    private Button btnLoginActivity,btnLoginSignUpActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Log In");

        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);

        btnLoginActivity=findViewById(R.id.btnLoginActivity);
        btnLoginSignUpActivity=findViewById(R.id.btnLoginSignUpActivity);

        btnLoginActivity.setOnClickListener(LoginActivity.this);
        btnLoginSignUpActivity.setOnClickListener(LoginActivity.this);

        if(ParseUser.getCurrentUser() !=null){

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btnLoginActivity:

               ParseUser.logInInBackground(edtLoginEmail.getText().toString(),
                       edtLoginPassword.getText().toString(),
                       new LogInCallback() {
                           @Override
                           public void done(ParseUser user, ParseException e) {

                        if(user !=null && e==null){

                            FancyToast.makeText(LoginActivity.this, user.getUsername()+" is Logged in successfully",
                                    Toast.LENGTH_SHORT, FancyToast.SUCCESS,false).show();
                        }else{
                            FancyToast.makeText(LoginActivity.this, "There is an error "+e.getMessage(),
                                    Toast.LENGTH_LONG, FancyToast.ERROR,false).show();
                        }

                    }
                });

                break;

            case R.id.btnLoginSignUpActivity:

                Intent intent = new Intent(LoginActivity.this, SignUp.class);
                startActivity(intent);

                break;
        }


    }
}
