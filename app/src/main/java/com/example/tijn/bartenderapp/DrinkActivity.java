package com.example.tijn.bartenderapp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DrinkActivity extends AppCompatActivity implements AsyncResponse{

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
        JsonRpc rpc = new JsonRpc(1, "drinks", ip);
        conn.delegate = this;
        conn.execute(rpc);
    }
    List<Map<String,String>> employeeList = new ArrayList<Map<String,String>>();
    @Override
    public void processFinish(Object output) {
        ListView drinkList = findViewById(R.id.drinkView);

        try{
            JSONObject jsonResponse = new JSONObject(output.toString());
            JSONArray jsonMainNode = jsonResponse.optJSONArray("employee");

            for(int i = 0; i<jsonMainNode.length();i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("emp_name");
                String number = jsonChildNode.optString("emp_no");
                String outPut = name + "-" +number;
                employeeList.add(createEmployee("employees", outPut));
            }
        }
        catch(JSONException e){
            Toast.makeText(getApplicationContext(), "Error"+e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private HashMap<String, String>createEmployee(String name,String number){
        HashMap<String, String> employeeNameNo = new HashMap<String, String>();
        employeeNameNo.put(name, number);
        return employeeNameNo;
    }

}
