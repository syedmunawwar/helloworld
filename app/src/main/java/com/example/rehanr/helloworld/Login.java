package com.example.rehanr.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {


    EditText email,password;
    Button register,login,forgetpassword;
    String Email,Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pword);

        register = (Button)findViewById(R.id.registerbtn);
        login = (Button)findViewById(R.id.login);
        forgetpassword = (Button)findViewById(R.id.passres);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent next = new Intent(getBaseContext(),Register.class);
                startActivity(next);
            }
        });

        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Email = email.getText().toString();
                Password = password.getText().toString();

                String method = "login";
                BackgroundTask_Login backgroundTask_login = new BackgroundTask_Login(Login.this);
                backgroundTask_login.execute(method,Email,Password);

            }
        });


    }
}
