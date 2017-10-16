package me.us.all.sample.crypyocompare.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import me.us.all.sample.crypyocompare.R;

import static java.lang.Math.round;

public class Convert extends AppCompatActivity {

    Double localbtc,localeth;

    protected EditText editText;
    protected TextView txtbasecurrency;
    protected TextView txtlocatbtc;
    protected EditText editextbtc;
    protected TextView txtbasecurrency2;
    protected TextView txtlocateth;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       super.setContentView(R.layout.activity_convert);
        localbtc = getIntent().getDoubleExtra("btc",0);
        localeth=getIntent().getDoubleExtra("eth",0);
        initView();
        System.out.println(localbtc);
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.editText);
        txtbasecurrency = (TextView) findViewById(R.id.txtbasecurrency);
        txtlocatbtc = (TextView) findViewById(R.id.txtlocatbtc);
        editextbtc = (EditText) findViewById(R.id.editextbtc);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView txttoolbar = (TextView) findViewById(R.id.toolbartextview);
        txttoolbar.setText("Convert BTC,ETH to "+getIntent().getStringExtra("Currency"));
        txtbasecurrency2 = (TextView) findViewById(R.id.txtbasecurrency2);
        txtlocateth = (TextView) findViewById(R.id.txtlocateth);
        txtbasecurrency.setText("BTC in "+ getIntent().getStringExtra("Currency"));
        txtbasecurrency2.setText("ETH in "+ getIntent().getStringExtra("Currency"));
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    double i = Double.parseDouble(editText.getText().toString());
                    txtlocatbtc.setText(""+round(i * localbtc));
                }else{
                    txtlocatbtc.setText("0.0");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        editextbtc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(@NonNull CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    double i = Double.parseDouble(editextbtc.getText().toString());
                    txtlocateth.setText(""+round((i*localeth)));
                }
                else{
                    txtlocateth.setText("0.0");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
