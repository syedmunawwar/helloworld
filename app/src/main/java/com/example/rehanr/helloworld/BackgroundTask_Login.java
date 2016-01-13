package com.example.rehanr.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rehan r on 13-01-2016.
 */
public class BackgroundTask_Login extends AsyncTask<String,Void,String> {


    Context ctx;
    String login_url;

    public BackgroundTask_Login(Context ctx){
        this.ctx = ctx;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
       login_url = "http://10.0.2.2/webapp/log.php";

        String method = params[0];

        if (method.equals("login")) {

            String email = params[1];
            String pass = params[2];

            URL url = null;
            try {
                url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                String response = "";
                String line="";

                while((line=bufferedReader.readLine())!=null){
                    response+= line;
                }
                bufferedReader.close();
                IS.close();

                httpURLConnection.disconnect();


                return response;



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
        if(result.equals("Login Successfull")) {

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx,Register.class);
            ctx.startActivity(i);
        }
        else if(result.equals("Login failed")){

            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
            Intent i1 = new Intent(ctx,Login.class);
            ctx.startActivity(i1);
        }
    }
}
