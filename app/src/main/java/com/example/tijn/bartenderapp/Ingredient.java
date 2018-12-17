package com.example.tijn.bartenderapp;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    public String name;
    public int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
