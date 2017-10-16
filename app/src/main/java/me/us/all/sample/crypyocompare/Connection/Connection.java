package me.us.all.sample.crypyocompare.Connection;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import me.us.all.sample.crypyocompare.activities.Home;

/**
 * Created by eugene on 10/7/2017. for enquires call me or email me
 */

public class Connection extends AsyncTask<Void,Void,String> {
    ProgressDialog dialog;
    Context context;


    public Connection(Context context) {
        this.context = context;
    }

    @NonNull
    private String link ="https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR,NGN,KES,JPY,GBP,AUD,CAD,HKD";

    @Override
    protected String doInBackground(Void... params) {
       return GetJson();
    }

    private String GetJson() {
        URL url;
        String response="";
        try {
           url  = new URL(link);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(15000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                while ((line = br.readLine()) != null) {
                    response += line;
                    Log.d("output lines", line);
                }
            } else {
                response = "";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
return response;
    }

    @Override
    protected void onPostExecute(@NonNull String s) {
         double usd = 0,eur = 0,ngn=0,kes=0,jpy=0, gbp=0,aud=0,cad=0,hkd=0;

        double usd2 = 0,eur2=0,ngn2=0,kes2=0,jpy2=0, gbp2=0,aud2=0,cad2=0,hkd2=0;



        JSONObject jsonobject1 = null;
        JSONObject jsonobject2=null;
        try{

            jsonobject1 = new JSONObject(s.trim());

            jsonobject1 = jsonobject1.getJSONObject("BTC");
            usd = jsonobject1.getDouble("USD");
            eur = jsonobject1.getDouble("EUR");
            ngn = jsonobject1.getDouble("NGN");
            kes  =jsonobject1.getDouble("KES");
            gbp = jsonobject1.getDouble("GBP");
            jpy =jsonobject1.getDouble("JPY");
            aud =jsonobject1.getDouble("AUD");
            cad = jsonobject1.getDouble("CAD");
            hkd = jsonobject1.getDouble("HKD");


            jsonobject2 = new JSONObject(s.trim());
            jsonobject2 = jsonobject2.getJSONObject("ETH");
            usd2 = jsonobject2.getDouble("USD");
            eur2 = jsonobject2.getDouble("EUR");
            ngn2 = jsonobject2.getDouble("NGN");
            kes2  =jsonobject2.getDouble("KES");
            gbp2 = jsonobject2.getDouble("GBP");
            jpy2 =jsonobject2.getDouble("JPY");
            aud2 =jsonobject2.getDouble("AUD");
            cad2 = jsonobject2.getDouble("CAD");
            hkd2 = jsonobject2.getDouble("HKD");









        } catch (JSONException e) {
            e.printStackTrace();
        }
        double [] BTCC = new double[]{ usd, eur,  ngn,  kes, jpy,  gbp, aud,  cad,  hkd};
        double [] ethh = new double[]{usd2, eur2,  ngn2,  kes2, jpy2,  gbp2, aud2,  cad2,  hkd2};

        Intent intent = new Intent(context, Home.class);
        intent.putExtra("BTCindollar",Double.toString(usd));
        intent.putExtra("EThindollar",Double.toString(usd2));
        intent.putExtra("btcc",BTCC);
        intent.putExtra("ethh",ethh);
        context.startActivity(intent);
    }

    @Override
    protected void onPreExecute() {


    }


}
