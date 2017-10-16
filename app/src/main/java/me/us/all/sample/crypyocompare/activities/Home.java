package me.us.all.sample.crypyocompare.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import me.us.all.sample.crypyocompare.R;

public class Home extends AppCompatActivity {
    GridView gridView;
    TextView txtDollarbtc, txtDollareth, txttoolbar;
    Toolbar toolbar;

    double[] locaalbtc, locaalethh;

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView = (GridView) findViewById(R.id.Gridview);
        gridView.setAdapter(new Gridadapter(this));
        txtDollarbtc = (TextView) findViewById(R.id.txtBTCDollar);
        txtDollarbtc.setText("$ " + getIntent().getStringExtra("BTCindollar"));
        txtDollareth = (TextView) findViewById(R.id.txtETCDollar);
        txtDollareth.setText("$ " + getIntent().getStringExtra("EThindollar"));
        locaalbtc = getIntent().getDoubleArrayExtra("btcc");
        locaalethh = getIntent().getDoubleArrayExtra("ethh");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txttoolbar = (TextView) findViewById(R.id.toolbartextview);
        txttoolbar.setText("Home");


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Home.this, Convert.class);
                String E = "eth";
                String B = "btc";
                switch (position) {
                    case 0:
                        intent.putExtra(E, locaalethh[3]);
                        intent.putExtra(B, locaalbtc[3]);
                        intent.putExtra("Currency", "Kshs");

                        break;
                    case 1:
                        intent.putExtra(E, locaalethh[2]);
                        intent.putExtra(B, locaalbtc[2]);
                        intent.putExtra("Currency", "Naira");
                        break;
                    case 2:
                        intent.putExtra(E, locaalethh[6]);
                        intent.putExtra(B, locaalbtc[6]);
                        intent.putExtra("Currency", "AUD");
                        break;
                    case 3:
                        intent.putExtra(E, locaalethh[4]);
                        intent.putExtra(B, locaalbtc[4]);
                        intent.putExtra("Currency", "JPY");
                        break;
                    case 4:
                        intent.putExtra(E, locaalethh[8]);
                        intent.putExtra(B, locaalbtc[8]);
                        intent.putExtra("Currency", "HKD");
                        break;
                    case 5:
                        intent.putExtra(E, locaalethh[5]);
                        intent.putExtra(B, locaalbtc[5]);
                        intent.putExtra("Currency", "GBP");
                        break;
                    case 6:
                        intent.putExtra(E, locaalethh[7]);
                        intent.putExtra(B, locaalbtc[7]);
                        intent.putExtra("Currency", "CAD");
                        break;
                    case 7:
                        intent.putExtra(E, locaalethh[0]);
                        intent.putExtra(B, locaalbtc[0]);
                        intent.putExtra("Currency", "USD");
                        System.out.println(locaalbtc[0]);
                        break;
                }
                startActivity(intent);

            }
        });

    }


    public class Gridadapter extends BaseAdapter {
        Context context;
        @NonNull
        String[] countries = new String[]{"Kenya", "Nigeria", "Austaria", "Japan", "Hong Kong", "Great Britain", "Canada", "Usa"};
        @NonNull
        int[] flags = new int[]{R.mipmap.ic_ke
                , R.mipmap.ic_ng
                , R.mipmap.ic_ai
                , R.mipmap.ic_jp
                , R.mipmap.ic_hk
                , R.mipmap.ic_gb
                , R.mipmap.ic_ca
                , R.mipmap.ic_us
        };


        public Gridadapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return countries.length;
        }

        @Nullable
        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Nullable
        @Override
        public View getView(int position, @Nullable View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view;
            if (convertView == null) {
                view = inflater.inflate(R.layout.grid_item, null);

                ImageView imageView = (ImageView) view.findViewById(R.id.countryimageview);
                imageView.setImageResource(flags[position]);
                TextView txtcountry = (TextView) view.findViewById(R.id.countryname);
                txtcountry.setText(countries[position]);
            } else {
                view = convertView;
            }
            return view;
        }
    }

}
