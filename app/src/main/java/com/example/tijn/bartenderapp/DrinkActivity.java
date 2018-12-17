package com.example.tijn.bartenderapp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DrinkActivity extends AppCompatActivity implements AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks);
        callRpc();
    }

    protected void callRpc() {
        Context context = getApplicationContext();
        String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("IP", "127.0.0.1");
        JsonRpcConnection conn = new JsonRpcConnection();
        JsonRpc rpc = new JsonRpc(1, "drinks", ip);
        conn.delegate = this;
        conn.execute(rpc);
    }

    @Override
    public void processFinish(Object output) {
        String jsonString = output.toString();
        Gson gson = new Gson();
        ArrayList<Drink> drinks = gson.fromJson(jsonString, new TypeToken<List<Drink>>(){}.getType());
        final ListView listview = (ListView) findViewById(R.id.drinkList);
        final DrinksAdapter adapter = new DrinksAdapter(this, R.layout.drinksrow, drinks);

        listview.setAdapter(adapter);
        listview.setEnabled(false);
    }
}
