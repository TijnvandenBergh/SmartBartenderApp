package com.example.tijn.bartenderapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements  AsyncResponse{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        final TextView ipText = findViewById(R.id.ipText);
        final Button button = findViewById(R.id.saveIPButton);
        //ListView population
        final ListView listview = (ListView) findViewById(R.id.optionsList);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        String[] values = new String[] { "Drinks", "Pumps", "Recipes" };

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,list);

        listview.setAdapter(adapter);
        listview.setEnabled(false);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0 ) {
                    Intent myDrinkIntent = new Intent(view.getContext(), DrinkActivity.class);
                    startActivity(myDrinkIntent);
                }
            }
        });

        //Load latest know IP
        String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("IP", "127.0.0.1");
        ipText.setHint(ip);
        final ImageView imageView = findViewById(R.id.networkStatusImg);
        final ProgressBar networkBar = (ProgressBar) findViewById(R.id.networkBar);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                //Save values
                PreferenceManager.getDefaultSharedPreferences(context).edit().putString("IP", "" + ipText.getText()).apply();
                String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("IP", "127.0.0.1");
                ipText.setHint(ip);
                imageView.setVisibility(View.INVISIBLE);
                networkBar.setVisibility(View.VISIBLE);
                callRpc();

            }
        });
        callRpc();
    }

    protected void callRpc() {
        Context context = getApplicationContext();
        String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("IP", "127.0.0.1");
        JsonRpcConnection conn = new JsonRpcConnection();
        JsonRpc rpc = new JsonRpc(1, "ping", ip);
        conn.delegate = this;
        conn.execute(rpc);
    }

    @Override
    protected void onStart() {

        super.onStart();

    }

    @Override
    public void processFinish(Object output) {
        System.out.println(output);

        //Pattern to match regex for ip
        Pattern p = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
        Matcher m = p.matcher(output.toString());
        //Get references
        ProgressBar networkBar = (ProgressBar) findViewById(R.id.networkBar);
        ImageView imageView = findViewById(R.id.networkStatusImg);
        TextView networkTxt = findViewById(R.id.netWorkSts);
        imageView.setVisibility(View.INVISIBLE);
        if (m.find() == true) {
            System.out.println("EWAAAA");
            networkBar.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.online);
            imageView.setVisibility(View.VISIBLE);
            networkTxt.setText("Bartender: " + output);
            final ListView listview = (ListView) findViewById(R.id.optionsList);
            listview.setEnabled(true);
        }
        else {
            System.out.println("Connectie gefaald");
            networkBar.setVisibility(View.GONE);
            imageView.setImageResource(R.drawable.offline);
            imageView.setVisibility(View.VISIBLE);
            networkTxt.setText("Geen verbinding mogelijk");
            final ListView listview = (ListView) findViewById(R.id.optionsList);
            listview.setEnabled(false);
        }

    }
}
