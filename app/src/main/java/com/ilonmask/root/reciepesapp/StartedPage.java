package com.ilonmask.root.reciepesapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private ArrayList<String> resultTask = new ArrayList<>();
    private ArrayList<String> secondResultTask = new ArrayList<>();
    private ArrayList<ImageView> allImageView;
    private ArrayList<View> cards = new ArrayList<>();
    private Button button;
    private View.OnClickListener listenerSearch;
    private String reserved = "%7B%22query%22%3A%20%22hello%22%2C%20%22tags%22%3A%20%5B%5D%2C%20%22filters%22%3A%20%5B%5D%7D";
    private String response = "%7B%22query%22%3A%20%22hello%22%2C%20%22tags%22%3A%20%5B%5D%2C%20%22filters%22%3A%20%5B%5D%7D";
    private boolean first = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_page);
        button = (Button)findViewById(R.id.buttonSearch);
        listenerSearch = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < cards.size(); i++) {
                    View view = cards.get(i);
                    view.setVisibility(View.GONE);
                }
                cards.clear();
                resultTask.clear();
                EditText editText = (EditText)findViewById(R.id.editText1);
                String input = editText.getText().toString();
                input = input.replaceAll(" ", "%20");
                response = reserved;
                response = response.replace("hello", input);
                new MyAsyncTask().execute();
                try {
                    parseJson();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        button.setOnClickListener(listenerSearch);
        if(!first) {
            new ParserTask().execute();
            first = true;
        }
        linear = (LinearLayout) findViewById(R.id.linearLayout);
        try {
            parseJson();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJson() throws JSONException {
        while(resultTask.size() == 0) {
            int i = 0;
        }
        for(int f = 0; f < resultTask.size(); f++) {
            JSONArray jsonArray = new JSONArray(resultTask.get(f));
            ArrayList<Food> foods = new ArrayList<>();
            for (int g = 0; g < jsonArray.length(); g++) {
                JSONObject json = jsonArray.getJSONObject(g);
                Food tmp = new Food(json);
                foods.add(tmp);
                createCard(tmp);
            }
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

        try {
            if (!food.images.get(0).equals("empty")) {
                new DownloadImageTask((ImageView) view.findViewById(R.id.imageView1)).execute(food.images.get(0));
            }

            if (!food.images.get(1).equals("empty")) {
                new DownloadImageTask((ImageView) view.findViewById(R.id.imageView2)).execute(food.images.get(1));
            }

            if (!food.images.get(2).equals("empty")) {
                new DownloadImageTask((ImageView) view.findViewById(R.id.imageView3)).execute(food.images.get(2));
            }

            if (!food.images.get(3).equals("empty")) {
                new DownloadImageTask((ImageView) view.findViewById(R.id.imageView4)).execute(food.images.get(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        cards.add(view);
        findAllImageView(view);
        int counterImage = 0;
        for (int i = 0; i < food.tags.size(); i++) {
            if (i >= allImageView.size()) {
                break;
            } else {
                String tag = food.tags.get(i);
                ImageView image = allImageView.get(counterImage);
                if (tag.equals("asian")) {
                    image.setImageResource(R.drawable.asian);
                    counterImage++;
                }
                if (tag.equals("breakfast")) {
                    image.setImageResource(R.drawable.breakfast);
                    counterImage++;
                }
                if (tag.equals("chocolate")) {
                    image.setImageResource(R.drawable.chocolate);
                    counterImage++;
                }
                if (tag.equals("cookies")) {
                    image.setImageResource(R.drawable.cookies);
                    counterImage++;
                }
                if (tag.equals("dessert")) {
                    image.setImageResource(R.drawable.dessert);
                    counterImage++;
                }
                if (tag.equals("drink")) {
                    image.setImageResource(R.drawable.drink);
                    counterImage++;
                }
                if (tag.equals("main")) {
                    image.setImageResource(R.drawable.main);
                    counterImage++;
                }
                if (tag.equals("mexican")) {
                    image.setImageResource(R.drawable.mexican);
                    counterImage++;
                }
                if (tag.equals("pasta")) {
                    image.setImageResource(R.drawable.pasta);
                    counterImage++;
                }
                if (tag.equals("salad")) {
                    image.setImageResource(R.drawable.salad);
                    counterImage++;
                }
                if (tag.equals("salmon")) {
                    image.setImageResource(R.drawable.salmon);
                    counterImage++;
                }
                if (tag.equals("sauce")) {
                    image.setImageResource(R.drawable.sauce);
                    counterImage++;
                }
                if (tag.equals("seafood")) {
                    image.setImageResource(R.drawable.seafood);
                    counterImage++;
                }
                if (tag.equals("smoothie")) {
                    image.setImageResource(R.drawable.smoothie);
                    counterImage++;
                }
                if (tag.equals("soup")) {
                    image.setImageResource(R.drawable.soup);
                    counterImage++;
                }
                if (tag.equals("untried")) {
                    image.setImageResource(R.drawable.untried);
                    counterImage++;
                }
                if (tag.equals("vegetarian")) {
                    image.setImageResource(R.drawable.vegetarian);
                    counterImage++;
                }
                //               image.setVisibility(View.VISIBLE);
            }
        }
    }










    //****************************************AsyncTask****************************************//


    private class MyAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            ArrayList<String> resultJsonList = new ArrayList<>();
            try {
                String url = "http://91.225.131.180:3000/api/search/";
                url += response;
                for (int i = 0; i < 45; i++) {
                    String tmpURL = url + "/" + i;
                    URL resultURL = new URL(tmpURL);
                    urlConnection = (HttpURLConnection) resultURL.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();
                    InputStream inputStream = urlConnection.getInputStream();
                    StringBuffer buffer = new StringBuffer();

                    reader = new BufferedReader(new InputStreamReader(inputStream));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        buffer.append(line);
                    }

                    resultJsonList.add(buffer.toString());
                }

            } catch (Exception e) {
                String error = e.toString();
                e.printStackTrace();
            }
            resultTask = resultJsonList;
            return resultJsonList.toString();
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
            resultTask.add(resultJson);
            return resultJson;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
