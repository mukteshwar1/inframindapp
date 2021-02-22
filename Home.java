package com.example.shree.sustainabilitywellness;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Home extends AppCompatActivity {

    EditText t1,t2,t3,t4,t5,t6,t7;
    TextView tv;
    private final String url = "http://192.168.56.1/Project2/insert_db.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        t1 = (EditText)findViewById(R.id.t1);
        t2 = (EditText)findViewById(R.id.t2);
        t3 = (EditText)findViewById(R.id.t3);
        t4 = (EditText)findViewById(R.id.t4);
        t5 = (EditText)findViewById(R.id.t5);
        t6 = (EditText)findViewById(R.id.t6);
        t7 = (EditText)findViewById(R.id.t7);
        tv = (TextView)findViewById(R.id.tv);
    }
    public void process(View view)
    {
        String n1 = t1.getText().toString();
        String n2 = t2.getText().toString();
        String n3 = t3.getText().toString();
        String n4 = t4.getText().toString();
        String n5 = t5.getText().toString();
        String n6 = t6.getText().toString();
        String n7 = t7.getText().toString();
        String qrystring="?t1="+n1+"&t2="+n2+"&t3="+n3+"&t4="+n4+"&t5="+n5+"&t6="+n6+"&t7="+n7;

        class dbclass extends AsyncTask<String,Void,String>
        {
            protected void onPostExecutte(String data)
            {


                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");
                Toast.makeText(getApplicationContext(),"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                tv.setText(data);

            }

            @Override
            protected String doInBackground(String... param) {

              try
              {
                URL url = new URL(param[0]);
                  HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                  return br.readLine();
              }
              catch (Exception ex)
              {
                  return ex.getMessage();

              }


            }
        }
        dbclass obj = new dbclass();
        obj.execute(url+qrystring);
    }
}
