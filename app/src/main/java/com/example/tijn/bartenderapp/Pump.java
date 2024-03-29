package com.example.tijn.bartenderapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pump {
    @JsonProperty("name")
    public String name;
    @JsonProperty("pin")
    public int pin;
    @JsonProperty("value")
    public String value;

    public Pump(String name, String value, int pin) {
        this.name = name;
        this.value = value;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
