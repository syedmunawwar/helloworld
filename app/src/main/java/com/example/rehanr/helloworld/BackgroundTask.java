package com.example.rehanr.helloworld;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rehan r on 09-01-2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    String add_info_url;
    boolean flagcontinueactivity;

    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {


        //URL OF DATABASE
        add_info_url = "127.0.0.1";

    }

    @Override
    protected String doInBackground(String... params) {

        String method = params[0];


        if (method.equals("register")) {

            String name = params[1];
            String age = params[2];
            String email = params[3];
            String username = params[4];
            String password = params[5];
            String phone = params[6];
            String country = params[7];
            String district = params[8];


            URL url = null;
            try {
                url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") +
                        URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8") + "&" +
                        URLEncoder.encode("country", "UTF-8") + "=" + URLEncoder.encode(country, "UTF-8") + "&" +
                        URLEncoder.encode("district", "UTF-8") + "=" + URLEncoder.encode(district, "UTF-8") + "&";

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                IS.close();

                //for 000webhost.com site
                httpURLConnection.disconnect();
                //for 000webhost.com site


                return "Registration Successfully Done...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        if (flagcontinueactivity) {
            if (result.equals("Registration Successfully Done...")) {
                Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            }
            //to disable LoginActivity after backbutton pressed
            //((Activity)ctx).finish();}
        }
    }
}
