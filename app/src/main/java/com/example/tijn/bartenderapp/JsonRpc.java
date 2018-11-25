package com.example.tijn.bartenderapp;

public class JsonRpc {
    public int id;
    public String method;
    public String ip;


    public JsonRpc(int id, String method, String ip) {
        this.id = id;
        this.method = method;
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
