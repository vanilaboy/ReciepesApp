package com.ilonmask.root.reciepesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StartedPage extends AppCompatActivity {

    private LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_page);

        linear = (LinearLayout)findViewById(R.id.linearLayout);
        fillingCards();

    }

    private void fillingCards(){
        //запрос к серверу


        for(int i = 0; i < 20; i++) {
            String result = "Name";
            View view = getLayoutInflater().inflate(R.layout.card_layout, null);
            TextView textView = (TextView) view.findViewById(R.id.text_Name);
            result = result + i;
            textView.setText(result);
            linear.addView(view);
        }
    }
}
