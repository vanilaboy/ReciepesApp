package com.ilonmask.root.reciepesapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class StartedPage extends AppCompatActivity {

    private LinearLayout linear;
    private String resultTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_page);

        new ParserTask().execute();
        linear = (LinearLayout) findViewById(R.id.linearLayout);
        try {
            parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void parseJson() throws JSONException {
        while(resultTask == null) {
            int i = 0;
        }
        JSONArray jsonArray = new JSONArray(resultTask);
        ArrayList<Food> foods = new ArrayList<>();
        for (int g = 0; g < jsonArray.length(); g++) {
            JSONObject json = jsonArray.getJSONObject(g);
            Food tmp = new Food(json);
            foods.add(tmp);
            createCard(tmp);
        }
    }

    private void createCard(Food food) {
        String name = food.name;
        View view = getLayoutInflater().inflate(R.layout.card_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text_Name);
        textView.setText(name);
        linear.addView(view);
    }


    private class ParserTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String resultJson = "";
            try {
                URL url = new URL("http://91.225.131.180:3000/api/welcome_page/0");

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                String error = e.toString();
                e.printStackTrace();
            }
            resultTask = resultJson;
            return resultJson;
        }
    }
}
