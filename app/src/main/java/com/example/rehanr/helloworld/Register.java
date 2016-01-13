package com.example.rehanr.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name,age,email,username,password,phone,country,district;

    String Name,Age,Email,Username,Password,Phone,Country,District;

    Button register;
    Button back;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        name = (EditText)findViewById(R.id.name);
        age = (EditText)findViewById(R.id.Age);
        email = (EditText)findViewById(R.id.email);
        username = (EditText)findViewById(R.id.uname);
        password= (EditText)findViewById(R.id.pword);
        phone = (EditText)findViewById(R.id.Phone);
        country = (EditText)findViewById(R.id.county);
        district = (EditText)findViewById(R.id.District);



        register = (Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Name = name.getText().toString();
                Age = age.getText().toString();
                Email = email.getText().toString();
                Username = username.getText().toString();
                Password = password.getText().toString();
                Phone = phone.getText().toString();
                Country = country.getText().toString();
                District = district.getText().toString();

               // String s= Name+Email+Age+Phone+Country+District+Password+Username;
                //Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
                String method = "register";
                BackgroundTask backgroundTask = new BackgroundTask(Register.this);
                backgroundTask.execute(method, Name,Age,Email,Username,Password,Phone,Country,District);

            }
        });



        back = (Button)findViewById(R.id.bktologin);
        t = (TextView)findViewById(R.id.textView3);
        back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(),Login.class);
                startActivity(i);
                    }
        });









    }
}
