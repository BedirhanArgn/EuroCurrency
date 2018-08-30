package com.example.argunpc.eurocurrency;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {
TextView tr;
TextView jpn2;
TextView usd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tr = findViewById(R.id.tr);

        usd = findViewById(R.id.usd);
        jpn2 = findViewById(R.id.jpn2);
    }


    public void get(View view) {

        DownloadData downloadData = new DownloadData();

        try {

            String url = "http://data.fixer.io/api/latest?access_key=78942a9a1105864492f94ed29a9f3e57&format=1";

            downloadData.execute(url);


        } catch (Exception e) {

        }


    }


    private class DownloadData extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();

                }


                return result;

            } catch (Exception e) {
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            //System.out.println("alınan data:" + s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                //System.out.println("base:" + base);

                String rates = jsonObject.getString("rates");


                JSONObject jsonObject1 = new JSONObject(rates);
                String turkishlira = jsonObject1.getString("TRY");
                tr.setText("TRY: " + turkishlira);

                String usd2= jsonObject1.getString("USD");
                usd.setText("USD: " + usd2);

                String jpy = jsonObject1.getString("JPY");
                jpn2.setText("JPY: " + jpy);

            } catch (Exception e) {

            }


        }
    }


}
