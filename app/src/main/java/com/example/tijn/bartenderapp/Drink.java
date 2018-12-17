package com.example.tijn.bartenderapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Drink {
    @SerializedName("name")
    private String name;
    @SerializedName("ingredients")
    private Map<String, Integer> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
