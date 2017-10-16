package me.us.all.sample.crypyocompare.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;

import me.us.all.sample.crypyocompare.Connection.Connection;
import me.us.all.sample.crypyocompare.DataStore.PrefManager;
import me.us.all.sample.crypyocompare.R;

/* Entry point of the applicatios and perfrom varous tasks

 */
public class SplashScreen extends AppCompatActivity {
    private PrefManager prefManager;

    private static final int timeout=200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        GotoHome();
    }
    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        Connection connection = new Connection(SplashScreen.this);
        connection.execute();
        finish();
    }

    private void GotoHome() {
        if(checkConnecion()) {



            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    prefManager = new PrefManager(SplashScreen.this);
                    if (!prefManager.isFirstTimeLaunch()) {

                        launchHomeScreen();
                        finish();
                    }
                    else{
                        Intent intent =  new Intent(getApplicationContext(),Introduction.class);
                        startActivity(intent);

                    }




                }
            }, timeout);
        }
        else{
            ShowAlert().show();

        }

    }

    private Dialog ShowAlert() {
        AlertDialog.Builder builder= new AlertDialog.Builder(new ContextThemeWrapper( SplashScreen.this,R.style.Alertdialogcustom))
                .setIcon(R.mipmap.ic_frown)
                .setView(R.layout.noconnection_alert)
                .setCancelable(false)
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }) .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        GotoHome();


                    }
                });
        return  builder.create();
    }

    public boolean checkConnecion(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo !=null && networkInfo.isConnected())
        {
            return  true;
        }
        else
        {
            return false;
        }
    }

}
