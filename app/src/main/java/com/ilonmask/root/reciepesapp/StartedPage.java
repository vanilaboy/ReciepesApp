package com.ilonmask.root.reciepesapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
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
    private ArrayList<ImageView> allImageView;

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

    private void findAllImageView(View view) {
        allImageView = new ArrayList<>();
        allImageView.add((ImageView)view.findViewById(R.id.imageTag1));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag2));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag3));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag4));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag5));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag6));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag7));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag8));
        allImageView.add((ImageView)view.findViewById(R.id.imageTag9));
    }

    private void createCard(Food food) {
        String name = food.name;
        View view = getLayoutInflater().inflate(R.layout.card_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text_Name);
        textView.setText(name);
        linear.addView(view);
        findAllImageView(view);
        int counterImage = 0;
        for(int i = 0; i < food.tags.size(); i++) {
            if(i >= allImageView.size()) {
                break;
            } else {
                String tag = food.tags.get(i);
                ImageView image = allImageView.get(counterImage);
                if(tag.equals("asian")) {
                    image.setImageResource(R.drawable.asian);
                    counterImage++;
                }
                if(tag.equals("breakfast")) {
                    image.setImageResource(R.drawable.breakfast);
                    counterImage++;
                }
                if(tag.equals("chocolate")) {
                    image.setImageResource(R.drawable.chocolate);
                    counterImage++;
                }
                if(tag.equals("cookies")) {
                    image.setImageResource(R.drawable.cookies);
                    counterImage++;
                }
                if(tag.equals("dessert")) {
                    image.setImageResource(R.drawable.dessert);
                    counterImage++;
                }
                if(tag.equals("drink")) {
                    image.setImageResource(R.drawable.drink);
                    counterImage++;
                }
                if(tag.equals("main")) {
                    image.setImageResource(R.drawable.main);
                    counterImage++;
                }
                if(tag.equals("mexican")) {
                    image.setImageResource(R.drawable.mexican);
                    counterImage++;
                }
                if(tag.equals("pasta")) {
                    image.setImageResource(R.drawable.pasta);
                    counterImage++;
                }
                if(tag.equals("salad")) {
                    image.setImageResource(R.drawable.salad);
                    counterImage++;
                }
                if(tag.equals("salmon")) {
                    image.setImageResource(R.drawable.salmon);
                    counterImage++;
                }
                if(tag.equals("sauce")) {
                    image.setImageResource(R.drawable.sauce);
                    counterImage++;
                }
                if(tag.equals("seafood")) {
                    image.setImageResource(R.drawable.seafood);
                    counterImage++;
                }
                if(tag.equals("smoothie")) {
                    image.setImageResource(R.drawable.smoothie);
                    counterImage++;
                }
                if(tag.equals("soup")) {
                    image.setImageResource(R.drawable.soup);
                    counterImage++;
                }
                if(tag.equals("untried")) {
                    image.setImageResource(R.drawable.untried);
                    counterImage++;
                }
                if(tag.equals("vegetarian")) {
                    image.setImageResource(R.drawable.vegetarian);
                    counterImage++;
                }
 //               image.setVisibility(View.VISIBLE);
            }
        }
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
