package com.example.tijn.bartenderapp;

import android.os.AsyncTask;
import android.util.Log;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2Session;
import com.thetransactioncompany.jsonrpc2.client.JSONRPC2SessionException;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonRpcConnection extends AsyncTask<JsonRpc, String, Object> {
    URL serverURL = null;
    public AsyncResponse delegate = null;

    @Override
    protected Object doInBackground(JsonRpc... jsonRpcs) {
        URL serverURL = null;
        try {
            System.out.println("Hoi");
            serverURL = new URL("http://" + jsonRpcs[0].ip + ":5000");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Create new JSON-RPC 2.0 client session
        JSONRPC2Session mySession = new JSONRPC2Session(serverURL);
        System.out.println("Sessie aan");
        // Construct new request
        String method = jsonRpcs[0].getMethod();
        int requestID = 0;
        JSONRPC2Request request = new JSONRPC2Request(method, requestID);
        // Send request
        // Send request
        JSONRPC2Response response = null;

        try {
            response = mySession.send(request);

        } catch (JSONRPC2SessionException e) {

            System.err.println(e.getMessage());
            // handle exception...
        }
        try {
            // Print response result / error
            if (response.indicatesSuccess()) {
                System.out.println(response.getResult());
                return response.getResult();
            } else {
                System.out.println(response.getError().getMessage());
                System.out.println("ZEZE");
                return "Error";
            }
        } catch (NullPointerException ex) {
            System.out.println("FEFE");
            System.err.println(ex.getMessage());
            return "Nullpunter";
        }
    }
    @Override
    protected void onPostExecute(Object result) {
        if(delegate!=null)
        {
            System.out.println(result);
            delegate.processFinish(result);
        }
        else
        {
            Log.e("ApiAccess", "You have not assigned IApiAccessResponse delegate");
        }
    }
}



