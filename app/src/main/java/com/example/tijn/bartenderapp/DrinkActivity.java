package com.example.tijn.bartenderapp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

import net.minidev.json.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrinkActivity extends AppCompatActivity implements AsyncResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);
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
        ObjectMapper mapper = new ObjectMapper();
        List<Pump> arrayList = new ArrayList<>();
        try {
            Map map = mapper.readValue(jsonString, Map.class);
            for (Object o : map.keySet()) {
                String key = (String) o;
                System.out.println(key);//prints Byc-yes for first
                Map<String, String> value = (Map<String, String>) map.get(key);
                System.out.println(value); //prints {Updated=week, Time=12pm} for first
                Pump pump = new Pump(value.get("name"), Integer.parseInt(value.get("pin")), value.get("value"));
                arrayList.add(pump);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(arrayList.get(2).name);
    }
}
