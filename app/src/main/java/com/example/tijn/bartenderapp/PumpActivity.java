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
import java.util.Map;

public class PumpActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pump);
        callRpc();
    }

    protected void callRpc() {
        Context context = getApplicationContext();
        String ip = PreferenceManager.getDefaultSharedPreferences(context).getString("IP", "127.0.0.1");
        JsonRpcConnection conn = new JsonRpcConnection();
        JsonRpc rpc = new JsonRpc(1, "pumpconfiguration", ip);
        conn.delegate = this;
        conn.execute(rpc);
    }

    @Override
    public void processFinish(Object output) {
        String jsonString = output.toString();
        ArrayList<Pump> pumps = new ArrayList<>();
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Pump>>() {
        }.getType();
        Map<String, Pump> result = new Gson()
                .fromJson(jsonString, mapType);

        for(Map.Entry<String, Pump> entry : result.entrySet()){
            System.out.println(entry.getValue().name);

            Pump pump = new Pump(entry.getValue().name, entry.getValue().value, entry.getValue().pin);
            pumps.add(pump);
        }

        System.out.println(pumps.get(2).name);
        final ListView listview = (ListView) findViewById(R.id.pumpView);
        final PumpAdapter adapter = new PumpAdapter(this, R.layout.row, pumps);
        listview.setAdapter(adapter);
        listview.setEnabled(false);

    }
}
