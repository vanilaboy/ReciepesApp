package com.ilonmask.root.reciepesapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 18.11.17.
 */

public class Food {
    public JSONObject json;
    public int id;
    public String name;
    public String source;
    public int preptime;
    public int waittime;
    public int cooktime;
    public int servings;
    public String comments;
    public int calories;
    public int fat;
    public int satfat;
    public int cards;
    public int fiber;
    public int sugar;
    public int protein;
    public String instructions;
    public JSONArray ingredientsJSON;
    public JSONArray tagsJSON;

    public Food(JSONObject json) throws JSONException {
        this.json = json;
        id = json.getInt("id");
        name = json.getString("name");
        source = json.getString("source");
        preptime = json.getInt("preptime");
        waittime = json.getInt("waittime");
        cooktime = json.getInt("cooktime");
        servings = json.getInt("servings");
        comments = json.getString("comments");
        calories = json.getInt("calories");
        fat = json.getInt("fat");
        satfat = json.getInt("satfat");
        cards = json.getInt("carbs");
        fiber = json.getInt("fiber");
        sugar = json.getInt("sugar");
        protein = json.getInt("protein");
        instructions = json.getString("instructions");
        ingredientsJSON = json.getJSONArray("ingredients");
        tagsJSON = json.getJSONArray("tags");

        int length = ingredientsJSON.length();
        String[] ingredients = new String[length];
        for (int i = 0; i < length; i++) {
            ingredients[i] = (String) ingredientsJSON.get(i);
        }
        length = tagsJSON.length();
        String[] tags = new String[length];
        for (int i = 0; i < length; i++) {
            tags[i] = (String) tagsJSON.get(i);
        }
    }
}
